package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.dominio.modelo.Venue;
import com.tiquetera.ticketing.dominio.ports.in.GetVenueUseCase;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

/**
 * Implementación del caso de uso para obtener un venue por ID.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class GetVenueService implements GetVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    public GetVenueService(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue execute(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con ID: " + id));
    }
}
