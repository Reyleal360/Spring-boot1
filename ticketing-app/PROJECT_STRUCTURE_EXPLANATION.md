# Estructura del Proyecto Ticketing App

Este documento detalla la arquitectura y organización del código del proyecto `ticketing-app`. El sistema sigue una arquitectura de **Microservicios** y utiliza **Arquitectura Hexagonal (Puertos y Adaptadores)** en el backend para asegurar un código limpio, mantenible y desacoplado.

## 1. Estructura de Alto Nivel

El proyecto se divide en dos grandes áreas:

*   **`frontend/`**: Contiene la aplicación cliente (Interfaz de Usuario). Es lo que el usuario final ve e interactúa en su navegador.
*   **`backend/`**: Contiene la lógica del servidor, dividida en microservicios independientes:
    *   **`events-service/`**: Microservicio encargado de la gestión de eventos (conciertos, obras, partidos, etc.).
    *   **`venues-service/`**: Microservicio encargado de la gestión de los recintos o lugares donde ocurren los eventos.

---

## 2. Arquitectura Hexagonal (Backend)

Cada microservicio (como `events-service`) está organizado internamente siguiendo la Arquitectura Hexagonal. El objetivo es aislar la lógica de negocio (Dominio) de los detalles técnicos (Infraestructura).

### 🟢 Domain (El Núcleo)
Ubicación: `src/main/java/com/tiquetera/events/domain`

Es el corazón de la aplicación. **No depende de nada externo** (ni de Spring Boot, ni de bases de datos, ni de librerías web). Aquí residen las reglas de negocio puras.

*   **`model/`**: Contiene las **Entidades de Dominio** (ej. `Event.java`). Son objetos simples (POJOs) que representan los conceptos del negocio.
*   **`ports/`**: Define los "enchufes" o contratos de la aplicación.
    *   **Input Ports**: Interfaces que definen qué casos de uso puede ejecutar la aplicación (ej. `CreateEventUseCase`).
    *   **Output Ports**: Interfaces que definen qué necesita la aplicación del mundo exterior (ej. `EventRepositoryPort` para guardar datos).

### 🟡 Application (La Orquestación)
Ubicación: `src/main/java/com/tiquetera/events/application`

Esta capa conecta el mundo exterior con el dominio. Orquesta el flujo de datos.

*   **`usecase/`**: Implementaciones concretas de la lógica de negocio (ej. `CreateEventService`, `ListEventsService`).
    *   Estos servicios implementan los *Input Ports*.
    *   Utilizan los *Output Ports* para interactuar con la base de datos u otros sistemas, sin saber los detalles técnicos de cómo se hace.

### 🔴 Infrastructure (Los Detalles Técnicos)
Ubicación: `src/main/java/com/tiquetera/events/infrastructure`

Aquí viven los frameworks, controladores web, configuraciones de base de datos y librerías externas. Son los "adaptadores" que se conectan a los puertos del dominio.

*   **`adapter/in/web/` (Adaptadores de Entrada)**:
    *   Contiene los **Controladores REST** (ej. `EventController`).
    *   Recibe peticiones HTTP (JSON), las valida y llama a los Casos de Uso de la capa de Aplicación.
    *   Usa **Mappers** para convertir DTOs (Data Transfer Objects) a modelos de dominio.

*   **`adapter/out/persistence/` (Adaptadores de Salida)**:
    *   Implementación real del acceso a datos.
    *   **`EventJpaAdapter`**: Implementa la interfaz `EventRepositoryPort` definida en el dominio. Usa JPA/Hibernate para hablar con la base de datos.
    *   **`mapper/`**: Convierte entre las entidades de dominio (`Event`) y las entidades de base de datos (`EventEntity`).

---

## Resumen del Flujo de Datos

1.  **Petición**: El usuario envía una petición HTTP (ej. `POST /events`).
2.  **Infraestructura (Web)**: El `EventController` recibe la petición.
3.  **Aplicación**: El controlador llama al servicio `CreateEventService`.
4.  **Dominio**: El servicio ejecuta la lógica de negocio.
5.  **Aplicación**: El servicio pide guardar el evento usando el puerto `EventRepositoryPort`.
6.  **Infraestructura (Persistencia)**: El `EventJpaAdapter` intercepta la llamada, convierte el modelo de dominio a una entidad de base de datos y lo guarda físicamente usando JPA.
