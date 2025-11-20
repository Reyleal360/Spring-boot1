package com.tiquetera.ticketing.dominio.ports.in;

import com.tiquetera.ticketing.dominio.modelo.Event;

/**
 * Puerto de entrada para actualizar un evento.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface UpdateEventUseCase {

    /**
     * Actualiza un evento existente.
     * 
     * @param id    El ID del evento a actualizar
     * @param event Los nuevos datos del evento
     * @return El evento actualizado
     * @throws com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException si
     *                                                                             no
     *                                                                             existe
     */
    Event execute(Long id, Event event);
}
