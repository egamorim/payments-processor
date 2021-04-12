package br.com.zup.paymentprocessor.ted_included;

import br.com.zup.paymentprocessor.application.mappers.payment.ted.included.TedIncludedMapper;
import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import org.jeasy.random.EasyRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TedIncludedTest {

    @Autowired
    private TedIncludedMapper tedIncludedMapper;

    public TedIncludedTest() {
    }

    @Test
    public void tedIncluded() {

        EasyRandom easyRandom = new EasyRandom();
        PaymentDTO paymentDTO = easyRandom.nextObject(PaymentDTO.class);

        TedIncluded tedIncluded = tedIncludedMapper.paymentDtoToTedIncluded(paymentDTO);

        assertThat(paymentDTO.getDatePayment(), notNullValue());
        assertThat(paymentDTO.getDatePayment().toString(), equalTo(tedIncluded.getDatePayment().toString()));
    }
}

