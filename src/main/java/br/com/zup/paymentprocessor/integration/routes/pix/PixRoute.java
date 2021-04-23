package br.com.zup.paymentprocessor.integration.routes.pix;

import br.com.zup.paymentprocessor.application.service.PaymentService;
import br.com.zup.paymentprocessor.integration.config.Route;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;

@Route
@RequiredArgsConstructor
public class PixRoute extends RouteBuilder {

    private final PaymentService paymentService;

    @Override
    public void configure() throws Exception {

        from("direct:pix")
                .bean(paymentService, "validate")
                .log("New PIX processed")
                .end();

    }
}
