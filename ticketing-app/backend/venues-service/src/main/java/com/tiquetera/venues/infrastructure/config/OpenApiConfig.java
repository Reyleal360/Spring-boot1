package com.tiquetera.venues.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI/Swagger para documentación de la API.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ticketing API - Catálogo de Eventos y Venues")
                        .description("""
                                API REST para gestión de eventos y lugares (venues) de una tiquetera online.

                                **Arquitectura**: Hexagonal (Ports & Adapters)
                                **Tecnologías**: Spring Boot, MapStruct

                                Esta API permite:
                                - ✅ Crear, consultar, actualizar y eliminar eventos
                                - ✅ Crear, consultar, actualizar y eliminar venues
                                - ✅ Buscar eventos por venue, estado, etc.
                                - ✅ Buscar venues por ciudad, país, estado
                                """)
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("Ticketing Team")
                                .email("contact@ticketing-api.com")
                                .url("https://github.com/ticketing-api"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desarrollo")));
    }
}
