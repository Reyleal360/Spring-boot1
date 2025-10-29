package com.tiquetera.eventos.exception;

/**
 * Excepción para cuando se intenta crear un recurso duplicado
 * Resulta en HTTP 409 Conflict
 */
public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s ya existe con %s: '%s'", resourceName, fieldName, fieldValue));
    }
}