package com.tiquetera.ticketing.infraestructura.adapters.in.web.mapper;

import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.infraestructura.adapters.in.web.dto.EventDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T18:48:35-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Ubuntu)"
)
@Component
public class EventWebMapperImpl implements EventWebMapper {

    @Override
    public Event toDomain(EventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Event event = new Event();

        event.setId( dto.getId() );
        event.setName( dto.getName() );
        event.setDescription( dto.getDescription() );
        event.setEventDate( dto.getEventDate() );
        event.setVenueId( dto.getVenueId() );
        event.setVenueName( dto.getVenueName() );
        event.setCapacity( dto.getCapacity() );
        event.setTicketPrice( dto.getTicketPrice() );
        event.setStatus( dto.getStatus() );
        event.setCreatedAt( dto.getCreatedAt() );
        event.setUpdatedAt( dto.getUpdatedAt() );

        return event;
    }

    @Override
    public EventDTO toDTO(Event domain) {
        if ( domain == null ) {
            return null;
        }

        EventDTO.EventDTOBuilder eventDTO = EventDTO.builder();

        eventDTO.id( domain.getId() );
        eventDTO.name( domain.getName() );
        eventDTO.description( domain.getDescription() );
        eventDTO.eventDate( domain.getEventDate() );
        eventDTO.venueId( domain.getVenueId() );
        eventDTO.venueName( domain.getVenueName() );
        eventDTO.capacity( domain.getCapacity() );
        eventDTO.ticketPrice( domain.getTicketPrice() );
        eventDTO.status( domain.getStatus() );
        eventDTO.createdAt( domain.getCreatedAt() );
        eventDTO.updatedAt( domain.getUpdatedAt() );

        return eventDTO.build();
    }

    @Override
    public List<EventDTO> toDTOList(List<Event> domains) {
        if ( domains == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>( domains.size() );
        for ( Event event : domains ) {
            list.add( toDTO( event ) );
        }

        return list;
    }
}
