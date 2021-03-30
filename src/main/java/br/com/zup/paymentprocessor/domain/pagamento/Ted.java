package br.com.zup.paymentprocessor.domain.pagamento;

import br.com.zup.paymentprocessor.domain.commons.Pagador;
import br.com.zup.paymentprocessor.domain.commons.Recebedor;
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

    private LocalDate dataPagamento;

    private BigDecimal valorPagamento;

    private Pagador pagador;

    private Recebedor recebedor;

}
