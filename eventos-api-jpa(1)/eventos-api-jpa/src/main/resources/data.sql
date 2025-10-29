-- Datos iniciales para H2 Database
-- Se cargan automáticamente al iniciar la aplicación

-- Insertar Venues
INSERT INTO venues (id, nombre, descripcion, direccion, ciudad, pais, capacidad, tipo, created_at, updated_at) 
VALUES 
(1, 'Teatro Amira de la Rosa', 'Teatro principal de la ciudad', 'Calle 76 #59-114', 'Barranquilla', 'Colombia', 1500, 'Teatro', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Estadio Metropolitano', 'Estadio de fútbol y eventos masivos', 'Calle 72 #46-31', 'Barranquilla', 'Colombia', 46000, 'Estadio', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Country Club', 'Salón de eventos y convenciones', 'Km 7 Vía al Mar', 'Barranquilla', 'Colombia', 800, 'Club Social', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar Events
INSERT INTO events (id, nombre, descripcion, fecha_evento, venue_id, categoria, precio, capacidad_disponible, created_at, updated_at)
VALUES
(1, 'Concierto de Vallenato 2025', 'Gran noche de música vallenata con los mejores exponentes', '2025-12-15 20:00:00', 1, 'Música', 75000.0, 1400, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Festival de Rock Internacional', 'Festival de rock con bandas nacionales e internacionales', '2025-11-20 18:00:00', 2, 'Música', 120000.0, 45000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Obra de Teatro: Don Quijote', 'Adaptación moderna del clásico de Cervantes', '2025-11-05 19:30:00', 1, 'Teatro', 50000.0, 1450, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Gala de Fin de Año', 'Cena y baile de fin de año', '2025-12-31 21:00:00', 3, 'Social', 250000.0, 750, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Configurar secuencias para IDs autoincrementales
ALTER SEQUENCE venues_seq RESTART WITH 4;
ALTER SEQUENCE events_seq RESTART WITH 5;