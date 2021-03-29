package br.com.zup.paymentprocessor.config;

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
        return new SpringCamelContext(applicationContext);
    }
}