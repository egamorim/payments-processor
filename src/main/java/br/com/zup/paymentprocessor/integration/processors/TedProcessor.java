package br.com.zup.paymentprocessor.integration.processors;

import br.com.zup.paymentprocessor.application.domain.payment.ted.TedEntity;
import br.com.zup.paymentprocessor.application.domain.payment.ted.TedRepository;
import br.com.zup.paymentprocessor.application.mappers.payment.ted.TedEntityMapper;
import br.com.zup.paymentprocessor.application.mappers.payment.ted.included.TedIncludedMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.ted_included.TedIncluded;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("TedProcessor")
public class TedProcessor implements PaymentProcessor {

    private final TedIncludedMapper tedIncludedMapper;

    public TedProcessor(TedIncludedMapper tedIncludedMapper) {
        this.tedIncludedMapper = tedIncludedMapper;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        PaymentDTO paymentDTO = exchange.getIn().getBody(PaymentDTO.class);

        TedIncluded tedIncluded = tedIncludedMapper.paymentDtoToTedIncluded(paymentDTO);
        exchange.getIn().setBody(tedIncluded);
    }
}
