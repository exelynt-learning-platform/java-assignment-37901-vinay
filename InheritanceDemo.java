/**
 * =============================================================================
 * Base Parent Class: Employee
 * Defines common attributes and behavior shared across all types of employees.
 * =============================================================================
 */
class Employee {
    // Fields common to all employees
    String name;
    double salary;

    // Parameterized constructor
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Method to display base employee details
    public void displayEmployeeDetails() {
        System.out.println("Name  : " + this.name);
        System.out.printf("Salary: $%.2f\n", this.salary);
    }
}

/**
 * =============================================================================
 * 1. Single Inheritance: Manager extends Employee
 * In single inheritance, a single subclass derives directly from a single parent class.
 * Manager inherits 'name' and 'salary' from Employee and adds its own field 'department'.
 * =============================================================================
 */
class Manager extends Employee {
    String department;

    // Constructor using 'super' to invoke the parent class constructor
    public Manager(String name, double salary, String department) {
        super(name, salary); // Calls Employee(name, salary)
        this.department = department;
    }

    public void displayManagerDetails() {
        displayEmployeeDetails(); // Calls inherited method from Employee
        System.out.println("Role  : Manager");
        System.out.println("Dept  : " + this.department);
    }
}

/**
 * =============================================================================
 * 2. Multilevel Inheritance: SeniorManager extends Manager
 * In multilevel inheritance, a class extends a subclass, forming an inheritance chain.
 * Hierarchy: Employee -> Manager -> SeniorManager.
 * SeniorManager inherits attributes from both Employee and Manager.
 * =============================================================================
 */
class SeniorManager extends Manager {
    int teamSize;

    public SeniorManager(String name, double salary, String department, int teamSize) {
        super(name, salary, department); // Calls Manager constructor
        this.teamSize = teamSize;
    }

    public void displaySeniorManagerDetails() {
        displayManagerDetails(); // Calls inherited Manager method
        System.out.println("Role  : Senior Manager");
        System.out.println("Team  : " + this.teamSize + " members managed");
    }
}

/**
 * =============================================================================
 * 3. Hierarchical Inheritance: Intern extends Employee
 * In hierarchical inheritance, multiple subclasses inherit directly from the same
 * parent class. Both 'Manager' and 'Intern' extend 'Employee'.
 * =============================================================================
 */
class Intern extends Employee {
    String university;

    public Intern(String name, double salary, String university) {
        super(name, salary); // Calls Employee constructor
        this.university = university;
    }

    public void displayInternDetails() {
        displayEmployeeDetails(); // Calls inherited method from Employee
        System.out.println("Role  : Intern");
        System.out.println("School: " + this.university);
    }
}

/**
 * =============================================================================
 * Driver Class: InheritanceDemo
 * Demonstrates instantiation and member access for all three inheritance types.
 * =============================================================================
 */
public class InheritanceDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("        JAVA INHERITANCE TYPES DEMONSTRATION      ");
        System.out.println("==================================================\n");

        // 1. Demonstrate Single Inheritance (Employee -> Manager)
        System.out.println("--- 1. Single Inheritance (Manager extends Employee) ---");
        Manager manager = new Manager("Sarah Connor", 85000.0, "Engineering");
        manager.displayManagerDetails();
        System.out.println("--------------------------------------------------");

        // 2. Demonstrate Multilevel Inheritance (Employee -> Manager -> SeniorManager)
        System.out.println("\n--- 2. Multilevel Inheritance (SeniorManager extends Manager) ---");
        SeniorManager seniorManager = new SeniorManager("David Miller", 120000.0, "Operations", 25);
        seniorManager.displaySeniorManagerDetails();
        System.out.println("--------------------------------------------------");

        // 3. Demonstrate Hierarchical Inheritance (Employee -> Manager & Employee -> Intern)
        System.out.println("\n--- 3. Hierarchical Inheritance (Intern extends Employee) ---");
        Intern intern = new Intern("Vinay Madarkhandi", 35000.0, "Stanford University");
        intern.displayInternDetails();
        System.out.println("--------------------------------------------------");

        System.out.println("\nAll inheritance relationships demonstrated successfully!");
    }
}
