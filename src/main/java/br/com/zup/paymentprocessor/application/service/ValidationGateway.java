package br.com.zup.paymentprocessor.application.service;

import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ValidationGateway {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    private static final String VALIDATION_PARAMETER = "date";

    @Value("${payments.validation.url}")
    private String url;
    private final RestTemplate client;

    public ResponseEntity<TedService.BusinessDayResponse> callValidationAPI(final PaymentDTO paymentDTO) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url).queryParam(VALIDATION_PARAMETER, formatter.format(paymentDTO.getPaymentDate()));
        return  client.getForEntity(uriComponentsBuilder.toUriString(), TedService.BusinessDayResponse.class);
    }
}
