package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.modelo.Venue;
import com.tiquetera.ticketing.dominio.ports.in.CreateVenueUseCase;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

import java.time.LocalDateTime;

/**
 * Implementación del caso de uso para crear venues.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class CreateVenueService implements CreateVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    public CreateVenueService(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

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
