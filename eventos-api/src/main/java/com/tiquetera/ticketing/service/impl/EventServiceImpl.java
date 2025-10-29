package com.tiquetera.ticketing.service.impl;

import com.tiquetera.ticketing.dto.EventDTO;
import com.tiquetera.ticketing.dto.VenueDTO;
import com.tiquetera.ticketing.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.repository.EventRepository;
import com.tiquetera.ticketing.repository.VenueRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

import com.tiquetera.ticketing.service.EventService;

/**
 * Implementación del servicio de Eventos.
 * 
 * Principios SOLID aplicados:
 * - Single Responsibility Principle (SRP): Solo maneja lógica de negocio de eventos
 * - Dependency Inversion Principle (DIP): Depende de abstracciones (interfaces)
 * - Liskov Substitution Principle (LSP): Puede ser sustituida por cualquier implementación de EventService
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService  {
    
    // Inyección de dependencias por constructor (DIP)
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    
    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        log.info("Creando nuevo evento: {}", eventDTO.getName());
        
        // Validar que el venue existe
        VenueDTO venue = venueRepository.findById(eventDTO.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con ID: " + eventDTO.getVenueId()));
        
        // Asignar nombre del venue al evento
        eventDTO.setVenueName(venue.getName());
        
        // Asignar estado por defecto si no viene
        if (eventDTO.getStatus() == null || eventDTO.getStatus().isBlank()) {
            eventDTO.setStatus("ACTIVE");
        }
        
        EventDTO savedEvent = eventRepository.save(eventDTO);
        log.info("Evento creado exitosamente con ID: {}", savedEvent.getId());
        
        return savedEvent;
    }
    
    @Override
    public EventDTO getEventById(Long id) {
        log.info("Buscando evento con ID: {}", id);
        
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento no encontrado con ID: " + id));
    }
    
    @Override
    public List<EventDTO> getAllEvents() {
        log.info("Obteniendo todos los eventos");
        
        List<EventDTO> events = eventRepository.findAll();
        log.info("Se encontraron {} eventos", events.size());
        
        return events;
    }
    
    @Override
    public List<EventDTO> getEventsByVenueId(Long venueId) {
        log.info("Buscando eventos para venue ID: {}", venueId);
        
        // Validar que el venue existe
        if (!venueRepository.existsById(venueId)) {
            throw new ResourceNotFoundException("Venue no encontrado con ID: " + venueId);
        }
        
        List<EventDTO> events = eventRepository.findByVenueId(venueId);
        log.info("Se encontraron {} eventos para el venue {}", events.size(), venueId);
        
        return events;
    }
    
    @Override
    public List<EventDTO> getEventsByStatus(String status) {
        log.info("Buscando eventos con estado: {}", status);
        
        List<EventDTO> events = eventRepository.findByStatus(status);
        log.info("Se encontraron {} eventos con estado {}", events.size(), status);
        
        return events;
    }
    
    @Override
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        log.info("Actualizando evento con ID: {}", id);
        
        // Verificar que el evento existe
        EventDTO existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento no encontrado con ID: " + id));
        
        // Validar que el venue existe si se está cambiando
        if (!eventDTO.getVenueId().equals(existingEvent.getVenueId())) {
            VenueDTO venue = venueRepository.findById(eventDTO.getVenueId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Venue no encontrado con ID: " + eventDTO.getVenueId()));
            eventDTO.setVenueName(venue.getName());
        } else {
            eventDTO.setVenueName(existingEvent.getVenueName());
        }
        
        // Mantener el ID y fecha de creación originales
        eventDTO.setId(id);
        eventDTO.setCreatedAt(existingEvent.getCreatedAt());
        
        EventDTO updatedEvent = eventRepository.save(eventDTO);
        log.info("Evento actualizado exitosamente con ID: {}", id);
        
        return updatedEvent;
    }
    
    @Override
    public void deleteEvent(Long id) {
        log.info("Eliminando evento con ID: {}", id);
        
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado con ID: " + id);
        }
        
        eventRepository.deleteById(id);
        log.info("Evento eliminado exitosamente con ID: {}", id);
    }
    
    @Override
    public boolean existsEvent(Long id) {
        return eventRepository.existsById(id);
    }
}