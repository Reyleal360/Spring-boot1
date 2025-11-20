-- ============================================================
-- Script de Base de Datos - Ticketing App
-- ============================================================
-- Descripción: Schema completo para el sistema de gestión de eventos
-- Autor: Ticketing Team
-- Versión: 1.0
-- Fecha: 2025-11-19
-- ============================================================

-- ============================================================
-- 1. CREAR BASE DE DATOS
-- ============================================================

CREATE DATABASE IF NOT EXISTS ticketing_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ticketing_db;

-- ============================================================
-- 2. TABLA: venues (Lugares/Recintos)
-- ============================================================

DROP TABLE IF EXISTS events;  -- Primero eliminar tabla dependiente
DROP TABLE IF EXISTS venues;

CREATE TABLE venues (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(200) NOT NULL COMMENT 'Nombre del venue',
    address     VARCHAR(300) COMMENT 'Dirección completa',
    city        VARCHAR(100) COMMENT 'Ciudad',
    country     VARCHAR(100) COMMENT 'País',
    capacity    INT COMMENT 'Capacidad máxima de personas',
    description TEXT COMMENT 'Descripción del venue',
    phone       VARCHAR(20) COMMENT 'Teléfono de contacto',
    email       VARCHAR(100) COMMENT 'Email de contacto',
    status      VARCHAR(20) DEFAULT 'ACTIVE' COMMENT 'Estado: ACTIVE, INACTIVE, MAINTENANCE',
    created_at  DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Fecha de creación',
    updated_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Fecha de última actualización',
    
    -- Índices para optimizar búsquedas
    INDEX idx_city (city),
    INDEX idx_country (country),
    INDEX idx_status (status),
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='Tabla de venues/recintos para eventos';

-- ============================================================
-- 3. TABLA: events (Eventos)
-- ============================================================

CREATE TABLE events (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(200) NOT NULL COMMENT 'Nombre del evento',
    description  TEXT COMMENT 'Descripción del evento',
    event_date   DATETIME(6) NOT NULL COMMENT 'Fecha y hora del evento',
    venue_id     BIGINT NOT NULL COMMENT 'ID del venue donde se realizará',
    venue_name   VARCHAR(200) COMMENT 'Nombre del venue (campo desnormalizado para performance)',
    capacity     INT COMMENT 'Capacidad disponible para este evento',
    ticket_price DECIMAL(10,2) COMMENT 'Precio del ticket en la moneda local',
    status       VARCHAR(20) DEFAULT 'SCHEDULED' COMMENT 'Estado: SCHEDULED, ACTIVE, COMPLETED, CANCELLED',
    created_at   DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Fecha de creación',
    updated_at   DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Fecha de última actualización',
    
    -- Foreign Key
    CONSTRAINT fk_event_venue 
        FOREIGN KEY (venue_id) 
        REFERENCES venues(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    
    -- Índices para optimizar búsquedas
    INDEX idx_event_date (event_date),
    INDEX idx_status (status),
    INDEX idx_venue_id (venue_id),
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='Tabla de eventos';

-- ============================================================
-- 4. DATOS DE EJEMPLO
-- ============================================================

-- Insertar venues de ejemplo
INSERT INTO venues (name, address, city, country, capacity, description, phone, email, status) VALUES
    ('Estadio El Campín', 'Carrera 30 #57-60', 'Bogotá', 'Colombia', 36000, 'Estadio multiusos de Bogotá', '+57 1 2222222', 'info@elcampin.com', 'ACTIVE'),
    ('Movistar Arena', 'Calle 61 #25-85', 'Bogotá', 'Colombia', 14000, 'Arena para espectáculos y conciertos', '+57 1 3333333', 'contacto@movistararena.co', 'ACTIVE'),
    ('Parque Simón Bolívar', 'Calle 63 # 68-95', 'Bogotá', 'Colombia', 100000, 'Espacio público para grandes eventos', '+57 1 4444444', 'eventos@simonbolivar.gov.co', 'ACTIVE'),
    ('Teatro Colón', 'Calle 10 #5-32', 'Bogotá', 'Colombia', 900, 'Teatro histórico para artes escénicas', '+57 1 5555555', 'info@teatrocolon.gov.co', 'ACTIVE'),
    ('Estadio Metropolitano', 'Calle 72 #46-31', 'Barranquilla', 'Colombia', 46000, 'Estadio de fútbol', '+57 5 6666666', 'info@metrobarranquilla.com', 'ACTIVE');

-- Insertar eventos de ejemplo
INSERT INTO events (name, description, event_date, venue_id, venue_name, capacity, ticket_price, status) VALUES
    ('Concierto de Rock', 'Gran evento musical con bandas internacionales', '2025-12-20 20:00:00', 1, 'Estadio El Campín', 30000, 150000.00, 'SCHEDULED'),
    ('Festival Electrónico', 'Música electrónica con los mejores DJs', '2025-12-25 22:00:00', 3, 'Parque Simón Bolívar', 80000, 200000.00, 'SCHEDULED'),
    ('Obra de Teatro Clásica', 'Presentación de obra del siglo XIX', '2025-11-30 19:00:00', 4, 'Teatro Colón', 850, 80000.00, 'SCHEDULED'),
    ('Partido de Fútbol', 'Clásico del fútbol colombiano', '2025-12-15 16:00:00', 1, 'Estadio El Campín', 35000, 50000.00, 'SCHEDULED'),
    ('Concierto Sinfónico', 'Orquesta Filarmónica de Bogotá', '2025-12-10 18:00:00', 4, 'Teatro Colón', 900, 100000.00, 'SCHEDULED');

-- ============================================================
-- 5. VERIFICACIÓN DE DATOS
-- ============================================================

-- Mostrar conteo de registros
SELECT 'VENUES' AS Tabla, COUNT(*) AS Total FROM venues
UNION ALL
SELECT 'EVENTS' AS Tabla, COUNT(*) AS Total FROM events;

-- Mostrar eventos con información del venue (JOIN)
SELECT 
    e.id,
    e.name AS evento,
    e.event_date AS fecha,
    v.name AS venue,
    v.city AS ciudad,
    e.capacity AS capacidad,
    e.ticket_price AS precio,
    e.status
FROM events e
INNER JOIN venues v ON e.venue_id = v.id
ORDER BY e.event_date;

-- ============================================================
-- 6. NOTAS IMPORTANTES
-- ============================================================

/*
CONFIGURACIÓN DE SPRING BOOT (application-dev.yml):

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ticketing_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    username: root
    password: 1234
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update  # Hibernate creará/actualizará las tablas automáticamente
    properties:
      hibernate:
        format_sql: true

NOTAS:
- Con ddl-auto: update, Hibernate crea las tablas automáticamente
- Este script es útil para crear la BD manualmente o en ambientes de producción
- Los timestamps se gestionan automáticamente con @CreationTimestamp y @UpdateTimestamp
- La relación venue_id en events tiene ON DELETE RESTRICT para evitar eliminar venues con eventos asociados
*/
