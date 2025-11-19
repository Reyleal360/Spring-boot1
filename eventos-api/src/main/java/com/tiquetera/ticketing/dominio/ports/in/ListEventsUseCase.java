package com.tiquetera.ticketing.dominio.ports.in;

import com.tiquetera.ticketing.dominio.modelo.Event;
import java.util.List;

/**
 * Puerto de entrada para listar eventos con varios filtros.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public interface ListEventsUseCase {

    /**
     * Obtiene todos los eventos.
     */
    List<Event> getAllEvents();

    /**
     * Obtiene eventos por venue ID.
     */
    List<Event> getEventsByVenueId(Long venueId);

    /**
     * Obtiene eventos por estado.
     */
    List<Event> getEventsByStatus(String status);
}
