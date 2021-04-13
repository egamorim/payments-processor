package br.com.zup.paymentprocessor.application.domain.payment.commons.exception;

public class NotBusinessDayException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Nao foi possivel concluir a operacao. Data nao 'e dia util.";

    public NotBusinessDayException() {
        super(DEFAULT_MESSAGE);
    }

}
