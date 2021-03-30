package br.com.zup.paymentprocessor.domain.commons;

import br.com.zup.paymentprocessor.domain.enums.TipoConta;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class Pagador implements Serializable {

    private static final long serialVersionUID = 1963385643368678314L;

    private UUID id;

    private String nome;

    private String conta;

    private String agencia;

    private TipoConta tipoConta;
}
