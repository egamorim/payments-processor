package br.com.zup.paymentprocessor.application.service;

import br.com.zup.paymentprocessor.application.domain.payment.commons.exception.NotBusinessDayException;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedEntity;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedRepository;
import br.com.zup.paymentprocessor.application.mappers.payment.ted.TedEntityMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    public void validate(PaymentDTO paymentDTO) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("http://localhost:9090/api/v1/businessday").queryParam("date", formatter.format(paymentDTO.getPaymentDate()));
        ResponseEntity<BusinessDayResponse> response = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), BusinessDayResponse.class);
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
