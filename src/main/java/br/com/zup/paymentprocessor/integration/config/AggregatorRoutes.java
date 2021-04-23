package br.com.zup.paymentprocessor.integration.config;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AggregatorRoutes {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
