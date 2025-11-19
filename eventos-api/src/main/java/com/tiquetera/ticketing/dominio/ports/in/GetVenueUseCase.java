package com.tiquetera.ticketing.dominio.ports.in;

import com.tiquetera.ticketing.dominio.modelo.Venue;

/**
 * Puerto de entrada para obtener un venue por ID.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface GetVenueUseCase {

    /**
     * Obtiene un venue por su ID.
     * 
     * @param id El ID del venue
     * @return El venue encontrado
     * @throws com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException si
     *                                                                             no
     *                                                                             existe
     */
    Venue execute(Long id);
}
