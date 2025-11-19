package com.tiquetera.ticketing.aplicacion.usecase;

import com.tiquetera.ticketing.dominio.modelo.Venue;
import com.tiquetera.ticketing.dominio.ports.in.ListVenuesUseCase;
import com.tiquetera.ticketing.dominio.ports.out.VenueRepositoryPort;

import java.util.List;

/**
 * Implementación del caso de uso para listar venues.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class ListVenuesService implements ListVenuesUseCase {

    private final VenueRepositoryPort venueRepository;

    public ListVenuesService(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

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
