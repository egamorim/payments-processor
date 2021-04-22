package br.com.zup.paymentprocessor.integration.processors.error;

import org.apache.camel.Exchange;

@FunctionalInterface
public interface RestPaymentRequestHeaderNotAcepted {
    public void headerNotAcceptable(Exchange exchange);
}
