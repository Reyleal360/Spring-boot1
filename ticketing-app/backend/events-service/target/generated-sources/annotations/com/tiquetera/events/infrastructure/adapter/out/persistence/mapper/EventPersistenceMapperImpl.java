package com.tiquetera.events.infrastructure.adapter.out.persistence.mapper;

import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.VenueEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-20T19:21:55-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class EventPersistenceMapperImpl implements EventPersistenceMapper {

    @Override
    public Event toDomain(EventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Event event = new Event();

        event.setVenueId( entityVenueId( entity ) );
        event.setVenueName( entityVenueName( entity ) );
        event.setId( entity.getId() );
        event.setName( entity.getName() );
        event.setDescription( entity.getDescription() );
        event.setEventDate( entity.getEventDate() );
        event.setCapacity( entity.getCapacity() );
        event.setTicketPrice( entity.getTicketPrice() );
        event.setStatus( entity.getStatus() );
        event.setCreatedAt( entity.getCreatedAt() );
        event.setUpdatedAt( entity.getUpdatedAt() );

        return event;
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

    private Long entityVenueId(EventEntity eventEntity) {
        if ( eventEntity == null ) {
            return null;
        }
        VenueEntity venue = eventEntity.getVenue();
        if ( venue == null ) {
            return null;
        }
        Long id = venue.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityVenueName(EventEntity eventEntity) {
        if ( eventEntity == null ) {
            return null;
        }
        VenueEntity venue = eventEntity.getVenue();
        if ( venue == null ) {
            return null;
        }
        String name = venue.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
