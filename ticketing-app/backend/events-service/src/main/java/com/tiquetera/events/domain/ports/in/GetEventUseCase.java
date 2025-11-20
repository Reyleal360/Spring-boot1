package com.tiquetera.events.domain.ports.in;

import com.tiquetera.events.domain.model.Event;

/**
 * Puerto de entrada para obtener un evento por ID.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface GetEventUseCase {

    /**
     * Obtiene un evento por su ID.
     * 
     * @param id El ID del evento
     * @return El evento encontrado
     * @throws com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException si
     *                                                                             no
     *                                                                             existe
     */
    Event execute(Long id);
}
