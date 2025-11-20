package com.tiquetera.ticketing.infraestructura.adapters.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad de persistencia para Venue.
 * 
 * Esta clase es parte de la infraestructura y puede contener
 * anotaciones JPA, validaciones, etc.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueEntity {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private Integer capacity;
    private String description;
    private String phone;
    private String email;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
