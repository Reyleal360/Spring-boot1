package com.tiquetera.eventos.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.tiquetera.eventos.dto.EventDTO;
import com.tiquetera.eventos.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para Events con paginación y filtros
 */
@Slf4j
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "API para gestión de eventos")
public class EventController {
    
    private final EventService eventService;
    
    /**
     * Crear un nuevo evento
     */
    @PostMapping
    @Operation(summary = "Crear evento", description = "Crea un nuevo evento en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Evento creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado"),
        @ApiResponse(responseCode = "409", description = "Ya existe un evento con ese nombre")
    })
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        log.info("POST /api/events - Creando evento: {}", eventDTO.getNombre());
        EventDTO created = eventService.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    /**
     * Obtener todos los eventos con paginación y filtros
     */
    @GetMapping
    @Operation(summary = "Obtener eventos", 
               description = "Retorna una lista paginada de eventos con opciones de filtrado por ciudad, categoría y fecha")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<Page<EventDTO>> getAllEvents(
            @Parameter(description = "Número de página (inicia en 0)")
            @RequestParam(defaultValue = "0") int page,
            
            @Parameter(description = "Tamaño de página")
            @RequestParam(defaultValue = "10") int size,
            
            @Parameter(description = "Campo para ordenar (id, nombre, fechaEvento, precio)")
            @RequestParam(defaultValue = "id") String sort,
            
            @Parameter(description = "Dirección de ordenamiento (asc/desc)")
            @RequestParam(defaultValue = "asc") String direction,
            
            @Parameter(description = "Filtrar por ciudad del venue")
            @RequestParam(required = false) String ciudad,
            
            @Parameter(description = "Filtrar por categoría")
            @RequestParam(required = false) String categoria,
            
            @Parameter(description = "Filtrar eventos desde esta fecha (formato: yyyy-MM-dd'T'HH:mm:ss)")
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
            LocalDateTime fechaInicio) {
        
        log.info("GET /api/events - page={}, size={}, ciudad={}, categoria={}, fechaInicio={}", 
                 page, size, ciudad, categoria, fechaInicio);
        
        Sort sortOrder = direction.equalsIgnoreCase("desc") 
                ? Sort.by(sort).descending() 
                : Sort.by(sort).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        
        Page<EventDTO> events;
        
        // Aplicar filtros si existen
        if (ciudad != null || categoria != null || fechaInicio != null) {
            events = eventService.getEventsByFilters(ciudad, categoria, fechaInicio, pageable);
        } else {
            events = eventService.getAllEvents(pageable);
        }
        
        return ResponseEntity.ok(events);
    }
    
    /**
     * Obtener un evento por ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por ID", description = "Retorna un evento específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evento encontrado"),
        @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        log.info("GET /api/events/{}", id);
        EventDTO event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
    
    /**
     * Obtener eventos por venue
     */
    @GetMapping("/venue/{venueId}")
    @Operation(summary = "Obtener eventos por venue", 
               description = "Retorna todos los eventos de un venue específico con paginación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    })
    public ResponseEntity<Page<EventDTO>> getEventsByVenue(
            @PathVariable Long venueId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fechaEvento") String sort) {
        
        log.info("GET /api/events/venue/{} - page={}, size={}", venueId, page, size);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<EventDTO> events = eventService.getEventsByVenueId(venueId, pageable);
        
        return ResponseEntity.ok(events);
    }
    
    /**
     * Actualizar un evento
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evento", description = "Actualiza los datos de un evento existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evento actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Evento o Venue no encontrado"),
        @ApiResponse(responseCode = "409", description = "Ya existe un evento con ese nombre")
    })
    public ResponseEntity<EventDTO> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventDTO eventDTO) {
        
        log.info("PUT /api/events/{}", id);
        EventDTO updated = eventService.updateEvent(id, eventDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Eliminar un evento
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evento", description = "Elimina un evento de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Evento eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.info("DELETE /api/events/{}", id);
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}