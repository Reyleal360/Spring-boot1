package com.tiquetera.events.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad de dominio Event (Evento).
 * 
 * Esta es una clase POJO pura sin dependencias de frameworks.
 * No tiene anotaciones de JPA, Spring, ni validación.
 * Representa el concepto de negocio de un evento en el dominio.
 * 
 * @author Ticketing Team
 * @version 3.0 - HU5 Error Handling
 */
public class Event {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private LocalDateTime endDate;
    private Long venueId;
    private String venueName;
    private Integer capacity;
    private BigDecimal ticketPrice;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor vacío
    public Event() {
    }

    // Constructor completo
    public Event(Long id, String name, String description, LocalDateTime eventDate, LocalDateTime endDate,
            Long venueId, String venueName, Integer capacity, BigDecimal ticketPrice,
            String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.endDate = endDate;
        this.venueId = venueId;
        this.venueName = venueName;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Métodos de lógica de negocio

    /**
     * Verifica si el evento está activo.
     */
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(this.status);
    }

    /**
     * Verifica si el evento es futuro.
     */
    public boolean isUpcoming() {
        return this.eventDate != null && this.eventDate.isAfter(LocalDateTime.now());
    }

    /**
     * Verifica si el evento puede ser cancelado.
     * Solo eventos activos y futuros pueden ser cancelados.
     */
    public boolean canBeCancelled() {
        return isActive() && isUpcoming();
    }

    /**
     * Cancela el evento si es posible.
     */
    public void cancel() {
        if (!canBeCancelled()) {
            throw new IllegalStateException("El evento no puede ser cancelado");
        }
        this.status = "CANCELLED";
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Completa el evento.
     */
    public void complete() {
        if (!isActive()) {
            throw new IllegalStateException("Solo eventos activos pueden ser completados");
        }
        this.status = "COMPLETED";
        this.updatedAt = LocalDateTime.now();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // equals y hashCode basados en el ID

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventDate=" + eventDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
