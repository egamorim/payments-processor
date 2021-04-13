package br.com.zup.paymentprocessor.application.service;

import br.com.zup.paymentprocessor.application.domain.payment.commons.exception.NotBusinessDayException;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedEntity;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedRepository;
import br.com.zup.paymentprocessor.application.mappers.payment.ted.TedEntityMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TedService {

    private final TedRepository tedRepository;

    private final TedEntityMapper tedEntityMapper;

    private final RestTemplate restTemplate;

    public TedService(TedRepository tedRepository, TedEntityMapper tedEntityMapper) {
        this.tedEntityMapper = tedEntityMapper;
        this.tedRepository = tedRepository;
        this.restTemplate = new RestTemplate();
    }

    public void validate(TedEntity tedEntity) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        final Map<String, String> params = Map.of("date", formatter.format(tedEntity.getDatePayment()));
        final ResponseEntity<BusinessDayResponse> response = restTemplate.getForEntity("http://localhost:9090/api/v1/", BusinessDayResponse.class, params);
        if (!response.getBody().isBusinessDay) {
            throw new NotBusinessDayException();
        }
    }

    public void store(PaymentDTO paymentDTO) {
        TedEntity tedEntity = tedEntityMapper.tedEntityToPaymentDto(paymentDTO);
        tedRepository.save(tedEntity);
    }

    @Data
    private class BusinessDayResponse {
        private boolean isBusinessDay;
    }

}
