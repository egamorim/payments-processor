package br.com.zup.paymentprocessor.integration.processors.error;

import org.apache.camel.Exchange;

public class RestPaymentError {

    public void paymentTypeError(Exchange exchange) {
        exchange.getIn().setBody("Wrong payment type");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
    }
}
