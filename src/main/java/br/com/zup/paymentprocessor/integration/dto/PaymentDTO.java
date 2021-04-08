package br.com.zup.paymentprocessor.integration.dto;

 import com.fasterxml.jackson.annotation.JsonFormat;
 import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
 import com.fasterxml.jackson.databind.annotation.JsonSerialize;
 import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
 import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
 import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PaymentDTO {

    private UUID id;
    private BigDecimal valuePayment;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataPayment;

}
