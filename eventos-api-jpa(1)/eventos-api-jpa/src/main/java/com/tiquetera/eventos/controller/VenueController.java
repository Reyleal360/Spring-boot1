package com.tiquetera.eventos.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiquetera.eventos.dto.VenueDTO;
import com.tiquetera.eventos.service.VenueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para Venues con paginación
 */
@Slf4j
@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
@Tag(name = "Venues", description = "API para gestión de venues (lugares)")
public class VenueController {
    
    private final VenueService venueService;
    
    /**
     * Crear un nuevo venue
     */
    @PostMapping
    @Operation(summary = "Crear venue", description = "Crea un nuevo venue en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Venue creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "409", description = "Ya existe un venue con ese nombre")
    })
    public ResponseEntity<VenueDTO> createVenue(@Valid @RequestBody VenueDTO venueDTO) {
        log.info("POST /api/venues - Creando venue: {}", venueDTO.getNombre());
        VenueDTO created = venueService.createVenue(venueDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    /**
     * Obtener todos los venues con paginación
     */
    @GetMapping
    @Operation(summary = "Obtener venues", 
               description = "Retorna una lista paginada de venues con opciones de filtrado")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<Page<VenueDTO>> getAllVenues(
            @Parameter(description = "Número de página (inicia en 0)")
            @RequestParam(defaultValue = "0") int page,
            
            @Parameter(description = "Tamaño de página")
            @RequestParam(defaultValue = "10") int size,
            
            @Parameter(description = "Campo para ordenar")
            @RequestParam(defaultValue = "id") String sort,
            
            @Parameter(description = "Dirección de ordenamiento (asc/desc)")
            @RequestParam(defaultValue = "asc") String direction,
            
            @Parameter(description = "Filtrar por ciudad")
            @RequestParam(required = false) String ciudad,
            
            @Parameter(description = "Filtrar por país")
            @RequestParam(required = false) String pais) {
        
        log.info("GET /api/venues - page={}, size={}, ciudad={}, pais={}", 
                 page, size, ciudad, pais);
        
        Sort sortOrder = direction.equalsIgnoreCase("desc") 
                ? Sort.by(sort).descending() 
                : Sort.by(sort).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        
        Page<VenueDTO> venues;
        
        if (ciudad != null) {
            venues = venueService.getVenuesByCity(ciudad, pageable);
        } else if (pais != null) {
            venues = venueService.getVenuesByCountry(pais, pageable);
        } else {
            venues = venueService.getAllVenues(pageable);
        }
        
        return ResponseEntity.ok(venues);
    }
    
    /**
     * Obtener un venue por ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener venue por ID", description = "Retorna un venue específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venue encontrado"),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    public ResponseEntity<VenueDTO> getVenueById(@PathVariable Long id) {
        log.info("GET /api/venues/{}", id);
        VenueDTO venue = venueService.getVenueById(id);
        return ResponseEntity.ok(venue);
    }
    
    /**
     * Actualizar un venue
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar venue", description = "Actualiza los datos de un venue existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venue actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado"),
        @ApiResponse(responseCode = "409", description = "Ya existe un venue con ese nombre")
    })
    public ResponseEntity<VenueDTO> updateVenue(
            @PathVariable Long id,
            @Valid @RequestBody VenueDTO venueDTO) {
        
        log.info("PUT /api/venues/{}", id);
        VenueDTO updated = venueService.updateVenue(id, venueDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Eliminar un venue
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venue", description = "Elimina un venue de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Venue eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        log.info("DELETE /api/venues/{}", id);
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}