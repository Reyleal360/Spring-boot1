package com.tiquetera.events.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad de persistencia para Event - Microservicio Events
 * 
 * Representa la tabla 'events'.
 * En arquitectura de microservicios, la relación con Venue es lógica (por ID)
 * y no física (FK a nivel de BD entre servicios distintos).
 * 
 * @author Ticketing Team
 * @version 3.0 - Microservices Architecture
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "venue_id", nullable = false)
    private Long venueId;

    // Campo desnormalizado para evitar consultas constantes al otro microservicio
    @Column(name = "venue_name", length = 200)
    private String venueName;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "ticket_price", precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    @Column(name = "status", length = 20)
    @Builder.Default
    private String status = "SCHEDULED";

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
