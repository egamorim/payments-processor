package br.com.zup.paymentprocessor.integration.processors.error.impl;

import br.com.zup.paymentprocessor.integration.processors.error.RestPaymentRequestHeaderNotAcepted;
import org.apache.camel.Exchange;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestPaymentRequestHeaderNotAceptedImpl implements RestPaymentRequestHeaderNotAcepted {

    public void headerNotAcceptable(Exchange exchange) {
        exchange.getIn().setBody(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.NOT_ACCEPTABLE.value());
    }
}
