import java.util.ArrayList;

/**
 * =============================================================================
 * 1. Interface: Payable
 * Defines a contract for entities entitled to compensation/salary.
 * =============================================================================
 */
interface Payable {
    /**
     * Calculates the monthly or total salary of the payable entity.
     *
     * @return double representing calculated salary
     */
    double calculateSalary();
}

/**
 * =============================================================================
 * 2. Abstract Base Class: Person
 * Implements Encapsulation with private fields and public accessors/mutators.
 * Defines the abstract method displayRole() that every subclass must implement.
 * =============================================================================
 */
abstract class Person {
    // Encapsulated private fields
    private String name;
    private int age;

    // Parameterized constructor
    public Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    // --- Getters and Setters with Validation ---
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            this.name = "Unknown";
        }
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            this.age = 18;
        }
    }

    /**
     * Abstract method: Subclasses must provide role-specific display logic.
     */
    public abstract void displayRole();
}

/**
 * =============================================================================
 * 3. Subclass: Student extends Person
 * Inherits common person traits and specializes with student-specific attributes.
 * =============================================================================
 */
class Student extends Person {
    private String studentId;
    private String gradeLevel;

    public Student(String name, int age, String studentId, String gradeLevel) {
        super(name, age);
        this.studentId = studentId;
        this.gradeLevel = gradeLevel;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    @Override
    public void displayRole() {
        System.out.println("Role        : Student");
        System.out.println("Name        : " + getName());
        System.out.println("Age         : " + getAge());
        System.out.println("Student ID  : " + this.studentId);
        System.out.println("Grade Level : " + this.gradeLevel);
        System.out.println("Status      : Enrolled in classes");
    }
}

/**
 * =============================================================================
 * 4. Subclass: Teacher extends Person implements Payable
 * Demonstrates:
 * - Single Inheritance: Teacher extends Person
 * - Interface Implementation: Teacher implements Payable
 * =============================================================================
 */
class Teacher extends Person implements Payable {
    private String employeeId;
    private String subject;
    private double baseSalary;
    private double performanceBonus;

    public Teacher(String name, int age, String employeeId, String subject, double baseSalary, double performanceBonus) {
        super(name, age);
        this.employeeId = employeeId;
        this.subject = subject;
        this.baseSalary = baseSalary;
        this.performanceBonus = performanceBonus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getSubject() {
        return subject;
    }

    /**
     * Implementation of Payable interface method.
     */
    @Override
    public double calculateSalary() {
        return this.baseSalary + this.performanceBonus;
    }

    /**
     * Implementation of Person abstract method.
     */
    @Override
    public void displayRole() {
        System.out.println("Role        : Teacher");
        System.out.println("Name        : " + getName());
        System.out.println("Age         : " + getAge());
        System.out.println("Employee ID : " + this.employeeId);
        System.out.println("Subject     : " + this.subject);
        System.out.printf("Total Salary: $%.2f (Base: $%.2f + Bonus: $%.2f)\n",
                calculateSalary(), this.baseSalary, this.performanceBonus);
    }
}

/**
 * =============================================================================
 * 5. Driver Class: SchoolManagement
 * Demonstrates:
 * - Polymorphism: Superclass reference (Person) pointing to Student and Teacher objects.
 * - Collections: ArrayList<Person> to store, manage, and iterate school members.
 * - Interface polymorphism: Checking and invoking Payable methods.
 * =============================================================================
 */
public class SchoolManagement {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("        MINI PROJECT: SCHOOL MANAGEMENT SYSTEM    ");
        System.out.println("==================================================\n");

        // 1. Collections: Using ArrayList to store polymorphic Person objects
        ArrayList<Person> schoolDirectory = new ArrayList<>();

        // Add students
        schoolDirectory.add(new Student("Alice Johnson", 16, "STU-1001", "10th Grade"));
        schoolDirectory.add(new Student("Vinay Madarkhandi", 17, "STU-1002", "11th Grade"));

        // Add teachers
        schoolDirectory.add(new Teacher("Dr. Robert Smith", 45, "EMP-2001", "Computer Science", 6500.0, 750.0));
        schoolDirectory.add(new Teacher("Prof. Maria Garcia", 38, "EMP-2002", "Mathematics", 6000.0, 500.0));

        // 2. Polymorphic Iteration: Person reference executing overridden displayRole()
        System.out.println("--- School Directory Listing (Polymorphic Iteration) ---\n");
        int index = 1;
        for (Person person : schoolDirectory) {
            System.out.println("Member #" + index++ + ":");
            // Dynamic Method Dispatch: Calls Student.displayRole() or Teacher.displayRole()
            person.displayRole();
            System.out.println("--------------------------------------------------");
        }

        // 3. Processing Payroll with Interface Polymorphism
        System.out.println("\n--- Monthly Payroll Summary (Payable Interface Demo) ---");
        double totalPayroll = 0.0;
        for (Person person : schoolDirectory) {
            // Using instanceof to check if a person implements the Payable interface
            if (person instanceof Payable) {
                Payable payableStaff = (Payable) person;
                double salary = payableStaff.calculateSalary();
                totalPayroll += salary;
                System.out.printf("Paid: %-20s | Salary: $%.2f\n", person.getName(), salary);
            }
        }
        System.out.printf("Total Monthly School Payroll Expenditure: $%.2f\n", totalPayroll);

        System.out.println("\n==================================================");
        System.out.println("   School Management System execution successful! ");
        System.out.println("==================================================");
    }
}
