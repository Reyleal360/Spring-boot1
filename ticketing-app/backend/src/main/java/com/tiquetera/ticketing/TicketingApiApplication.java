package com.tiquetera.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Ticketing API.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@SpringBootApplication
public class TicketingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingApiApplication.class, args);

        System.out.println("\n" +
                "╔═══════════════════════════════════════════════════════════╗\n" +
                "║                                                           ║\n" +
                "║          🎫 TICKETING API - Sistema Iniciado 🎫          ║\n" +
                "║                                                           ║\n" +
                "║  📚 Swagger UI:      http://localhost:8080/swagger-ui.html  ║\n" +
                "║  📄 OpenAPI Docs:    http://localhost:8080/api-docs      ║\n" +
                "║                                                           ║\n" +
                "║  🏗️  Arquitectura:   Hexagonal (Ports & Adapters)        ║\n" +
                "║  💾 Almacenamiento:  In-Memory (temporal)                ║\n" +
                "║                                                           ║\n" +
                "╚═══════════════════════════════════════════════════════════╝\n");
    }
}