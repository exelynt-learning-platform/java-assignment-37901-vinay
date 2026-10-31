/**
 * =============================================================================
 * Parent Class: Employee
 * Defines base fields and the method displayDetails() to be overridden.
 * =============================================================================
 */
class Employee {
    String name;
    double salary;

    // Constructor to initialize Employee instance
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * Base implementation of displayDetails().
     * Displays general employee information.
     */
    public void displayDetails() {
        System.out.println("Employee Name  : " + this.name);
        System.out.printf("Employee Salary: $%.2f\n", this.salary);
    }
}

/**
 * =============================================================================
 * Subclass: Manager extends Employee
 * Demonstrates Method Overriding (Runtime Polymorphism):
 * - Subclass provides its own specific implementation of displayDetails().
 * - Uses 'super.displayDetails()' to reuse parent logic before adding subclass-specific details.
 * =============================================================================
 */
class Manager extends Employee {
    String department;

    // Constructor invokes parent constructor via super(name, salary)
    public Manager(String name, double salary, String department) {
        super(name, salary); // Invokes Employee constructor
        this.department = department;
    }

    /**
     * Overridden displayDetails() method.
     * The @Override annotation confirms this method overrides a method in the superclass.
     * Uses 'super.displayDetails()' to execute the parent class implementation first.
     */
    @Override
    public void displayDetails() {
        // Calling parent class method using the 'super' keyword
        super.displayDetails();

        // Adding Manager-specific information
        System.out.println("Department     : " + this.department);
        System.out.println("Designation    : Manager");
    }
}

/**
 * =============================================================================
 * Driver Class: OverridingDemo
 * Tests method overriding and demonstrates dynamic method dispatch.
 * =============================================================================
 */
public class OverridingDemo {

    public static void main(String[] args) {
        System.out.println("=== Method Overriding & super Keyword Demo ===\n");

        // 1. Calling displayDetails() on a direct Employee object
        System.out.println("--- 1. Employee Object (Parent Implementation) ---");
        Employee emp = new Employee("Alice Johnson", 60000.0);
        emp.displayDetails();

        // 2. Calling overridden displayDetails() on a Manager object
        System.out.println("\n--- 2. Manager Object (Overridden Implementation with super) ---");
        Manager mgr = new Manager("Bob Williams", 95000.0, "Engineering");
        mgr.displayDetails();

        // 3. Demonstrating Runtime Polymorphism (Dynamic Method Dispatch)
        // Parent class reference pointing to a Subclass object
        System.out.println("\n--- 3. Dynamic Method Dispatch (Runtime Polymorphism) ---");
        Employee polyRef = new Manager("Charlie Davis", 105000.0, "Marketing");
        // Calls the overridden Manager version at runtime based on the actual object type
        polyRef.displayDetails();
    }
}
