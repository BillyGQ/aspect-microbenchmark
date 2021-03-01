package com.priceline.jmh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Computation computation() {
        return new Computation();
    }
}