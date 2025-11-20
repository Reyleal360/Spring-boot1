package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.dominio.modelo.Venue;
import com.tiquetera.ticketing.dominio.ports.in.CreateEventUseCase;
import com.tiquetera.ticketing.dominio.ports.out.EventRepositoryPort;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

import java.time.LocalDateTime;

/**
 * Implementación del caso de uso para crear eventos.
 * 
 * Aplica principios SOLID:
 * - SRP: Solo se encarga de crear eventos
 * - DIP: Depende de abstracciones (ports), no de implementaciones
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class CreateEventService implements CreateEventUseCase {

    private final EventRepositoryPort eventRepository;
    private final VenueRepositoryPort venueRepository;

    public CreateEventService(EventRepositoryPort eventRepository, VenueRepositoryPort venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public Event execute(Event event) {
        // Validar que el venue existe
        Venue venue = venueRepository.findById(event.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con ID: " + event.getVenueId()));

        // Asignar nombre del venue si no viene
        if (event.getVenueName() == null || event.getVenueName().isBlank()) {
            event.setVenueName(venue.getName());
        }

        // Asignar estado por defecto si no viene
        if (event.getStatus() == null || event.getStatus().isBlank()) {
            event.setStatus("ACTIVE");
        }

        // Establecer timestamps
        LocalDateTime now = LocalDateTime.now();
        event.setCreatedAt(now);
        event.setUpdatedAt(now);

        // Guardar el evento
        return eventRepository.save(event);
    }
}
