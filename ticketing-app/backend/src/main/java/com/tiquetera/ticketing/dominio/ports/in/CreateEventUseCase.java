package com.tiquetera.ticketing.dominio.ports.in;

import com.tiquetera.ticketing.dominio.modelo.Event;

/**
 * Puerto de entrada (Input Port) para crear un evento.
 * 
 * Define el contrato del caso de uso para crear eventos.
 * Los adaptadores de entrada (controllers, CLI, etc.) usarán esta interfaz.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface CreateEventUseCase {

    /**
     * Crea un nuevo evento.
     * 
     * @param event El evento a crear (sin ID)
     * @return El evento creado con ID asignado
     * @throws com.tiquetera.ticketing.dominio.exception.VenueNotFoundException si
     *                                                                          el
     *                                                                          venue
     *                                                                          no
     *                                                                          existe
     */
    Event execute(Event event);
}
