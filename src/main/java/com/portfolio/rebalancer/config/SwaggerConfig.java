package com.portfolio.rebalancer.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Rebalancer API")
                .description("Rebalancer API DOCS");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components())
                .info(info);
    }
}
