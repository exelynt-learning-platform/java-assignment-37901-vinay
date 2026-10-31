package com.enterprise.app.model;

/**
 * =============================================================================
 *                               MODEL LAYER
 * =============================================================================
 * Pure domain entity representing an Employee in the enterprise application.
 */
public class Employee {

    private final int id;
    private String name;
    private String department;
    private double salary;
    private String role;

    public Employee(int id, String name, String department, double salary, String role) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("Employee[ID=%-3d | Name=%-18s | Dept=%-13s | Role=%-12s | Salary=$%,.2f]",
                id, name, department, role, salary);
    }
}
