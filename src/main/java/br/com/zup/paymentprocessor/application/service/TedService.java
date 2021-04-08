package br.com.zup.paymentprocessor.application.service;

import br.com.zup.paymentprocessor.application.domain.payment.ted.TedEntity;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedRepository;
import br.com.zup.paymentprocessor.application.mappers.payment.ted.TedEntityMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TedService {

    private final TedRepository tedRepository;

    private final TedEntityMapper tedEntityMapper;

    public TedService(TedRepository tedRepository, TedEntityMapper tedEntityMapper) {
        this.tedEntityMapper = tedEntityMapper;
        this.tedRepository = tedRepository;
    }

    public void validate(TedEntity tedEntity) {
        System.out.println("validating: " + tedEntity.toString());
    }

    public void store(PaymentDTO paymentDTO) {

        TedEntity tedEntity = tedEntityMapper.tedEntityToPaymentDto(paymentDTO);
        tedRepository.save(tedEntity);
    }
}
