package com.tiquetera.ticketing.infraestructura.adapters.out.persistence;

import com.tiquetera.ticketing.dominio.modelo.Event;
import com.tiquetera.ticketing.dominio.ports.out.EventRepositoryPort;
import com.tiquetera.ticketing.infraestructura.adapters.out.persistence.entity.EventEntity;
import com.tiquetera.ticketing.infraestructura.adapters.out.persistence.mapper.EventPersistenceMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia in-memory para eventos.
 * 
 * Implementa el puerto EventRepositoryPort usando almacenamiento en memoria.
 * En el futuro, puede ser reemplazado por un adaptador JPA real.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Component
public class EventInMemoryAdapter implements EventRepositoryPort {

    // Almacenamiento thread-safe en memoria
    private final Map<Long, EventEntity> events = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private final EventPersistenceMapper mapper;

    public EventInMemoryAdapter(EventPersistenceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Event save(Event event) {
        EventEntity entity = mapper.toEntity(event);

        if (entity.getId() == null) {
            entity.setId(idGenerator.getAndIncrement());
            entity.setCreatedAt(LocalDateTime.now());
        }
        entity.setUpdatedAt(LocalDateTime.now());

        events.put(entity.getId(), entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(events.get(id))
                .map(mapper::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return events.values().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findByVenueId(Long venueId) {
        return events.values().stream()
                .filter(event -> event.getVenueId().equals(venueId))
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findByStatus(String status) {
        return events.values().stream()
                .filter(event -> event.getStatus() != null && event.getStatus().equalsIgnoreCase(status))
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        events.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return events.containsKey(id);
    }
}
