package br.com.zup.paymentprocessor.application.service;

import br.com.zup.paymentprocessor.application.domain.payment.commons.exception.NotBusinessDayException;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedEntity;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedRepository;
import br.com.zup.paymentprocessor.application.mappers.payment.ted.TedEntityMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TedService {

    private final TedRepository tedRepository;
    private final TedEntityMapper tedEntityMapper;
    private final ValidationGateway validationGateway;

    public void validate(PaymentDTO paymentDTO) {
        ResponseEntity<BusinessDayResponse> response = validationGateway.callValidationAPI(paymentDTO);
        if (!response.getBody().isBusinessDay) {
            throw new NotBusinessDayException();
        }
    }

    public void store(PaymentDTO paymentDTO) {
        TedEntity tedEntity = tedEntityMapper.tedEntityToPaymentDto(paymentDTO);
        tedRepository.save(tedEntity);
    }

    @Data
    @NoArgsConstructor
    public static class BusinessDayResponse {
        private Boolean isBusinessDay;
    }

}
