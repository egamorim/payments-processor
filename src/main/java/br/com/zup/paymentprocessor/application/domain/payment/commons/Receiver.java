package br.com.zup.paymentprocessor.application.domain.payment.commons;

import br.com.zup.paymentprocessor.application.domain.payment.commons.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class Receiver implements Serializable {

    private static final long serialVersionUID = 9090533664164308960L;

    private UUID id;

    private String name;

    private String account;

    private String agency;

    private AccountType accountType;
}
