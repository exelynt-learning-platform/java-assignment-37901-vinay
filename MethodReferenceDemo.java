import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * MethodReferenceDemo demonstrates the four types of Method References
 * and Constructor References introduced in Java 8 as concise alternatives
 * to lambda expressions.
 *
 * Comparison: Lambda Expression vs. Method Reference
 * --------------------------------------------------
 * 1. Readability: Method references are shorter and more readable when a lambda
 *    merely delegates directly to an existing method or constructor.
 *    - Lambda:             s -> System.out.println(s)
 *    - Method Reference:   System.out::println
 *
 * 2. Boilerplate: Method references eliminate repetitive argument declarations
 *    and parameter passing.
 *    - Lambda:             name -> new Student(name)
 *    - Constructor Ref:    Student::new
 *
 * 3. When to use what:
 *    - Use Method References when the lambda only calls an existing method with no extra operations.
 *    - Use Lambdas when transformations, multiple statements, or custom logic are required.
 */
public class MethodReferenceDemo {

    // Student model class for demonstrating constructor references and method references
    static class Student {
        private final int id;
        private final String name;
        private final double score;

        // Default constructor
        public Student() {
            this(0, "Unknown", 0.0);
        }

        // Single-parameter constructor for constructor reference
        public Student(String name) {
            this(100, name, 85.0);
        }

        // Full parameterized constructor
        public Student(int id, String name, double score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getScore() {
            return score;
        }

        public String getUppercaseName() {
            return this.name.toUpperCase();
        }

        @Override
        public String toString() {
            return String.format("Student[ID=%d, Name='%s', Score=%.1f]", id, name, score);
        }
    }

    // Custom Functional Interface for multi-parameter constructor reference
    @FunctionalInterface
    interface StudentCreator {
        Student create(int id, String name, double score);
    }

    // Static helper method for demonstrating static method references
    public static void displayStudentHeader(String header) {
        System.out.println(">>> " + header.toUpperCase());
    }

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("   METHOD REFERENCES & CONSTRUCTOR REFERENCES DEMO            ");
        System.out.println("===============================================================");

        // =====================================================================
        // 1. Method Reference to an Instance Method of a Particular Object
        // Syntax: object::instanceMethod
        // Lambda equivalent: str -> System.out.println(str)
        // Method reference: System.out::println
        // =====================================================================
        System.out.println("\n--- 1. Printing Values using Method Reference (System.out::println) ---");

        List<String> studentNames = Arrays.asList("Vinay Madarkhandi", "Alice Johnson", "Bob Smith", "Charlie Brown");

        System.out.println("Printing using Lambda: ");
        studentNames.forEach(name -> System.out.println("  [Lambda] " + name));

        System.out.println("\nPrinting using Method Reference (System.out::println): ");
        studentNames.forEach(System.out::println);

        // =====================================================================
        // 2. Constructor Reference to Create Objects
        // Syntax: ClassName::new
        // Lambda equivalent: name -> new Student(name)
        // Constructor reference: Student::new
        // =====================================================================
        System.out.println("\n--- 2. Creating Objects using Constructor Reference (Student::new) ---");

        // Single-arg constructor reference using Function<String, Student>
        Function<String, Student> studentFactory1 = Student::new; // Equivalent to: name -> new Student(name)
        Student s1 = studentFactory1.apply("Vinay Madarkhandi");
        Student s2 = studentFactory1.apply("Diana Prince");

        System.out.println("Created via Function<String, Student> constructor reference:");
        System.out.println("  " + s1);
        System.out.println("  " + s2);

        // Multi-arg constructor reference using custom functional interface
        StudentCreator studentFactory2 = Student::new; // Equivalent to: (id, name, score) -> new Student(id, name, score)
        Student s3 = studentFactory2.create(101, "Bruce Wayne", 94.5);
        Student s4 = studentFactory2.create(102, "Clark Kent", 98.0);

        System.out.println("\nCreated via StudentCreator constructor reference:");
        System.out.println("  " + s3);
        System.out.println("  " + s4);

        // =====================================================================
        // 3. Reference to a Static Method
        // Syntax: ClassName::staticMethod
        // Lambda equivalent: str -> MethodReferenceDemo.displayStudentHeader(str)
        // Method reference: MethodReferenceDemo::displayStudentHeader
        // =====================================================================
        System.out.println("\n--- 3. Static Method Reference (ClassName::staticMethod) ---");
        Consumer<String> headerPrinter = MethodReferenceDemo::displayStudentHeader;
        headerPrinter.accept("All students processed successfully");

        // =====================================================================
        // 4. Reference to an Instance Method of an Arbitrary Object of a Type
        // Syntax: ClassName::instanceMethod
        // Lambda equivalent: student -> student.getUppercaseName()
        // Method reference: Student::getUppercaseName
        // =====================================================================
        System.out.println("\n--- 4. Arbitrary Object Instance Method Reference ---");
        List<Student> students = Arrays.asList(s1, s2, s3, s4);

        System.out.println("Uppercase Names via Student::getUppercaseName:");
        students.stream()
                .map(Student::getUppercaseName)
                .forEach(System.out::println);

        System.out.println("\n===============================================================");
        System.out.println("Method Reference & Constructor Reference demo completed!");
        System.out.println("===============================================================");
    }
}
