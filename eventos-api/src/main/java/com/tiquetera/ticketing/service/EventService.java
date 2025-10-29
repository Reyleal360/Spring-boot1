package com.tiquetera.ticketing.service;

import java.util.List;

import com.tiquetera.ticketing.dto.EventDTO;

/**
 * Interfaz que define el contrato de servicios para Eventos.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
public interface EventService {
    
    /**
     * Crea un nuevo evento.
     */
    EventDTO createEvent(EventDTO eventDTO);
    
    /**
     * Obtiene un evento por su ID.
     */
    EventDTO getEventById(Long id);
    
    /**
     * Obtiene todos los eventos.
     */
    List<EventDTO> getAllEvents();
    
    /**
     * Obtiene eventos por ID de venue.
     */
    List<EventDTO> getEventsByVenueId(Long venueId);
    
    /**
     * Obtiene eventos por estado.
     */
    List<EventDTO> getEventsByStatus(String status);
    
    /**
     * Actualiza un evento existente.
     */
    EventDTO updateEvent(Long id, EventDTO eventDTO);
    
    /**
     * Elimina un evento por su ID.
     */
    void deleteEvent(Long id);
    
    /**
     * Verifica si existe un evento con el ID dado.
     */
    boolean existsEvent(Long id);
}