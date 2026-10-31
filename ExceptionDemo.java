import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * ExceptionDemo demonstrates the fundamental difference between
 * Checked Exceptions and Unchecked Exceptions in Java.
 *
 * 1. CHECKED EXCEPTIONS:
 *    - Inherit from java.lang.Exception (excluding RuntimeException).
 *    - The Java compiler checks them at compile time.
 *    - The programmer is REQUIRED to either handle them using try-catch
 *      or declare them using the 'throws' keyword.
 *    - Common examples: IOException, FileNotFoundException, SQLException.
 *
 * 2. UNCHECKED EXCEPTIONS (Runtime Exceptions):
 *    - Inherit from java.lang.RuntimeException (which extends Exception).
 *    - Occur during program execution due to logical errors, bad inputs,
 *      or invalid operations.
 *    - The compiler does NOT mandate handling or declaring them.
 *    - Common examples: ArithmeticException, NullPointerException, ArrayIndexOutOfBoundsException.
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   JAVA EXCEPTION HANDLING: CHECKED VS UNCHECKED EXCEPTIONS      ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // PART 1: DEMONSTRATING A CHECKED EXCEPTION (IOException / FileNotFoundException)
        // ---------------------------------------------------------------------
        // Why it occurs:
        // Attempting to read a file ("non_existent_data_file.txt") that does not exist
        // on disk causes FileReader to throw a FileNotFoundException (a subclass of IOException).
        // Because IOException is a CHECKED exception, the compiler refuses to compile the
        // code unless enclosed in a try-catch block or declared in a throws clause.
        // ---------------------------------------------------------------------
        System.out.println("--- 1. Demonstrating Checked Exception (IOException) ---");
        FileReader fileReader = null;
        try {
            System.out.println("Attempting to open non-existent file 'non_existent_data_file.txt'...");
            File targetFile = new File("non_existent_data_file.txt");
            fileReader = new FileReader(targetFile); // Triggers FileNotFoundException (Checked)

            // The following line will not execute if exception is thrown above
            System.out.println("File opened successfully.");
        } catch (IOException e) {
            // Handled gracefully: Program does not crash
            System.out.println("[CAUGHT CHECKED EXCEPTION]");
            System.out.println("Exception Type   : " + e.getClass().getName());
            System.out.println("Error Message    : " + e.getMessage());
            System.out.println("Explanation      : Checked exceptions represent recoverable external failures (e.g., missing file).");
            System.out.println("Resolution       : Caught via try-catch; file existence can be verified or alternative file selected.");
        } finally {
            // Clean up resources if necessary
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    System.out.println("Error closing fileReader: " + ex.getMessage());
                }
            }
        }

        System.out.println("\n>> Program execution continues uninterrupted after handling checked exception.\n");

        // ---------------------------------------------------------------------
        // PART 2: DEMONSTRATING AN UNCHECKED EXCEPTION (ArithmeticException)
        // ---------------------------------------------------------------------
        // Why it occurs:
        // In integer arithmetic, dividing an integer by zero (e.g., 50 / 0) is mathematically
        // undefined. The JVM detects this at runtime and automatically throws an ArithmeticException.
        // Because ArithmeticException extends RuntimeException, the compiler does not force us
        // to write try-catch, but unhandled it would immediately terminate/crash the application.
        // ---------------------------------------------------------------------
        System.out.println("--- 2. Demonstrating Unchecked Exception (ArithmeticException) ---");
        try {
            int dividend = 50;
            int divisor = 0;
            System.out.println("Attempting integer division: " + dividend + " / " + divisor + "...");

            int result = dividend / divisor; // Triggers ArithmeticException (Unchecked)

            // The following line will not execute
            System.out.println("Division Result: " + result);
        } catch (ArithmeticException e) {
            // Handled gracefully: Prevents abrupt application crash
            System.out.println("[CAUGHT UNCHECKED EXCEPTION]");
            System.out.println("Exception Type   : " + e.getClass().getName());
            System.out.println("Error Message    : " + e.getMessage());
            System.out.println("Explanation      : Unchecked exceptions indicate logical or programming flaws (e.g., dividing by zero).");
            System.out.println("Resolution       : Caught via try-catch; input should be validated before arithmetic calculation.");
        }

        System.out.println("\n>> Program execution continues uninterrupted after handling unchecked exception.\n");

        // ---------------------------------------------------------------------
        // PART 3: ADDITIONAL UNCHECKED EXCEPTION (ArrayIndexOutOfBoundsException)
        // ---------------------------------------------------------------------
        System.out.println("--- 3. Bonus Unchecked Exception (ArrayIndexOutOfBoundsException) ---");
        try {
            int[] scores = { 85, 90, 95 };
            System.out.println("Accessing scores array of length " + scores.length + " at index 5...");
            int invalidAccess = scores[5]; // Triggers ArrayIndexOutOfBoundsException
            System.out.println("Score: " + invalidAccess);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[CAUGHT UNCHECKED EXCEPTION]");
            System.out.println("Exception Type   : " + e.getClass().getName());
            System.out.println("Error Message    : " + e.getMessage());
            System.out.println("Explanation      : Attempted to access an index outside the valid array bounds [0 to " + (3 - 1) + "].");
        }

        // ---------------------------------------------------------------------
        // Program Completion Confirmation
        // ---------------------------------------------------------------------
        System.out.println("\n=================================================================");
        System.out.println("SUCCESS: All checked and unchecked exceptions handled properly!  ");
        System.out.println("The program completed its entire workflow without crashing.      ");
        System.out.println("=================================================================");
    }
}
