package com.enterprise.app.service;

import com.enterprise.app.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * =============================================================================
 *                              SERVICE LAYER
 * =============================================================================
 * Handles business logic, domain validation, and application event logging.
 * Uses java.util.logging.Logger to record milestones, warnings, and failures.
 */
public class EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());
    private final List<Employee> repository = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (employee == null) {
            logger.warning("Attempted to register null employee. Operation aborted.");
            throw new IllegalArgumentException("Employee cannot be null.");
        }

        if (findById(employee.getId()).isPresent()) {
            logger.warning("Conflict: Employee ID " + employee.getId() + " already exists in repository.");
            throw new IllegalStateException("Duplicate employee ID: " + employee.getId());
        }

        repository.add(employee);
        logger.info("Registered new employee: " + employee.getName() + " (ID: " + employee.getId() + ")");
    }

    public List<Employee> getAllEmployees() {
        logger.fine("Fetching all registered employees (Count: " + repository.size() + ")");
        return Collections.unmodifiableList(repository);
    }

    public Optional<Employee> findById(int id) {
        return repository.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public void applyPromotion(int id, double salaryIncrease, String newRole) {
        logger.info("Applying promotion request for Employee ID: " + id);
        Optional<Employee> empOpt = findById(id);

        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            double oldSalary = emp.getSalary();
            emp.setSalary(oldSalary + salaryIncrease);
            if (newRole != null && !newRole.trim().isEmpty()) {
                emp.setRole(newRole);
            }
            logger.info(String.format("Promotion applied: %s salary adjusted $%.2f -> $%.2f",
                    emp.getName(), oldSalary, emp.getSalary()));
        } else {
            logger.log(Level.SEVERE, "Failed to apply promotion: Employee ID " + id + " was not found.");
            throw new IllegalArgumentException("Employee ID " + id + " not found.");
        }
    }

    public double calculateTotalPayroll() {
        return repository.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}
