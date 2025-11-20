CREATE TABLE IF NOT EXISTS venues (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    address VARCHAR(300),
    city VARCHAR(100),
    country VARCHAR(100),
    capacity INT,
    description TEXT,
    phone VARCHAR(20),
    email VARCHAR(100),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);
