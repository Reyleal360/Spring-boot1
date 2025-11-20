package com.tiquetera.events.application.usecase;

import com.tiquetera.events.domain.exception.ResourceNotFoundException;
import com.tiquetera.events.domain.ports.in.DeleteEventUseCase;
import com.tiquetera.events.domain.ports.out.EventRepositoryPort;

/**
 * Implementación del caso de uso para eliminar eventos.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class DeleteEventService implements DeleteEventUseCase {

    private final EventRepositoryPort eventRepository;

    public DeleteEventService(EventRepositoryPort eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void execute(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado con ID: " + id);
        }
        eventRepository.deleteById(id);
    }
}
