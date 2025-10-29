package com.tiquetera.ticketing.config;

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
 * Configuración de OpenAPI (Swagger) para documentación de la API.
 * 
 * Esta clase configura la información general que aparecerá en Swagger UI,
 * incluyendo título, descripción, versión, contacto y servidores disponibles.
 * 
 * Acceso: http://localhost:8080/swagger-ui.html
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    /**
     * Configura la información de OpenAPI para la documentación.
     * 
     * @return Objeto OpenAPI configurado
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ticketing API - Catálogo de Eventos y Venues")
                        .description("""
                                API REST para gestión de eventos y lugares (venues) de una tiquetera online.
                                
                                Esta API permite:
                                - ✅ Crear, consultar, actualizar y eliminar eventos
                                - ✅ Crear, consultar, actualizar y eliminar venues
                                - ✅ Filtrar eventos por venue o estado
                                - ✅ Filtrar venues por ciudad, país o estado
                                
                                **Versión actual:** In-Memory (sin persistencia en base de datos)
                                
                                **Arquitectura:** MVC por capas (Controller-Service-Repository)
                                
                                **Principios aplicados:** SOLID, Clean Architecture
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ticketing Team")
                                .email("support@ticketing.com")
                                .url("https://github.com/ticketing-api"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desarrollo"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Testing")
                ));
    }
}