package com.tiquetera.ticketing.service;

import java.util.List;

import com.tiquetera.ticketing.dto.VenueDTO;

/**
 * Interfaz que define el contrato de servicios para Venues.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
public interface VenueService {
    
    /**
     * Crea un nuevo venue.
     */
    VenueDTO createVenue(VenueDTO venueDTO);
    
    /**
     * Obtiene un venue por su ID.
     */
    VenueDTO getVenueById(Long id);
    
    /**
     * Obtiene todos los venues.
     */
    List<VenueDTO> getAllVenues();
    
    /**
     * Obtiene venues por ciudad.
     */
    List<VenueDTO> getVenuesByCity(String city);
    
    /**
     * Obtiene venues por país.
     */
    List<VenueDTO> getVenuesByCountry(String country);
    
    /**
     * Obtiene venues por estado.
     */
    List<VenueDTO> getVenuesByStatus(String status);
    
    /**
     * Actualiza un venue existente.
     */
    VenueDTO updateVenue(Long id, VenueDTO venueDTO);
    
    /**
     * Elimina un venue por su ID.
     */
    void deleteVenue(Long id);
    
    /**
     * Verifica si existe un venue con el ID dado.
     */
    boolean existsVenue(Long id);
}