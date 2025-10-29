package com.tiquetera.eventos.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para transferir datos de Venue
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para Venue")
public class VenueDTO {
    
    @Schema(description = "ID único del venue", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @NotBlank(message = "El nombre del venue es obligatorio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres")
    @Schema(description = "Nombre del venue", example = "Teatro Amira de la Rosa", required = true)
    private String nombre;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Schema(description = "Descripción del venue", example = "Teatro principal de la ciudad")
    private String descripcion;
    
    @Size(max = 300, message = "La dirección no puede exceder 300 caracteres")
    @Schema(description = "Dirección del venue", example = "Calle 76 #59-114")
    private String direccion;
    
    @Size(max = 100, message = "La ciudad no puede exceder 100 caracteres")
    @Schema(description = "Ciudad", example = "Barranquilla")
    private String ciudad;
    
    @Size(max = 100, message = "El país no puede exceder 100 caracteres")
    @Schema(description = "País", example = "Colombia")
    private String pais;
    
    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser positiva")
    @Schema(description = "Capacidad total", example = "1500", required = true)
    private Integer capacidad;
    
    @Size(max = 50, message = "El tipo no puede exceder 50 caracteres")
    @Schema(description = "Tipo de venue", example = "Teatro")
    private String tipo;
    
    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de última actualización", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}