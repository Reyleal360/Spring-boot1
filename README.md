# Sistema de Gestión de Eventos y Venues (Ticketing App)

Este proyecto es una aplicación backend construida con **Spring Boot** siguiendo una **Arquitectura Hexagonal (Ports & Adapters)**. Su objetivo es gestionar eventos y lugares (venues) de manera escalable, mantenible y segura, permitiendo la administración de catálogos, ventas y control de acceso.

## Arquitectura

El proyecto sigue estrictamente el patrón de **Arquitectura Hexagonal**, lo que permite desacoplar la lógica de negocio de los detalles de infraestructura (base de datos, controladores web, seguridad).

```mermaid
graph TD
    subgraph Infrastructure
        WebAdapter[Web Adapter (REST Controllers)]
        JPAAdapter[JPA Adapter (Repositories)]
        SecurityAdapter[Security Adapter (JWT/Auth)]
    end

    subgraph Application
        UseCase[Use Cases (Service Implementation)]
    end

    subgraph Domain
        Model[Domain Models]
        InputPort[Input Ports (Interfaces)]
        OutputPort[Output Ports (Interfaces)]
    end

    WebAdapter --> InputPort
    UseCase --> OutputPort
    UseCase ..|> InputPort
    JPAAdapter ..|> OutputPort
    SecurityAdapter --> WebAdapter
```

### Capas del Sistema

1.  **Dominio (Domain)**:
    *   Es el núcleo del sistema.
    *   Contiene las entidades (`Event`, `Venue`, `User`), excepciones de negocio y las interfaces de los puertos (Input/Output).
    *   No tiene dependencias de frameworks externos como Spring o Hibernate.

2.  **Aplicación (Application)**:
    *   Contiene la implementación de los casos de uso (Servicios).
    *   Orquesta la lógica de negocio utilizando los puertos definidos en el dominio.
    *   Ejemplo: `CreateEventService`, `GetVenueService`.

3.  **Infraestructura (Infrastructure)**:
    *   Implementa los adaptadores que interactúan con el mundo exterior.
    *   **Entrada**: Controladores REST (`EventController`, `AuthController`).
    *   **Salida**: Persistencia JPA (`EventJpaAdapter`), Seguridad (`JwtAuthenticationFilter`), Configuración (`BeanConfiguration`).

## Tecnologías Utilizadas

*   **Java 17**: Lenguaje de programación principal.
*   **Spring Boot 3**: Framework para el desarrollo de la aplicación.
*   **Spring Data JPA**: Abstracción para la persistencia de datos.
*   **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
*   **MySQL**: Base de datos relacional para producción.
*   **Flyway**: Herramienta para el versionado y migración de la base de datos.
*   **MapStruct**: Librería para el mapeo eficiente entre objetos (DTOs <-> Entidades).
*   **Spring Security + JWT**: Gestión de autenticación y autorización mediante Tokens.
*   **Lombok**: Librería para reducir el código repetitivo (Getters, Setters, Builders).
*   **Maven**: Gestor de dependencias y construcción del proyecto.

## Funcionalidades Implementadas (Historias de Usuario)

### HU2: Catálogo Persistente con Validación y Paginación
Implementación de la persistencia de datos y validación de entradas.
*   **Persistencia**: Se configuró JPA para interactuar con bases de datos H2 y MySQL.
*   **Validación**: Se utilizaron anotaciones de `jakarta.validation` (`@NotNull`, `@Size`, `@Future`) en los DTOs para asegurar la integridad de los datos antes de procesarlos.
*   **Código Relevante**:
    *   `EventRepository`: Interfaz que extiende `JpaRepository`.
    *   `EventRequest`: DTO con anotaciones de validación.

### HU3: Refactorización a Arquitectura Hexagonal
Reestructuración completa del proyecto para seguir el patrón de Puertos y Adaptadores.
*   **Desacoplamiento**: Se movieron las clases a los paquetes `domain`, `application` e `infrastructure`.
*   **Puertos**: Creación de interfaces `EventRepositoryPort` (Output) y `CreateEventUseCase` (Input).
*   **Adaptadores**: Implementación de `EventJpaAdapter` para la persistencia.

### HU4: Administración de Eventos y Venues (Relaciones)
Gestión de entidades relacionadas y control de versiones de base de datos.
*   **Relaciones JPA**:
    *   Un `Venue` puede tener múltiples `Event` (`@OneToMany`).
    *   Un `Event` pertenece a un `Venue` (`@ManyToOne`).
*   **Flyway**: Se añadieron scripts SQL en `src/main/resources/db/migration` para crear y modificar tablas de forma controlada (`V1__init.sql`, `V2__add_venues.sql`).

### HU5: Gestión de Errores y Seguridad
Mejora de la robustez y seguridad de la API.
*   **Manejo Global de Errores**: Implementación de `GlobalExceptionHandler` que retorna respuestas con formato **ProblemDetail** (RFC 7807).
*   **Seguridad JWT**:
    *   `JwtService`: Generación y validación de tokens.
    *   `SecurityConfig`: Configuración de filtros y reglas de acceso.
    *   Roles: Diferenciación entre usuarios `ADMIN` y `USER`.

## Configuración y Ejecución

### Prerrequisitos
*   Java 17 o superior
*   Maven 3.8+

### Pasos para ejecutar
1.  Clonar el repositorio.
2.  Configurar las credenciales de base de datos en `src/main/resources/application.yml` (si se usa MySQL).
3.  Ejecutar el comando de construcción y ejecución:
    ```bash
    ./mvnw spring-boot:run
    ```

### Documentación de la API
*   **Swagger UI**: Disponible en `http://localhost:8080/swagger-ui.html` para explorar y probar los endpoints.
*   **H2 Console**: Disponible en `http://localhost:8080/h2-console` para inspeccionar la base de datos en memoria.