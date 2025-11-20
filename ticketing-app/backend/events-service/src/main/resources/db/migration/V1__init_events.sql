CREATE TABLE IF NOT EXISTS events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    event_date DATETIME NOT NULL,
    venue_id BIGINT NOT NULL,
    venue_name VARCHAR(200),
    capacity INT,
    ticket_price DECIMAL(10, 2),
    status VARCHAR(20) DEFAULT 'SCHEDULED',
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);
