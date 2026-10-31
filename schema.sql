-- =============================================================================
-- Employee Management System Database Schema
-- Compatible with SQLite, MySQL, and PostgreSQL
-- =============================================================================

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL
);
