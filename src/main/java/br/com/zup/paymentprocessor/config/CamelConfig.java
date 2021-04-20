package br.com.zup.paymentprocessor.config;

import br.com.zup.paymentprocessor.integration.converters.TedConverter;
import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public CamelContext camelContext(){
        TedConverter converter = new TedConverter();
        SpringCamelContext context = new SpringCamelContext(applicationContext);
        context.setTypeConverterStatisticsEnabled(true);
        context.getTypeConverterRegistry().addTypeConverters(converter);
        return context;
    }
}