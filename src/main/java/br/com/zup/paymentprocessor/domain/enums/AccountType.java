package br.com.zup.paymentprocessor.domain.enums;

import lombok.Getter;

import java.util.*;

public enum AccountType {
    CORRENTE("corrente"),
    POUPANCA("poupança");

    @Getter
    private final String value;

    AccountType(final String accountType) { this.value= (accountType);}

    public static AccountType entryOf(final String accountType){
        return Arrays.stream(AccountType.values())
                .filter(t -> t.getValue()   .equalsIgnoreCase(accountType))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
