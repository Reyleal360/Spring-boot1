package com.tiquetera.venues.domain.ports.in;

import com.tiquetera.venues.domain.model.Venue;
import java.util.List;

/**
 * Puerto de entrada para listar venues con varios filtros.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface ListVenuesUseCase {

    /**
     * Obtiene todos los venues.
     */
    List<Venue> getAllVenues();

    /**
     * Obtiene venues por ciudad.
     */
    List<Venue> getVenuesByCity(String city);

    /**
     * Obtiene venues por país.
     */
    List<Venue> getVenuesByCountry(String country);

    /**
     * Obtiene venues por estado.
     */
    List<Venue> getVenuesByStatus(String status);
}
