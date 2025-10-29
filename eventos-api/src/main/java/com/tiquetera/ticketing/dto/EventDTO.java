package com.tiquetera.ticketing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para representar un Evento.
 * 
 * Responsabilidad única (SRP): Solo transporta datos entre capas.
 * No contiene lógica de negocio.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para Eventos")
public class EventDTO {
    
    @Schema(description = "ID único del evento", example = "1")
    private Long id;
    
    @NotBlank(message = "El nombre del evento es obligatorio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres")
    @Schema(description = "Nombre del evento", example = "Concierto de Rock 2025", required = true)
    private String name;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    @Schema(description = "Descripción detallada del evento", 
            example = "El mejor concierto de rock del año con bandas internacionales", 
            required = true)
    private String description;
    
    @NotNull(message = "La fecha del evento es obligatoria")
    @Future(message = "La fecha del evento debe ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Fecha y hora del evento", 
            example = "2025-12-31T20:00:00", 
            required = true)
    private LocalDateTime eventDate;
    
    @NotNull(message = "El ID del venue es obligatorio")
    @Positive(message = "El ID del venue debe ser un número positivo")
    @Schema(description = "ID del venue donde se realizará el evento", example = "1", required = true)
    private Long venueId;
    
    @NotBlank(message = "El nombre del venue es obligatorio")
    @Schema(description = "Nombre del venue", example = "Estadio Metropolitano")
    private String venueName;
    
    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser un número positivo")
    @Schema(description = "Capacidad total del evento", example = "50000", required = true)
    private Integer capacity;
    
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 enteros y 2 decimales")
    @Schema(description = "Precio del boleto", example = "150000.00", required = true)
    private BigDecimal ticketPrice;
    
    @Schema(description = "Estado del evento", example = "ACTIVE", allowableValues = {"ACTIVE", "CANCELLED", "COMPLETED"})
    private String status;
    
    @Schema(description = "Fecha de creación del registro", example = "2025-01-15T10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de última actualización", example = "2025-01-20T15:45:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}