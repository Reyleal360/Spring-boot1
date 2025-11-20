package com.tiquetera.venues.infrastructure.adapter.in.web.mapper;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.infrastructure.adapter.in.web.dto.VenueDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper MapStruct para convertir entre Venue (dominio) y VenueDTO (web).
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Mapper(componentModel = "spring")
public interface VenueWebMapper {

    /**
     * Convierte de DTO web a entidad de dominio.
     */
    Venue toDomain(VenueDTO dto);

    /**
     * Convierte de entidad de dominio a DTO web.
     */
    VenueDTO toDTO(Venue domain);

    /**
     * Convierte una lista de entidades de dominio a DTOs web.
     */
    List<VenueDTO> toDTOList(List<Venue> domains);
}
