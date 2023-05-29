-- V1__create__tables.sql
CREATE TABLE IF NOT EXISTS customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE
);

CREATE TABLE IF NOT EXISTS vehicle (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(255),
    model VARCHAR(255),
    model_year INT,
    identification_number VARCHAR(255),
    price DECIMAL(20,2)
);

CREATE TABLE IF NOT EXISTS leasingContract (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    contract_number VARCHAR(255),
    monthly_rate DECIMAL(20,2),
    customer_id BIGINT,
    vehicle_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);