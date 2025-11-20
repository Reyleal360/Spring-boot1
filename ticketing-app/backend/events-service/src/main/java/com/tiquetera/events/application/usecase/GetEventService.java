package com.tiquetera.events.application.usecase;

import com.tiquetera.events.domain.exception.ResourceNotFoundException;
import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.domain.ports.in.GetEventUseCase;
import com.tiquetera.events.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del caso de uso para obtener un evento por ID.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetEventService implements GetEventUseCase {

    private final EventRepositoryPort eventRepository;

    @Override
    public Event execute(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento no encontrado con ID: " + id));
    }
}
