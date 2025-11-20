package com.tiquetera.events.application.usecase;

import com.tiquetera.events.domain.exception.ResourceNotFoundException;
import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.domain.ports.in.UpdateEventUseCase;
import com.tiquetera.events.domain.ports.out.EventRepositoryPort;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del caso de uso para actualizar eventos - Microservicio Events
 * 
 * @author Ticketing Team
 * @version 3.0 - Microservices Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UpdateEventService implements UpdateEventUseCase {

    private final EventRepositoryPort eventRepository;

    @Override
    public Event execute(Long id, Event event) {
        // Verificar que el evento existe
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));

        // Actualizar solo los campos que pueden cambiar
        existingEvent.setName(event.getName());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setVenueId(event.getVenueId());
        existingEvent.setVenueName(event.getVenueName());
        existingEvent.setCapacity(event.getCapacity());
        existingEvent.setTicketPrice(event.getTicketPrice());
        existingEvent.setStatus(event.getStatus());
        existingEvent.setUpdatedAt(LocalDateTime.now());

        return eventRepository.save(existingEvent);
    }
}
