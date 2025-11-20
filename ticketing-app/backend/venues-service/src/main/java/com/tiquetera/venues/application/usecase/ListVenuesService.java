package com.tiquetera.venues.application.usecase;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.domain.ports.in.ListVenuesUseCase;
import com.tiquetera.venues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del caso de uso para listar venues.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListVenuesService implements ListVenuesUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    public List<Venue> getVenuesByCity(String city) {
        return venueRepository.findByCity(city);
    }

    @Override
    public List<Venue> getVenuesByCountry(String country) {
        return venueRepository.findByCountry(country);
    }

    @Override
    public List<Venue> getVenuesByStatus(String status) {
        return venueRepository.findByStatus(status);
    }
}
