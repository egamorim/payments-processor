package br.com.zup.paymentprocessor.data.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@DynamoDBTable(tableName = "pagamento")
public class TedEntity {

    @DynamoDBHashKey
    private UUID id;

    @DynamoDBAttribute
    private LocalDate dataPagamento;

    @DynamoDBAttribute
    private BigDecimal valorPagamento;

}