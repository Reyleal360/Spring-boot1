package com.tiquetera.events.infrastructure.adapter.out.persistence.repository;

import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio Spring Data JPA para EventEntity.
 * 
 * @author Ticketing Team
 * @version 3.0 - JPA/MySQL Implementation
 */
@Repository
public interface EventJpaRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    /**
     * Encuentra eventos por estado.
     * 
     * @param status estado del evento
     * @return lista de eventos
     */
    @Query("SELECT e FROM EventEntity e WHERE LOWER(e.status) = LOWER(:status)")
    List<EventEntity> findByStatusIgnoreCase(@Param("status") String status);

    /**
     * Encuentra eventos por ID de venue.
     * 
     * @param venueId ID del venue
     * @return lista de eventos
     */
    @Query("SELECT e FROM EventEntity e WHERE e.venueId = :venueId")
    List<EventEntity> findByVenueId(@Param("venueId") Long venueId);

    /**
     * Encuentra eventos en un rango de fechas.
     * 
     * @param start fecha inicio
     * @param end   fecha fin
     * @return lista de eventos
     */
    @Query("SELECT e FROM EventEntity e WHERE e.eventDate BETWEEN :start AND :end")
    List<EventEntity> findByEventDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    /**
     * Encuentra eventos futuros (después de una fecha).
     * 
     * @param date fecha de referencia
     * @return lista de eventos futuros
     */
    List<EventEntity> findByEventDateAfter(LocalDateTime date);

    /**
     * Encuentra eventos por nombre (búsqueda parcial).
     * 
     * @param name nombre o parte del nombre del evento
     * @return lista de eventos que coinciden con el nombre
     */
    List<EventEntity> findByNameContainingIgnoreCase(String name);

    /**
     * Encuentra eventos próximos con JPQL custom query.
     * Útil para mostrar eventos destacados en el frontend.
     * 
     * @param currentDate fecha actual
     * @param limit       número máximo de eventos a retornar
     * @return lista de eventos próximos ordenados por fecha
     */
    @Query("SELECT e FROM EventEntity e WHERE e.eventDate >= :currentDate AND e.status = 'SCHEDULED' ORDER BY e.eventDate ASC")
    List<EventEntity> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);
}
