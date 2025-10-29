package com.tiquetera.eventos.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configuración de OpenAPI (Swagger) para documentación de la API
 */
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Eventos y Venues - JPA")
                        .version("2.0.0")
                        .description("""
                                API REST para gestión de eventos y venues con persistencia JPA + H2.
                                
                                **Nuevas características en v2.0:**
                                - ✅ Persistencia con Spring Data JPA
                                - ✅ Base de datos H2 en memoria
                                - ✅ Paginación en todos los endpoints
                                - ✅ Filtros por ciudad, categoría y fecha
                                - ✅ Validación de nombres únicos (409 Conflict)
                                - ✅ Manejo de errores 400, 404, 409
                                
                                **Arquitectura:**
                                - Controller → Service → Repository → Database
                                - Entity ↔ DTO (separación de capas)
                                
                                **Consola H2:** http://localhost:8080/h2-console
                                - JDBC URL: jdbc:h2:mem:eventosdb
                                - Usuario: sa
                                - Contraseña: (vacío)
                                """)
                        .contact(new Contact()
                                .name("Equipo Tiquetera")
                                .email("contacto@tiquetera.com")
                                .url("https://github.com/tiquetera"))
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