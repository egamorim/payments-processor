package br.com.zup.paymentprocessor.config;

import br.com.zup.paymentprocessor.integration.converters.TedConverter;
import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.inject.Singleton;

@Configuration
@RequiredArgsConstructor
public class CamelConfig {

    private final ApplicationContext applicationContext;
    private final Environment env;
    @Value("${server.port}")
    private final String serverPort;

    @Bean
    public CamelContext camelContext(){
        TedConverter converter = new TedConverter();
        SpringCamelContext context = new SpringCamelContext(applicationContext);
        context.setTypeConverterStatisticsEnabled(true);
        context.getTypeConverterRegistry().addTypeConverters(converter);
        return context;
    }

    @Bean
    @Singleton
    public RouteBuilder routeBuilder(){
        return new RouteBuilder() {
                    public void configure() {
                        restConfiguration()
                                .contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
                                .apiContextPath("/api-doc")
                                .apiProperty("api.title", "Zup Payments Rest API.")
                                .apiProperty("api.version", "1.0")
                                .apiProperty("cors", "true")
                                .apiContextRouteId("doc-api")
                                .port(env.getProperty("server.port", serverPort))
                                .bindingMode(RestBindingMode.json);
                        errorHandler(deadLetterChannel("direct:generalError"));

                    }
                };
    }

}