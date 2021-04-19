package br.com.zup.paymentprocessor.application.mappers.payment.ted.included;

import br.com.zup.paymentprocessor.integration.dto.PaymentDTO;
import br.com.zup.paymentprocessor.ted_included.TedIncluded;
import org.jeasy.random.EasyRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TedIncludedMapperTest {

    @Autowired
    private TedIncludedMapper mapper;

    @Test
    public void tedIncluded() {

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        EasyRandom easyRandom = new EasyRandom();
        PaymentDTO paymentDTO = easyRandom.nextObject(PaymentDTO.class);

        TedIncluded tedIncluded = mapper.paymentDtoToTedIncluded(paymentDTO);

        assertThat(paymentDTO.getPaymentDate(), notNullValue());
        assertThat(paymentDTO.getPaymentDate(), equalTo(LocalDate.parse(tedIncluded.getPaymentDate().toString(), dtf)));
    }
}