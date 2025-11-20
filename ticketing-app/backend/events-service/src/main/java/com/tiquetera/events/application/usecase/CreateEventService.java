package com.tiquetera.events.application.usecase;

import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.domain.ports.in.CreateEventUseCase;
import com.tiquetera.events.domain.ports.out.EventRepositoryPort;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del caso de uso para crear eventos - Microservicio Events
 * 
 * @author Ticketing Team
 * @version 3.0 - Microservices Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CreateEventService implements CreateEventUseCase {

    private final EventRepositoryPort eventRepository;

    @Override
    public Event execute(Event event) {
        // NOTA: En arquitectura de microservicios, la validación del venue
        // debería hacerse via REST call al venues-service.
        // Por ahora, asumimos que el frontend ya validó que el venue existe.

        // Asignar estado por defecto si no viene
        if (event.getStatus() == null || event.getStatus().isBlank()) {
            event.setStatus("SCHEDULED");
        }

        // Establecer timestamps
        LocalDateTime now = LocalDateTime.now();
        event.setCreatedAt(now);
        event.setUpdatedAt(now);

        // Guardar el evento
        return eventRepository.save(event);
    }
}
