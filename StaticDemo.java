/**
 * The Student class demonstrates static members in Java:
 * 1. static variable (schoolName): Belongs to the class itself rather than any specific
 *    instance. Allocated once in memory (in Metaspace/class area) and shared by all objects.
 * 2. static method (displaySchoolName): Can be invoked directly using the Class name
 *    (Student.displaySchoolName()) without instantiating any object.
 */
class Student {
    // Instance fields: Each object maintains its own independent copy of these variables
    String name;
    double marks;

    // Static variable: Shared across all instances of the Student class
    static String schoolName = "Global High School";

    // Constructor to initialize instance variables
    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    /**
     * Static method:
     * Associated with the class, not with any particular object instance.
     * Can access static variables directly, but cannot access non-static instance fields.
     */
    public static void displaySchoolName() {
        System.out.println("School Name (via static method): " + schoolName);
    }

    /**
     * Instance method:
     * Displays both instance-specific details and the shared static variable.
     */
    public void displayStudentDetails() {
        System.out.println("Student Name : " + this.name);
        System.out.println("Student Marks: " + this.marks);
        System.out.println("School Name  : " + schoolName); // Accessing shared static variable
    }
}

/**
 * StaticDemo tests static variables and methods, demonstrating shared memory
 * across multiple instances and invoking static methods without object creation.
 */
public class StaticDemo {

    public static void main(String[] args) {
        System.out.println("=== Static Variables and Methods Demo ===\n");

        // 1. Calling static method directly without creating any object
        System.out.println("--- 1. Invoking Static Method without Object Creation ---");
        Student.displaySchoolName();

        // 2. Instantiating multiple objects
        System.out.println("\n--- 2. Creating Multiple Student Objects ---");
        Student student1 = new Student("Vinay", 91.5);
        Student student2 = new Student("Anita", 87.0);

        System.out.println(">> Student 1 Details:");
        student1.displayStudentDetails();

        System.out.println("\n>> Student 2 Details:");
        student2.displayStudentDetails();

        // 3. Demonstrating that all objects share the exact same static variable
        System.out.println("\n--- 3. Modifying Static Variable at Class Level ---");
        System.out.println("Updating Student.schoolName to 'Exelynt International Academy'...");
        Student.schoolName = "Exelynt International Academy";

        // Displaying details again to verify the change is reflected in both objects
        System.out.println("\n>> Student 1 Details after modifying static variable:");
        student1.displayStudentDetails();

        System.out.println("\n>> Student 2 Details after modifying static variable:");
        student2.displayStudentDetails();

        // 4. Calling static method again to confirm updated static state
        System.out.println("\n--- 4. Calling Static Method after Update ---");
        Student.displaySchoolName();
    }
}
