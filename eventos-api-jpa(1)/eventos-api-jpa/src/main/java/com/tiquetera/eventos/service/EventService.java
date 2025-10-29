package com.tiquetera.eventos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiquetera.eventos.dto.EventDTO;
import com.tiquetera.eventos.entity.EventEntity;
import com.tiquetera.eventos.entity.VenueEntity;
import com.tiquetera.eventos.exception.DuplicateResourceException;
import com.tiquetera.eventos.exception.ResourceNotFoundException;
import com.tiquetera.eventos.mapper.EventMapper;
import com.tiquetera.eventos.repository.EventRepository;
import com.tiquetera.eventos.repository.VenueRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicio para gestionar Events con persistencia JPA
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    
    /**
     * Crear un nuevo evento
     */
    @Transactional
    public EventDTO createEvent(EventDTO eventDTO) {
        log.info("Creando evento: {}", eventDTO.getNombre());
        
        // Validar que no exista un evento con el mismo nombre
        if (eventRepository.existsByNombre(eventDTO.getNombre())) {
            throw new DuplicateResourceException("Evento", "nombre", eventDTO.getNombre());
        }
        
        // Validar que el venue existe
        VenueEntity venue = venueRepository.findById(eventDTO.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException("Venue", "id", eventDTO.getVenueId()));
        
        EventEntity entity = EventMapper.toEntity(eventDTO);
        entity.setVenue(venue);
        
        EventEntity savedEntity = eventRepository.save(entity);
        
        log.info("Evento creado con ID: {}", savedEntity.getId());
        return EventMapper.toDTO(savedEntity);
    }
    
    /**
     * Obtener todos los eventos
     */
    @Transactional(readOnly = true)
    public List<EventDTO> getAllEvents() {
        log.info("Obteniendo todos los eventos");
        
        return eventRepository.findAll().stream()
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtener todos los eventos con paginación
     */
    @Transactional(readOnly = true)
    public Page<EventDTO> getAllEvents(Pageable pageable) {
        log.info("Obteniendo eventos con paginación: page={}, size={}", 
                 pageable.getPageNumber(), pageable.getPageSize());
        
        return eventRepository.findAll(pageable)
                .map(EventMapper::toDTO);
    }
    
    /**
     * Obtener un evento por ID
     */
    @Transactional(readOnly = true)
    public EventDTO getEventById(Long id) {
        log.info("Buscando evento con ID: {}", id);
        
        EventEntity entity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento", "id", id));
        
        return EventMapper.toDTO(entity);
    }
    
    /**
     * Obtener eventos por venue ID
     */
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByVenueId(Long venueId) {
        log.info("Buscando eventos para venue ID: {}", venueId);
        
        // Validar que el venue existe
        if (!venueRepository.existsById(venueId)) {
            throw new ResourceNotFoundException("Venue", "id", venueId);
        }
        
        return eventRepository.findByVenueId(venueId).stream()
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtener eventos por venue ID con paginación
     */
    @Transactional(readOnly = true)
    public Page<EventDTO> getEventsByVenueId(Long venueId, Pageable pageable) {
        log.info("Buscando eventos para venue ID: {} con paginación", venueId);
        
        if (!venueRepository.existsById(venueId)) {
            throw new ResourceNotFoundException("Venue", "id", venueId);
        }
        
        return eventRepository.findByVenueId(venueId, pageable)
                .map(EventMapper::toDTO);
    }
    
    /**
     * Obtener eventos por categoría
     */
    @Transactional(readOnly = true)
    public Page<EventDTO> getEventsByCategory(String categoria, Pageable pageable) {
        log.info("Buscando eventos por categoría: {}", categoria);
        
        return eventRepository.findByCategoria(categoria, pageable)
                .map(EventMapper::toDTO);
    }
    
    /**
     * Obtener eventos por ciudad
     */
    @Transactional(readOnly = true)
    public Page<EventDTO> getEventsByCity(String ciudad, Pageable pageable) {
        log.info("Buscando eventos en ciudad: {}", ciudad);
        
        return eventRepository.findByVenueCiudad(ciudad, pageable)
                .map(EventMapper::toDTO);
    }
    
    /**
     * Obtener eventos desde una fecha
     */
    @Transactional(readOnly = true)
    public Page<EventDTO> getEventsByDateAfter(LocalDateTime fechaInicio, Pageable pageable) {
        log.info("Buscando eventos desde fecha: {}", fechaInicio);
        
        return eventRepository.findByFechaEventoAfter(fechaInicio, pageable)
                .map(EventMapper::toDTO);
    }
    
    /**
     * Obtener eventos con filtros múltiples
     */
    @Transactional(readOnly = true)
    public Page<EventDTO> getEventsByFilters(String ciudad, String categoria, 
                                             LocalDateTime fechaInicio, Pageable pageable) {
        log.info("Buscando eventos con filtros - ciudad: {}, categoría: {}, fecha: {}", 
                 ciudad, categoria, fechaInicio);
        
        return eventRepository.findByFilters(ciudad, categoria, fechaInicio, pageable)
                .map(EventMapper::toDTO);
    }
    
    /**
     * Actualizar un evento existente
     */
    @Transactional
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        log.info("Actualizando evento con ID: {}", id);
        
        EventEntity existingEntity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento", "id", id));
        
        // Validar nombre único si se está cambiando
        if (!existingEntity.getNombre().equals(eventDTO.getNombre())) {
            if (eventRepository.existsByNombre(eventDTO.getNombre())) {
                throw new DuplicateResourceException("Evento", "nombre", eventDTO.getNombre());
            }
        }
        
        // Validar venue si se está cambiando
        if (!existingEntity.getVenue().getId().equals(eventDTO.getVenueId())) {
            VenueEntity venue = venueRepository.findById(eventDTO.getVenueId())
                    .orElseThrow(() -> new ResourceNotFoundException("Venue", "id", eventDTO.getVenueId()));
            existingEntity.setVenue(venue);
        }
        
        EventMapper.updateEntityFromDTO(eventDTO, existingEntity);
        EventEntity updatedEntity = eventRepository.save(existingEntity);
        
        log.info("Evento actualizado con ID: {}", id);
        return EventMapper.toDTO(updatedEntity);
    }
    
    /**
     * Eliminar un evento
     */
    @Transactional
    public void deleteEvent(Long id) {
        log.info("Eliminando evento con ID: {}", id);
        
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento", "id", id);
        }
        
        eventRepository.deleteById(id);
        log.info("Evento eliminado con ID: {}", id);
    }
}