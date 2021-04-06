package br.com.zup.paymentprocessor.application.service;

import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    public void validate(PaymentDTO payment) {
        System.out.println("validating: " + payment.toString());
    }

    public void store(PaymentDTO payment) {
        System.out.println("storing new ted: " + payment.toString());
    }
}
