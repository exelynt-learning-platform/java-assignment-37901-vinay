/**
 * The Student class models a student entity demonstrating core OOP concepts:
 * - Data Encapsulation: Private instance fields with public accessors and mutators.
 * - Static Variable: Shared school name accessible across all Student instances.
 * - Constructors: Default and parameterized constructors utilizing constructor chaining.
 * - 'this' Keyword: Resolves parameter shadowing and enables constructor chaining.
 * - Method Overloading: calculateGrade() implemented with and without extra credit.
 */
class Student {
    // 1. Private fields (Encapsulation)
    private String name;
    private int age;
    private double marks;

    // 2. Static variable shared across all Student instances
    public static String schoolName = "Global Excellence Academy";

    /**
     * Default Constructor:
     * Initializes default attributes using constructor chaining with 'this(...)'.
     */
    public Student() {
        this("Unknown Student", 18, 0.0);
    }

    /**
     * Parameterized Constructor:
     * Initializes a student object with user-provided arguments.
     * Uses 'this' to distinguish instance fields from parameters.
     *
     * @param name  Student's name
     * @param age   Student's age
     * @param marks Student's initial marks
     */
    public Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // --- Getters and Setters ---
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    public double getMarks() {
        return this.marks;
    }

    public void setMarks(double marks) {
        if (marks >= 0.0 && marks <= 100.0) {
            this.marks = marks;
        }
    }

    /**
     * Standard calculateGrade() method:
     * Evaluates the student's current marks and returns a letter grade.
     * Scale: >= 90: A, >= 75: B, >= 50: C, < 50: F.
     *
     * @return char representing letter grade
     */
    public char calculateGrade() {
        if (this.marks >= 90.0) {
            return 'A';
        } else if (this.marks >= 75.0) {
            return 'B';
        } else if (this.marks >= 50.0) {
            return 'C';
        } else {
            return 'F';
        }
    }

    /**
     * Overloaded calculateGrade(double extraCredit) method:
     * Adds extra credit marks to the current marks and calculates the new grade.
     * Demonstrates compile-time method overloading.
     *
     * @param extraCredit Additional credit marks
     * @return char representing letter grade after extra credit
     */
    public char calculateGrade(double extraCredit) {
        double effectiveMarks = this.marks + extraCredit;
        if (effectiveMarks > 100.0) {
            effectiveMarks = 100.0;
        }

        if (effectiveMarks >= 90.0) {
            return 'A';
        } else if (effectiveMarks >= 75.0) {
            return 'B';
        } else if (effectiveMarks >= 50.0) {
            return 'C';
        } else {
            return 'F';
        }
    }

    /**
     * Public method to display comprehensive student details.
     */
    public void displayDetails() {
        System.out.println("School Name    : " + schoolName);
        System.out.println("Student Name   : " + this.name);
        System.out.println("Age            : " + this.age);
        System.out.printf("Marks          : %.2f / 100.00\n", this.marks);
        System.out.println("Standard Grade : " + calculateGrade());
        System.out.println("--------------------------------------------------");
    }
}

/**
 * StudentManagement acts as the driver class to instantiate Student objects,
 * test constructors, methods, method overloading, access modifiers, and static variables.
 */
public class StudentManagement {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM (OOP DEMO)       ");
        System.out.println("==================================================");

        // Display shared static variable
        System.out.println("Initial School: " + Student.schoolName + "\n");

        // 1. Create Student 1 using the Default Constructor
        System.out.println("--- Student 1: Initialized with Default Constructor ---");
        Student student1 = new Student();
        student1.displayDetails();

        // Update student1 using public setter methods (encapsulation)
        System.out.println("Updating Student 1 using setters...");
        student1.setName("Vinay Madarkhandi");
        student1.setAge(21);
        student1.setMarks(72.5);
        student1.displayDetails();

        // 2. Create Student 2 using Parameterized Constructor
        System.out.println("--- Student 2: Initialized with Parameterized Constructor ---");
        Student student2 = new Student("Aarav Patel", 20, 94.0);
        student2.displayDetails();

        // 3. Create Student 3 using Parameterized Constructor
        System.out.println("--- Student 3: Initialized with Parameterized Constructor ---");
        Student student3 = new Student("Zara Khan", 19, 48.0);
        student3.displayDetails();

        // 4. Demonstrate Method Overloading with Extra Credit
        System.out.println("=== Testing Overloaded calculateGrade() with Extra Credit ===");
        System.out.println(student1.getName() + " marks: " + student1.getMarks() + 
            " | Standard Grade: " + student1.calculateGrade() + 
            " | With +5 extra credit: " + student1.calculateGrade(5.0));

        System.out.println(student3.getName() + " marks: " + student3.getMarks() + 
            " | Standard Grade: " + student3.calculateGrade() + 
            " | With +5 extra credit: " + student3.calculateGrade(5.0));

        // 5. Demonstrate Static Variable modification across all instances
        System.out.println("\n=== Modifying Static Variable schoolName ===");
        Student.schoolName = "Exelynt Institute of Technology";
        System.out.println("Updated static schoolName: " + Student.schoolName);
        System.out.println("Student 1 school: " + Student.schoolName);
        System.out.println("Student 2 school: " + Student.schoolName);
        System.out.println("Student 3 school: " + Student.schoolName);

        System.out.println("\n==================================================");
        System.out.println("   Student Management System execution complete!  ");
        System.out.println("==================================================");
    }
}
