package com.tiquetera.events.domain.ports.in;

/**
 * Puerto de entrada para eliminar un evento.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface DeleteEventUseCase {

    /**
     * Elimina un evento por su ID.
     * 
     * @param id El ID del evento a eliminar
     * @throws com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException si
     *                                                                             no
     *                                                                             existe
     */
    void execute(Long id);
}
