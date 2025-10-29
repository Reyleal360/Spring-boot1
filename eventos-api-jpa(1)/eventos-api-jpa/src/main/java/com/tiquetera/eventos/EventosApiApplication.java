package com.tiquetera.eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación
 * API REST con Spring Data JPA + H2
 * 
 * @author Equipo Tiquetera
 * @version 2.0.0
 */
@SpringBootApplication
public class EventosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventosApiApplication.class, args);
        
        System.out.println("\n" +
                "╔═══════════════════════════════════════════════════════════════╗\n" +
                "║                                                               ║\n" +
                "║       🎭 API DE EVENTOS Y VENUES v2.0 - INICIADA ✓           ║\n" +
                "║                                                               ║\n" +
                "║  📍 Swagger UI:    http://localhost:8080/swagger-ui.html     ║\n" +
                "║  📄 API Docs:      http://localhost:8080/api-docs            ║\n" +
                "║  🗄️  Consola H2:    http://localhost:8080/h2-console         ║\n" +
                "║                                                               ║\n" +
                "║  🆕 Nuevas características:                                   ║\n" +
                "║     - Persistencia JPA + H2                                   ║\n" +
                "║     - Paginación (page, size, sort)                           ║\n" +
                "║     - Filtros (ciudad, categoría, fecha)                      ║\n" +
                "║     - Validación de duplicados (409)                          ║\n" +
                "║                                                               ║\n" +
                "║  📊 Endpoints:                                                ║\n" +
                "║     GET    /api/events?page=0&size=10&sort=nombre            ║\n" +
                "║     GET    /api/events?ciudad=Barranquilla                    ║\n" +
                "║     GET    /api/venues?page=0&size=10                         ║\n" +
                "║     POST   /api/events                                        ║\n" +
                "║     PUT    /api/events/{id}                                   ║\n" +
                "║     DELETE /api/events/{id}                                   ║\n" +
                "║                                                               ║\n" +
                "╚═══════════════════════════════════════════════════════════════╝\n");
    }
}