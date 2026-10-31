import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TryCatchFinallyDemo demonstrates robust exception handling in Java using:
 * - try block: Encloses code that may potentially throw an exception.
 * - catch block: Intercepts and handles specific exceptions when thrown.
 * - finally block: Always executes regardless of whether an exception was thrown
 *   or handled, commonly used for resource cleanup.
 */
public class TryCatchFinallyDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("   TRY-CATCH-FINALLY EXCEPTION HANDLING DEMO      ");
        System.out.println("==================================================");

        /*
         * PURPOSE OF THE TRY BLOCK:
         * ---------------------------------------------------------------
         * The 'try' block encloses the code segment that might throw one
         * or more runtime exceptions. Java monitors all statements within
         * the try block during execution. If an exception occurs, the
         * remaining code inside the try block is skipped immediately, and
         * control transfers to the matching catch block.
         */
        try {
            System.out.print("Enter numerator (integer): ");
            int numerator = scanner.nextInt();

            System.out.print("Enter denominator (integer): ");
            int denominator = scanner.nextInt();

            System.out.println("Performing division: " + numerator + " / " + denominator + "...");

            // If denominator is 0, integer division throws ArithmeticException
            int result = numerator / denominator;

            System.out.println("Division successful! Result: " + result);

        /*
         * PURPOSE OF THE CATCH BLOCK:
         * ---------------------------------------------------------------
         * The 'catch' block acts as an exception handler. It defines the
         * specific exception type it can handle (ArithmeticException in this case).
         * When an ArithmeticException is thrown in the try block, control jumps
         * directly into this catch block. It allows the program to log the error,
         * notify the user, and recover gracefully instead of abruptly crashing.
         */
        } catch (ArithmeticException e) {
            System.out.println("\n[Error Caught in Catch Block]");
            System.out.println("ArithmeticException: Cannot divide an integer by zero!");
            System.out.println("Technical Details: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("\n[Error Caught in Catch Block]");
            System.out.println("InputMismatchException: Please enter valid integer values only.");

        /*
         * PURPOSE OF THE FINALLY BLOCK:
         * ---------------------------------------------------------------
         * The 'finally' block contains essential code that is GUARANTEED to
         * execute in all scenarios:
         * 1. If the try block completes successfully with NO exceptions.
         * 2. If an exception is thrown in the try block and CAUGHT by catch.
         * 3. Even if an unhandled exception occurs or a return statement is hit.
         * It is the standard location for closing file streams, database
         * connections, or releasing scanner resources.
         */
        } finally {
            System.out.println("\n[Executing Finally Block]");
            System.out.println("Operation completed");

            // Safe resource cleanup
            scanner.close();
            System.out.println("Scanner resource successfully closed.");
        }

        System.out.println("\n==================================================");
        System.out.println("Program execution continues and terminates normally.");
        System.out.println("==================================================");
    }
}
