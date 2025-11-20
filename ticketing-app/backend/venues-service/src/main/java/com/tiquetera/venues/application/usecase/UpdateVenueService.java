package com.tiquetera.venues.application.usecase;

import com.tiquetera.venues.domain.exception.ResourceNotFoundException;
import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.domain.ports.in.UpdateVenueUseCase;
import com.tiquetera.venues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Implementación del caso de uso para actualizar venues.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UpdateVenueService implements UpdateVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public Venue execute(Long id, Venue venue) {
        // Verificar que el venue existe
        Venue existingVenue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con ID: " + id));

        // Mantener el ID y fecha de creación originales
        venue.setId(id);
        venue.setCreatedAt(existingVenue.getCreatedAt());
        venue.setUpdatedAt(LocalDateTime.now());

        return venueRepository.save(venue);
    }
}
