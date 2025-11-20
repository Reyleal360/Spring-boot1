package com.tiquetera.ticketing.infraestructura.adapters.out.persistence;

import com.tiquetera.ticketing.dominio.modelo.Venue;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;
import com.tiquetera.ticketing.infraestructura.adapters.out.persistence.entity.VenueEntity;
import com.tiquetera.ticketing.infraestructura.adapters.out.persistence.mapper.VenuePersistenceMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia in-memory para venues.
 * 
 * Implementa el puerto VenueRepositoryPort usando almacenamiento en memoria.
 * En el futuro, puede ser reemplazado por un adaptador JPA real.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Component
public class VenueInMemoryAdapter implements VenueRepositoryPort {

    // Almacenamiento thread-safe en memoria
    private final Map<Long, VenueEntity> venues = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private final VenuePersistenceMapper mapper;

    public VenueInMemoryAdapter(VenuePersistenceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Venue save(Venue venue) {
        VenueEntity entity = mapper.toEntity(venue);

        if (entity.getId() == null) {
            entity.setId(idGenerator.getAndIncrement());
            entity.setCreatedAt(LocalDateTime.now());
        }
        entity.setUpdatedAt(LocalDateTime.now());

        venues.put(entity.getId(), entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return Optional.ofNullable(venues.get(id))
                .map(mapper::toDomain);
    }

    @Override
    public List<Venue> findAll() {
        return venues.values().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venue> findByCity(String city) {
        return venues.values().stream()
                .filter(venue -> venue.getCity() != null && venue.getCity().equalsIgnoreCase(city))
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venue> findByCountry(String country) {
        return venues.values().stream()
                .filter(venue -> venue.getCountry() != null && venue.getCountry().equalsIgnoreCase(country))
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venue> findByStatus(String status) {
        return venues.values().stream()
                .filter(venue -> venue.getStatus() != null && venue.getStatus().equalsIgnoreCase(status))
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        venues.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return venues.containsKey(id);
    }
}
