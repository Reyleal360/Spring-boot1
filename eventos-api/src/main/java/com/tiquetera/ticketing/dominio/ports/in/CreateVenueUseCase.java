package com.tiquetera.ticketing.dominio.ports.in;

import com.tiquetera.ticketing.dominio.modelo.Venue;

/**
 * Puerto de entrada para crear un venue.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface CreateVenueUseCase {

    /**
     * Crea un nuevo venue.
     * 
     * @param venue El venue a crear (sin ID)
     * @return El venue creado con ID asignado
     */
    Venue execute(Venue venue);
}
