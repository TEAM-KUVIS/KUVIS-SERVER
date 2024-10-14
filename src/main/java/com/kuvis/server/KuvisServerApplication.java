package com.kuvis.server;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(servers = {@Server(url = "https://server.kuvis.shop", description = "product kuvis server")})
@SpringBootApplication
@EnableFeignClients
public class KuvisServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KuvisServerApplication.class, args);
    }

}
