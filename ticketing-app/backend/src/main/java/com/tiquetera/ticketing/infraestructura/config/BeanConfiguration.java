package com.tiquetera.ticketing.infraestructura.config;

import com.tiquetera.ticketing.aplicacion.usecase.*;
import com.tiquetera.ticketing.dominio.ports.in.*;
import com.tiquetera.ticketing.dominio.ports.out.EventRepositoryPort;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de beans para inyección de dependencias.
 * 
 * Conecta los casos de uso con sus dependencias (puertos).
 * Implementa el patrón de Inversión de Dependencias.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Configuration
public class BeanConfiguration {

    // ==================== EVENT USE CASES ====================

    @Bean
    public CreateEventUseCase createEventUseCase(
            EventRepositoryPort eventRepository,
            VenueRepositoryPort venueRepository) {
        return new CreateEventService(eventRepository, venueRepository);
    }

    @Bean
    public GetEventUseCase getEventUseCase(EventRepositoryPort eventRepository) {
        return new GetEventService(eventRepository);
    }

    @Bean
    public ListEventsUseCase listEventsUseCase(
            EventRepositoryPort eventRepository,
            VenueRepositoryPort venueRepository) {
        return new ListEventsService(eventRepository, venueRepository);
    }

    @Bean
    public UpdateEventUseCase updateEventUseCase(
            EventRepositoryPort eventRepository,
            VenueRepositoryPort venueRepository) {
        return new UpdateEventService(eventRepository, venueRepository);
    }

    @Bean
    public DeleteEventUseCase deleteEventUseCase(EventRepositoryPort eventRepository) {
        return new DeleteEventService(eventRepository);
    }

    // ==================== VENUE USE CASES ====================

    @Bean
    public CreateVenueUseCase createVenueUseCase(VenueRepositoryPort venueRepository) {
        return new CreateVenueService(venueRepository);
    }

    @Bean
    public GetVenueUseCase getVenueUseCase(VenueRepositoryPort venueRepository) {
        return new GetVenueService(venueRepository);
    }

    @Bean
    public ListVenuesUseCase listVenuesUseCase(VenueRepositoryPort venueRepository) {
        return new ListVenuesService(venueRepository);
    }

    @Bean
    public UpdateVenueUseCase updateVenueUseCase(VenueRepositoryPort venueRepository) {
        return new UpdateVenueService(venueRepository);
    }

    @Bean
    public DeleteVenueUseCase deleteVenueUseCase(VenueRepositoryPort venueRepository) {
        return new DeleteVenueService(venueRepository);
    }
}
