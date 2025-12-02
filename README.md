# Sistema de Gestión de Eventos y Venues (Ticketing App)

Este proyecto es una aplicación backend construida con **Spring Boot** siguiendo una **Arquitectura Hexagonal (Ports & Adapters)**. Su objetivo es gestionar eventos y lugares (venues) de manera escalable, mantenible y segura.

## 🚀 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (Hibernate)
- **H2 Database** (Desarrollo/Tests) / **MySQL** (Producción)
- **Flyway** (Migraciones de base de datos)
- **MapStruct** (Mapeo de objetos)
- **Spring Security + JWT** (Autenticación y Autorización)
- **Lombok**
- **Maven**

---

## 🏗 Arquitectura
El proyecto sigue el patrón de **Arquitectura Hexagonal**, dividiendo el código en capas claramente separadas:

- **Dominio (`domain`)**: Núcleo de la lógica de negocio. Contiene modelos, excepciones y puertos (interfaces). No tiene dependencias de frameworks externos.
- **Aplicación (`application`)**: Implementación de los casos de uso (`usecase`). Orquesta la lógica de negocio utilizando los puertos.
- **Infraestructura (`infrastructure`)**: Adaptadores de entrada (Controladores REST) y salida (Persistencia JPA, Seguridad, Configuración).

---

## 📋 Funcionalidades Implementadas (Historias de Usuario)

### HU2: Catálogo Persistente con Validación y Paginación
- **Persistencia**: Integración con JPA y base de datos H2/MySQL.
- **Validaciones**: Uso de Bean Validation (`@Validated`, `@NotBlank`, etc.) y validaciones personalizadas.
- **Paginación y Filtros**: Endpoints capaces de filtrar resultados (aunque la paginación completa está en proceso de mejora).

### HU3: Refactor Hexagonal (Ports & Adapters)
- **Desacoplamiento**: Separación total entre el dominio y la infraestructura.
- **Puertos**: Definición de interfaces `InputPort` (Casos de uso) y `OutputPort` (Repositorios).
- **Adaptadores**: Implementaciones concretas para JPA y REST, usando MapStruct para la conversión de datos.

### HU4: Administración de Eventos y Venues (Relaciones y Optimización)
- **Relaciones**:
    - `Venue` 1:N `Event` (Un venue tiene muchos eventos).
    - `Event` N:1 `Venue` (Un evento pertenece a un venue).
- **Optimización**: Uso de `FetchType.LAZY` y `Specifications` para consultas eficientes.
- **Migraciones**: Gestión de esquema de base de datos versionada con **Flyway**.

### HU5: Gestión Estándar de Errores y Seguridad JWT
- **Manejo de Errores**: Respuesta estandarizada bajo **RFC 7807 (ProblemDetail)**, incluyendo `timestamp` y `traceId`.
- **Seguridad**:
    - Autenticación Stateless con **JWT**.
    - Endpoints de Registro (`/auth/register`) y Login (`/auth/login`).
    - Control de acceso basado en roles (`ADMIN`, `USER`) mediante `@PreAuthorize`.
- **Observabilidad**: Logging estructurado para trazabilidad de errores.

---

## 🛠 Configuración y Ejecución

### Prerrequisitos
- Java 17+
- Maven
- Docker (opcional, para base de datos MySQL)

### Ejecutar la aplicación
1. Clonar el repositorio.
2. Configurar la base de datos en `application.yml` (por defecto usa H2 en memoria).
3. Ejecutar el comando:
   ```bash
   ./mvnw spring-boot:run
   ```

### Acceso a la API
- **Swagger UI**: `http://localhost:8080/swagger-ui.html` (si está habilitado)
- **H2 Console**: `http://localhost:8080/h2-console`

---

## 🔒 Seguridad
Para acceder a los endpoints protegidos:
1. Registrar un usuario en `/auth/register`.
2. Iniciar sesión en `/auth/login` para obtener el token JWT.
3. Incluir el token en el header `Authorization`: `Bearer <token>`.

---

## 📦 Estructura del Proyecto
```
src/main/java/com/tiquetera/events
├── application
│   └── usecase       # Implementación de casos de uso
├── domain
│   ├── model         # Entidades de dominio
│   ├── ports         # Interfaces (In/Out)
│   └── exception     # Excepciones de dominio
└── infrastructure
    ├── adapter
    │   ├── in/web    # Controladores REST
    │   └── out/jpa   # Repositorios JPA
    ├── config        # Configuración de Beans, Seguridad, Swagger
    └── security      # Filtros y utilidades JWT
```