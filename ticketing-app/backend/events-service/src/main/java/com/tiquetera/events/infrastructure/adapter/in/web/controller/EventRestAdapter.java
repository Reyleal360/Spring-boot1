package com.tiquetera.events.infrastructure.adapter.in.web.controller;

import com.tiquetera.events.domain.model.Event;
import com.tiquetera.events.domain.ports.in.*;
import com.tiquetera.events.infrastructure.adapter.in.web.dto.EventDTO;
import com.tiquetera.events.infrastructure.adapter.in.web.mapper.EventWebMapper;
import com.tiquetera.events.infrastructure.adapter.in.web.validation.group.Create;
import com.tiquetera.events.infrastructure.adapter.in.web.validation.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Adaptador REST para eventos (Input Adapter).
 * 
 * Implementa los endpoints REST y los conecta con los casos de uso.
 * Reemplaza el antiguo EventController.
 * 
 * @author Ticketing Team
 * @version 3.0 - HU5 Error Handling
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "API para gestión de eventos")
public class EventRestAdapter {

        private final CreateEventUseCase createEventUseCase;
        private final GetEventUseCase getEventUseCase;
        private final ListEventsUseCase listEventsUseCase;
        private final UpdateEventUseCase updateEventUseCase;
        private final DeleteEventUseCase deleteEventUseCase;
        private final EventWebMapper mapper;

        @PostMapping
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Crear un nuevo evento", description = "Crea un nuevo evento en el sistema (Solo ADMIN)")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Evento creado exitosamente", content = @Content(schema = @Schema(implementation = EventDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "403", description = "No autorizado")
        })
        public ResponseEntity<EventDTO> createEvent(@Validated(Create.class) @RequestBody EventDTO eventDTO) {
                log.info("POST /api/v1/events - Creando evento: {}", eventDTO.getName());

                Event event = mapper.toDomain(eventDTO);
                Event createdEvent = createEventUseCase.execute(event);

                return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(createdEvent));
        }

        @GetMapping
        @Operation(summary = "Obtener todos los eventos", description = "Retorna una lista con todos los eventos registrados")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de eventos obtenida exitosamente")
        })
        public ResponseEntity<List<EventDTO>> getAllEvents() {
                log.info("GET /api/v1/events - Obteniendo todos los eventos");

                List<Event> events = listEventsUseCase.getAllEvents();

                return ResponseEntity.ok(mapper.toDTOList(events));
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener evento por ID", description = "Retorna los detalles de un evento específico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Evento encontrado", content = @Content(schema = @Schema(implementation = EventDTO.class))),
                        @ApiResponse(responseCode = "404", description = "Evento no encontrado")
        })
        public ResponseEntity<EventDTO> getEventById(
                        @Parameter(description = "ID del evento a buscar", required = true) @PathVariable Long id) {

                log.info("GET /api/v1/events/{} - Obteniendo evento", id);

                Event event = getEventUseCase.execute(id);

                return ResponseEntity.ok(mapper.toDTO(event));
        }

        @GetMapping("/venue/{venueId}")
        @Operation(summary = "Obtener eventos por venue", description = "Retorna todos los eventos de un venue específico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de eventos obtenida exitosamente")
        })
        public ResponseEntity<List<EventDTO>> getEventsByVenueId(
                        @Parameter(description = "ID del venue", required = true) @PathVariable Long venueId) {

                log.info("GET /api/v1/events/venue/{} - Obteniendo eventos por venue", venueId);

                List<Event> events = listEventsUseCase.getEventsByVenueId(venueId);

                return ResponseEntity.ok(mapper.toDTOList(events));
        }

        @GetMapping("/status/{status}")
        @Operation(summary = "Obtener eventos por estado", description = "Retorna todos los eventos que tienen un estado específico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de eventos obtenida exitosamente")
        })
        public ResponseEntity<List<EventDTO>> getEventsByStatus(
                        @Parameter(description = "Estado del evento (ACTIVE, CANCELLED, COMPLETED)", required = true) @PathVariable String status) {

                log.info("GET /api/v1/events/status/{} - Obteniendo eventos por estado", status);

                List<Event> events = listEventsUseCase.getEventsByStatus(status);

                return ResponseEntity.ok(mapper.toDTOList(events));
        }

        @PutMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Actualizar un evento", description = "Actualiza la información de un evento existente (Solo ADMIN)")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Evento actualizado exitosamente", content = @Content(schema = @Schema(implementation = EventDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "404", description = "Evento no encontrado"),
                        @ApiResponse(responseCode = "403", description = "No autorizado")
        })
        public ResponseEntity<EventDTO> updateEvent(
                        @Parameter(description = "ID del evento a actualizar", required = true) @PathVariable Long id,
                        @Validated(Update.class) @RequestBody EventDTO eventDTO) {

                log.info("PUT /api/v1/events/{} - Actualizando evento", id);

                Event event = mapper.toDomain(eventDTO);
                Event updatedEvent = updateEventUseCase.execute(id, event);

                return ResponseEntity.ok(mapper.toDTO(updatedEvent));
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Eliminar un evento", description = "Elimina un evento del sistema (Solo ADMIN)")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Evento eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Evento no encontrado"),
                        @ApiResponse(responseCode = "403", description = "No autorizado")
        })
        public ResponseEntity<Void> deleteEvent(
                        @Parameter(description = "ID del evento a eliminar", required = true) @PathVariable Long id) {

                log.info("DELETE /api/v1/events/{} - Eliminando evento", id);

                deleteEventUseCase.execute(id);

                return ResponseEntity.noContent().build();
        }
}
