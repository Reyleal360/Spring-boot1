package com.tiquetera.events.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad espejo de Venue para el microservicio de Eventos.
 * Permite relaciones JPA @ManyToOne desde EventEntity.
 * Mapea a la misma tabla 'venues'.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venues")
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "capacity")
    private Integer capacity;

    // Relación inversa para navegación (opcional pero solicitada)
    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    private List<EventEntity> events;
}
