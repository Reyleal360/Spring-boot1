package com.tiquetera.events.infrastructure.adapter.out.persistence.mapper;

import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper MapStruct para convertir entre Event (dominio) y EventEntity
 * (persistencia).
 * 
 * MapStruct generará automáticamente la implementación de este mapper.
 * 
 * @author Ticketing Team
 * @version 3.0 - HU5 Error Handling
 */
@Mapper(componentModel = "spring")
public interface EventPersistenceMapper {

    /**
     * Convierte de entidad de persistencia a entidad de dominio.
     */
    @org.mapstruct.Mapping(source = "venue.id", target = "venueId")
    @org.mapstruct.Mapping(source = "venue.name", target = "venueName")
    Event toDomain(EventEntity entity);

    // Eliminamos el método abstracto para evitar ambigüedad y usamos el default
    // como la implementación principal
    // EventEntity toEntity(Event domain);

    List<Event> toDomainList(List<EventEntity> entities);

    List<EventEntity> toEntityList(List<Event> domains);

    // Este método será usado por MapStruct para la conversión
    default EventEntity toEntity(Event domain) {
        if (domain == null) {
            return null;
        }
        EventEntity.EventEntityBuilder builder = EventEntity.builder();
        builder.id(domain.getId());
        builder.name(domain.getName());
        builder.description(domain.getDescription());
        builder.eventDate(domain.getEventDate());
        builder.endDate(domain.getEndDate());
        builder.capacity(domain.getCapacity());
        builder.ticketPrice(domain.getTicketPrice());
        builder.status(domain.getStatus());
        builder.createdAt(domain.getCreatedAt());
        builder.updatedAt(domain.getUpdatedAt());

        if (domain.getVenueId() != null) {
            VenueEntity venue = new VenueEntity();
            venue.setId(domain.getVenueId());
            builder.venue(venue);
        }

        return builder.build();
    }
}
