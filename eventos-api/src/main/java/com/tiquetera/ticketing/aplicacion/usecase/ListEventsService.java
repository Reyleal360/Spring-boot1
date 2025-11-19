package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.dominio.ports.in.ListEventsUseCase;
import com.tiquetera.ticketing.dominio.ports.out.EventRepositoryPort;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

import java.util.List;

/**
 * Implementación del caso de uso para listar eventos.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class ListEventsService implements ListEventsUseCase {

    private final EventRepositoryPort eventRepository;
    private final VenueRepositoryPort venueRepository;

    public ListEventsService(EventRepositoryPort eventRepository, VenueRepositoryPort venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getEventsByVenueId(Long venueId) {
        // Validar que el venue existe
        if (!venueRepository.existsById(venueId)) {
            throw new ResourceNotFoundException("Venue no encontrado con ID: " + venueId);
        }
        return eventRepository.findByVenueId(venueId);
    }

    @Override
    public List<Event> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status);
    }
}
