package br.com.zup.paymentprocessor.integration.converters;

import br.com.zup.paymentprocessor.ted_included.TedIncluded;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class TedConverter implements TypeConverters {

    @Converter
    public PaymentDTO fromTedEntity(TedIncluded ted) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PaymentDTO dto = new PaymentDTO();
        dto.setId(UUID.fromString(ted.getId().toString()));
        dto.setPaymentDate(LocalDate.parse(ted.getPaymentDate(), formatter));
        dto.setPaymentValue(new BigDecimal(ted.getPaymentValue().toString()));

        return dto;
    }
}
