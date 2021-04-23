package br.com.zup.paymentprocessor.integration.routes;

import br.com.zup.paymentprocessor.integration.config.AggregatorRoutes;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.integration.processors.error.RestPaymentRequestHeaderNotAcepted;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.http.MediaType;

import static br.com.zup.paymentprocessor.application.domain.Constants.*;

@AggregatorRoutes
@RequiredArgsConstructor
public class PaymentRoutes extends RouteBuilder {

    private static final String TYPE_HEADER = "payment_type";
    private static final String PAYMENTS_PATH = "payments";

    private final RestPaymentRequestHeaderNotAcepted restPaymentRequestHeaderNotAcepted;


    @Override
    public void configure() throws Exception {

        rest(PAYMENTS_PATH)
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .post("/").route()
                .marshal().json()
                .unmarshal(new JacksonDataFormat(PaymentDTO.class))
                .choice()
                .when(header(TYPE_HEADER).isEqualToIgnoreCase(PAYMENT_TYPE_PIX))
                    .to("direct:pix")
                .when(header(TYPE_HEADER).isEqualToIgnoreCase(PAYMENT_TYPE_TED))
                    .to("direct:ted")
                .when(header(TYPE_HEADER).isEqualToIgnoreCase(PAYMENT_TYPE_DOC))
                    .to("direct:doc")
                .otherwise()
                .bean(restPaymentRequestHeaderNotAcepted, "headerNotAcceptable")
                .end();

    }

}
