package com.tiquetera.eventos.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tiquetera.eventos.entity.EventEntity;

/**
 * Repositorio JPA para Event
 * Proporciona métodos CRUD y consultas personalizadas con paginación
 */
@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    
    /**
     * Busca un evento por nombre
     */
    Optional<EventEntity> findByNombre(String nombre);
    
    /**
     * Busca eventos por venue ID
     */
    List<EventEntity> findByVenueId(Long venueId);
    
    /**
     * Busca eventos por venue ID con paginación
     */
    Page<EventEntity> findByVenueId(Long venueId, Pageable pageable);
    
    /**
     * Busca eventos por categoría
     */
    List<EventEntity> findByCategoria(String categoria);
    
    /**
     * Busca eventos por categoría con paginación
     */
    Page<EventEntity> findByCategoria(String categoria, Pageable pageable);
    
    /**
     * Busca eventos por ciudad del venue
     */
    @Query("SELECT e FROM EventEntity e WHERE e.venue.ciudad = :ciudad")
    List<EventEntity> findByVenueCiudad(@Param("ciudad") String ciudad);
    
    /**
     * Busca eventos por ciudad con paginación
     */
    @Query("SELECT e FROM EventEntity e WHERE e.venue.ciudad = :ciudad")
    Page<EventEntity> findByVenueCiudad(@Param("ciudad") String ciudad, Pageable pageable);
    
    /**
     * Busca eventos desde una fecha específica
     */
    @Query("SELECT e FROM EventEntity e WHERE e.fechaEvento >= :fechaInicio")
    List<EventEntity> findByFechaEventoAfter(@Param("fechaInicio") LocalDateTime fechaInicio);
    
    /**
     * Busca eventos desde una fecha con paginación
     */
    @Query("SELECT e FROM EventEntity e WHERE e.fechaEvento >= :fechaInicio")
    Page<EventEntity> findByFechaEventoAfter(@Param("fechaInicio") LocalDateTime fechaInicio, Pageable pageable);
    
    /**
     * Verifica si existe un evento con el nombre dado
     */
    boolean existsByNombre(String nombre);
    
    /**
     * Busca eventos con filtros múltiples
     */
    @Query("SELECT e FROM EventEntity e WHERE " +
           "(:ciudad IS NULL OR e.venue.ciudad = :ciudad) AND " +
           "(:categoria IS NULL OR e.categoria = :categoria) AND " +
           "(:fechaInicio IS NULL OR e.fechaEvento >= :fechaInicio)")
    Page<EventEntity> findByFilters(
        @Param("ciudad") String ciudad,
        @Param("categoria") String categoria,
        @Param("fechaInicio") LocalDateTime fechaInicio,
        Pageable pageable
    );
}