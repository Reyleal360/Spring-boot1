# HU3: Refactorización a Arquitectura Hexagonal

Esta rama introduce la **Arquitectura Hexagonal (Ports & Adapters)** para desacoplar la lógica de negocio de la infraestructura, mejorando la mantenibilidad y testabilidad del código.

## Diagrama de Arquitectura

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

## Estructura del Proyecto

El código se ha reorganizado en tres capas principales:

1.  **Domain (`domain`)**:
    *   Contiene las entidades (`Event`) y las interfaces de los puertos.
    *   **Input Ports**: Interfaces que definen los casos de uso (ej. `CreateEventUseCase`).
    *   **Output Ports**: Interfaces que definen el acceso a datos (ej. `EventRepositoryPort`).

2.  **Application (`application`)**:
    *   Implementa los casos de uso.
    *   Ejemplo: `CreateEventService` implementa `CreateEventUseCase` y utiliza `EventRepositoryPort`.

3.  **Infrastructure (`infrastructure`)**:
    *   Implementa los adaptadores.
    *   **Adapter JPA**: `EventJpaAdapter` implementa `EventRepositoryPort` usando Spring Data JPA.
    *   **Adapter Web**: `EventController` utiliza `CreateEventUseCase`.

## Código Relevante

**Puerto de Salida (`domain/ports/out/EventRepositoryPort.java`)**:
```java
public interface EventRepositoryPort {
    Event save(Event event);
    Optional<Event> findById(Long id);
    // ...
}
```

**Adaptador JPA (`infrastructure/adapter/out/jpa/EventJpaAdapter.java`)**:
```java
@Component
@RequiredArgsConstructor
public class EventJpaAdapter implements EventRepositoryPort {
    private final EventRepository eventRepository;
    private final EventPersistenceMapper mapper;

    @Override
    public Event save(Event event) {
        // Mapeo de Dominio a Entidad JPA y guardado
        return mapper.toDomain(eventRepository.save(mapper.toEntity(event)));
    }
}
```

## Beneficios
*   **Independencia de Frameworks**: El dominio no depende de Spring ni de JPA.
*   **Testabilidad**: Es fácil probar el dominio usando Mocks para los puertos.
*   **Flexibilidad**: Se pueden cambiar los adaptadores (ej. cambiar base de datos) sin tocar el dominio.