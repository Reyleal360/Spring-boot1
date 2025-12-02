package com.tiquetera.venues.infrastructure.adapter.in.web.mapper;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.infrastructure.adapter.in.web.dto.VenueDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T18:57:21-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Ubuntu)"
)
@Component
public class VenueWebMapperImpl implements VenueWebMapper {

    @Override
    public Venue toDomain(VenueDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setId( dto.getId() );
        venue.setName( dto.getName() );
        venue.setAddress( dto.getAddress() );
        venue.setCity( dto.getCity() );
        venue.setCountry( dto.getCountry() );
        venue.setCapacity( dto.getCapacity() );
        venue.setDescription( dto.getDescription() );
        venue.setPhone( dto.getPhone() );
        venue.setEmail( dto.getEmail() );
        venue.setStatus( dto.getStatus() );
        venue.setCreatedAt( dto.getCreatedAt() );
        venue.setUpdatedAt( dto.getUpdatedAt() );

        return venue;
    }

    @Override
    public VenueDTO toDTO(Venue domain) {
        if ( domain == null ) {
            return null;
        }

        VenueDTO.VenueDTOBuilder venueDTO = VenueDTO.builder();

        venueDTO.id( domain.getId() );
        venueDTO.name( domain.getName() );
        venueDTO.address( domain.getAddress() );
        venueDTO.city( domain.getCity() );
        venueDTO.country( domain.getCountry() );
        venueDTO.capacity( domain.getCapacity() );
        venueDTO.description( domain.getDescription() );
        venueDTO.phone( domain.getPhone() );
        venueDTO.email( domain.getEmail() );
        venueDTO.status( domain.getStatus() );
        venueDTO.createdAt( domain.getCreatedAt() );
        venueDTO.updatedAt( domain.getUpdatedAt() );

        return venueDTO.build();
    }

    @Override
    public List<VenueDTO> toDTOList(List<Venue> domains) {
        if ( domains == null ) {
            return null;
        }

        List<VenueDTO> list = new ArrayList<VenueDTO>( domains.size() );
        for ( Venue venue : domains ) {
            list.add( toDTO( venue ) );
        }

        return list;
    }
}
