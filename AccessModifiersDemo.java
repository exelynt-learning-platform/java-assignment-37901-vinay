/**
 * The Student class demonstrates encapsulation and Java access modifiers:
 * 1. private   : Accessible ONLY within this class.
 * 2. default   : Accessible within the same package (package-private).
 * 3. protected : Accessible within the same package and by subclasses.
 * 4. public    : Accessible from any class anywhere.
 */
class Student {
    // 1. private fields: Hidden from direct external access to enforce data encapsulation
    private String name;
    private int age;

    // 2. public field: Directly accessible from any class
    public char grade;

    // 3. protected field: Accessible within the package and by subclasses
    protected String schoolName;

    // 4. default (package-private) field: Accessible only within the same package
    int rollNumber;

    // Constructor
    public Student(String name, int age, char grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.schoolName = "Springfield Academy";
        this.rollNumber = 101;
    }

    // --- Public Getter and Setter Methods for Encapsulated (private) Fields ---

    /**
     * Getter for private field 'name'.
     * Provides controlled, read-only access to 'name'.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for private field 'name'.
     * Allows controlled modification with validation.
     */
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Validation Warning: Name cannot be empty.");
        }
    }

    /**
     * Getter for private field 'age'.
     * Returns the current age value.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Setter for private field 'age'.
     * Validates input before updating the private field.
     */
    public void setAge(int age) {
        if (age > 0 && age < 120) {
            this.age = age;
        } else {
            System.out.println("Validation Warning: Age must be a realistic positive number.");
        }
    }

    /**
     * Public method to display all student attributes.
     */
    public void displayStudentInfo() {
        System.out.println("Name        (private via method) : " + this.name);
        System.out.println("Age         (private via method) : " + this.age);
        System.out.println("Grade       (public)             : " + this.grade);
        System.out.println("School      (protected)          : " + this.schoolName);
        System.out.println("Roll Number (default)            : " + this.rollNumber);
    }
}

/**
 * AccessModifiersDemo tests encapsulation by attempting direct and indirect
 * access to fields and demonstrating controlled modification via getters and setters.
 */
public class AccessModifiersDemo {

    public static void main(String[] args) {
        System.out.println("=== Access Modifiers and Encapsulation Demo ===\n");

        // Create a Student instance
        Student student = new Student("Vinay", 20, 'A');

        System.out.println("--- Initial Student State ---");
        student.displayStudentInfo();

        // 1. Direct access to public field: Permitted from anywhere
        System.out.println("\n--- 1. Modifying Public Field Directly ---");
        student.grade = 'O'; // Directly updating public field
        System.out.println("Updated public field 'grade' directly: " + student.grade);

        // 2. Encapsulated access to private fields:
        // Direct access like 'student.name = "John";' or 'student.age = 25;'
        // causes a compilation error: "name has private access in Student"
        System.out.println("\n--- 2. Updating Encapsulated (Private) Fields via Setters ---");
        student.setName("Vinay Madarkhandi");
        student.setAge(21);

        // 3. Reading private fields via Getters
        System.out.println("Reading updated private field via getName(): " + student.getName());
        System.out.println("Reading updated private field via getAge() : " + student.getAge());

        // 4. Testing input validation through setters
        System.out.println("\n--- 3. Testing Encapsulation Input Validation ---");
        student.setAge(-5); // Triggers validation warning, preserves previous valid state

        System.out.println("\n--- Final Student State ---");
        student.displayStudentInfo();
    }
}
