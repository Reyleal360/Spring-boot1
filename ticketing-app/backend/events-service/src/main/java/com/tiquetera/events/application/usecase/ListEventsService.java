package com.tiquetera.events.application.usecase;

import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.domain.ports.in.ListEventsUseCase;
import com.tiquetera.events.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del caso de uso para listar eventos - Microservicio Events
 * 
 * @author Ticketing Team
 * @version 3.0 - Microservices Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListEventsService implements ListEventsUseCase {

    private final EventRepositoryPort eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getEventsByVenueId(Long venueId) {
        return eventRepository.findByVenueId(venueId);
    }

    @Override
    public List<Event> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status);
    }
}
