package br.com.zup.paymentprocessor.application.domain.payment.ted;

import br.com.zup.paymentprocessor.application.converter.LocalDateToStringTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@DynamoDBTable(tableName = "ted-included")
public class TedEntity {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateToStringTypeConverter.class)
    private LocalDate datePayment;

     @DynamoDBAttribute
     private BigDecimal valuePayment;

}
