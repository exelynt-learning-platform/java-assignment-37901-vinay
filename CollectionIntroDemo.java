import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * CollectionIntroDemo demonstrates the Java Collections Framework (JCF)
 * and highlights key differences between traditional Arrays and Collections.
 *
 * =============================================================================
 * DETAILED COMPARISON: ARRAYS vs. JAVA COLLECTIONS
 * =============================================================================
 * 1. RESIZABILITY (SIZE):
 *    - Array      : Fixed size. Once declared (e.g. new String[5]), its length cannot
 *                   grow or shrink. Adding a 6th element requires creating a new array.
 *    - Collection : Dynamic size. Grows and shrinks automatically as elements are
 *                   added or removed.
 *
 * 2. DATA TYPES SUPPORTED:
 *    - Array      : Can store both primitive types (int, double) and object references.
 *    - Collection : Can ONLY store objects (reference types). Primitive types must be
 *                   boxed into Wrapper classes (e.g., Integer, Double).
 *
 * 3. BUILT-IN METHODS & FUNCTIONALITY:
 *    - Array      : Has very few built-in operations (only the '.length' property).
 *                   Searching, inserting, deleting, or sorting requires manual loops
 *                   or the Arrays utility class.
 *    - Collection : Rich API with powerful built-in methods: add(), remove(), contains(),
 *                   indexOf(), clear(), size(), iterator(), isEmpty(), and Collections.sort().
 *
 * 4. PERFORMANCE & MEMORY:
 *    - Array      : Faster access time with minimal memory overhead.
 *    - Collection : Slight memory overhead due to object wrappers and internal capacity
 *                   doubling, but significantly increases developer productivity.
 * =============================================================================
 */
public class CollectionIntroDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("       INTRODUCTION TO JAVA COLLECTIONS FRAMEWORK (List/ArrayList)");
        System.out.println("=================================================================\n");

        // 1. Initializing an ArrayList to store student names
        // Uses the List interface as the reference type (Polymorphic programming)
        List<String> studentList = new ArrayList<>();

        // ---------------------------------------------------------------------
        // OPERATION 1: add() - Adding Elements Dynamically
        // Unlike arrays, we do not need to specify an initial fixed capacity.
        // ---------------------------------------------------------------------
        System.out.println("--- 1. Performing 'add' Operations ---");
        studentList.add("Vinay Madarkhandi");
        studentList.add("Alice Johnson");
        studentList.add("Bob Smith");
        studentList.add("Charlie Brown");
        studentList.add("Diana Prince");

        // Inserting an element at a specific index
        studentList.add(2, "Zara Khan"); // Inserts at index 2, shifts subsequent elements right

        System.out.println("Initial student list added successfully!");
        System.out.println("Current Collection size via size(): " + studentList.size());

        // ---------------------------------------------------------------------
        // OPERATION 2: Displaying Elements
        // Traversing collection using an enhanced for-each loop
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. Displaying Student List ---");
        int index = 0;
        for (String student : studentList) {
            System.out.printf("  Index [%d]: %s\n", index++, student);
        }

        // ---------------------------------------------------------------------
        // OPERATION 3: remove() - Removing Elements
        // Demonstrates removing by object value and by index
        // ---------------------------------------------------------------------
        System.out.println("\n--- 3. Performing 'remove' Operations ---");

        // Removing by object value
        boolean removedCharlie = studentList.remove("Charlie Brown");
        System.out.println("Removed 'Charlie Brown' by value? " + removedCharlie);

        // Removing by index
        String removedStudentAtIndex1 = studentList.remove(1); // Removes element at index 1
        System.out.println("Removed student at index 1: " + removedStudentAtIndex1);

        System.out.println("Collection size after removals: " + studentList.size());

        // ---------------------------------------------------------------------
        // OPERATION 4: Search & Query Methods (contains, get)
        // ---------------------------------------------------------------------
        System.out.println("\n--- 4. Querying Collection State ---");
        System.out.println("Is 'Vinay Madarkhandi' in the list? " + studentList.contains("Vinay Madarkhandi"));
        System.out.println("Is 'Charlie Brown' in the list? " + studentList.contains("Charlie Brown"));
        System.out.println("First student (via get(0)): " + studentList.get(0));

        // ---------------------------------------------------------------------
        // FINAL COLLECTION DISPLAY
        // ---------------------------------------------------------------------
        System.out.println("\n=================================================================");
        System.out.println("                 FINAL ROSTER OF STUDENTS                        ");
        System.out.println("=================================================================");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.printf("Student #%d: %s\n", (i + 1), studentList.get(i));
        }
        System.out.println("=================================================================");
        System.out.println("Collection operations and comparison demonstrated successfully!");
    }
}
