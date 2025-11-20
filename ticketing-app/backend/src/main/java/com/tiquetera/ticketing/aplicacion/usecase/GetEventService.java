package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.dominio.ports.in.GetEventUseCase;
import com.tiquetera.ticketing.dominio.ports.out.EventRepositoryPort;

/**
 * Implementación del caso de uso para obtener un evento por ID.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class GetEventService implements GetEventUseCase {

    private final EventRepositoryPort eventRepository;

    public GetEventService(EventRepositoryPort eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event execute(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento no encontrado con ID: " + id));
    }
}
