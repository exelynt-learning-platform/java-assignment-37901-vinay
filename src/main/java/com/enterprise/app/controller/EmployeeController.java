package com.enterprise.app.controller;

import com.enterprise.app.factory.EmployeeFactory;
import com.enterprise.app.model.Employee;
import com.enterprise.app.service.EmployeeService;
import com.enterprise.app.view.EmployeeView;

import java.util.Optional;

/**
 * =============================================================================
 *                            CONTROLLER LAYER
 * =============================================================================
 * Coordinates user requests, delegates to EmployeeService, and commands EmployeeView
 * to present results.
 */
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeView view;

    public EmployeeController(EmployeeService service, EmployeeView view) {
        this.service = service;
        this.view = view;
    }

    public void registerEmployee(int id, String name, String department, String roleType) {
        Employee employee = EmployeeFactory.createEmployee(id, name, department, roleType);
        service.addEmployee(employee);
        view.displayStatus("Employee successfully onboarded: " + employee.getName());
    }

    public void showDirectory() {
        view.displayEmployeeDirectory(service.getAllEmployees());
    }

    public void showEmployeeDetails(int id) {
        Optional<Employee> emp = service.findById(id);
        if (emp.isPresent()) {
            view.displayEmployeeCard(emp.get());
        } else {
            view.displayStatus("No employee found with ID: " + id);
        }
    }

    public void promoteEmployee(int id, double increase, String newRole) {
        service.applyPromotion(id, increase, newRole);
        view.displayStatus("Promotion applied for Employee ID: " + id);
    }
}
