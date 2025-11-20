package com.tiquetera.venues.infrastructure.adapter.in.web.controller;

import com.tiquetera.venues.domain.model.Venue;
import com.tiquetera.venues.domain.ports.in.*;
import com.tiquetera.venues.infrastructure.adapter.in.web.dto.VenueDTO;
import com.tiquetera.venues.infrastructure.adapter.in.web.mapper.VenueWebMapper;
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
 * Adaptador REST para venues (Input Adapter).
 * 
 * Implementa los endpoints REST y los conecta con los casos de uso.
 * Reemplaza el antiguo VenueController.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
@Tag(name = "Venues", description = "API para gestión de lugares de eventos")
public class VenueRestAdapter {

        private final CreateVenueUseCase createVenueUseCase;
        private final GetVenueUseCase getVenueUseCase;
        private final ListVenuesUseCase listVenuesUseCase;
        private final UpdateVenueUseCase updateVenueUseCase;
        private final DeleteVenueUseCase deleteVenueUseCase;
        private final VenueWebMapper mapper;

        @PostMapping
        @Operation(summary = "Crear un nuevo venue", description = "Crea un nuevo lugar de eventos en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Venue creado exitosamente", content = @Content(schema = @Schema(implementation = VenueDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
        })
        public ResponseEntity<VenueDTO> createVenue(@Valid @RequestBody VenueDTO venueDTO) {
                log.info("POST /api/v1/venues - Creando venue: {}", venueDTO.getName());

                Venue venue = mapper.toDomain(venueDTO);
                Venue createdVenue = createVenueUseCase.execute(venue);

                return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(createdVenue));
        }

        @GetMapping
        @Operation(summary = "Obtener todos los venues", description = "Retorna una lista con todos los lugares de eventos registrados")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
        })
        public ResponseEntity<List<VenueDTO>> getAllVenues() {
                log.info("GET /api/v1/venues - Obteniendo todos los venues");

                List<Venue> venues = listVenuesUseCase.getAllVenues();

                return ResponseEntity.ok(mapper.toDTOList(venues));
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener venue por ID", description = "Retorna los detalles de un venue específico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venue encontrado", content = @Content(schema = @Schema(implementation = VenueDTO.class))),
                        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
        })
        public ResponseEntity<VenueDTO> getVenueById(
                        @Parameter(description = "ID del venue a buscar", required = true) @PathVariable Long id) {

                log.info("GET /api/v1/venues/{} - Obteniendo venue", id);

                Venue venue = getVenueUseCase.execute(id);

                return ResponseEntity.ok(mapper.toDTO(venue));
        }

        @GetMapping("/city/{city}")
        @Operation(summary = "Obtener venues por ciudad", description = "Retorna todos los venues ubicados en una ciudad específica")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
        })
        public ResponseEntity<List<VenueDTO>> getVenuesByCity(
                        @Parameter(description = "Nombre de la ciudad", required = true) @PathVariable String city) {

                log.info("GET /api/v1/venues/city/{} - Obteniendo venues por ciudad", city);

                List<Venue> venues = listVenuesUseCase.getVenuesByCity(city);

                return ResponseEntity.ok(mapper.toDTOList(venues));
        }

        @GetMapping("/country/{country}")
        @Operation(summary = "Obtener venues por país", description = "Retorna todos los venues ubicados en un país específico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
        })
        public ResponseEntity<List<VenueDTO>> getVenuesByCountry(
                        @Parameter(description = "Nombre del país", required = true) @PathVariable String country) {

                log.info("GET /api/v1/venues/country/{} - Obteniendo venues por país", country);

                List<Venue> venues = listVenuesUseCase.getVenuesByCountry(country);

                return ResponseEntity.ok(mapper.toDTOList(venues));
        }

        @GetMapping("/status/{status}")
        @Operation(summary = "Obtener venues por estado", description = "Retorna todos los venues que tienen un estado específico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de venues obtenida exitosamente")
        })
        public ResponseEntity<List<VenueDTO>> getVenuesByStatus(
                        @Parameter(description = "Estado del venue (ACTIVE, INACTIVE, MAINTENANCE)", required = true) @PathVariable String status) {

                log.info("GET /api/v1/venues/status/{} - Obteniendo venues por estado", status);

                List<Venue> venues = listVenuesUseCase.getVenuesByStatus(status);

                return ResponseEntity.ok(mapper.toDTOList(venues));
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar un venue", description = "Actualiza la información de un venue existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venue actualizado exitosamente", content = @Content(schema = @Schema(implementation = VenueDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
        })
        public ResponseEntity<VenueDTO> updateVenue(
                        @Parameter(description = "ID del venue a actualizar", required = true) @PathVariable Long id,
                        @Valid @RequestBody VenueDTO venueDTO) {

                log.info("PUT /api/v1/venues/{} - Actualizando venue", id);

                Venue venue = mapper.toDomain(venueDTO);
                Venue updatedVenue = updateVenueUseCase.execute(id, venue);

                return ResponseEntity.ok(mapper.toDTO(updatedVenue));
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar un venue", description = "Elimina un venue del sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Venue eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
        })
        public ResponseEntity<Void> deleteVenue(
                        @Parameter(description = "ID del venue a eliminar", required = true) @PathVariable Long id) {

                log.info("DELETE /api/v1/venues/{} - Eliminando venue", id);

                deleteVenueUseCase.execute(id);

                return ResponseEntity.noContent().build();
        }
}
