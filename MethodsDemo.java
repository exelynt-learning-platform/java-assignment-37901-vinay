/**
 * The Student class illustrates defining and invoking methods with different
 * return types: void (returns no value) and String (returns a String object).
 */
class Student {
    // Instance fields
    String name;
    double marks;

    // Constructor to initialize Student object
    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    /**
     * Method with a return type (String):
     * Evaluates the student's marks and returns the corresponding letter grade.
     * Demonstrates returning data to the caller using the 'return' keyword.
     *
     * @return String representing the calculated grade
     */
    public String calculateGrade() {
        if (this.marks >= 90.0) {
            return "Grade A";
        } else if (this.marks >= 75.0) {
            return "Grade B";
        } else if (this.marks >= 50.0) {
            return "Grade C";
        } else {
            return "Grade F";
        }
    }

    /**
     * Method with a void return type:
     * Prints the student's name, marks, and calculated grade directly to the console.
     * The 'void' keyword signifies that this method performs an action without
     * returning any value to the caller.
     */
    public void displayDetails() {
        System.out.println("Student Name    : " + this.name);
        System.out.printf("Marks Obtained  : %.2f / 100.00\n", this.marks);
        // Invoking the return-type method calculateGrade() within displayDetails()
        System.out.println("Calculated Grade: " + calculateGrade());
    }
}

/**
 * MethodsDemo tests defining, invoking, and processing results of methods
 * with and without return types for multiple objects.
 */
public class MethodsDemo {

    public static void main(String[] args) {
        System.out.println("=== Methods in Java: Return Types vs Void ===\n");

        // Create multiple Student objects
        Student student1 = new Student("Alice Johnson", 92.5);
        Student student2 = new Student("Bob Smith", 78.0);
        Student student3 = new Student("Charlie Brown", 45.0);

        // 1. Invoking displayDetails() (void return type)
        System.out.println("--- Student 1 Report (via void method) ---");
        student1.displayDetails();

        System.out.println("\n--- Student 2 Report (via void method) ---");
        student2.displayDetails();

        System.out.println("\n--- Student 3 Report (via void method) ---");
        student3.displayDetails();

        // 2. Invoking calculateGrade() directly and storing/using the returned value
        System.out.println("\n--- Direct Return Type Invocations ---");
        String grade1 = student1.calculateGrade();
        String grade2 = student2.calculateGrade();
        String grade3 = student3.calculateGrade();

        System.out.println(student1.name + "'s returned grade: " + grade1);
        System.out.println(student2.name + "'s returned grade: " + grade2);
        System.out.println(student3.name + "'s returned grade: " + grade3);
    }
}
