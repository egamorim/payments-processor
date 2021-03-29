package br.com.zup.paymentprocessor.integration.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PaymentDTO {

    private String sender;
    private String receiver;
    private BigDecimal amount;

}
