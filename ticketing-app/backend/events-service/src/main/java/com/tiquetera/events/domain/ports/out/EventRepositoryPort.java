package com.tiquetera.events.domain.ports.out;

import com.tiquetera.events.domain.model.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida (Output Port) para la persistencia de eventos.
 * 
 * Define el contrato que deben implementar los adaptadores de persistencia.
 * El dominio no conoce la implementación concreta (JPA, MongoDB, in-memory,
 * etc.)
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface EventRepositoryPort {

    /**
     * Guarda un evento (crear o actualizar).
     * 
     * @param event El evento a guardar
     * @return El evento guardado con ID generado si es nuevo
     */
    Event save(Event event);

    /**
     * Busca un evento por su ID.
     * 
     * @param id El ID del evento
     * @return Optional con el evento si existe
     */
    Optional<Event> findById(Long id);

    /**
     * Obtiene todos los eventos.
     * 
     * @return Lista de todos los eventos
     */
    List<Event> findAll();

    /**
     * Busca eventos por ID de venue.
     * 
     * @param venueId El ID del venue
     * @return Lista de eventos en ese venue
     */
    List<Event> findByVenueId(Long venueId);

    /**
     * Busca eventos por estado.
     * 
     * @param status El estado a buscar
     * @return Lista de eventos con ese estado
     */
    List<Event> findByStatus(String status);

    /**
     * Elimina un evento por su ID.
     * 
     * @param id El ID del evento a eliminar
     */
    void deleteById(Long id);

    /**
     * Verifica si existe un evento con el ID dado.
     * 
     * @param id El ID a verificar
     * @return true si existe, false si no
     */
    boolean existsById(Long id);

    List<Event> findByFilters(Long venueId, String status, LocalDateTime startDate, LocalDateTime endDate);
}
