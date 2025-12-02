package com.tiquetera.events.infrastructure.exception;

import com.tiquetera.events.domain.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manejador global de excepciones para la API.
 * 
 * Centraliza el manejo de errores, interceptando excepciones del dominio
 * y convirtiéndolas en respuestas HTTP apropiadas usando ProblemDetail (RFC
 * 7807).
 * 
 * @author Ticketing Team
 * @version 3.0 - HU5 Error Handling
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String TRACE_ID = "traceId";
    private static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {

        log.error("Recurso no encontrado: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("https://tiquetera.com/errors/not-found"));
        problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        enrichProblemDetail(problemDetail);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        log.error("Error de validación: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            if (error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            } else {
                fieldName = error.getObjectName();
            }
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Error de validación en los datos enviados");
        problemDetail.setTitle("Validation Error");
        problemDetail.setType(URI.create("https://tiquetera.com/errors/validation-error"));
        problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        problemDetail.setProperty("errors", errors);
        enrichProblemDetail(problemDetail);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            WebRequest request) {

        log.error("Error de integridad de datos: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                "Conflicto de integridad de datos");
        problemDetail.setTitle("Data Integrity Violation");
        problemDetail.setType(URI.create("https://tiquetera.com/errors/conflict"));
        problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        enrichProblemDetail(problemDetail);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGlobalException(
            Exception ex,
            WebRequest request) {

        log.error("Error interno del servidor: ", ex);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ha ocurrido un error interno en el servidor");
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(URI.create("https://tiquetera.com/errors/internal-server-error"));
        problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        enrichProblemDetail(problemDetail);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

    private void enrichProblemDetail(ProblemDetail problemDetail) {
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        String traceId = MDC.get(TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
        }
        problemDetail.setProperty(TRACE_ID, traceId);
    }
}
