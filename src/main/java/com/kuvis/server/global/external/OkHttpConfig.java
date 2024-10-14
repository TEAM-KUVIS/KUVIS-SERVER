package com.kuvis.server.global.external;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> true)  // Hostname 검증 무시
                .build();
    }
}