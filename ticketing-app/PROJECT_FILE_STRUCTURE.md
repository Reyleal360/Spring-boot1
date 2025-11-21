# Esquema de Archivos del Proyecto

Este documento describe la estructura de carpetas y la función de cada archivo en los microservicios del backend (`events-service` y `venues-service`). Ambos servicios comparten la misma estructura basada en **Arquitectura Hexagonal**.

## Estructura de `events-service`

```
events-service/src/main/java/com/tiquetera/events
├── EventsServiceApplication.java           # Punto de entrada de la aplicación Spring Boot.
├── application                             # Capa de APLICACIÓN (Orquestación)
│   └── usecase                             # Implementación de la lógica de negocio (Casos de Uso)
│       ├── CreateEventService.java         # Lógica para crear un evento.
│       ├── DeleteEventService.java         # Lógica para eliminar un evento.
│       ├── GetEventService.java            # Lógica para obtener un evento por ID.
│       ├── ListEventsService.java          # Lógica para listar y filtrar eventos.
│       └── UpdateEventService.java         # Lógica para actualizar un evento existente.
├── domain                                  # Capa de DOMINIO (Núcleo del Negocio)
│   ├── exception
│   │   └── ResourceNotFoundException.java  # Excepción de negocio cuando no se encuentra algo.
│   ├── model
│   │   └── Event.java                      # Entidad pura del negocio. Define qué es un Evento.
│   └── ports                               # Puertos (Interfaces/Contratos)
│       ├── in                              # Puertos de Entrada (Qué puede hacer la app)
│       │   ├── CreateEventUseCase.java     # Interfaz para crear eventos.
│       │   ├── DeleteEventUseCase.java     # Interfaz para eliminar eventos.
│       │   ├── GetEventUseCase.java        # Interfaz para obtener eventos.
│       │   ├── ListEventsUseCase.java      # Interfaz para listar eventos.
│       │   └── UpdateEventUseCase.java     # Interfaz para actualizar eventos.
│       └── out                             # Puertos de Salida (Qué necesita la app)
│           └── EventRepositoryPort.java    # Contrato para guardar/leer eventos (independiente de la BD).
└── infrastructure                          # Capa de INFRAESTRUCTURA (Detalles Técnicos)
    ├── adapter
    │   ├── in
    │   │   └── web                         # Adaptador Web (REST API)
    │   │       ├── controller
    │   │       │   └── EventRestAdapter.java # Controlador REST. Recibe peticiones HTTP.
    │   │       ├── dto
    │   │       │   └── EventDTO.java       # Objeto de Transferencia de Datos (JSON) para la API.
    │   │       └── mapper
    │   │           └── EventWebMapper.java # Convierte entre EventDTO y Event (Dominio).
    │   └── out
    │       └── persistence                 # Adaptador de Persistencia (Base de Datos)
    │           ├── EventJpaAdapter.java    # Implementa EventRepositoryPort usando JPA.
    │           ├── entity
    │           │   └── EventEntity.java    # Entidad JPA (Tabla de base de datos).
    │           ├── mapper
    │           │   └── EventPersistenceMapper.java # Convierte entre Event (Dominio) y EventEntity.
    │           ├── repository
    │           │   └── EventJpaRepository.java # Interfaz de Spring Data JPA (SQL automático).
    │           └── specifications
    │               └── EventSpecifications.java # Filtros dinámicos para búsquedas en BD.
    ├── config                              # Configuraciones de Spring
    │   ├── BeanConfiguration.java          # Inyección de dependencias (conecta UseCases con Adaptadores).
    │   ├── CorsConfig.java                 # Configuración de seguridad CORS (permite peticiones del frontend).
    │   └── OpenApiConfig.java              # Configuración de documentación Swagger/OpenAPI.
    └── exception
        └── GlobalExceptionHandler.java     # Manejador global de errores HTTP.
```

## Estructura de `venues-service`

La estructura es idéntica a la de `events-service`, pero enfocada en la entidad `Venue` (Recinto/Lugar).

```
venues-service/src/main/java/com/tiquetera/venues
├── VenuesServiceApplication.java
├── application
│   └── usecase
│       ├── CreateVenueService.java
│       ├── ... (Otros casos de uso CRUD)
├── domain
│   ├── model
│   │   └── Venue.java                      # Entidad de negocio Venue.
│   └── ports
│       ├── in                              # Interfaces de casos de uso (CreateVenueUseCase, etc.)
│       └── out
│           └── VenueRepositoryPort.java    # Contrato para persistencia de Venues.
└── infrastructure
    ├── adapter
    │   ├── in
    │   │   └── web                         # VenueRestAdapter, VenueDTO, VenueWebMapper
    │   └── out
    │       └── persistence                 # VenueJpaAdapter, VenueEntity, VenueJpaRepository
    └── config                              # Configuraciones similares
```

## Resumen de Responsabilidades

| Archivo / Carpeta | Responsabilidad Principal |
| :--- | :--- |
| **`model/Event.java`** | Define los datos y reglas puras del negocio. No tiene anotaciones de framework. |
| **`ports/in/*UseCase.java`** | Define las acciones disponibles para el usuario (API del negocio). |
| **`usecase/*Service.java`** | Ejecuta la lógica. Valida reglas y llama a los puertos de salida. |
| **`adapter/in/web/*Controller`** | La "puerta de entrada" HTTP. Recibe JSON, valida formato y llama al UseCase. |
| **`adapter/out/persistence/*Adapter`** | La "puerta de salida" a la BD. Traduce objetos de dominio a filas de BD. |
| **`config/BeanConfiguration.java`** | El "pegamento". Configura qué implementación concreta usa cada interfaz. |
