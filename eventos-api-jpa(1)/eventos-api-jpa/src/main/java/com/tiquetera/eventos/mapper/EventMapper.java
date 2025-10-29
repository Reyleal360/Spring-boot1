package com.tiquetera.eventos.mapper;

import com.tiquetera.eventos.dto.EventDTO;
import com.tiquetera.eventos.entity.EventEntity;

/**
 * Mapper para convertir entre EventEntity y EventDTO
 */
public class EventMapper {
    
    /**
     * Convierte una entidad Event a DTO
     */
    public static EventDTO toDTO(EventEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return EventDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .fechaEvento(entity.getFechaEvento())
                .venueId(entity.getVenue() != null ? entity.getVenue().getId() : null)
                .venueNombre(entity.getVenue() != null ? entity.getVenue().getNombre() : null)
                .categoria(entity.getCategoria())
                .precio(entity.getPrecio())
                .capacidadDisponible(entity.getCapacidadDisponible())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Convierte un DTO Event a entidad (sin venue, se asigna después)
     */
    public static EventEntity toEntity(EventDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return EventEntity.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaEvento(dto.getFechaEvento())
                .categoria(dto.getCategoria())
                .precio(dto.getPrecio())
                .capacidadDisponible(dto.getCapacidadDisponible())
                .build();
    }
    
    /**
     * Actualiza una entidad existente con los datos del DTO
     */
    public static void updateEntityFromDTO(EventDTO dto, EventEntity entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaEvento(dto.getFechaEvento());
        entity.setCategoria(dto.getCategoria());
        entity.setPrecio(dto.getPrecio());
        entity.setCapacidadDisponible(dto.getCapacidadDisponible());
    }
}