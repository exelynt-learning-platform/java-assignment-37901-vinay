/**
 * The Student class serves as a blueprint (template) for student entities.
 * It contains instance fields representing the state/attributes of a student.
 */
class Student {
    // Instance fields (attributes) of the Student class
    String name;
    int age;
}

/**
 * StudentDemo demonstrates the creation of objects and accessing their
 * instance variables in Java.
 */
public class StudentDemo {

    public static void main(String[] args) {
        System.out.println("=== Introduction to OOP: Classes & Objects ===\n");

        // --- Object Creation 1 ---
        // 'new Student()' allocates memory for a new Student object on the heap
        // 'student1' is a reference variable holding the memory address of the object
        Student student1 = new Student();

        // Assigning values to student1's instance fields using dot notation
        student1.name = "Alice Johnson";
        student1.age = 20;

        // --- Object Creation 2 ---
        // Creating a second, distinct Student instance with its own state
        Student student2 = new Student();

        // Assigning values to student2's instance fields
        student2.name = "Bob Smith";
        student2.age = 22;

        // --- Accessing and Printing Object Details ---
        System.out.println("--- Student 1 Details ---");
        System.out.println("Name: " + student1.name);
        System.out.println("Age : " + student1.age);

        System.out.println("\n--- Student 2 Details ---");
        System.out.println("Name: " + student2.name);
        System.out.println("Age : " + student2.age);
    }
}
