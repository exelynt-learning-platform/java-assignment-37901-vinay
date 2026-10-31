/**
 * The Student class demonstrates:
 * 1. The 'this' keyword:
 *    - To resolve variable shadowing between instance fields and method/constructor parameters.
 *    - For constructor chaining using this(...) to avoid redundant initialization logic.
 * 2. Method Overloading (Compile-Time Polymorphism):
 *    - Multiple methods sharing the same name but differing in parameter lists.
 */
class Student {
    // Instance fields
    String name;
    double marks;

    /**
     * Constructor 1 (Single parameter):
     * Uses the 'this(...)' call for constructor chaining to invoke Constructor 2
     * with a default marks value of 0.0.
     *
     * @param name Student's name
     */
    public Student(String name) {
        this(name, 0.0); // Constructor chaining using this()
    }

    /**
     * Constructor 2 (Two parameters):
     * Uses 'this.name' and 'this.marks' to distinguish instance fields from parameter variables.
     *
     * @param name  Student's name
     * @param marks Student's marks
     */
    public Student(String name, double marks) {
        this.name = name;   // 'this.name' refers to instance field; 'name' is the parameter
        this.marks = marks; // 'this.marks' refers to instance field; 'marks' is the parameter
    }

    /**
     * Overloaded Method Version 1 (no parameters):
     * Prints the student's name and marks only.
     */
    public void displayDetails() {
        System.out.println("Student Name : " + this.name);
        System.out.println("Student Marks: " + this.marks);
    }

    /**
     * Overloaded Method Version 2 (with String grade parameter):
     * Prints the student's name, marks, and letter grade.
     * Overloads displayDetails() by having a different parameter list.
     *
     * @param grade Assigned letter grade
     */
    public void displayDetails(String grade) {
        System.out.println("Student Name : " + this.name);
        System.out.println("Student Marks: " + this.marks);
        System.out.println("Student Grade: " + grade);
    }
}

/**
 * ThisOverloadDemo instantiates Student objects using chained constructors
 * and calls overloaded methods with different signatures.
 */
public class ThisOverloadDemo {

    public static void main(String[] args) {
        System.out.println("=== 'this' Keyword and Method Overloading Demo ===\n");

        // 1. Instantiating using Constructor 1 (which internally chains to Constructor 2 via this())
        System.out.println("--- Student 1: Created with Single-Argument Constructor (Chained) ---");
        Student student1 = new Student("Alice Walker");
        // Calling overloaded displayDetails() version 1 (no arguments)
        student1.displayDetails();

        // 2. Instantiating using Constructor 2 (explicitly assigning name and marks via this.name/this.marks)
        System.out.println("\n--- Student 2: Created with Two-Argument Constructor ---");
        Student student2 = new Student("Bob Miller", 88.5);

        // Calling overloaded displayDetails() version 1 (no arguments)
        System.out.println(">> Calling displayDetails() without grade:");
        student2.displayDetails();

        // Calling overloaded displayDetails() version 2 (with grade argument)
        System.out.println("\n>> Calling overloaded displayDetails(String grade) with grade:");
        student2.displayDetails("Grade A");
    }
}
