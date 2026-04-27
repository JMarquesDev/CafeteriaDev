package com.joao.br.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Café Dev - Gestão de Cafeteria")
                        .version("1.0")
                        .description("Sistema para controle de clientes e pedidos.")
                        .contact(new Contact()
                                .name("João Marques")
                                .email("joao.marquesdev2025@gmail.com")));
    }
}