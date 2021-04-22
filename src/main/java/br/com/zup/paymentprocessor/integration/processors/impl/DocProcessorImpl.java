package br.com.zup.paymentprocessor.integration.processors.impl;

import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.integration.processors.DocProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

@Service
public class DocProcessorImpl implements DocProcessor {

    @Override
    public void process(Exchange exchange) throws Exception {

        PaymentDTO payment = exchange.getIn().getBody(PaymentDTO.class);
        System.out.println("Running doc processor. payload: " + payment.toString());
    }
}
