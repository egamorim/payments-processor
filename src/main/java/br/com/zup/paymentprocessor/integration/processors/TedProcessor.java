package br.com.zup.paymentprocessor.integration.processors;

import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("TedProcessor")
public class TedProcessor implements PaymentProcessor{

    @Override
    public void process(Exchange exchange) throws Exception {

        PaymentDTO payment = exchange.getIn().getBody(PaymentDTO.class);
        System.out.println("Running ted processor. payload: " + payment.toString());
    }
}
