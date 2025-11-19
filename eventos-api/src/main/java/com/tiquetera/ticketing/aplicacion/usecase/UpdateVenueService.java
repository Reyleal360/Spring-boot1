package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.dominio.modelo.Venue;
import com.tiquetera.ticketing.dominio.ports.in.UpdateVenueUseCase;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

import java.time.LocalDateTime;

/**
 * Implementación del caso de uso para actualizar venues.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class UpdateVenueService implements UpdateVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    public UpdateVenueService(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

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
