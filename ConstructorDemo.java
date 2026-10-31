/**
 * The Student class demonstrates constructor overloading with both
 * a default (no-argument) constructor and a parameterized constructor.
 */
class Student {
    // Instance fields
    String name;
    int age;
    char grade;

    /**
     * Default Constructor (no arguments):
     * Initializes a newly created Student object with predefined default values.
     * Executes automatically when 'new Student()' is called.
     */
    public Student() {
        this.name = "Unknown";
        this.age = 18;
        this.grade = 'N'; // N for Not Assigned
    }

    /**
     * Parameterized Constructor:
     * Accepts arguments to initialize the object's instance fields with specific values
     * provided at the time of instantiation.
     *
     * @param name  Student's name
     * @param age   Student's age
     * @param grade Student's assigned letter grade
     */
    public Student(String name, int age, char grade) {
        // 'this' keyword distinguishes instance fields from local parameters
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    /**
     * Displays the student's details in a formatted manner.
     */
    public void displayDetails() {
        System.out.println("Name : " + this.name);
        System.out.println("Age  : " + this.age);
        System.out.println("Grade: " + this.grade);
    }
}

/**
 * ConstructorDemo demonstrates object instantiation using both default
 * and parameterized constructors in Java.
 */
public class ConstructorDemo {

    public static void main(String[] args) {
        System.out.println("=== Constructors in Java: Default vs Parameterized ===\n");

        // 1. Instantiating an object using the Default Constructor
        // This invokes Student() and assigns default values ("Unknown", 18, 'N')
        System.out.println("--- Student 1: Initialized with Default Constructor ---");
        Student student1 = new Student();
        student1.displayDetails();

        // 2. Instantiating objects using the Parameterized Constructor
        // This invokes Student(String, int, char) and initializes fields with specific values
        System.out.println("\n--- Student 2: Initialized with Parameterized Constructor ---");
        Student student2 = new Student("Vinay", 21, 'A');
        student2.displayDetails();

        System.out.println("\n--- Student 3: Initialized with Parameterized Constructor ---");
        Student student3 = new Student("Priya Sharma", 22, 'B');
        student3.displayDetails();
    }
}
