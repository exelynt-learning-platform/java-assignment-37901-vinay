import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * =============================================================================
 * IteratorDemo demonstrates different traversal techniques over Java Collections:
 *
 * 1. java.util.Iterator:
 *    - The traditional, universal mechanism to sequentially access elements of a Collection.
 *    - Key methods:
 *        * hasNext() : Returns true if the collection has more elements.
 *        * next()    : Returns the next element and advances the cursor.
 *        * remove()  : Safely removes the last element returned by next() WITHOUT
 *                      triggering a ConcurrentModificationException.
 *    - Essential when you need to remove elements while iterating.
 *
 * 2. Enhanced For-Each Loop:
 *    - Introduced in Java 5 as cleaner, more readable syntactic sugar over Iterator.
 *    - Works for any class implementing the java.lang.Iterable interface (like all Collections).
 *    - Ideal for read-only sequential traversal when elements do not need to be removed.
 *    - Modifying the collection structure directly inside a for-each loop throws
 *      ConcurrentModificationException.
 * =============================================================================
 */
public class IteratorDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("      JAVA COLLECTION ITERATION TECHNIQUES DEMONSTRATION         ");
        System.out.println("=================================================================\n");

        // 1. Initializing and populating an ArrayList of student names
        List<String> studentList = new ArrayList<>();
        studentList.add("Vinay Madarkhandi");
        studentList.add("Alice Johnson");
        studentList.add("Bob Smith");
        studentList.add("Temporary Guest"); // Candidate for removal via Iterator
        studentList.add("Charlie Davis");
        studentList.add("Diana Prince");

        System.out.println("Initial ArrayList size: " + studentList.size() + "\n");

        // =====================================================================
        // TECHNIQUE 1: TRAVERSAL USING java.util.Iterator
        // =====================================================================
        System.out.println("--- 1. Traversal using java.util.Iterator ---");

        /*
         * Obtaining an Iterator object from the collection:
         * studentList.iterator() returns an Iterator positioned just before the first element.
         */
        Iterator<String> iterator = studentList.iterator();

        int count = 1;
        while (iterator.hasNext()) {
            // iterator.next() retrieves the next element and moves cursor forward
            String currentStudent = iterator.next();
            System.out.printf("  [%d] %s\n", count++, currentStudent);

            /*
             * SAFE REMOVAL USING ITERATOR:
             * 'iterator.remove()' safely deletes the current element from the collection.
             * Doing 'studentList.remove("Temporary Guest")' inside this loop would throw
             * ConcurrentModificationException!
             */
            if (currentStudent.equals("Temporary Guest")) {
                System.out.println("      >> [Iterator.remove()] Safely removing: " + currentStudent);
                iterator.remove();
            }
        }

        System.out.println("\nSize after Iterator removal: " + studentList.size() + "\n");

        // =====================================================================
        // TECHNIQUE 2: TRAVERSAL USING ENHANCED FOR-EACH LOOP
        // =====================================================================
        System.out.println("--- 2. Traversal using Enhanced For-Each Loop ---");

        /*
         * The enhanced for loop provides concise, readable syntax:
         * for (Type variable : collection) { ... }
         * Under the hood, the compiler converts this to an Iterator, but hides
         * the cursor management logic.
         */
        int displayIndex = 1;
        for (String student : studentList) {
            System.out.printf("  Student #%d: %s\n", displayIndex++, student);
        }

        // =====================================================================
        // TECHNIQUE 3: FOR-EACH WITH LAMBDA (Java 8+ Modern Approach)
        // =====================================================================
        System.out.println("\n--- 3. Modern Traversal using forEach() and Lambda ---");
        studentList.forEach(student -> System.out.println("  * Enrolled: " + student));

        System.out.println("\n=================================================================");
        System.out.println("Iteration demonstration completed successfully!");
        System.out.println("=================================================================");
    }
}
