# 🎫 Ticketing API - Arquitectura Hexagonal

API REST para gestión de eventos y venues (lugares) implementada con **Spring Boot** y **Arquitectura Hexagonal (Ports & Adapters)**.

## 🏗️ Arquitectura

Este proyecto sigue los principios de **Arquitectura Hexagonal** con separación clara en tres capas:

- **Dominio** (`dominio/`): Lógica de negocio pura, sin dependencias de frameworks
- **Aplicación** (`aplicacion/`): Casos de uso que orquestan la lógica de dominio
- **Infraestructura** (`infraestructura/`): Adaptadores para REST, persistencia, y configuración

```
📦 Estructura de Capas
┌─────────────────────────────────────┐
│     INFRAESTRUCTURA (Adapters)      │
│  ┌──────────────┬──────────────┐    │
│  │ REST (Input) │ DB (Output)  │    │
│  └──────┬───────┴───────┬──────┘    │
├─────────┼───────────────┼──────────── PUERTOS (Interfaces)
│         ↓               ↓            │
│   ┌─────────────────────────────┐   │
│   │  APLICACIÓN (Use Cases)     │   │
│   └──────────────┬──────────────┘   │
│                  ↓                   │
│   ┌─────────────────────────────┐   │
│   │   DOMINIO (Business Logic)  │   │
│   │   ✓ Event, Venue entities   │   │
│   │   ✓ Business methods        │   │
│   └─────────────────────────────┘   │
└─────────────────────────────────────┘
```

## ✨ Características

- ✅ **Arquitectura Hexagonal** (Ports & Adapters)
- ✅ **SOLID Principles**
- ✅ **MapStruct** para mapeo automático de objetos
- ✅ **Swagger/OpenAPI** para documentación interactiva
- ✅ **Validación** con Bean Validation
- ✅ **Manejo de excepciones** centralizado
- ✅ **Persistencia in-memory** (thread-safe)
- ✅ **Cero dependencias de frameworks en dominio**

## 🛠️ Tecnologías

- **Java** 17
- **Spring Boot** 3.x
- **MapStruct** 1.5.5
- **Lombok**
- **Swagger/OpenAPI** 3
- **Maven**

## 📂 Estructura del Proyecto

```
src/main/java/com/tiquetera/ticketing/
├── dominio/                    # Sin dependencias de frameworks
│   ├── modelo/                 # Event, Venue
│   ├── ports/in/               # Use case interfaces
│   ├── ports/out/              # Repository interfaces
│   └── exception/              # Domain exceptions
├── aplicacion/
│   └── usecase/                # Use case implementations
└── infraestructura/
    ├── adapters/in/web/        # REST controllers + DTOs
    ├── adapters/out/persistence/ # Repository implementations
    ├── config/                 # Spring configuration
    └── exception/              # Exception handlers
```

## 🚀 Inicio Rápido

### 1. Clonar y compilar

```bash
cd eventos-api
./mvnw clean compile
```

### 2. Ejecutar

```bash
./mvnw spring-boot:run
```

### 3. Abrir Swagger UI

```
http://localhost:8080/swagger-ui.html
```

## 📝 Ejemplos de Uso

### Crear un Venue

```bash
curl -X POST http://localhost:8080/api/v1/venues \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Estadio Metropolitano",
    "address": "Calle 72, Barranquilla",
    "city": "Barranquilla",
    "country": "Colombia",
    "capacity": 46000
  }'
```

### Crear un Evento

```bash
curl -X POST http://localhost:8080/api/v1/events \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Concierto de Shakira",
    "description": "Tour mundial 2025",
    "eventDate": "2025-12-31T20:00:00",
    "venueId": 1,
    "capacity": 45000,
    "ticketPrice": 350000.00
  }'
```

### Listar Eventos

```bash
curl http://localhost:8080/api/v1/events
```

## 📚 Endpoints Disponibles

### Venues (Lugares)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/venues` | Crear venue |
| GET | `/api/v1/venues` | Listar todos |
| GET | `/api/v1/venues/{id}` | Obtener por ID |
| GET | `/api/v1/venues/city/{city}` | Filtrar por ciudad |
| PUT | `/api/v1/venues/{id}` | Actualizar |
| DELETE | `/api/v1/venues/{id}` | Eliminar |

### Events (Eventos)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/events` | Crear evento |
| GET | `/api/v1/events` | Listar todos |
| GET | `/api/v1/events/{id}` | Obtener por ID |
| GET | `/api/v1/events/venue/{venueId}` | Filtrar por venue |
| GET | `/api/v1/events/status/{status}` | Filtrar por estado |
| PUT | `/api/v1/events/{id}` | Actualizar |
| DELETE | `/api/v1/events/{id}` | Eliminar |

## 🧪 Testing

Para probar los endpoints, puedes usar:

1. **Swagger UI**: `http://localhost:8080/swagger-ui.html` (recomendado)
2. **curl**: Ver ejemplos arriba
3. **Postman/Insomnia**: Importar desde `http://localhost:8080/v3/api-docs`

## 🎓 Ventajas de Arquitectura Hexagonal

1. **Independencia de Frameworks**: El dominio no conoce Spring, JPA, etc.
2. **Testeable**: Casos de uso se pueden probar sin Spring Boot
3. **Flexible**: Fácil cambiar de in-memory a base de datos real
4. **Mantenible**: Separación clara de responsabilidades

## 📖 Documentación Completa

- Ver [`GUIA_COMPLETA.md`](/home/Coder/.gemini/antigravity/brain/db58656f-086f-4083-b0f6-5988538b693b/GUIA_COMPLETA.md) para descripción detallada de cada archivo
- Ver [`walkthrough.md`](/home/Coder/.gemini/antigravity/brain/db58656f-086f-4083-b0f6-5988538b693b/walkthrough.md) para walkthrough completo de la refactorización

## 👥 Autor

Ticketing Team - Arquitectura Hexagonal v2.0

## 📄 Licencia

MIT License
