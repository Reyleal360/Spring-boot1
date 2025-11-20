package com.tiquetera.venues.domain.ports.in;

import com.tiquetera.venues.domain.model.Venue;

/**
 * Puerto de entrada para actualizar un venue.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface UpdateVenueUseCase {

    /**
     * Actualiza un venue existente.
     * 
     * @param id    El ID del venue a actualizar
     * @param venue Los nuevos datos del venue
     * @return El venue actualizado
     * @throws com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException si
     *                                                                             no
     *                                                                             existe
     */
    Venue execute(Long id, Venue venue);
}
