package com.tiquetera.ticketing.service;

import com.tiquetera.ticketing.dto.VenueDTO;

import java.util.List;

/**
 * Interfaz que define el contrato de servicios para Venues.
 * 
 * Principios SOLID aplicados:
 * - Interface Segregation Principle (ISP): Define solo métodos relevantes para venues
 * - Dependency Inversion Principle (DIP): Las capas superiores dependen de esta abstracción
 * - Open/Closed Principle (OCP): Abierto para extensión, cerrado para modificación
 * 
 * @author Ticketing Team
 * @version 1.0
 */
public interface VenueService {
    
    /**
     * Crea un nuevo venue.
     * 
     * @param venueDTO Datos del venue a crear
     * @return El venue creado con su ID asignado
     */
    VenueDTO createVenue(VenueDTO venueDTO);
    
    /**
     * Obtiene un venue por su ID.
     * 
     * @param id El ID del venue
     * @return El venue encontrado
     * @throws com.ticketing.exception.ResourceNotFoundException si no existe
     */
    VenueDTO getVenueById(Long id);
    
    /**
     * Obtiene todos los venues.
     * 
     * @return Lista de todos los venues
     */
    List<VenueDTO> getAllVenues();
    
    /**
     * Obtiene venues por ciudad.
     * 
     * @param city La ciudad a buscar
     * @return Lista de venues en esa ciudad
     */
    List<VenueDTO> getVenuesByCity(String city);
    
    /**
     * Obtiene venues por país.
     * 
     * @param country El país a buscar
     * @return Lista de venues en ese país
     */
    List<VenueDTO> getVenuesByCountry(String country);
    
    /**
     * Obtiene venues por estado.
     * 
     * @param status El estado a buscar
     * @return Lista de venues con ese estado
     */
    List<VenueDTO> getVenuesByStatus(String status);
    
    /**
     * Actualiza un venue existente.
     * 
     * @param id El ID del venue a actualizar
     * @param venueDTO Los nuevos datos del venue
     * @return El venue actualizado
     * @throws com.ticketing.exception.ResourceNotFoundException si no existe
     */
    VenueDTO updateVenue(Long id, VenueDTO venueDTO);
    
    /**
     * Elimina un venue por su ID.
     * 
     * @param id El ID del venue a eliminar
     * @throws com.ticketing.exception.ResourceNotFoundException si no existe
     */
    void deleteVenue(Long id);
    
    /**
     * Verifica si existe un venue con el ID dado.
     * 
     * @param id El ID a verificar
     * @return true si existe, false si no
     */
    boolean existsVenue(Long id);
}