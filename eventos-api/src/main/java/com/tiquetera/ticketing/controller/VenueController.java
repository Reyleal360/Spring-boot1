package com.tiquetera.ticketing.controller;

import com.tiquetera.ticketing.dto.VenueDTO;
import com.tiquetera.ticketing.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar Venues.
 * 
 * Responsabilidad única (SRP): Solo maneja las peticiones HTTP relacionadas con venues.
 * Principio de Inversión de Dependencias (DIP): Depende de la abstracción VenueService.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
@Tag(name = "Venues", description = "API para gestión de lugares de eventos")
public class VenueController {
    
    // Inyección de dependencias por constructor
    private final VenueService venueService;
    
    /**
     * Crea un nuevo venue.
     * 
     * @param venueDTO Datos del venue a crear
     * @return ResponseEntity con el venue creado y código 201 Created
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo venue", 
               description = "Crea un nuevo lugar de eventos en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Venue creado exitosamente",
                     content = @Content(schema = @Schema(implementation = VenueDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<VenueDTO> createVenue(
            @Valid @RequestBody VenueDTO venueDTO) {
        
        log.info("POST /api/v1/venues - Creando venue: {}", venueDTO.getName());
        
        VenueDTO createdVenue = venueService.createVenue(venueDTO);
        
        return new ResponseEntity<>(createdVenue, HttpStatus.CREATED);
    }
    
    /**
     * Obtiene todos los venues.
     * 
     * @return ResponseEntity con lista de venues y código 200 OK
     */
    @GetMapping
    @Operation(summary = "Obtener todos los venues", 
               description = "Retorna una lista con todos los lugares de eventos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
    })
    public ResponseEntity<List<VenueDTO>> getAllVenues() {
        log.info("GET /api/v1/venues - Obteniendo todos los venues");
        
        List<VenueDTO> venues = venueService.getAllVenues();
        
        return ResponseEntity.ok(venues);
    }
    
    /**
     * Obtiene un venue por su ID.
     * 
     * @param id El ID del venue
     * @return ResponseEntity con el venue encontrado y código 200 OK
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener venue por ID", 
               description = "Retorna los detalles de un venue específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venue encontrado",
                     content = @Content(schema = @Schema(implementation = VenueDTO.class))),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    public ResponseEntity<VenueDTO> getVenueById(
            @Parameter(description = "ID del venue a buscar", required = true)
            @PathVariable Long id) {
        
        log.info("GET /api/v1/venues/{} - Obteniendo venue", id);
        
        VenueDTO venue = venueService.getVenueById(id);
        
        return ResponseEntity.ok(venue);
    }
    
    /**
     * Obtiene venues por ciudad.
     * 
     * @param city La ciudad a buscar
     * @return ResponseEntity con lista de venues y código 200 OK
     */
    @GetMapping("/city/{city}")
    @Operation(summary = "Obtener venues por ciudad", 
               description = "Retorna todos los venues ubicados en una ciudad específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
    })
    public ResponseEntity<List<VenueDTO>> getVenuesByCity(
            @Parameter(description = "Nombre de la ciudad", required = true)
            @PathVariable String city) {
        
        log.info("GET /api/v1/venues/city/{} - Obteniendo venues por ciudad", city);
        
        List<VenueDTO> venues = venueService.getVenuesByCity(city);
        
        return ResponseEntity.ok(venues);
    }
    
    /**
     * Obtiene venues por país.
     * 
     * @param country El país a buscar
     * @return ResponseEntity con lista de venues y código 200 OK
     */
    @GetMapping("/country/{country}")
    @Operation(summary = "Obtener venues por país", 
               description = "Retorna todos los venues ubicados en un país específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
    })
    public ResponseEntity<List<VenueDTO>> getVenuesByCountry(
            @Parameter(description = "Nombre del país", required = true)
            @PathVariable String country) {
        
        log.info("GET /api/v1/venues/country/{} - Obteniendo venues por país", country);
        
        List<VenueDTO> venues = venueService.getVenuesByCountry(country);
        
        return ResponseEntity.ok(venues);
    }
    
    /**
     * Obtiene venues por estado.
     * 
     * @param status El estado a buscar
     * @return ResponseEntity con lista de venues y código 200 OK
     */
    @GetMapping("/status/{status}")
    @Operation(summary = "Obtener venues por estado", 
               description = "Retorna todos los venues que tienen un estado específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
    })
    public ResponseEntity<List<VenueDTO>> getVenuesByStatus(
            @Parameter(description = "Estado del venue (ACTIVE, INACTIVE, MAINTENANCE)", required = true)
            @PathVariable String status) {
        
        log.info("GET /api/v1/venues/status/{} - Obteniendo venues por estado", status);
        
        List<VenueDTO> venues = venueService.getVenuesByStatus(status);
        
        return ResponseEntity.ok(venues);
    }
    
    /**
     * Actualiza un venue existente.
     * 
     * @param id El ID del venue a actualizar
     * @param venueDTO Los nuevos datos del venue
     * @return ResponseEntity con el venue actualizado y código 200 OK
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un venue", 
               description = "Actualiza la información de un venue existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venue actualizado exitosamente",
                     content = @Content(schema = @Schema(implementation = VenueDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    public ResponseEntity<VenueDTO> updateVenue(
            @Parameter(description = "ID del venue a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody VenueDTO venueDTO) {
        
        log.info("PUT /api/v1/venues/{} - Actualizando venue", id);
        
        VenueDTO updatedVenue = venueService.updateVenue(id, venueDTO);
        
        return ResponseEntity.ok(updatedVenue);
    }
    
    /**
     * Elimina un venue.
     * 
     * @param id El ID del venue a eliminar
     * @return ResponseEntity con código 204 No Content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un venue", 
               description = "Elimina un venue del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Venue eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    public ResponseEntity<Void> deleteVenue(
            @Parameter(description = "ID del venue a eliminar", required = true)
            @PathVariable Long id) {
        
        log.info("DELETE /api/v1/venues/{} - Eliminando venue", id);
        
        venueService.deleteVenue(id);
        
        return ResponseEntity.noContent().build();
    }
}