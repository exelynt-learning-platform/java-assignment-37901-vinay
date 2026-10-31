package com.enterprise.app.factory;

import com.enterprise.app.model.Employee;

/**
 * =============================================================================
 *             DESIGN PATTERN: FACTORY (Object Creation)
 * =============================================================================
 * Encapsulates instantiation logic for various organizational employee tiers.
 * Clients request an Employee by role rather than coupling directly to constructor parameters.
 */
public class EmployeeFactory {

    public static Employee createEmployee(int id, String name, String department, String roleType) {
        if (roleType == null || roleType.trim().isEmpty()) {
            throw new IllegalArgumentException("Role type must be specified.");
        }

        switch (roleType.trim().toUpperCase()) {
            case "ENGINEER":
                return new Employee(id, name, department, 85000.0, "Software Engineer");
            case "MANAGER":
                return new Employee(id, name, department, 110000.0, "Engineering Manager");
            case "ANALYST":
                return new Employee(id, name, department, 72000.0, "Business Analyst");
            case "DIRECTOR":
                return new Employee(id, name, department, 145000.0, "Technical Director");
            default:
                return new Employee(id, name, department, 60000.0, roleType);
        }
    }
}
