package com.tiquetera.venues.infrastructure.adapter.out.persistence;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.domain.ports.out.VenueRepositoryPort;
import com.tiquetera.venues.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.tiquetera.venues.infrastructure.adapter.out.persistence.mapper.VenuePersistenceMapper;
import com.tiquetera.venues.infrastructure.adapter.out.persistence.repository.VenueJpaRepository;
import com.tiquetera.venues.infrastructure.adapter.out.persistence.specifications.VenueSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia JPA para venues.
 * 
 * Implementa el puerto VenueRepositoryPort usando Spring Data JPA
 * y MySQL como base de datos. Reemplaza al VenueInMemoryAdapter.
 * 
 * @author Ticketing Team
 * @version 3.0 - JPA/MySQL Implementation
 */
@Component
@Primary
@RequiredArgsConstructor
@Transactional
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final VenueJpaRepository repository;
    private final VenuePersistenceMapper mapper;

    @Override
    public Venue save(Venue venue) {
        VenueEntity entity = mapper.toEntity(venue);
        VenueEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venue> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venue> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venue> findByCity(String city) {
        return repository.findByCityIgnoreCase(city).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venue> findByCountry(String country) {
        return repository.findByCountryIgnoreCase(country).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venue> findByStatus(String status) {
        return repository.findByStatusIgnoreCase(status).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<Venue> findByFilters(String city, String country, Integer capacity, String status) {
        Specification<VenueEntity> spec = (root, query, criteriaBuilder) -> null;

        if (city != null && !city.isEmpty()) {
            spec = spec.and(VenueSpecifications.withCity(city));
        }
        if (country != null && !country.isEmpty()) {
            spec = spec.and(VenueSpecifications.withCountry(country));
        }
        if (capacity != null) {
            spec = spec.and(VenueSpecifications.withCapacityGreaterThan(capacity));
        }
        if (status != null && !status.isEmpty()) {
            spec = spec.and(VenueSpecifications.withStatus(status));
        }

        return mapper.toDomainList(repository.findAll(spec));
    }
}
