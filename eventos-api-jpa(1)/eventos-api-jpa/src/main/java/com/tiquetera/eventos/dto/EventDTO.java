package com.tiquetera.eventos.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para transferir datos de Event
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para Event")
public class EventDTO {
    
    @Schema(description = "ID único del evento", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @NotBlank(message = "El nombre del evento es obligatorio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres")
    @Schema(description = "Nombre del evento", example = "Concierto de Vallenato", required = true)
    private String nombre;
    
    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    @Schema(description = "Descripción del evento", example = "Gran noche de música vallenata")
    private String descripcion;
    
    @NotNull(message = "La fecha del evento es obligatoria")
    @Future(message = "La fecha del evento debe ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Fecha y hora del evento", example = "2025-12-15T20:00:00", required = true)
    private LocalDateTime fechaEvento;
    
    @NotNull(message = "El ID del venue es obligatorio")
    @Positive(message = "El ID del venue debe ser positivo")
    @Schema(description = "ID del venue", example = "1", required = true)
    private Long venueId;
    
    @Schema(description = "Nombre del venue", accessMode = Schema.AccessMode.READ_ONLY)
    private String venueNombre;
    
    @Size(max = 100, message = "La categoría no puede exceder 100 caracteres")
    @Schema(description = "Categoría del evento", example = "Música")
    private String categoria;
    
    @Positive(message = "El precio debe ser positivo")
    @Schema(description = "Precio del boleto", example = "75000.0")
    private Double precio;
    
    @PositiveOrZero(message = "La capacidad disponible no puede ser negativa")
    @Schema(description = "Capacidad disponible", example = "1400")
    private Integer capacidadDisponible;
    
    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de última actualización", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}