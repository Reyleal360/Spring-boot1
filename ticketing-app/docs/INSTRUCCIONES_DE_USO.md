# 📘 Manual de Uso y Documentación Técnica - Ticketing App

## 🚀 1. Descripción del Proyecto
**Ticketing App** es una plataforma moderna para la gestión de eventos y lugares (venues). 
Originalmente construida como una aplicación monolítica, ha sido **refactorizada y migrada exitosamente a una arquitectura de Microservicios**.

El sistema permite:
- **Gestión de Venues (Lugares):** Crear, editar y listar sitios donde ocurren eventos.
- **Gestión de Eventos:** Programar eventos asociados a un venue, controlar aforo y precios.

---

## 🛠️ 2. Arquitectura y Tecnologías
El proyecto sigue una **Arquitectura Hexagonal (Ports & Adapters)** para garantizar que la lógica de negocio sea independiente de la tecnología.

### Componentes Principales:
1.  **Frontend (Angular 17+)**: Interfaz de usuario moderna y reactiva. Usa un **Proxy** para redirigir peticiones a los microservicios correctos.
2.  **Backend (Spring Boot 3 - Java 17)**:
    *   **Venues Service (Puerto 8081)**: Microservicio autónomo para gestionar lugares.
    *   **Events Service (Puerto 8082)**: Microservicio autónomo para gestionar eventos.
3.  **Base de Datos (MySQL 8)**: Cada microservicio tiene su propia base de datos lógica (`venues_db` y `events_db`) para asegurar el desacoplamiento.

### Diagrama de Flujo
```mermaid
Usuario -> [Frontend Angular] -> [Proxy] -> [Microservicio (8081/8082)] -> [MySQL]
```

---

## ⚡ 3. Guía de Ejecución (Cómo prenderlo)

### Prerrequisitos
- Java 17 o superior.
- Node.js y npm.
- MySQL corriendo en puerto 3306 (Usuario: `root`, Password: `1234`).

### Paso A: Iniciar el Backend
Hemos creado un script automatizado para facilitarte la vida.

1.  Abre una terminal en la carpeta `backend`.
2.  Ejecuta:
    ```bash
    ./start-services.sh
    ```
    *Esto levantará ambos microservicios en segundo plano.*

> **Opción Manual:** Si prefieres ver los logs en tiempo real, abre dos terminales en `backend` y ejecuta:
> - `java -jar venues-service/target/venues-service-1.0.0.jar`
> - `java -jar events-service/target/events-service-1.0.0.jar`

### Paso B: Iniciar el Frontend
1.  Abre una terminal en `frontend/ticketing-frontend`.
2.  Ejecuta:
    ```bash
    npm start
    ```
3.  Abre tu navegador en: **http://localhost:4200**

---

## 📝 4. ¿Qué hicimos? (Resumen de Cambios)

Para llegar a este estado, realizamos las siguientes tareas técnicas:

1.  **Desacoplamiento del Monolito**: Separamos el código original en dos proyectos Maven independientes (`venues-service` y `events-service`).
2.  **Configuración de Bases de Datos**: Creamos esquemas separados para garantizar que un servicio no toque las tablas del otro.
3.  **Refactorización de Código**:
    *   Eliminamos dependencias cruzadas (ej. `Events` ya no importa clases de `Venues`).
    *   Implementamos **DTOs** y **Mappers** para transformar datos entre capas.
    *   Corregimos la entidad `Event` para manejar `venueId` como referencia lógica.
4.  **Corrección de Errores**:
    *   Solucionamos el error 500 al crear eventos ajustando el formato de fecha en el Frontend (agregando segundos).
    *   Arreglamos el bug donde el nombre del Venue no se actualizaba al editar un evento.
5.  **Limpieza**: Eliminamos todo el código "basura" del antiguo monolito (`src` folder) y scripts temporales.

---

## 📂 5. Estructura del Proyecto

```text
ticketing-app/
├── backend/
│   ├── venues-service/     # Código fuente del servicio de Lugares
│   ├── events-service/     # Código fuente del servicio de Eventos
│   └── start-services.sh   # Script de arranque
├── frontend/
│   └── ticketing-frontend/ # Código fuente Angular
└── docs/
    └── INSTRUCCIONES_DE_USO.md  # Este archivo
```

---
*Documentación generada automáticamente por tu Asistente de IA.*
