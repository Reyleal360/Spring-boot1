package com.tiquetera.venues.domain.ports.out;

import com.tiquetera.venues.domain.model.Venue;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida (Output Port) para la persistencia de venues.
 * 
 * Define el contrato que deben implementar los adaptadores de persistencia.
 * El dominio no conoce la implementación concreta (JPA, MongoDB, in-memory,
 * etc.)
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface VenueRepositoryPort {

    /**
     * Guarda un venue (crear o actualizar).
     * 
     * @param venue El venue a guardar
     * @return El venue guardado con ID generado si es nuevo
     */
    Venue save(Venue venue);

    /**
     * Busca un venue por su ID.
     * 
     * @param id El ID del venue
     * @return Optional con el venue si existe
     */
    Optional<Venue> findById(Long id);

    /**
     * Obtiene todos los venues.
     * 
     * @return Lista de todos los venues
     */
    List<Venue> findAll();

    /**
     * Busca venues por ciudad.
     * 
     * @param city La ciudad a buscar
     * @return Lista de venues en esa ciudad
     */
    List<Venue> findByCity(String city);

    /**
     * Busca venues por país.
     * 
     * @param country El país a buscar
     * @return Lista de venues en ese país
     */
    List<Venue> findByCountry(String country);

    /**
     * Busca venues por estado.
     * 
     * @param status El estado a buscar
     * @return Lista de venues con ese estado
     */
    List<Venue> findByStatus(String status);

    /**
     * Elimina un venue por su ID.
     * 
     * @param id El ID del venue a eliminar
     */
    void deleteById(Long id);

    /**
     * Verifica si existe un venue con el ID dado.
     * 
     * @param id El ID a verificar
     * @return true si existe, false si no
     */
    boolean existsById(Long id);

    List<Venue> findByFilters(String city, String country, Integer capacity, String status);
}
