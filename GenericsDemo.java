import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * GenericsDemo demonstrates the power and safety of Java Generics:
 *
 * 1. What are Generics?
 *    - Introduced in Java 5, Generics enable types (classes and interfaces) to be
 *      parameters when defining classes, interfaces, and methods.
 *    - Syntax: CollectionName<ElementType> variableName = new ConcreteCollection<>();
 *
 * 2. Key Benefits of Generics:
 *    a) Compile-Time Type Safety:
 *       - The Java compiler checks that only elements of the specified type are
 *         added to the collection, catching type mismatches at compile time rather
 *         than at runtime.
 *    b) Elimination of Type Casting:
 *       - Before Generics, collections stored Objects, requiring manual casting:
 *         Integer id = (Integer) rawList.get(0); // Risky!
 *       - With Generics, casting is automatic and guaranteed safe:
 *         Integer id = genericList.get(0); // Clean and safe!
 *    c) Prevention of ClassCastException:
 *       - Catches invalid data before the program even runs.
 * =============================================================================
 */
public class GenericsDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("      JAVA GENERICS & TYPE-SAFE COLLECTIONS DEMONSTRATION        ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // 1. Creating a Type-Safe Generic Collection for Student IDs
        // Type Parameter: <Integer> restricts the list to only Integer objects.
        // ---------------------------------------------------------------------
        List<Integer> studentIds = new ArrayList<>();

        // Adding valid Integer elements (Autoboxing converts int to Integer)
        studentIds.add(1001);
        studentIds.add(1002);
        studentIds.add(1003);
        studentIds.add(1004);
        studentIds.add(1005);

        System.out.println("--- 1. Populating Generic Collection <Integer> ---");
        System.out.println("Successfully added valid Integer student IDs to List<Integer>.");

        // ---------------------------------------------------------------------
        // 2. Demonstrating Compile-Time Type Safety
        // The compiler rejects attempts to add incompatible types.
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. Compile-Time Type Safety Verification ---");
        System.out.println("The following invalid additions are prevented by the Java compiler:");

        /*
         * ATTEMPTING TO ADD INVALID DATA (COMMENTED OUT TO PERMIT COMPILATION):
         *
         * // Compilation Error 1: Adding a String to List<Integer>
         * studentIds.add("STUDENT-9999");
         * >> ERROR: java: incompatible types: java.lang.String cannot be converted to java.lang.Integer
         * >> WHY IT FAILS: Generics strictly enforces that only 'Integer' or its subtypes
         *    can be passed to add(). A String is an incompatible type.
         *
         * // Compilation Error 2: Adding a double to List<Integer>
         * studentIds.add(45.67);
         * >> ERROR: java: incompatible types: double cannot be converted to java.lang.Integer
         * >> WHY IT FAILS: 'double' boxes to 'Double', which cannot be assigned to an 'Integer'.
         *
         * // Compilation Error 3: Adding a boolean to List<Integer>
         * studentIds.add(true);
         * >> ERROR: java: incompatible types: boolean cannot be converted to java.lang.Integer
         */
        System.out.println("  [Blocked]: studentIds.add(\"STUDENT-9999\"); -> String rejected by compiler!");
        System.out.println("  [Blocked]: studentIds.add(45.67);          -> Double rejected by compiler!");
        System.out.println("  [Blocked]: studentIds.add(true);           -> Boolean rejected by compiler!");

        // ---------------------------------------------------------------------
        // 3. Retrieval without Manual Type Casting
        // Because of Generics, the compiler guarantees that every element is an Integer.
        // No manual '(Integer)' cast is needed!
        // ---------------------------------------------------------------------
        System.out.println("\n--- 3. Direct Retrieval Without Type Casting ---");
        int firstId = studentIds.get(0); // Auto-unboxing and no casting needed!
        System.out.println("First student ID retrieved directly (int): " + firstId);

        // ---------------------------------------------------------------------
        // 4. Traversing Type-Safe Collection
        // ---------------------------------------------------------------------
        System.out.println("\n=================================================================");
        System.out.println("              VERIFIED TYPE-SAFE STUDENT ID ROSTER               ");
        System.out.println("=================================================================");
        for (int i = 0; i < studentIds.size(); i++) {
            // Direct assignment to Integer/int without casting
            Integer id = studentIds.get(i);
            System.out.printf("  Record #%d -> Student ID: %d (Class: %s)\n",
                    (i + 1), id, id.getClass().getSimpleName());
        }

        System.out.println("=================================================================");
        System.out.println("Generics type safety and compile-time verification complete!");
    }
}
