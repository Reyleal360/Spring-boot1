package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.dominio.ports.in.DeleteVenueUseCase;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

/**
 * Implementación del caso de uso para eliminar venues.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class DeleteVenueService implements DeleteVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    public DeleteVenueService(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public void execute(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venue no encontrado con ID: " + id);
        }
        venueRepository.deleteById(id);
    }
}
