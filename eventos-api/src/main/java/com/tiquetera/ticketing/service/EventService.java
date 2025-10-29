package com.tiquetera.ticketing.service;

import com.tiquetera.ticketing.dto.EventDTO;

import java.util.List;

/**
 * Interfaz que define el contrato de servicios para Eventos.
 * 
 * Principios SOLID aplicados:
 * - Interface Segregation Principle (ISP): Define solo métodos relevantes para eventos
 * - Dependency Inversion Principle (DIP): Las capas superiores dependen de esta abstracción
 * - Open/Closed Principle (OCP): Abierto para extensión, cerrado para modificación
 * 
 * @author Ticketing Team
 * @version 1.0
 */
public interface EventService {
    
    /**
     * Crea un nuevo evento.
     * 
     * @param eventDTO Datos del evento a crear
     * @return El evento creado con su ID asignado
     */
    EventDTO createEvent(EventDTO eventDTO);
    
    /**
     * Obtiene un evento por su ID.
     * 
     * @param id El ID del evento
     * @return El evento encontrado
     * @throws com.ticketing.exception.ResourceNotFoundException si no existe
     */
    EventDTO getEventById(Long id);
    
    /**
     * Obtiene todos los eventos.
     * 
     * @return Lista de todos los eventos
     */
    List<EventDTO> getAllEvents();
    
    /**
     * Obtiene eventos por ID de venue.
     * 
     * @param venueId El ID del venue
     * @return Lista de eventos en ese venue
     */
    List<EventDTO> getEventsByVenueId(Long venueId);
    
    /**
     * Obtiene eventos por estado.
     * 
     * @param status El estado a buscar
     * @return Lista de eventos con ese estado
     */
    List<EventDTO> getEventsByStatus(String status);
    
    /**
     * Actualiza un evento existente.
     * 
     * @param id El ID del evento a actualizar
     * @param eventDTO Los nuevos datos del evento
     * @return El evento actualizado
     * @throws com.ticketing.exception.ResourceNotFoundException si no existe
     */
    EventDTO updateEvent(Long id, EventDTO eventDTO);
    
    /**
     * Elimina un evento por su ID.
     * 
     * @param id El ID del evento a eliminar
     * @throws com.ticketing.exception.ResourceNotFoundException si no existe
     */
    void deleteEvent(Long id);
    
    /**
     * Verifica si existe un evento con el ID dado.
     * 
     * @param id El ID a verificar
     * @return true si existe, false si no
     */
    boolean existsEvent(Long id);
}