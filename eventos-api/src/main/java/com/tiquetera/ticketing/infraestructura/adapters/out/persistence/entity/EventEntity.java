package com.tiquetera.ticketing.infraestructura.adapters.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad de persistencia para Event.
 * 
 * Esta clase es parte de la infraestructura y puede contener
 * anotaciones JPA, validaciones, etc.
 * 
 * Por ahora usa la estructura del DTO original para mantener
 * compatibilidad con el repositorio in-memory.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private Long venueId;
    private String venueName;
    private Integer capacity;
    private BigDecimal ticketPrice;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
