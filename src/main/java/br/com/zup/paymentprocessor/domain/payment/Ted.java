package br.com.zup.paymentprocessor.domain.payment;

import br.com.zup.paymentprocessor.domain.commons.Payer;
import br.com.zup.paymentprocessor.domain.commons.Receiver;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Ted  implements Serializable {

    private static final long serialVersionUID = -1559680134653896507L;

    private UUID id;

    private LocalDate date;

    private BigDecimal amount;

    private Payer payer;

    private Receiver receiver;

}
