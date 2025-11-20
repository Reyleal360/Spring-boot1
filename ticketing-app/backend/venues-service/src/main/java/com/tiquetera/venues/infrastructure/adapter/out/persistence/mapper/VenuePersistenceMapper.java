package com.tiquetera.venues.infrastructure.adapter.out.persistence.mapper;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper MapStruct para convertir entre Venue (dominio) y VenueEntity
 * (persistencia).
 * 
 * MapStruct generará automáticamente la implementación de este mapper.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Mapper(componentModel = "spring")
public interface VenuePersistenceMapper {

    /**
     * Convierte de entidad de persistencia a entidad de dominio.
     */
    Venue toDomain(VenueEntity entity);

    /**
     * Convierte de entidad de dominio a entidad de persistencia.
     */
    VenueEntity toEntity(Venue domain);

    /**
     * Convierte una lista de entidades de persistencia a entidades de dominio.
     */
    List<Venue> toDomainList(List<VenueEntity> entities);

    /**
     * Convierte una lista de entidades de dominio a entidades de persistencia.
     */
    List<VenueEntity> toEntityList(List<Venue> domains);
}
