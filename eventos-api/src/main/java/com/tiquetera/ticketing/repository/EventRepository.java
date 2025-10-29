package com.tiquetera.ticketing.repository;

import com.tiquetera.ticketing.dto.EventDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Repositorio In-Memory para gestionar Eventos.
 * 
 * Responsabilidad única (SRP): Gestiona EXCLUSIVAMENTE la persistencia en memoria de eventos.
 * Principio de Inversión de Dependencias (DIP): Implementa operaciones de acceso a datos.
 * 
 * NOTA: Esta es una implementación temporal usando memoria.
 * En el futuro se reemplazará por una interfaz que extienda JpaRepository.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@Repository
public class EventRepository {
    
    // Almacenamiento thread-safe en memoria
    private final Map<Long, EventDTO> events = new ConcurrentHashMap<>();
    
    // Generador de IDs automático
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Guarda un nuevo evento en memoria.
     * 
     * @param event El evento a guardar
     * @return El evento guardado con su ID asignado
     */
    public EventDTO save(EventDTO event) {
        if (event.getId() == null) {
            event.setId(idGenerator.getAndIncrement());
            event.setCreatedAt(LocalDateTime.now());
        }
        event.setUpdatedAt(LocalDateTime.now());
        events.put(event.getId(), event);
        return event;
    }
    
    /**
     * Busca un evento por su ID.
     * 
     * @param id El ID del evento
     * @return Optional con el evento si existe, vacío si no
     */
    public Optional<EventDTO> findById(Long id) {
        return Optional.ofNullable(events.get(id));
    }
    
    /**
     * Obtiene todos los eventos almacenados.
     * 
     * @return Lista de todos los eventos
     */
    public List<EventDTO> findAll() {
        return new ArrayList<>(events.values());
    }
    
    /**
     * Busca eventos por ID de venue.
     * 
     * @param venueId El ID del venue
     * @return Lista de eventos en ese venue
     */
    public List<EventDTO> findByVenueId(Long venueId) {
        return events.values().stream()
                .filter(event -> event.getVenueId().equals(venueId))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca eventos por estado.
     * 
     * @param status El estado a buscar
     * @return Lista de eventos con ese estado
     */
    public List<EventDTO> findByStatus(String status) {
        return events.values().stream()
                .filter(event -> event.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
    
    /**
     * Elimina un evento por su ID.
     * 
     * @param id El ID del evento a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean deleteById(Long id) {
        return events.remove(id) != null;
    }
    
    /**
     * Verifica si existe un evento con el ID dado.
     * 
     * @param id El ID a verificar
     * @return true si existe, false si no
     */
    public boolean existsById(Long id) {
        return events.containsKey(id);
    }
    
    /**
     * Cuenta el total de eventos almacenados.
     * 
     * @return Número total de eventos
     */
    public long count() {
        return events.size();
    }
    
    /**
     * Elimina todos los eventos (útil para testing).
     */
    public void deleteAll() {
        events.clear();
        idGenerator.set(1);
    }
}