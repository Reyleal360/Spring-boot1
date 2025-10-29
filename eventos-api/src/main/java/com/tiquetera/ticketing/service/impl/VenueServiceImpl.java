package com.tiquetera.ticketing.service.impl;

import com.tiquetera.ticketing.dto.VenueDTO;
import com.tiquetera.ticketing.exception.ResourceNotFoundException;
import com.tiquetera.ticketing.repository.VenueRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

import com.tiquetera.ticketing.service.VenueService;

/**
 * Implementación del servicio de Venues.
 * 
 * Principios SOLID aplicados:
 * - Single Responsibility Principle (SRP): Solo maneja lógica de negocio de venues
 * - Dependency Inversion Principle (DIP): Depende de abstracciones (interfaces)
 * - Liskov Substitution Principle (LSP): Puede ser sustituida por cualquier implementación de VenueService
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    
    // Inyección de dependencias por constructor (DIP)
    private final VenueRepository venueRepository;
    
    @Override
    public VenueDTO createVenue(VenueDTO venueDTO) {
        log.info("Creando nuevo venue: {}", venueDTO.getName());
        
        // Asignar estado por defecto si no viene
        if (venueDTO.getStatus() == null || venueDTO.getStatus().isBlank()) {
            venueDTO.setStatus("ACTIVE");
        }
        
        VenueDTO savedVenue = venueRepository.save(venueDTO);
        log.info("Venue creado exitosamente con ID: {}", savedVenue.getId());
        
        return savedVenue;
    }
    
    @Override
    public VenueDTO getVenueById(Long id) {
        log.info("Buscando venue con ID: {}", id);
        
        return venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con ID: " + id));
    }
    
    @Override
    public List<VenueDTO> getAllVenues() {
        log.info("Obteniendo todos los venues");
        
        List<VenueDTO> venues = venueRepository.findAll();
        log.info("Se encontraron {} venues", venues.size());
        
        return venues;
    }
    
    @Override
    public List<VenueDTO> getVenuesByCity(String city) {
        log.info("Buscando venues en ciudad: {}", city);
        
        List<VenueDTO> venues = venueRepository.findByCity(city);
        log.info("Se encontraron {} venues en {}", venues.size(), city);
        
        return venues;
    }
    
    @Override
    public List<VenueDTO> getVenuesByCountry(String country) {
        log.info("Buscando venues en país: {}", country);
        
        List<VenueDTO> venues = venueRepository.findByCountry(country);
        log.info("Se encontraron {} venues en {}", venues.size(), country);
        
        return venues;
    }
    
    @Override
    public List<VenueDTO> getVenuesByStatus(String status) {
        log.info("Buscando venues con estado: {}", status);
        
        List<VenueDTO> venues = venueRepository.findByStatus(status);
        log.info("Se encontraron {} venues con estado {}", venues.size(), status);
        
        return venues;
    }
    
    @Override
    public VenueDTO updateVenue(Long id, VenueDTO venueDTO) {
        log.info("Actualizando venue con ID: {}", id);
        
        // Verificar que el venue existe
        VenueDTO existingVenue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con ID: " + id));
        
        // Mantener el ID y fecha de creación originales
        venueDTO.setId(id);
        venueDTO.setCreatedAt(existingVenue.getCreatedAt());
        
        VenueDTO updatedVenue = venueRepository.save(venueDTO);
        log.info("Venue actualizado exitosamente con ID: {}", id);
        
        return updatedVenue;
    }
    
    @Override
    public void deleteVenue(Long id) {
        log.info("Eliminando venue con ID: {}", id);
        
        if (!venueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venue no encontrado con ID: " + id);
        }
        
        venueRepository.deleteById(id);
        log.info("Venue eliminado exitosamente con ID: {}", id);
    }
    
    @Override
    public boolean existsVenue(Long id) {
        return venueRepository.existsById(id);
    }
}