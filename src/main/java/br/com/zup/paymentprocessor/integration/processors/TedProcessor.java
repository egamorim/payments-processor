package br.com.zup.paymentprocessor.integration.processors;

import br.com.zup.paymentprocessor.application.domain.payment.ted.TedEntity;
import br.com.zup.paymentprocessor.application.mappers.payment.ted.included.TedIncludedMapper;
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

        TedEntity tedEntity = exchange.getIn().getBody(TedEntity.class);

        TedIncluded tedIncluded = tedIncludedMapper.tedEntityToTedIncluded(tedEntity);
        exchange.getIn().setBody(tedIncluded);
    }
}
