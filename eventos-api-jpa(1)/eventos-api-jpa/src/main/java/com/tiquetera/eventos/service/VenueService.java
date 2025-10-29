package com.tiquetera.eventos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiquetera.eventos.dto.VenueDTO;
import com.tiquetera.eventos.entity.VenueEntity;
import com.tiquetera.eventos.exception.DuplicateResourceException;
import com.tiquetera.eventos.exception.ResourceNotFoundException;
import com.tiquetera.eventos.mapper.VenueMapper;
import com.tiquetera.eventos.repository.VenueRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicio para gestionar Venues con persistencia JPA
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VenueService {
    
    private final VenueRepository venueRepository;
    
    /**
     * Crear un nuevo venue
     */
    @Transactional
    public VenueDTO createVenue(VenueDTO venueDTO) {
        log.info("Creando venue: {}", venueDTO.getNombre());
        
        // Validar que no exista un venue con el mismo nombre
        if (venueRepository.existsByNombre(venueDTO.getNombre())) {
            throw new DuplicateResourceException("Venue", "nombre", venueDTO.getNombre());
        }
        
        VenueEntity entity = VenueMapper.toEntity(venueDTO);
        VenueEntity savedEntity = venueRepository.save(entity);
        
        log.info("Venue creado con ID: {}", savedEntity.getId());
        return VenueMapper.toDTO(savedEntity);
    }
    
    /**
     * Obtener todos los venues
     */
    @Transactional(readOnly = true)
    public List<VenueDTO> getAllVenues() {
        log.info("Obteniendo todos los venues");
        
        return venueRepository.findAll().stream()
                .map(VenueMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtener todos los venues con paginación
     */
    @Transactional(readOnly = true)
    public Page<VenueDTO> getAllVenues(Pageable pageable) {
        log.info("Obteniendo venues con paginación: page={}, size={}", 
                 pageable.getPageNumber(), pageable.getPageSize());
        
        return venueRepository.findAll(pageable)
                .map(VenueMapper::toDTO);
    }
    
    /**
     * Obtener un venue por ID
     */
    @Transactional(readOnly = true)
    public VenueDTO getVenueById(Long id) {
        log.info("Buscando venue con ID: {}", id);
        
        VenueEntity entity = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", "id", id));
        
        return VenueMapper.toDTO(entity);
    }
    
    /**
     * Obtener venues por ciudad
     */
    @Transactional(readOnly = true)
    public List<VenueDTO> getVenuesByCity(String ciudad) {
        log.info("Buscando venues en ciudad: {}", ciudad);
        
        return venueRepository.findByCiudad(ciudad).stream()
                .map(VenueMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtener venues por ciudad con paginación
     */
    @Transactional(readOnly = true)
    public Page<VenueDTO> getVenuesByCity(String ciudad, Pageable pageable) {
        log.info("Buscando venues en ciudad: {} con paginación", ciudad);
        
        return venueRepository.findByCiudad(ciudad, pageable)
                .map(VenueMapper::toDTO);
    }
    
    /**
     * Obtener venues por país
     */
    @Transactional(readOnly = true)
    public List<VenueDTO> getVenuesByCountry(String pais) {
        log.info("Buscando venues en país: {}", pais);
        
        return venueRepository.findByPais(pais).stream()
                .map(VenueMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtener venues por país con paginación
     */
    @Transactional(readOnly = true)
    public Page<VenueDTO> getVenuesByCountry(String pais, Pageable pageable) {
        log.info("Buscando venues en país: {} con paginación", pais);
        
        return venueRepository.findByPais(pais, pageable)
                .map(VenueMapper::toDTO);
    }
    
    /**
     * Actualizar un venue existente
     */
    @Transactional
    public VenueDTO updateVenue(Long id, VenueDTO venueDTO) {
        log.info("Actualizando venue con ID: {}", id);
        
        VenueEntity existingEntity = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", "id", id));
        
        // Validar nombre único si se está cambiando
        if (!existingEntity.getNombre().equals(venueDTO.getNombre())) {
            if (venueRepository.existsByNombre(venueDTO.getNombre())) {
                throw new DuplicateResourceException("Venue", "nombre", venueDTO.getNombre());
            }
        }
        
        VenueMapper.updateEntityFromDTO(venueDTO, existingEntity);
        VenueEntity updatedEntity = venueRepository.save(existingEntity);
        
        log.info("Venue actualizado con ID: {}", id);
        return VenueMapper.toDTO(updatedEntity);
    }
    
    /**
     * Eliminar un venue
     */
    @Transactional
    public void deleteVenue(Long id) {
        log.info("Eliminando venue con ID: {}", id);
        
        if (!venueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venue", "id", id);
        }
        
        venueRepository.deleteById(id);
        log.info("Venue eliminado con ID: {}", id);
    }
}