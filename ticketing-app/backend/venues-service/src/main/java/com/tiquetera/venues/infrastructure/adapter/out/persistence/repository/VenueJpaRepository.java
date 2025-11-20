package com.tiquetera.venues.infrastructure.adapter.out.persistence.repository;

import com.tiquetera.venues.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para VenueEntity.
 * 
 * Proporciona operaciones CRUD y consultas personalizadas para venues.
 * Spring Data JPA implementa automáticamente los métodos básicos y
 * los métodos de consulta por convención de nombres.
 * 
 * @author Ticketing Team
 * @version 3.0 - JPA/MySQL Implementation
 */
@Repository
public interface VenueJpaRepository extends JpaRepository<VenueEntity, Long>, JpaSpecificationExecutor<VenueEntity> {

    /**
     * Encuentra todos los venues en una ciudad específica.
     * 
     * @param city nombre de la ciudad
     * @return lista de venues en la ciudad
     */
    @Query("SELECT v FROM VenueEntity v WHERE LOWER(v.city) = LOWER(:city)")
    List<VenueEntity> findByCityIgnoreCase(@Param("city") String city);

    /**
     * Encuentra todos los venues en un país específico.
     * 
     * @param country nombre del país
     * @return lista de venues en el país
     */
    @Query("SELECT v FROM VenueEntity v WHERE LOWER(v.country) = LOWER(:country)")
    List<VenueEntity> findByCountryIgnoreCase(@Param("country") String country);

    /**
     * Encuentra todos los venues por estado.
     * 
     * @param status estado del venue (ACTIVE, INACTIVE, etc.)
     * @return lista de venues con el estado especificado
     */
    @Query("SELECT v FROM VenueEntity v WHERE LOWER(v.status) = LOWER(:status)")
    List<VenueEntity> findByStatusIgnoreCase(@Param("status") String status);

    /**
     * Encuentra venues por nombre (búsqueda parcial).
     * 
     * @param name nombre o parte del nombre del venue
     * @return lista de venues que coinciden con el nombre
     */
    @Query("SELECT v FROM VenueEntity v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<VenueEntity> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Encuentra venues con capacidad mayor o igual a la especificada.
     * 
     * @param capacity capacidad mínima
     * @return lista de venues
     */
    @Query("SELECT v FROM VenueEntity v WHERE v.capacity >= :capacity")
    List<VenueEntity> findByCapacityGreaterThanEqual(@Param("capacity") Integer capacity);
}
