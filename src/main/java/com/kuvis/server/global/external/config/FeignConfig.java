package com.kuvis.server.global.external.config;

import feign.Feign;
import feign.Request;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .options(new Request.Options(5, TimeUnit.MINUTES, 10, TimeUnit.MINUTES, true));
    }
}