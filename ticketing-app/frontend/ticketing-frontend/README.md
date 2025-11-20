# 🎫 Ticketing Frontend - Angular Application

Frontend en Angular para el sistema de gestión de eventos y venues.

## 🚀 Inicio Rápido

### Requisitos
- Node.js 20+
- npm 10+
- Backend corriendo en `http://localhost:8080`

### Instalación

```bash
cd /home/Coder/Imágenes/Spring-boot1/ticketing-app/frontend/ticketing-frontend
npm install
```

### Ejecutar en Desarrollo

```bash
npm start
# o
ng serve --proxy-config proxy.conf.json
```

La aplicación estará disponible en: **http://localhost:4200**

## 📂 Estructura del Proyecto

```
src/app/
├── core/
│   ├── models/          # Event, Venue interfaces
│   └── services/        # EventService, VenueService
├── features/
│   ├── home/            # Dashboard component
│   ├── events/          # Event list & form
│   └── venues/          # Venue list & form
├── shared/
│   └── navbar/          # Navigation bar
├── app.routes.ts        # Routing configuration
└── app.config.ts        # App configuration
```

## ✨ Características

- ✅ **CRUD completo** para Eventos y Venues
- ✅ **Navegación** con Angular Router
- ✅ **Formularios reactivos** con validación
- ✅ **Servicios HTTP** con HttpClient
- ✅ **Proxy configurado** para desarrollo
- ✅ **Componentes standalone** (Angular 17+)
- ✅ **Dashboard** con estadísticas
- ✅ **Estilos modernos** responsive

## 🎨 Componentes Principales

### Venues
- **Lista de Venues**: Tabla con todos los venues, botones para editar/eliminar
- **Formulario de Venue**: Create/edit con validación

### Events
- **Lista de Eventos**: Tabla con filtros, muestra venue asociado
- **Formulario de Evento**: Create/edit con selector de venue

### Dashboard (Home)
- Estadísticas de eventos y venues
- Enlaces rápidos a crear evento/venue

## 📡 API Endpoints Consumidos

### Venues
- `GET /api/v1/venues` - Listar todos
- `POST /api/v1/venues` - Crear
- `PUT /api/v1/venues/{id}` - Actualizar
- `DELETE /api/v1/venues/{id}` - Eliminar

### Events
- `GET /api/v1/events` - Listar todos
- `POST /api/v1/events` - Crear
- `PUT /api/v1/events/{id}` - Actualizar
- `DELETE /api/v1/events/{id}` - Eliminar

## 🔗 Integración con Backend

El proxy configurado en `proxy.conf.json` redirige las peticiones `/api/*` a `http://localhost:8080`.

**Importante**: Asegúrate de que el backend esté corriendo antes de iniciar el frontend.

## 📚 Tecnologías

- Angular 20
- TypeScript
- SCSS
- RxJS
- Angular Router
- HttpClient

## 👥 Autor

Ticketing Team - Frontend v1.0
