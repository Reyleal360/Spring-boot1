# HU2: Persistent Catalog with Validation and Pagination

This branch implements data persistence for events and input validation, ensuring that stored information is consistent and reliable.

## Key Technologies

*   **Spring Data JPA**: For database interaction.
*   **H2 Database**: In-memory database used for rapid development.
*   **Jakarta Validation**: To validate REST request fields.

## Detailed Implementation

### 1. Persistence (JPA)
The `Event` entity and its corresponding repository have been created.

**Entity `Event` (`domain/model/Event.java`)**:
Represents the `events` table in the database.
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
    // ... other fields
}
```

**Repository `EventRepository` (`infrastructure/adapter/out/jpa/EventRepository.java`)**:
Interface extending `JpaRepository` to provide automatic CRUD operations.
```java
public interface EventRepository extends JpaRepository<Event, Long> {
    // Custom query methods can go here
}
```

### 2. Validation
Standard annotations are used to validate input DTOs (`EventRequest`).

*   `@NotNull`: The field cannot be null.
*   `@NotBlank`: The field cannot be empty.
*   `@Size(min, max)`: Restricts string length.
*   `@Future`: Ensures the event date is in the future.

**Usage Example in Controller**:
```java
@PostMapping
public ResponseEntity<EventResponse> createEvent(@RequestBody @Validated EventRequest request) {
    // ... creation logic
}
```

### 3. Pagination
Although robust pagination implementation is in progress, the repository supports `Pageable` for future paginated queries.

## Execution
1.  Run `./mvnw spring-boot:run`.
2.  Access the H2 console at `http://localhost:8080/h2-console`.
    *   JDBC URL: `jdbc:h2:mem:testdb`
    *   User: `sa`
    *   Password: (empty)