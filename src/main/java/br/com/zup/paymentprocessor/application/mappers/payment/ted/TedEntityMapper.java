package br.com.zup.paymentprocessor.application.mappers.payment.ted;

import br.com.zup.paymentprocessor.application.domain.payment.ted.TedEntity;
import br.com.zup.paymentprocessor.application.mappers.payment.commons.CommonsMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(uses = CommonsMapper.class, builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface TedEntityMapper {

    TedEntity tedEntityToPaymentDto(PaymentDTO paymentDTO);
}
