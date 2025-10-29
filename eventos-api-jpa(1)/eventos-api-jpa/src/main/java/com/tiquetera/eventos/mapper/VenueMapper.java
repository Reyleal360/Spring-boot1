package com.tiquetera.eventos.mapper;

import com.tiquetera.eventos.dto.VenueDTO;
import com.tiquetera.eventos.entity.VenueEntity;

/**
 * Mapper para convertir entre VenueEntity y VenueDTO
 */
public class VenueMapper {
    
    /**
     * Convierte una entidad Venue a DTO
     */
    public static VenueDTO toDTO(VenueEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return VenueDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .direccion(entity.getDireccion())
                .ciudad(entity.getCiudad())
                .pais(entity.getPais())
                .capacidad(entity.getCapacidad())
                .tipo(entity.getTipo())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    /**
     * Convierte un DTO Venue a entidad
     */
    public static VenueEntity toEntity(VenueDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return VenueEntity.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .direccion(dto.getDireccion())
                .ciudad(dto.getCiudad())
                .pais(dto.getPais())
                .capacidad(dto.getCapacidad())
                .tipo(dto.getTipo())
                .build();
    }
    
    /**
     * Actualiza una entidad existente con los datos del DTO
     */
    public static void updateEntityFromDTO(VenueDTO dto, VenueEntity entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setDireccion(dto.getDireccion());
        entity.setCiudad(dto.getCiudad());
        entity.setPais(dto.getPais());
        entity.setCapacidad(dto.getCapacidad());
        entity.setTipo(dto.getTipo());
    }
}