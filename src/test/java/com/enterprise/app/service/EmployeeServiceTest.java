package com.enterprise.app.service;

import com.enterprise.app.config.AppConfig;
import com.enterprise.app.factory.EmployeeFactory;
import com.enterprise.app.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 Unit Tests for EnterpriseJavaApp:
 * Validates EmployeeService operations, Singleton pattern integrity, and Factory pattern instantiation.
 */
public class EmployeeServiceTest {

    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
    }

    @Test
    @DisplayName("Verify Singleton pattern maintains identical instance reference")
    void testSingletonConfigIntegrity() {
        AppConfig config1 = AppConfig.getInstance();
        AppConfig config2 = AppConfig.getInstance();

        assertNotNull(config1, "AppConfig should not be null");
        assertSame(config1, config2, "Both instances must reference the exact same memory location");
        assertEquals("EnterpriseJavaApp", config1.getProperty("app.name"));
    }

    @Test
    @DisplayName("Verify Factory pattern creates correct role and salary tier")
    void testEmployeeFactoryCreation() {
        Employee engineer = EmployeeFactory.createEmployee(1, "Vinay", "Engineering", "ENGINEER");
        Employee manager = EmployeeFactory.createEmployee(2, "Alice", "Management", "MANAGER");

        assertEquals("Software Engineer", engineer.getRole());
        assertEquals(85000.0, engineer.getSalary());

        assertEquals("Engineering Manager", manager.getRole());
        assertEquals(110000.0, manager.getSalary());
    }

    @Test
    @DisplayName("Test adding and finding employee in EmployeeService")
    void testAddAndFindEmployee() {
        Employee emp = new Employee(101, "Vinay Madarkhandi", "Engineering", 90000.0, "Tech Lead");
        service.addEmployee(emp);

        Optional<Employee> found = service.findById(101);
        assertTrue(found.isPresent(), "Employee should be found by ID");
        assertEquals("Vinay Madarkhandi", found.get().getName());
        assertEquals(1, service.getAllEmployees().size());
    }

    @Test
    @DisplayName("Test duplicate employee ID throws IllegalStateException")
    void testDuplicateEmployeeThrowsException() {
        Employee emp1 = new Employee(201, "Bob Smith", "Marketing", 60000.0, "Analyst");
        Employee emp2 = new Employee(201, "Bob Smith", "Marketing", 60000.0, "Analyst");

        service.addEmployee(emp1);
        assertThrows(IllegalStateException.class, () -> service.addEmployee(emp2),
                "Adding duplicate ID must throw IllegalStateException");
    }

    @Test
    @DisplayName("Test employee promotion updates salary and role correctly")
    void testApplyPromotion() {
        Employee emp = new Employee(301, "Diana Prince", "Security", 95000.0, "Senior Officer");
        service.addEmployee(emp);

        service.applyPromotion(301, 15000.0, "Chief Security Officer");

        Optional<Employee> updated = service.findById(301);
        assertTrue(updated.isPresent());
        assertEquals(110000.0, updated.get().getSalary());
        assertEquals("Chief Security Officer", updated.get().getRole());
    }

    @Test
    @DisplayName("Test promoting non-existent employee throws IllegalArgumentException")
    void testPromoteNonExistentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.applyPromotion(999, 5000.0, "Lead"),
                "Promoting non-existent employee must throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Test total payroll calculation across multiple employees")
    void testCalculateTotalPayroll() {
        service.addEmployee(new Employee(1, "Emp1", "D1", 50000.0, "R1"));
        service.addEmployee(new Employee(2, "Emp2", "D2", 70000.0, "R2"));

        assertEquals(120000.0, service.calculateTotalPayroll(), 0.001);
    }
}
