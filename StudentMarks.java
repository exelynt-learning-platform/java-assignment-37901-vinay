import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * =============================================================================
 * Program: StudentMarks
 * Demonstrates:
 * 1. Purpose of 'throw':
 *    - The 'throw' keyword is used inside a method body or block to EXPLICITLY
 *      raise/instigate an exception instance.
 *    - Syntax: throw new InvalidMarksException("error message");
 *    - Once executed, normal control stops immediately, and Java searches
 *      for an enclosing catch block or pops the method off the call stack.
 *
 * 2. Purpose of 'throws':
 *    - The 'throws' keyword is used in a METHOD SIGNATURE to declare the types
 *      of checked exceptions that this method might throw during its execution.
 *    - It acts as an explicit warning to caller methods, stating: "Call me, but
 *      you must either wrap the call in try-catch or propagate with your own throws".
 *    - Syntax: public static void validateMarks(double marks) throws InvalidMarksException
 *
 * 3. How Custom Exceptions Work:
 *    - User-defined classes extending Exception model application-specific rules.
 *    - Provides precise error classification, improving debugging and business logic isolation.
 * =============================================================================
 */
public class StudentMarks {

    /**
     * Validates whether the student's marks fall within the permissible range [0 to 100].
     *
     * PURPOSE OF 'throws':
     * Declares that validateMarks() may propagate an InvalidMarksException to its caller.
     *
     * @param marks The student marks to validate
     * @throws InvalidMarksException if marks are strictly less than 0 or greater than 100
     */
    public static void validateMarks(double marks) throws InvalidMarksException {
        // Validation check for negative marks
        if (marks < 0.0) {
            /*
             * PURPOSE OF 'throw':
             * Explicitly instantiates and throws the custom exception instance.
             */
            throw new InvalidMarksException("Validation Failure: Marks cannot be negative! Received: " + marks);
        }

        // Validation check for marks exceeding maximum limit of 100
        if (marks > 100.0) {
            /*
             * Explicitly throwing custom exception with informative message
             */
            throw new InvalidMarksException("Validation Failure: Marks cannot exceed 100.00! Received: " + marks);
        }

        // If validation succeeds:
        System.out.printf("Validation Success: %.2f is a valid mark within the range [0 - 100].\n", marks);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("   STUDENT MARKS VALIDATION (CUSTOM EXCEPTION DEMO)              ");
        System.out.println("=================================================================\n");

        try {
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            System.out.print("Enter student marks (0 to 100): ");
            double marksInput = scanner.nextDouble();

            System.out.println("\nValidating entered marks for student '" + studentName + "'...");

            // Invoking the method that declares 'throws InvalidMarksException'
            validateMarks(marksInput);

            // Display success status and grade estimate if validation passes
            System.out.println("Student Record Created Successfully!");
            System.out.println("Student Name : " + studentName);
            System.out.printf("Recorded Mark: %.2f%%\n", marksInput);

        /*
         * Catching the custom user-defined exception
         */
        } catch (InvalidMarksException ime) {
            System.out.println("\n[CAUGHT CUSTOM EXCEPTION]");
            System.out.println("Exception Type: " + ime.getClass().getSimpleName());
            System.out.println("Error Message : " + ime.getMessage());
            System.out.println("Explanation   : The throw statement triggered because marks violated business rules.");

        } catch (InputMismatchException ime) {
            System.out.println("\n[CAUGHT INPUT MISMATCH EXCEPTION]");
            System.out.println("Error: Please enter a valid numeric value for marks.");

        } finally {
            scanner.close();
            System.out.println("\n[CLEANUP]: Scanner closed successfully.");
        }

        System.out.println("\n=================================================================");
        System.out.println("Program execution concluded safely.");
        System.out.println("=================================================================");
    }
}
