package com.tiquetera.ticketing.repository;

import com.tiquetera.ticketing.dto.VenueDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Repositorio In-Memory para gestionar Venues.
 * 
 * Responsabilidad única (SRP): Gestiona EXCLUSIVAMENTE la persistencia en memoria de venues.
 * Principio de Inversión de Dependencias (DIP): Implementa operaciones de acceso a datos.
 * 
 * NOTA: Esta es una implementación temporal usando memoria.
 * En el futuro se reemplazará por una interfaz que extienda JpaRepository.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@Repository
public class VenueRepository {
    
    // Almacenamiento thread-safe en memoria
    private final Map<Long, VenueDTO> venues = new ConcurrentHashMap<>();
    
    // Generador de IDs automático
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Guarda un nuevo venue en memoria.
     * 
     * @param venue El venue a guardar
     * @return El venue guardado con su ID asignado
     */
    public VenueDTO save(VenueDTO venue) {
        if (venue.getId() == null) {
            venue.setId(idGenerator.getAndIncrement());
            venue.setCreatedAt(LocalDateTime.now());
        }
        venue.setUpdatedAt(LocalDateTime.now());
        venues.put(venue.getId(), venue);
        return venue;
    }
    
    /**
     * Busca un venue por su ID.
     * 
     * @param id El ID del venue
     * @return Optional con el venue si existe, vacío si no
     */
    public Optional<VenueDTO> findById(Long id) {
        return Optional.ofNullable(venues.get(id));
    }
    
    /**
     * Obtiene todos los venues almacenados.
     * 
     * @return Lista de todos los venues
     */
    public List<VenueDTO> findAll() {
        return new ArrayList<>(venues.values());
    }
    
    /**
     * Busca venues por ciudad.
     * 
     * @param city La ciudad a buscar
     * @return Lista de venues en esa ciudad
     */
    public List<VenueDTO> findByCity(String city) {
        return venues.values().stream()
                .filter(venue -> venue.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca venues por país.
     * 
     * @param country El país a buscar
     * @return Lista de venues en ese país
     */
    public List<VenueDTO> findByCountry(String country) {
        return venues.values().stream()
                .filter(venue -> venue.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca venues por estado.
     * 
     * @param status El estado a buscar
     * @return Lista de venues con ese estado
     */
    public List<VenueDTO> findByStatus(String status) {
        return venues.values().stream()
                .filter(venue -> venue.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
    
    /**
     * Elimina un venue por su ID.
     * 
     * @param id El ID del venue a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean deleteById(Long id) {
        return venues.remove(id) != null;
    }
    
    /**
     * Verifica si existe un venue con el ID dado.
     * 
     * @param id El ID a verificar
     * @return true si existe, false si no
     */
    public boolean existsById(Long id) {
        return venues.containsKey(id);
    }
    
    /**
     * Cuenta el total de venues almacenados.
     * 
     * @return Número total de venues
     */
    public long count() {
        return venues.size();
    }
    
    /**
     * Elimina todos los venues (útil para testing).
     */
    public void deleteAll() {
        venues.clear();
        idGenerator.set(1);
    }
}