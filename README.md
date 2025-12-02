# HU2: Catálogo Persistente con Validación y Paginación

Esta rama implementa la persistencia de datos para los eventos y la validación de los datos de entrada, asegurando que la información almacenada sea consistente y fiable.

## Tecnologías Clave

*   **Spring Data JPA**: Para la interacción con la base de datos.
*   **H2 Database**: Base de datos en memoria utilizada para desarrollo rápido.
*   **Jakarta Validation**: Para validar los campos de las peticiones REST.

## Implementación Detallada

### 1. Persistencia (JPA)
Se ha creado la entidad `Event` y su repositorio correspondiente.

**Entidad `Event` (`domain/model/Event.java`)**:
Representa la tabla `events` en la base de datos.
```java
@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    // ... otros campos
}
```

**Repositorio `EventRepository` (`infrastructure/adapter/out/jpa/EventRepository.java`)**:
Interfaz que extiende `JpaRepository` para obtener operaciones CRUD automáticas.
```java
public interface EventRepository extends JpaRepository<Event, Long> {
    // Métodos de consulta personalizados pueden ir aquí
}
```

### 2. Validación
Se utilizan anotaciones estándar para validar los DTOs de entrada (`EventRequest`).

*   `@NotNull`: El campo no puede ser nulo.
*   `@NotBlank`: El campo no puede estar vacío.
*   `@Size(min, max)`: Restringe la longitud de cadenas.
*   `@Future`: Asegura que la fecha del evento sea futura.

**Ejemplo de uso en Controlador**:
```java
@PostMapping
public ResponseEntity<EventResponse> createEvent(@RequestBody @Validated EventRequest request) {
    // ... lógica de creación
}
```

### 3. Paginación
Aunque la implementación completa de paginación robusta está en progreso, el repositorio soporta `Pageable` para consultas paginadas futuras.

## Ejecución
1.  Ejecutar `./mvnw spring-boot:run`.
2.  Acceder a la consola H2 en `http://localhost:8080/h2-console`.
    *   JDBC URL: `jdbc:h2:mem:testdb`
    *   User: `sa`
    *   Password: (vacío)