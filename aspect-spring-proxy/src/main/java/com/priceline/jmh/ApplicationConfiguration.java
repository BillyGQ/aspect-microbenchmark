package com.priceline.jmh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfiguration {
    @Bean
    public Computation computation() {
        return new Computation();
    }

    @Bean
    public SomeAspect someAspect() {
        return new SomeAspect();
    }
}