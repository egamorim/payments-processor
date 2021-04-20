package br.com.zup.paymentprocessor.application.mappers.payment.ted.included;

import br.com.zup.paymentprocessor.application.mappers.payment.commons.CommonsMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.ted_included.TedIncluded;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(uses = CommonsMapper.class, builder = @Builder(disableBuilder = true), componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TedIncludedMapper {

    TedIncluded paymentDtoToTedIncluded(final PaymentDTO paymentDTO);
}
