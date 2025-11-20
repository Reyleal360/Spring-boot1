package com.tiquetera.venues.application.usecase;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.domain.ports.in.CreateVenueUseCase;
import com.tiquetera.venues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Implementación del caso de uso para crear venues.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CreateVenueService implements CreateVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public Venue execute(Venue venue) {
        // Asignar estado por defecto si no viene
        if (venue.getStatus() == null || venue.getStatus().isBlank()) {
            venue.setStatus("ACTIVE");
        }

        // Establecer timestamps
        LocalDateTime now = LocalDateTime.now();
        venue.setCreatedAt(now);
        venue.setUpdatedAt(now);

        return venueRepository.save(venue);
    }
}
