# 📚 Documentación de Base de Datos - Ticketing App

## 📄 Archivos en esta carpeta

### `schema.sql`
Script SQL completo para crear la base de datos desde cero con:
- Creación de base de datos `ticketing_db`
- Tabla `venues` con índices y comentarios
- Tabla `events` con foreign key a venues
- Datos de ejemplo (5 venues y 5 eventos)
- Queries de verificación

## 🔧 Cómo usar el script

### Opción 1: Ejecutar desde línea de comandos
```bash
# Ejecutar todo el script
mysql -u root -p1234 < schema.sql

# O específicamente
mysql -u root -p1234 ticketing_db < schema.sql
```

### Opción 2: Desde MySQL Workbench o cliente MySQL
1. Abrir el archivo `schema.sql`
2. Ejecutar el script completo
3. Verificar que las tablas se crearon correctamente

### Opción 3: Dejar que Spring Boot lo haga automáticamente
Spring Boot con `hibernate.ddl-auto: update` crea las tablas automáticamente al iniciar.
Este script es útil para:
- Ambientes de producción
- Resetear la base de datos
- Documentación de estructura

## 📊 Estructura de Base de Datos

### Tabla: `venues`
| Campo       | Tipo         | Descripción                    |
|-------------|--------------|--------------------------------|
| id          | BIGINT       | ID único (auto_increment)      |
| name        | VARCHAR(200) | Nombre del venue              |
| address     | VARCHAR(300) | Dirección completa            |
| city        | VARCHAR(100) | Ciudad                        |
| country     | VARCHAR(100) | País                          |
| capacity    | INT          | Capacidad máxima              |
| description | TEXT         | Descripción                   |
| phone       | VARCHAR(20)  | Teléfono                      |
| email       | VARCHAR(100) | Email                         |
| status      | VARCHAR(20)  | Estado (ACTIVE, INACTIVE)     |
| created_at  | DATETIME(6)  | Fecha creación (automático)   |
| updated_at  | DATETIME(6)  | Fecha actualización (automático) |

### Tabla: `events`
| Campo        | Tipo          | Descripción                    |
|--------------|---------------|--------------------------------|
| id           | BIGINT        | ID único (auto_increment)      |
| name         | VARCHAR(200)  | Nombre del evento             |
| description  | TEXT          | Descripción                   |
| event_date   | DATETIME(6)   | Fecha y hora del evento       |
| venue_id     | BIGINT        | FK a venues                   |
| venue_name   | VARCHAR(200)  | Nombre venue (desnormalizado) |
| capacity     | INT           | Capacidad del evento          |
| ticket_price | DECIMAL(10,2) | Precio del ticket             |
| status       | VARCHAR(20)   | Estado (SCHEDULED, COMPLETED) |
| created_at   | DATETIME(6)   | Fecha creación (automático)   |
| updated_at   | DATETIME(6)   | Fecha actualización (automático) |

### Relaciones
- `events.venue_id` → `venues.id` (Many-to-One)
- Constraint: `ON DELETE RESTRICT` (no se puede eliminar un venue con eventos asociados)
- Constraint: `ON UPDATE CASCADE` (si cambia el ID del venue, se actualiza en eventos)

## 🔍 Queries Útiles

### Ver todos los eventos con información del venue
```sql
SELECT 
    e.name AS evento,
    e.event_date AS fecha,
    v.name AS venue,
    v.city AS ciudad,
    e.capacity,
    e.ticket_price
FROM events e
INNER JOIN venues v ON e.venue_id = v.id
ORDER BY e.event_date;
```

### Eventos próximos
```sql
SELECT * FROM events
WHERE event_date >= NOW()
  AND status = 'SCHEDULED'
ORDER BY event_date ASC;
```

### Venues por ciudad
```sql
SELECT city, COUNT(*) as total_venues
FROM venues
WHERE status = 'ACTIVE'
GROUP BY city
ORDER BY total_venues DESC;
```

## ⚙️ Configuración de Spring Boot

El backend está configurado para conectarse automáticamente a esta base de datos:

```yaml
# application-dev.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ticketing_db
    username: root
    password: 1234
  jpa:
    hibernate:
      ddl-auto: update  # Crea/actualiza tablas automáticamente
```

## 🎯 Datos de Ejemplo Incluidos

El script incluye:
- **5 Venues**: Estadios, arenas, parques y teatros en Colombia
- **5 Eventos**: Conciertos, partidos, obras de teatro programados

Puedes modificar o eliminar estos datos según necesites.
