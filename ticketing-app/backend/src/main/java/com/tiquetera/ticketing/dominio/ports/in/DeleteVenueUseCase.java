package com.tiquetera.ticketing.dominio.ports.in;

/**
 * Puerto de entrada para eliminar un venue.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface DeleteVenueUseCase {

    /**
     * Elimina un venue por su ID.
     * 
     * @param id El ID del venue a eliminar
     * @throws com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException si
     *                                                                             no
     *                                                                             existe
     */
    void execute(Long id);
}
