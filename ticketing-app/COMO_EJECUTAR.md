# 🚀 Guía Rápida de Ejecución - Ticketing App

## 📋 Requisitos Previos

- **Java**: JDK 17+
- **Node.js**: v20+
- **npm**: v10+
- **Maven**: Incluido (mvnw)

---

## ▶️ Cómo Ejecutar

### 1️⃣ Iniciar el Backend (Spring Boot)

```bash
# Navegar al directorio del backend
cd /home/Coder/Imágenes/Spring-boot1/ticketing-app/backend

# Ejecutar con Maven wrapper
./mvnw spring-boot:run
```

**✅ Backend listo cuando veas:**
```
Started TicketingApiApplication in X seconds
Tomcat started on port 8080

╔═══════════════════════════════════════════════════════════╗
║          🎫 TICKETING API - Sistema Iniciado 🎫          ║
╚═══════════════════════════════════════════════════════════╝
```

**URLs del Backend:**
- 🌐 API: http://localhost:8080/api/v1/
- 📚 Swagger: http://localhost:8080/swagger-ui.html

---

### 2️⃣ Iniciar el Frontend (Angular)

**Abrir una NUEVA terminal** (deja el backend corriendo)

```bash
# Navegar al directorio del frontend
cd /home/Coder/Imágenes/Spring-boot1/ticketing-app/frontend/ticketing-frontend

# Instalar dependencias (solo la primera vez)
npm install

# Ejecutar en modo desarrollo
npm start
```

**✅ Frontend listo cuando veas:**
```
✔ Browser application bundle generation complete.

Local:   http://localhost:4200/
```

**URL del Frontend:**
- 🎨 Aplicación: http://localhost:4200

---

## 🛑 Cómo Detener

### Detener Backend
En la terminal del backend, presiona: `Ctrl + C`

### Detener Frontend
En la terminal del frontend, presiona: `Ctrl + C`

---

## 📱 Cómo Usar la Aplicación

### Acceso Inicial
1. Abre tu navegador en: **http://localhost:4200**
2. Verás el dashboard con:
   - Estadísticas de eventos y venues
   - Botones para crear nuevo evento/venue

### Crear un Venue (Lugar)
1. Click en **"Agregar Venue"** o navega a "Venues" → "+ Nuevo Venue"
2. Completa el formulario:
   - Nombre: "Estadio Metropolitano"
   - Dirección: "Calle 72 # 46-31"
   - Ciudad: "Barranquilla"
   - País: "Colombia"
   - Capacidad: 46000
3. Click en **"Crear"**
4. ✅ Verás el venue en la lista

### Crear un Evento
1. Click en **"Crear Evento"** o navega a "Eventos" → "+ Nuevo Evento"
2. Completa el formulario:
   - Nombre: "Concierto de Shakira"
   - Descripción: "Tour mundial 2025"
   - Fecha: Selecciona fecha futura
   - Venue: Selecciona de la lista desplegable
   - Capacidad: 45000
   - Precio: 250000
3. Click en **"Crear"**
4. ✅ Verás el evento en la lista con el venue asociado

### Editar/Eliminar
- Click en **"Editar"** para modificar
- Click en **"Eliminar"** para borrar (pide confirmación)

---

## 🔧 Comandos Útiles

### Backend
```bash
# Limpiar y compilar
./mvnw clean compile

# Ejecutar tests
./mvnw test

# Generar JAR
./mvnw clean package
```

### Frontend
```bash
# Compilar para producción
npm run build

# Ejecutar linter
npm run lint

# Ejecutar tests
npm test
```

---

## ⚠️ Troubleshooting

### Backend no inicia
- Verifica que Java 17+ esté instalado: `java -version`
- Verifica que el puerto 8080 esté libre: `lsof -i :8080`

### Frontend no inicia
- Verifica que Node.js esté instalado: `node --version`
- Verifica que el puerto 4200 esté libre: `lsof -i :4200`
- Reinstala dependencias: `rm -rf node_modules package-lock.json && npm install`

### Frontend no se conecta al backend
- Asegúrate de que el backend esté corriendo primero
- Verifica que el proxy esté configurado (ya incluido en el proyecto)
- Revisa la consola del navegador (F12) para ver errores

---

## 📂 Estructura de Carpetas

```
ticketing-app/
├── backend/              # ← Ejecutar aquí: ./mvnw spring-boot:run
│   ├── src/
│   ├── pom.xml
│   └── mvnw
│
└── frontend/
    └── ticketing-frontend/  # ← Ejecutar aquí: npm start
        ├── src/
        ├── package.json
        └── proxy.conf.json
```

---

## ✨ ¡Listo!

Ahora tienes:
- ✅ Backend corriendo en http://localhost:8080
- ✅ Frontend corriendo en http://localhost:4200
- ✅ Aplicación completa funcionando
