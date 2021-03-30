package br.com.zup.paymentprocessor.domain.commons;

import br.com.zup.paymentprocessor.domain.enums.TipoConta;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class Recebedor implements Serializable {

    private static final long serialVersionUID = 9090533664164308960L;

    private UUID id;

    private String nome;

    private String conta;

    private String agencia;

    private TipoConta tipoConta;
}
