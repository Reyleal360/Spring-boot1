package com.tiquetera.events.infrastructure.adapter.out.persistence.mapper;

import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper MapStruct para convertir entre Event (dominio) y EventEntity
 * (persistencia).
 * 
 * MapStruct generará automáticamente la implementación de este mapper.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Mapper(componentModel = "spring")
public interface EventPersistenceMapper {

    /**
     * Convierte de entidad de persistencia a entidad de dominio.
     */
    Event toDomain(EventEntity entity);

    /**
     * Convierte de entidad de dominio a entidad de persistencia.
     */
    EventEntity toEntity(Event domain);

    /**
     * Convierte una lista de entidades de persistencia a entidades de dominio.
     */
    List<Event> toDomainList(List<EventEntity> entities);

    /**
     * Convierte una lista de entidades de dominio a entidades de persistencia.
     */
    List<EventEntity> toEntityList(List<Event> domains);
}
