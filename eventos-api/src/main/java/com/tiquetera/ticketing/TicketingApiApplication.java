package com.tiquetera.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal de la aplicación Ticketing API.
 * 
 * Esta aplicación proporciona una API REST para gestionar:
 * - Eventos (conciertos, deportes, teatro, etc.)
 * - Venues (estadios, teatros, centros de convenciones, etc.)
 * 
 * Arquitectura: MVC por capas (Controller-Service-Repository)
 * Principios: SOLID, Clean Architecture
 * Framework: Spring Boot 3.5.6
 * Java: 17
 * 
 * Acceso a la aplicación:
 * - API: http://localhost:8080/api/v1/
 * - Swagger UI: http://localhost:8080/swagger-ui.html
 * - OpenAPI JSON: http://localhost:8080/api-docs
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ticketing")
public class TicketingApiApplication {
    
    /**
     * Método principal que inicia la aplicación Spring Boot.
     * 
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(TicketingApiApplication.class, args);
        
        // Banner de inicio
        System.out.println("\n" +
                "╔═══════════════════════════════════════════════════════════╗\n" +
                "║                                                           ║\n" +
                "║          🎫 TICKETING API - Sistema Iniciado 🎫          ║\n" +
                "║                                                           ║\n" +
                "║  📌 API REST:        http://localhost:8080/v3/api-docs.yaml   ║\n" +
                "║  📚 Swagger UI:    http://localhost:8080/swagger-ui/index.html  ║\n" +
                "║  📄 OpenAPI Docs:    http://localhost:8080/api-docs      ║\n" +
                "║                                                           ║\n" +
                "║  🏗️  Arquitectura:   MVC por Capas                       ║\n" +
                "║  ⚙️  Principios:     SOLID + Clean Architecture          ║\n" +
                "║  💾 Almacenamiento:  In-Memory (temporal)                ║\n" +
                "║                                                           ║\n" +
                "╚═══════════════════════════════════════════════════════════╝\n");
    }
}