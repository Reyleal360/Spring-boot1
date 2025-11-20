package com.tiquetera.ticketing.infraestructura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * Configuración de CORS (Cross-Origin Resource Sharing).
 * 
 * Permite que aplicaciones frontend (Angular, React, Vue, etc.)
 * en diferentes orígenes puedan consumir esta API REST.
 * 
 * IMPORTANTE: Esta configuración es permisiva para desarrollo.
 * En producción, se debe restringir a orígenes específicos.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Configuration
public class CorsConfig {

    /**
     * Configura el filtro CORS para permitir peticiones cross-origin.
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Orígenes permitidos (desarrollo: localhost)
        corsConfiguration.setAllowedOriginPatterns(List.of(
                "http://localhost:*",
                "http://127.0.0.1:*"));

        // Métodos HTTP permitidos
        corsConfiguration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        // Headers permitidos
        corsConfiguration.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "X-Requested-With"));

        // Headers expuestos al cliente
        corsConfiguration.setExposedHeaders(Arrays.asList(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                "Authorization"));

        // Permitir credenciales
        corsConfiguration.setAllowCredentials(true);

        // Tiempo de cache de la configuración CORS
        corsConfiguration.setMaxAge(3600L);

        // Aplicar configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
