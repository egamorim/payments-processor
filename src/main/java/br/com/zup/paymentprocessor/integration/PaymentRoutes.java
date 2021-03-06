package br.com.zup.paymentprocessor.integration;

import br.com.zup.paymentprocessor.application.service.PaymentService;
import br.com.zup.paymentprocessor.application.service.TedService;
import br.com.zup.paymentprocessor.config.KafkaProperties;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.integration.processors.PaymentProcessor;
import br.com.zup.paymentprocessor.integration.processors.error.RestPaymentError;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static br.com.zup.paymentprocessor.application.domain.Constants.*;

@Component
public class PaymentRoutes extends RouteBuilder {

    private final KafkaProperties kafkaProperties;

    private static final String TYPE_HEADER = "payment_type";
    private static final String PAYMENTS_PATH = "payments";

    private final PaymentService paymentService;
    private final TedService tedService;
    private final PaymentProcessor tedProcessor;
    private final PaymentProcessor docProcessor;
    private final Environment env;
    private final String serverPort;

    public PaymentRoutes(@Qualifier("tedProcessor") PaymentProcessor tedProcessor,
                         @Qualifier("docProcessor") PaymentProcessor docProcessor,
                         Environment env,
                         PaymentService paymentService,
                         TedService tedService,
                         @Value("${server.port}") String serverPort,
                         KafkaProperties kafkaProperties) {
        this.tedProcessor = tedProcessor;
        this.tedService = tedService;
        this.docProcessor = docProcessor;
        this.kafkaProperties = kafkaProperties;
        this.env = env;
        this.paymentService = paymentService;
        this.serverPort = serverPort;
    }

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Zup Payments Rest API.")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port(env.getProperty("server.port", serverPort))
                .bindingMode(RestBindingMode.json);

        errorHandler(deadLetterChannel("direct:generalError"));

        rest(PAYMENTS_PATH)
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .post("/").route()
                .marshal().json()
                .unmarshal(getJacksonDataFormat(PaymentDTO.class))
                .choice()
                .when(header(TYPE_HEADER).isEqualToIgnoreCase(PAYMENT_TYPE_PIX))
                    .bean(paymentService, "validate")
                    .to("direct:pix")
                .when(header(TYPE_HEADER).isEqualToIgnoreCase(PAYMENT_TYPE_TED))
                    .to("direct:ted")
                .when(header(TYPE_HEADER).isEqualToIgnoreCase(PAYMENT_TYPE_DOC))
                    .process(docProcessor)
                    .to("direct:doc")
                .otherwise()
                .bean(new RestPaymentError(), "paymentTypeError")
                .end();

        from("direct:ted")
                .log("New TED requested")
                .bean(tedService, "validate")
                .bean(tedService, "store")
                .process(tedProcessor)
                .setHeader(KafkaConstants.KEY, constant("Camel"))
                .to(String.format(kafkaProperties.getKafkaBroker(), kafkaProperties.getTedIncluded().getTopicName()))
                .convertBodyTo(PaymentDTO.class)
                .end();

        from("direct:doc")
                .log("New DOC processed")
                .end();

        from("direct:pix")
                .log("New PIX processed")
                .end();

    }

    private JacksonDataFormat getJacksonDataFormat(Class<?> unmarshalType) {
        JacksonDataFormat format = new JacksonDataFormat();
        format.setUnmarshalType(unmarshalType);
        return format;
    }
}
