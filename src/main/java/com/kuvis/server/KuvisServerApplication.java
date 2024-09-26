package com.kuvis.server;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "https://server.kuvis.shop", description = "product kuvis server")})
@SpringBootApplication
public class KuvisServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KuvisServerApplication.class, args);
    }

}
