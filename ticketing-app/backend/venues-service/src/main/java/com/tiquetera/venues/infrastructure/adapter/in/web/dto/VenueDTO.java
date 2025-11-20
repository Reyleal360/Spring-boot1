package com.tiquetera.venues.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para request/response de venues en la capa REST.
 * 
 * Contiene anotaciones de validación y documentación Swagger.
 * Es parte de la infraestructura, NO del dominio.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para Venues (lugares de eventos)")
public class VenueDTO {

    @Schema(description = "ID único del venue", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del venue es obligatorio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres")
    @Schema(description = "Nombre del venue", example = "Estadio Metropolitano Roberto Meléndez", required = true)
    private String name;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 10, max = 300, message = "La dirección debe tener entre 10 y 300 caracteres")
    @Schema(description = "Dirección completa del venue", example = "Calle 72 # 46-31, Barranquilla, Atlántico", required = true)
    private String address;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(min = 2, max = 100, message = "La ciudad debe tener entre 2 y 100 caracteres")
    @Schema(description = "Ciudad donde se ubica el venue", example = "Barranquilla", required = true)
    private String city;

    @NotBlank(message = "El país es obligatorio")
    @Size(min = 2, max = 100, message = "El país debe tener entre 2 y 100 caracteres")
    @Schema(description = "País donde se ubica el venue", example = "Colombia", required = true)
    private String country;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser un número positivo")
    @Schema(description = "Capacidad máxima del venue", example = "46000", required = true)
    private Integer capacity;

    @Size(max = 500, message = "La descripción no debe exceder 500 caracteres")
    @Schema(description = "Descripción adicional del venue", example = "Estadio principal de Barranquilla, sede de eventos deportivos y conciertos")
    private String description;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "El teléfono debe tener entre 10 y 15 dígitos")
    @Schema(description = "Teléfono de contacto del venue", example = "+573001234567")
    private String phone;

    @Email(message = "El email debe tener un formato válido")
    @Schema(description = "Email de contacto del venue", example = "info@estadiometropolitano.com")
    private String email;

    @Schema(description = "Estado del venue", example = "ACTIVE", allowableValues = { "ACTIVE", "INACTIVE",
            "MAINTENANCE" })
    private String status;

    @Schema(description = "Fecha de creación del registro", example = "2025-01-15T10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última actualización", example = "2025-01-20T15:45:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
