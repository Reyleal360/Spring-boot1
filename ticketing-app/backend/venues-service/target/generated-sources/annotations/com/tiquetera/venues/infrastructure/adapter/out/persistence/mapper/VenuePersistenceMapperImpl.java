package com.tiquetera.venues.infrastructure.adapter.out.persistence.mapper;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.infrastructure.adapter.out.persistence.entity.VenueEntity;
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
public class VenuePersistenceMapperImpl implements VenuePersistenceMapper {

    @Override
    public Venue toDomain(VenueEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setId( entity.getId() );
        venue.setName( entity.getName() );
        venue.setAddress( entity.getAddress() );
        venue.setCity( entity.getCity() );
        venue.setCountry( entity.getCountry() );
        venue.setCapacity( entity.getCapacity() );
        venue.setDescription( entity.getDescription() );
        venue.setPhone( entity.getPhone() );
        venue.setEmail( entity.getEmail() );
        venue.setStatus( entity.getStatus() );
        venue.setCreatedAt( entity.getCreatedAt() );
        venue.setUpdatedAt( entity.getUpdatedAt() );

        return venue;
    }

    @Override
    public VenueEntity toEntity(Venue domain) {
        if ( domain == null ) {
            return null;
        }

        VenueEntity.VenueEntityBuilder venueEntity = VenueEntity.builder();

        venueEntity.id( domain.getId() );
        venueEntity.name( domain.getName() );
        venueEntity.address( domain.getAddress() );
        venueEntity.city( domain.getCity() );
        venueEntity.country( domain.getCountry() );
        venueEntity.capacity( domain.getCapacity() );
        venueEntity.description( domain.getDescription() );
        venueEntity.phone( domain.getPhone() );
        venueEntity.email( domain.getEmail() );
        venueEntity.status( domain.getStatus() );
        venueEntity.createdAt( domain.getCreatedAt() );
        venueEntity.updatedAt( domain.getUpdatedAt() );

        return venueEntity.build();
    }

    @Override
    public List<Venue> toDomainList(List<VenueEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Venue> list = new ArrayList<Venue>( entities.size() );
        for ( VenueEntity venueEntity : entities ) {
            list.add( toDomain( venueEntity ) );
        }

        return list;
    }

    @Override
    public List<VenueEntity> toEntityList(List<Venue> domains) {
        if ( domains == null ) {
            return null;
        }

        List<VenueEntity> list = new ArrayList<VenueEntity>( domains.size() );
        for ( Venue venue : domains ) {
            list.add( toEntity( venue ) );
        }

        return list;
    }
}
