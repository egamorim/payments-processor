package br.com.zup.paymentprocessor.domain.enums;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public enum TipoConta {
    CONTA_CORRENT("conta_corrent"),
    CONTA_POUPANCA("conta_poupanca");

    private static final Map<String, TipoConta>  mapString = new HashMap<>();

    static {
        for(final TipoConta tipoConta : EnumSet.allOf(TipoConta.class)){
            mapString.put(tipoConta.getValue(), tipoConta);
        }
    }

    private final String value;

    TipoConta(final String tipoConta) { this.value= (tipoConta);}

    public static TipoConta entryOf(final String tipoConta){
        return Objects.nonNull(tipoConta) ? mapString.get(tipoConta.toLowerCase()) : null;
    }
}
