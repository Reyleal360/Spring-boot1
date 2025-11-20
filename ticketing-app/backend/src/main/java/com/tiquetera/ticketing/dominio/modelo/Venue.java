package com.tiquetera.ticketing.dominio.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad de dominio Venue (Lugar de eventos).
 * 
 * Esta es una clase POJO pura sin dependencias de frameworks.
 * No tiene anotaciones de JPA, Spring, ni validación.
 * Representa el concepto de negocio de un venue en el dominio.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class Venue {

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

    // Constructor vacío
    public Venue() {
    }

    // Constructor completo
    public Venue(Long id, String name, String address, String city, String country,
            Integer capacity, String description, String phone, String email,
            String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.capacity = capacity;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Métodos de lógica de negocio

    /**
     * Verifica si el venue está activo.
     */
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(this.status);
    }

    /**
     * Verifica si el venue está en mantenimiento.
     */
    public boolean isUnderMaintenance() {
        return "MAINTENANCE".equalsIgnoreCase(this.status);
    }

    /**
     * Verifica si el venue puede recibir eventos.
     */
    public boolean canHostEvents() {
        return isActive();
    }

    /**
     * Pone el venue en mantenimiento.
     */
    public void putInMaintenance() {
        if (!isActive()) {
            throw new IllegalStateException("Solo venues activos pueden entrar en mantenimiento");
        }
        this.status = "MAINTENANCE";
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Activa el venue.
     */
    public void activate() {
        this.status = "ACTIVE";
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Desactiva el venue.
     */
    public void deactivate() {
        this.status = "INACTIVE";
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Venue venue = (Venue) o;
        return Objects.equals(id, venue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
