# Historia de Usuario: Catálogo Persistente con Validación y Paginación (Spring Data JPA)

## Objetivo de la HU
Integrar persistencia real mediante Spring Data JPA + H2, agregando validaciones y paginación para mejorar la calidad del servicio REST.

## Tareas

### TASK 1: Persistencia con JPA
1. Crear entidades `EventEntity` y `VenueEntity`.
2. Crear interfaces `EventRepository` y `VenueRepository` (extienden `JpaRepository`).
3. Migrar los servicios para operar sobre base de datos.
4. Validar relaciones y constraints básicos (nombre único de evento).

### TASK 2: Validaciones
- Aplicar `@Valid`, `@NotBlank`, `@Size`, `@Future`, etc.
- Implementar mensajes de error descriptivos en el payload.
- Validar duplicados en nombres de eventos.

### TASK 3: Paginación y Filtros
- `GET /events?page=&size=&sort=` con `Pageable`.
- Filtros opcionales por ciudad, categoría, fechaInicio.

### TASK 4: Manejo básico de errores
- Implementar `@ControllerAdvice` para capturar excepciones.
- Retornar errores 400, 404 y 409 con mensajes claros.

## Criterios de Aceptación
- CRUD persistente con H2.
- Validaciones activas y mensajes legibles.
- Paginación funcional y documentada.
- Errores coherentes y manejados globalmente.

## Story Points: 10

## Cierre de la actividad
La API debe listar y filtrar eventos desde base de datos, con control de errores y paginación adecuada para simular consultas en producción.