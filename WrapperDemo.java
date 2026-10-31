import java.util.Scanner;

/**
 * =============================================================================
 * WrapperDemo demonstrates:
 *
 * 1. WHAT ARE WRAPPER CLASSES?
 *    - In Java, primitive data types (byte, short, int, long, float, double,
 *      char, boolean) are not objects.
 *    - Wrapper classes (Byte, Short, Integer, Long, Float, Double, Character, Boolean)
 *      wrap/encapsulate these primitive values into full-fledged Java objects.
 *    - They are essential for using primitives in Java Collections (e.g., ArrayList<Double>),
 *      generics, and provide useful utility methods for type parsing (e.g., Double.parseDouble).
 *
 * 2. HOW AUTOBOXING WORKS:
 *    - Introduced in Java 5, Autoboxing is the automatic conversion that the Java
 *      compiler makes between the primitive types and their corresponding object
 *      wrapper classes.
 *    - Example: Assigning a primitive double '88.5' directly to a 'Double' reference:
 *      Double wrappedVal = 88.5; // Compiler translates to: Double.valueOf(88.5)
 *
 * 3. HOW UNBOXING WORKS:
 *    - Unboxing is the reverse process of autoboxing: the automatic conversion
 *      of a wrapper class object back into its corresponding primitive type.
 *    - Example: Assigning a Double object directly to a primitive double:
 *      double primitiveVal = wrappedVal; // Compiler translates to: wrappedVal.doubleValue()
 * =============================================================================
 */
public class WrapperDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("   WRAPPER CLASSES, AUTOBOXING & UNBOXING DEMONSTRATION          ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // Step 1: Accept Student Marks as String Input
        // ---------------------------------------------------------------------
        System.out.println("Enter marks for three subjects as String representations (e.g., \"85.5\"):");

        System.out.print("Enter Subject 1 Marks (String): ");
        String markStr1 = scanner.nextLine();

        System.out.print("Enter Subject 2 Marks (String): ");
        String markStr2 = scanner.nextLine();

        System.out.print("Enter Subject 3 Marks (String): ");
        String markStr3 = scanner.nextLine();

        // ---------------------------------------------------------------------
        // Step 2: Convert String Values to Numeric Types using Wrapper Methods
        // Wrapper class method Double.parseDouble() parses the String into a primitive double
        // Double.valueOf() parses the String directly into a Double wrapper object
        // ---------------------------------------------------------------------
        System.out.println("\n--- 1. Parsing Strings to Numeric Types via Wrapper Class Methods ---");
        double mark1Primitive = Double.parseDouble(markStr1.trim()); // String -> primitive double
        double mark2Primitive = Double.parseDouble(markStr2.trim());
        double mark3Primitive = Double.parseDouble(markStr3.trim());

        System.out.println("Parsed primitive double 1: " + mark1Primitive);
        System.out.println("Parsed primitive double 2: " + mark2Primitive);
        System.out.println("Parsed primitive double 3: " + mark3Primitive);

        // ---------------------------------------------------------------------
        // Step 3: Demonstrate Autoboxing
        // Assigning primitive 'double' values directly to 'Double' wrapper objects.
        // The compiler automatically boxes the primitive into an object behind the scenes.
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. Demonstrating Autoboxing (Primitive -> Wrapper Object) ---");
        Double boxedMark1 = mark1Primitive; // Autoboxing: double -> Double object
        Double boxedMark2 = mark2Primitive; // Autoboxing
        Double boxedMark3 = mark3Primitive; // Autoboxing

        System.out.println("Autoboxed Object 1 (Double): " + boxedMark1 + " | Class: " + boxedMark1.getClass().getSimpleName());
        System.out.println("Autoboxed Object 2 (Double): " + boxedMark2 + " | Class: " + boxedMark2.getClass().getSimpleName());
        System.out.println("Autoboxed Object 3 (Double): " + boxedMark3 + " | Class: " + boxedMark3.getClass().getSimpleName());

        // ---------------------------------------------------------------------
        // Step 4: Demonstrate Unboxing
        // Converting Wrapper class objects back into primitive types.
        // The compiler automatically extracts the primitive value (.doubleValue()).
        // ---------------------------------------------------------------------
        System.out.println("\n--- 3. Demonstrating Unboxing (Wrapper Object -> Primitive) ---");
        double unboxedMark1 = boxedMark1; // Unboxing: Double object -> primitive double
        double unboxedMark2 = boxedMark2; // Unboxing
        double unboxedMark3 = boxedMark3; // Unboxing

        System.out.println("Unboxed Primitive 1 (double): " + unboxedMark1);
        System.out.println("Unboxed Primitive 2 (double): " + unboxedMark2);
        System.out.println("Unboxed Primitive 3 (double): " + unboxedMark3);

        // ---------------------------------------------------------------------
        // Step 5: Calculate and Display Average Marks
        // Arithmetic expressions seamlessly combine unboxed values.
        // ---------------------------------------------------------------------
        double totalMarks = unboxedMark1 + unboxedMark2 + unboxedMark3;
        double averageMarks = totalMarks / 3.0;

        // Autoboxing the result into a Double object for display
        Double boxedAverage = averageMarks;

        System.out.println("\n=================================================================");
        System.out.println("                   STUDENT PERFORMANCE REPORT                    ");
        System.out.println("=================================================================");
        System.out.printf("Subject 1 Marks : %.2f\n", unboxedMark1);
        System.out.printf("Subject 2 Marks : %.2f\n", unboxedMark2);
        System.out.printf("Subject 3 Marks : %.2f\n", unboxedMark3);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("Total Marks     : %.2f / 300.00\n", totalMarks);
        System.out.printf("Average Marks   : %.2f%%\n", boxedAverage);
        System.out.println("Status          : " + (boxedAverage >= 50.0 ? "PASSED" : "FAILED"));
        System.out.println("=================================================================");

        scanner.close();
    }
}
