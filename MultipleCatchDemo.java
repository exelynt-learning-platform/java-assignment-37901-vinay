import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MultipleCatchDemo demonstrates:
 * 1. Multiple Catch Blocks:
 *    - Allows a single try block to be followed by multiple catch handlers.
 *    - Each catch block specifies a distinct exception type.
 *    - Order matters: Most specific exceptions (subclasses) must appear BEFORE
 *      general exceptions (superclasses), otherwise a compile error occurs.
 *
 * 2. Nested Try-Catch Blocks:
 *    - A try-catch block situated inside another try or catch block.
 *    - Provides fine-grained, localized exception handling. If the inner try fails,
 *      its inner catch handles the error without breaking or terminating the outer try flow.
 *
 * 3. Flow of Exception Handling:
 *    - Statements run until an exception is raised.
 *    - The JVM halts subsequent statements in that try block and traverses matching catch blocks top-to-bottom.
 *    - Once a matching catch block executes, control resumes after the entire try-catch structure.
 */
public class MultipleCatchDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("      MULTIPLE CATCH BLOCKS & NESTED TRY-CATCH DEMO              ");
        System.out.println("=================================================================\n");

        /*
         * OUTER TRY BLOCK:
         * Encapsulates the overall user input collection, array creation, and indexing logic.
         */
        try {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();

            int[] numbers = new int[size];
            System.out.println("Enter " + size + " integers to populate the array:");
            for (int i = 0; i < size; i++) {
                System.out.print("Element [" + i + "]: ");
                numbers[i] = scanner.nextInt();
            }

            // Display populated array
            System.out.print("\nCurrent Array Elements: [ ");
            for (int val : numbers) {
                System.out.print(val + " ");
            }
            System.out.println("]\n");

            // Prompt user for an index to query
            System.out.print("Enter an index to access (0 to " + (size - 1) + "): ");
            int targetIndex = scanner.nextInt();

            // Potentially triggers ArrayIndexOutOfBoundsException
            int retrievedValue = numbers[targetIndex];
            System.out.println("Successfully accessed index " + targetIndex + " -> Value: " + retrievedValue);

            /*
             * NESTED TRY-CATCH BLOCK:
             * Isolated inside the outer try block to demonstrate localized error recovery.
             * Demonstrates handling an unexpected NullPointerException scenario.
             */
            System.out.println("\n--- Initiating Nested Operation (Object Formatting) ---");
            try {
                // Simulating an uninitialized/null string or object retrieval
                String dataTag = null;
                System.out.println("Attempting to calculate length of dataTag...");

                // This triggers a NullPointerException because dataTag is null
                int textLength = dataTag.length();
                System.out.println("Data tag length: " + textLength);

            } catch (NullPointerException npe) {
                /*
                 * PURPOSE OF NESTED CATCH:
                 * Catches the NullPointerException locally so the outer try block can
                 * proceed without being aborted by this sub-operation failure.
                 */
                System.out.println("[NESTED CATCH]: Caught NullPointerException locally!");
                System.out.println("Message: Attempted to invoke a method on a null reference.");
                System.out.println("Resolution: Fallback value applied; continuing outer execution.");
            }

            System.out.println("--- Nested operation concluded safely. Continuing outer flow. ---");

        /*
         * MULTIPLE CATCH BLOCKS (OUTER TRY):
         * Sorted in order of specificity to handle different failure modes.
         */
        } catch (InputMismatchException ime) {
            // Handles non-integer input when reading integers
            System.out.println("\n[OUTER CATCH - InputMismatchException]:");
            System.out.println("Error: Invalid input format. Expected an integer value.");
            System.out.println("Technical Details: Input does not match the integer token regex.");

        } catch (ArrayIndexOutOfBoundsException aiobe) {
            // Handles accessing an array index < 0 or >= array.length
            System.out.println("\n[OUTER CATCH - ArrayIndexOutOfBoundsException]:");
            System.out.println("Error: The requested index is out of bounds!");
            System.out.println("Technical Details: " + aiobe.getMessage());

        } catch (Exception generalException) {
            // General catch-all for any other unanticipated checked/unchecked exceptions
            System.out.println("\n[OUTER CATCH - Generic Exception]:");
            System.out.println("Unexpected Error: " + generalException.getMessage());

        } finally {
            // Safe cleanup of input resources
            scanner.close();
            System.out.println("\n[FINALLY]: Scanner closed. Cleanup complete.");
        }

        System.out.println("\n=================================================================");
        System.out.println("Program execution concluded smoothly without crashing!");
        System.out.println("=================================================================");
    }
}
