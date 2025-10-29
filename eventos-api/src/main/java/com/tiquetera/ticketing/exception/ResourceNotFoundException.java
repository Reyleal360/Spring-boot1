package com.tiquetera.ticketing.exception;


/**
 * Excepción personalizada para recursos no encontrados.
 * 
 * Se lanza cuando se intenta acceder a un recurso (Evento, Venue) que no existe.
 * Esta excepción es manejada globalmente por el GlobalExceptionHandler.
 * 
 * @author Ticketing Team
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructor con mensaje personalizado.
     * 
     * @param message El mensaje de error
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructor con mensaje y causa.
     * 
     * @param message El mensaje de error
     * @param cause La causa de la excepción
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}