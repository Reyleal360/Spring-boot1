# 🎫 Ticketing App - Monorepo

Sistema completo de gestión de eventos y venues con backend Spring Boot y frontend Angular.

## 📁 Estructura del Proyecto

```
ticketing-app/
├── backend/               # API REST con Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
└── frontend/              # Aplicación Angular
    └── ticketing-frontend/
        ├── src/
        ├── package.json
        └── README.md
```

## 🚀 Inicio Rápido

### 1. Iniciar Backend

```bash
cd backend
./mvnw spring-boot:run
```

El backend estará disponible en: **http://localhost:8080**
- Swagger UI: http://localhost:8080/swagger-ui.html

### 2. Iniciar Frontend

```bash
cd frontend/ticketing-frontend
npm install
npm start
```

El frontend estará disponible en: **http://localhost:4200**

## 🏗️ Arquitecturas

### Backend
- **Patrón**: Arquitectura Hexagonal (Ports & Adapters)
- **Framework**: Spring Boot 3
- **Persistencia**: In-Memory (ConcurrentHashMap)
- **Mapeo**: MapStruct
- **Documentación**: Swagger/OpenAPI

### Frontend
- **Framework**: Angular 20
- **Componentes**: Standalone
- **Estilos**: SCSS personalizado
- **HTTP**: Angular HttpClient
- **Routing**: Angular Router

## ✨ Funcionalidades

### Gestión de Venues (Lugares)
- ✅ Crear, listar, editar y eliminar venues
- ✅ Filtrar por ciudad, país, estado
- ✅ Validación de formularios
- ✅ Interfaz responsive

### Gestión de Eventos
- ✅ Crear, listar, editar y eliminar eventos
- ✅ Asociar eventos a venues
- ✅ Filtrar por venue y estado
- ✅ Precios y capacidades
- ✅ Fechas de eventos

## 📚 Documentación Adicional

- Ver `backend/README.md` para detalles del backend
- Ver `frontend/ticketing-frontend/README.md` para detalles del frontend

## 👥 Equipo

Ticketing Team - Full Stack Application v2.0
