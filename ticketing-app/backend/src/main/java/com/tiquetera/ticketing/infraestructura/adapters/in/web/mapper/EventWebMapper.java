package com.tiquetera.ticketing.infraestructura.adapters.in.web.mapper;

import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.infraestructura.adapters.in.web.dto.EventDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper MapStruct para convertir entre Event (dominio) y EventDTO (web).
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Mapper(componentModel = "spring")
public interface EventWebMapper {

    /**
     * Convierte de DTO web a entidad de dominio.
     */
    Event toDomain(EventDTO dto);

    /**
     * Convierte de entidad de dominio a DTO web.
     */
    EventDTO toDTO(Event domain);

    /**
     * Convierte una lista de entidades de dominio a DTOs web.
     */
    List<EventDTO> toDTOList(List<Event> domains);
}
