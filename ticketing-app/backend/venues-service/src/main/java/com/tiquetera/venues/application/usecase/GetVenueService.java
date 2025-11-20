package com.tiquetera.venues.application.usecase;

import com.tiquetera.venues.domain.exception.ResourceNotFoundException;
import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.domain.ports.in.GetVenueUseCase;
import com.tiquetera.venues.domain.ports.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del caso de uso para obtener un venue por ID.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetVenueService implements GetVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public Venue execute(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con ID: " + id));
    }
}
