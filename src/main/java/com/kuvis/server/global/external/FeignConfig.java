package com.kuvis.server.global.external;
import feign.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final okhttp3.OkHttpClient okHttpClient;

    public FeignConfig(okhttp3.OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Bean
    public Client feignClient() {
        return new feign.okhttp.OkHttpClient(this.okHttpClient);
    }
}