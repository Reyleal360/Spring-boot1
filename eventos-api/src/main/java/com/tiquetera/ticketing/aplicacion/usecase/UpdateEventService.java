package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.dominio.modelo.Venue;
import com.tiquetera.ticketing.dominio.ports.in.UpdateEventUseCase;
import com.tiquetera.ticketing.dominio.ports.out.EventRepositoryPort;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

import java.time.LocalDateTime;

/**
 * Implementación del caso de uso para actualizar eventos.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class UpdateEventService implements UpdateEventUseCase {

    private final EventRepositoryPort eventRepository;
    private final VenueRepositoryPort venueRepository;

    public UpdateEventService(EventRepositoryPort eventRepository, VenueRepositoryPort venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public Event execute(Long id, Event event) {
        // Verificar que el evento existe
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento no encontrado con ID: " + id));

        // Validar que el venue existe si se está cambiando
        if (!event.getVenueId().equals(existingEvent.getVenueId())) {
            Venue venue = venueRepository.findById(event.getVenueId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Venue no encontrado con ID: " + event.getVenueId()));
            event.setVenueName(venue.getName());
        } else {
            event.setVenueName(existingEvent.getVenueName());
        }

        // Mantener el ID y fecha de creación originales
        event.setId(id);
        event.setCreatedAt(existingEvent.getCreatedAt());
        event.setUpdatedAt(LocalDateTime.now());

        return eventRepository.save(event);
    }
}
