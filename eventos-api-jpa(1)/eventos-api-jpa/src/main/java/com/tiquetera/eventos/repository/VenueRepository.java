package com.tiquetera.eventos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tiquetera.eventos.entity.VenueEntity;

/**
 * Repositorio JPA para Venue
 * Proporciona métodos CRUD y consultas personalizadas
 */
@Repository
public interface VenueRepository extends JpaRepository<VenueEntity, Long> {
    
    /**
     * Busca un venue por nombre
     */
    Optional<VenueEntity> findByNombre(String nombre);
    
    /**
     * Busca venues por ciudad
     */
    List<VenueEntity> findByCiudad(String ciudad);
    
    /**
     * Busca venues por ciudad con paginación
     */
    Page<VenueEntity> findByCiudad(String ciudad, Pageable pageable);
    
    /**
     * Busca venues por país
     */
    List<VenueEntity> findByPais(String pais);
    
    /**
     * Busca venues por país con paginación
     */
    Page<VenueEntity> findByPais(String pais, Pageable pageable);
    
    /**
     * Busca venues por tipo
     */
    List<VenueEntity> findByTipo(String tipo);
    
    /**
     * Verifica si existe un venue con el nombre dado
     */
    boolean existsByNombre(String nombre);
    
    /**
     * Busca venues con capacidad mayor a la especificada
     */
    @Query("SELECT v FROM VenueEntity v WHERE v.capacidad >= :capacidad")
    List<VenueEntity> findByCapacidadGreaterThanEqual(@Param("capacidad") Integer capacidad);
}