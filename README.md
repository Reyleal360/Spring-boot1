# HU3: Hexagonal Architecture Refactor

This branch introduces the **Hexagonal Architecture (Ports & Adapters)** to decouple business logic from infrastructure, improving code maintainability and testability.

## Architecture Diagram

```mermaid
graph TD
    subgraph Infrastructure
        WebAdapter[Web Adapter (REST)]
        JPAAdapter[JPA Adapter (Database)]
    end

    subgraph Application
        UseCase[Use Cases]
    end

    subgraph Domain
        Model[Domain Model]
        InputPort[Input Ports]
        OutputPort[Output Ports]
    end

    WebAdapter --> InputPort
    UseCase --> OutputPort
    UseCase ..|> InputPort
    JPAAdapter ..|> OutputPort
```

## Project Structure

The code has been reorganized into three main layers:

1.  **Domain (`domain`)**:
    *   Contains entities (`Event`) and port interfaces.
    *   **Input Ports**: Interfaces defining use cases (e.g., `CreateEventUseCase`).
    *   **Output Ports**: Interfaces defining data access (e.g., `EventRepositoryPort`).

2.  **Application (`application`)**:
    *   Implements the use cases.
    *   Example: `CreateEventService` implements `CreateEventUseCase` and uses `EventRepositoryPort`.

3.  **Infrastructure (`infrastructure`)**:
    *   Implements the adapters.
    *   **JPA Adapter**: `EventJpaAdapter` implements `EventRepositoryPort` using Spring Data JPA.
    *   **Web Adapter**: `EventController` uses `CreateEventUseCase`.

## Relevant Code

**Output Port (`domain/ports/out/EventRepositoryPort.java`)**:
```java
public interface EventRepositoryPort {
    Event save(Event event);
    Optional<Event> findById(Long id);
    // ...
}
```

**JPA Adapter (`infrastructure/adapter/out/jpa/EventJpaAdapter.java`)**:
```java
@Component
@RequiredArgsConstructor
public class EventJpaAdapter implements EventRepositoryPort {
    private final EventRepository eventRepository;
    private final EventPersistenceMapper mapper;

    @Override
    public Event save(Event event) {
        // Mapping Domain to JPA Entity and saving
        return mapper.toDomain(eventRepository.save(mapper.toEntity(event)));
    }
}
```

## Benefits
*   **Framework Independence**: The domain does not depend on Spring or JPA.
*   **Testability**: Easy to test the domain using Mocks for the ports.
*   **Flexibility**: Adapters can be changed (e.g., switching databases) without touching the domain.