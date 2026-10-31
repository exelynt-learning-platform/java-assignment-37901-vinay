/**
 * =============================================================================
 * Base Parent Class: Employee
 * Demonstrates:
 * 1. Compile-Time Polymorphism (Static Binding / Method Overloading):
 *    Multiple displayInfo() methods in the same class with different parameters.
 * 2. Method Overriding baseline for Runtime Polymorphism.
 * =============================================================================
 */
class Employee {
    String name;
    double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // --- Compile-Time Polymorphism: Method Overloading ---

    /**
     * Overloaded method 1 (no parameters):
     * Displays base employee info.
     */
    public void displayInfo() {
        System.out.println("[Employee] Name: " + this.name + ", Salary: $" + this.salary);
    }

    /**
     * Overloaded method 2 (with String message parameter):
     * Demonstrates method overloading by changing the parameter list.
     */
    public void displayInfo(String message) {
        System.out.println("[Employee Notice] " + message + " -> Name: " + this.name + ", Salary: $" + this.salary);
    }

    /**
     * Overloaded method 3 (with int performanceRating parameter):
     * Demonstrates method overloading by using a different parameter type.
     */
    public void displayInfo(int performanceRating) {
        System.out.println("[Performance Review] Name: " + this.name + " | Rating: " + performanceRating + "/5 Stars");
    }
}

/**
 * =============================================================================
 * Subclass 1: Manager extends Employee
 * Overrides displayInfo() to provide manager-specific behavior at runtime.
 * =============================================================================
 */
class Manager extends Employee {
    String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    // Overridden method for Runtime Polymorphism
    @Override
    public void displayInfo() {
        System.out.println("[Manager] Name: " + this.name + ", Salary: $" + this.salary + ", Department: " + this.department);
    }
}

/**
 * =============================================================================
 * Subclass 2: Intern extends Employee
 * Overrides displayInfo() to provide intern-specific behavior at runtime.
 * =============================================================================
 */
class Intern extends Employee {
    String university;

    public Intern(String name, double salary, String university) {
        super(name, salary);
        this.university = university;
    }

    // Overridden method for Runtime Polymorphism
    @Override
    public void displayInfo() {
        System.out.println("[Intern] Name: " + this.name + ", Stipend: $" + this.salary + ", University: " + this.university);
    }
}

/**
 * =============================================================================
 * Driver Class: PolymorphismDemo
 * Executes both Compile-Time and Runtime Polymorphism demonstrations.
 * =============================================================================
 */
public class PolymorphismDemo {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("   POLYMORPHISM IN JAVA: COMPILE-TIME VS RUNTIME DEMO          ");
        System.out.println("===============================================================\n");

        // ---------------------------------------------------------------------
        // PART 1: COMPILE-TIME POLYMORPHISM (Static / Early Binding)
        // Achieved through Method Overloading within the same class.
        // The Java compiler determines which method to bind at compile time based
        // on method signature (name, parameter count, and types).
        // ---------------------------------------------------------------------
        System.out.println("--- 1. Compile-Time Polymorphism (Method Overloading) ---");
        Employee generalEmp = new Employee("Vinay Madarkhandi", 70000.0);

        // Invoking overloaded version 1 (no args)
        generalEmp.displayInfo();

        // Invoking overloaded version 2 (String message)
        generalEmp.displayInfo("Annual Salary Review Completed");

        // Invoking overloaded version 3 (int rating)
        generalEmp.displayInfo(5);

        // ---------------------------------------------------------------------
        // PART 2: RUNTIME POLYMORPHISM (Dynamic Method Dispatch / Late Binding)
        // Achieved through Method Overriding via Inheritance.
        // A superclass reference (Employee) refers to subclass objects (Manager, Intern).
        // The decision of which method to execute is resolved at RUNTIME based on the
        // actual object type created on the heap, not the reference type.
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. Runtime Polymorphism (Dynamic Method Dispatch) ---");

        // Parent class reference pointing to a Manager object
        Employee empRef1 = new Manager("Sarah Connor", 110000.0, "Infrastructure");

        // Parent class reference pointing to an Intern object
        Employee empRef2 = new Intern("Alex Rivera", 30000.0, "MIT");

        // Dynamic binding: JVM invokes Manager's overridden displayInfo() at runtime
        System.out.print("Invoking empRef1.displayInfo(): ");
        empRef1.displayInfo();

        // Dynamic binding: JVM invokes Intern's overridden displayInfo() at runtime
        System.out.print("Invoking empRef2.displayInfo(): ");
        empRef2.displayInfo();

        // Demonstrating array of superclass references pointing to polymorphic objects
        System.out.println("\n--- 3. Polymorphic Array Processing ---");
        Employee[] companyStaff = {
            new Employee("John Doe", 55000.0),
            new Manager("Elena Rostova", 125000.0, "AI Research"),
            new Intern("Liam Patel", 32000.0, "UC Berkeley")
        };

        for (Employee staffMember : companyStaff) {
            staffMember.displayInfo(); // Automatically dispatches to the correct subclass method
        }

        System.out.println("\n===============================================================");
        System.out.println("   Both polymorphism mechanisms executed successfully!         ");
        System.out.println("===============================================================");
    }
}
