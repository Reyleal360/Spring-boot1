# Event & Venue Management System (Ticketing App)

This project is a robust backend application built with **Spring Boot**, designed to follow the **Hexagonal Architecture (Ports & Adapters)** pattern. It provides a scalable, maintainable, and secure solution for managing events, venues, and ticket sales.

## 🏗 Architecture

The project strictly adheres to the **Hexagonal Architecture** principles, ensuring a clear separation of concerns between the core business logic and external infrastructure (database, web controllers, security).

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

### System Layers

1.  **Domain**:
    *   The core of the system.
    *   Contains entities (`Event`, `Venue`, `User`), business exceptions, and port interfaces (Input/Output).
    *   **Dependency-free**: No dependencies on external frameworks like Spring or Hibernate.

2.  **Application**:
    *   Implements the use cases (Services).
    *   Orchestrates business logic using the ports defined in the Domain layer.
    *   Examples: `CreateEventService`, `GetVenueService`.

3.  **Infrastructure**:
    *   Implements adapters that interact with the external world.
    *   **Input**: REST Controllers (`EventController`, `AuthController`).
    *   **Output**: JPA Persistence (`EventJpaAdapter`), Security (`JwtAuthenticationFilter`), Configuration (`BeanConfiguration`).

## 🚀 Technologies

*   **Java 17**: Core programming language.
*   **Spring Boot 3**: Application framework.
*   **Spring Data JPA**: Data persistence abstraction.
*   **H2 Database**: In-memory database for development/testing.
*   **MySQL**: Relational database for production.
*   **Flyway**: Database version control and migration.
*   **MapStruct**: Efficient object mapping (DTOs <-> Entities).
*   **Spring Security + JWT**: Stateless authentication and authorization.
*   **Lombok**: Boilerplate code reduction.
*   **Maven**: Dependency management and build tool.

## 📋 Features (User Stories)

### HU2: Persistent Catalog with Validation
*   **Persistence**: JPA integration with H2/MySQL.
*   **Validation**: Robust input validation using `jakarta.validation` (`@NotNull`, `@Size`, `@Future`).

### HU3: Hexagonal Refactor
*   **Decoupling**: Complete separation of Domain, Application, and Infrastructure.
*   **Ports & Adapters**: Implementation of Input/Output ports and their respective adapters.

### HU4: Event & Venue Administration
*   **Relationships**: `Venue` (1) : (N) `Event` relationship management.
*   **Migrations**: Database schema evolution using **Flyway**.

### HU5: Security & Error Handling
*   **Global Error Handling**: Standardized error responses using **RFC 7807 (ProblemDetail)**.
*   **Security**: JWT-based stateless authentication with Role-Based Access Control (RBAC).

## 🛠 Getting Started

### Prerequisites
*   Java 17+
*   Maven 3.8+
*   Docker (Optional, for MySQL)

### Installation & Execution
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/Reyleal360/Spring-boot1.git
    cd Spring-boot1
    ```
2.  **Configure Database**:
    Update `src/main/resources/application.yml` with your database credentials (defaults to H2 in-memory).
3.  **Run the Application**:
    ```bash
    ./mvnw spring-boot:run
    ```

### API Documentation
*   **Swagger UI**: `http://localhost:8080/swagger-ui.html` - Explore and test endpoints.
*   **H2 Console**: `http://localhost:8080/h2-console` - Inspect the in-memory database.

## 🤝 Contributing
Contributions are welcome! Please follow these steps:
1.  Fork the repository.
2.  Create a feature branch (`git checkout -b feature/AmazingFeature`).
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4.  Push to the branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.