package com.tiquetera.events.infrastructure.adapter.out.persistence;

import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.domain.ports.out.EventRepositoryPort;
import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.tiquetera.events.infrastructure.adapter.out.persistence.mapper.EventPersistenceMapper;
import com.tiquetera.events.infrastructure.adapter.out.persistence.repository.EventJpaRepository;
import com.tiquetera.events.infrastructure.adapter.out.persistence.specifications.EventSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia JPA para events - Microservicio Events
 * 
 * @author Ticketing Team
 * @version 3.0 - Microservices Architecture
 */
@Component
@Primary
@RequiredArgsConstructor
@Transactional
public class EventJpaAdapter implements EventRepositoryPort {

    private final EventJpaRepository repository;
    private final EventPersistenceMapper mapper;

    @Override
    public Event save(Event event) {
        EventEntity entity = mapper.toEntity(event);

        // En microservicios, ya no seteamos la entidad Venue directamente.
        // El venueId y venueName ya vienen en el objeto Event desde el caso de uso.

        EventEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByStatus(String status) {
        return repository.findByStatusIgnoreCase(status).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByVenueId(Long venueId) {
        return repository.findByVenueId(venueId).stream()
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
    public List<Event> findByFilters(Long venueId, String status, LocalDateTime startDate, LocalDateTime endDate) {
        return mapper.toDomainList(
                repository.findAll(EventSpecifications.withFilters(venueId, status, startDate, endDate)));
    }
}
