package com.tiquetera.venues.domain.exception;

/**
 * Excepción de dominio para recursos no encontrados.
 * 
 * Esta excepción es parte del dominio y no tiene dependencias de frameworks.
 * 
 * @author Ticketing Team
 * @version 2.0 - Hexagonal Architecture
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
