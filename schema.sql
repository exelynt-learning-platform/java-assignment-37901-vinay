-- =============================================================================
-- Database Schema for Employee Management System
-- Compatible with MySQL, SQLite, and H2
-- =============================================================================

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL,
    email VARCHAR(100)
);
