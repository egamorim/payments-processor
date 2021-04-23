package br.com.zup.paymentprocessor.integration.routes.ted;

import br.com.zup.paymentprocessor.application.service.TedService;
import br.com.zup.paymentprocessor.config.KafkaProperties;
import br.com.zup.paymentprocessor.integration.config.Route;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.integration.processors.TedProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;


@Route
@RequiredArgsConstructor
public class TedRoute extends RouteBuilder {

    private final KafkaProperties kafkaProperties;
    private final TedService tedService;
    private final TedProcessor tedProcessor;

    @Override
    public void configure() throws Exception {
        from("direct:ted")
                .log("New TED requested")
                .bean(tedService, "validate")
                .bean(tedService, "store")
                .process(tedProcessor)
                .setHeader(KafkaConstants.KEY, constant("Camel"))
                .to(String.format(kafkaProperties.getKafkaBroker(), kafkaProperties.getTedIncluded().getTopicName()))
                .convertBodyTo(PaymentDTO.class)
                .end();

    }
}
