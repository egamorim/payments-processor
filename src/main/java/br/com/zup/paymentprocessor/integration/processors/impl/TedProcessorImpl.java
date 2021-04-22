package br.com.zup.paymentprocessor.integration.processors.impl;

import br.com.zup.paymentprocessor.application.mappers.payment.ted.included.TedIncludedMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.integration.processors.TedProcessor;
import br.com.zup.paymentprocessor.ted_included.TedIncluded;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TedProcessorImpl implements TedProcessor {

    private final TedIncludedMapper tedIncludedMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        PaymentDTO paymentDTO = exchange.getIn().getBody(PaymentDTO.class);
        TedIncluded tedIncluded = tedIncludedMapper.paymentDtoToTedIncluded(paymentDTO);
        exchange.getIn().setBody(tedIncluded);
    }
}
