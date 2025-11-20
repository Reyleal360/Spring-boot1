package com.tiquetera.ticketing.infraestructura.adapters.out.persistence.mapper;

import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.infraestructura.adapters.out.persistence.entity.EventEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T18:48:34-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Ubuntu)"
)
@Component
public class EventPersistenceMapperImpl implements EventPersistenceMapper {

    @Override
    public Event toDomain(EventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Event event = new Event();

        event.setId( entity.getId() );
        event.setName( entity.getName() );
        event.setDescription( entity.getDescription() );
        event.setEventDate( entity.getEventDate() );
        event.setVenueId( entity.getVenueId() );
        event.setVenueName( entity.getVenueName() );
        event.setCapacity( entity.getCapacity() );
        event.setTicketPrice( entity.getTicketPrice() );
        event.setStatus( entity.getStatus() );
        event.setCreatedAt( entity.getCreatedAt() );
        event.setUpdatedAt( entity.getUpdatedAt() );

        return event;
    }

    @Override
    public EventEntity toEntity(Event domain) {
        if ( domain == null ) {
            return null;
        }

        EventEntity.EventEntityBuilder eventEntity = EventEntity.builder();

        eventEntity.id( domain.getId() );
        eventEntity.name( domain.getName() );
        eventEntity.description( domain.getDescription() );
        eventEntity.eventDate( domain.getEventDate() );
        eventEntity.venueId( domain.getVenueId() );
        eventEntity.venueName( domain.getVenueName() );
        eventEntity.capacity( domain.getCapacity() );
        eventEntity.ticketPrice( domain.getTicketPrice() );
        eventEntity.status( domain.getStatus() );
        eventEntity.createdAt( domain.getCreatedAt() );
        eventEntity.updatedAt( domain.getUpdatedAt() );

        return eventEntity.build();
    }

    @Override
    public List<Event> toDomainList(List<EventEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( entities.size() );
        for ( EventEntity eventEntity : entities ) {
            list.add( toDomain( eventEntity ) );
        }

        return list;
    }

    @Override
    public List<EventEntity> toEntityList(List<Event> domains) {
        if ( domains == null ) {
            return null;
        }

        List<EventEntity> list = new ArrayList<EventEntity>( domains.size() );
        for ( Event event : domains ) {
            list.add( toEntity( event ) );
        }

        return list;
    }
}
