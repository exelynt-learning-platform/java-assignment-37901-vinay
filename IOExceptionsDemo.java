import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * =============================================================================
 * IOExceptionsDemo demonstrates Exception Handling with I/O Operations in Java:
 *
 * 1. TRY-WITH-RESOURCES STATEMENT:
 *    - Introduced in Java 7 to manage resources that implement java.lang.AutoCloseable.
 *    - Automatically closes stream resources (readers, writers, files) at the
 *      conclusion of the try block, whether an exception was thrown or not.
 *    - Eliminates cumbersome and error-prone boilerplate finally blocks.
 *
 * 2. EXCEPTION HIERARCHY & ORDERING:
 *    - java.io.FileNotFoundException is a DIRECT SUBCLASS of java.io.IOException.
 *    - Multiple catch blocks must declare FileNotFoundException FIRST before
 *      the broader IOException superclass; otherwise, the compiler flags unreachable code.
 *
 * 3. MEANINGFUL ERROR HANDLING:
 *    - Intercepts specific I/O failures (file missing, permission denied, disk full)
 *      and outputs clear, actionable diagnostic information.
 * =============================================================================
 */
public class IOExceptionsDemo {

    private static final String VALID_FILE = "io_demo_output.txt";
    private static final String NON_EXISTENT_FILE = "missing_system_config.txt";

    /**
     * Performs a file write operation using try-with-resources.
     *
     * @param fileName Target file path
     * @param content  Text content to write
     */
    public static void writeToFile(String fileName, String content) {
        System.out.println("--- 1. Writing Data to '" + fileName + "' using Try-With-Resources ---");

        /*
         * Try-With-Resources automatically closes the BufferedWriter and FileWriter
         * upon exiting the try block.
         */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.newLine();
            System.out.println(">> Write Success: Data safely written and stream automatically flushed/closed.");

        } catch (IOException e) {
            // Catches any I/O errors occurring during writing
            System.err.println("[CAUGHT IOException during Write]");
            System.err.println("Error Message : " + e.getMessage());
            System.err.println("Actionable Tip: Verify write permissions and available disk space.");
        }
    }

    /**
     * Reads a file using try-with-resources and handles FileNotFoundException
     * and general IOException with distinct catch blocks.
     *
     * @param fileName Target file path to read
     */
    public static void readFromFile(String fileName) {
        System.out.println("\n--- Reading Data from '" + fileName + "' ---");

        /*
         * BufferedReader implements AutoCloseable; closed automatically on exit.
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("File Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println("  > " + line);
            }
            System.out.println(">> Read Success: File reading completed.");

        /*
         * CATCH ORDER:
         * FileNotFoundException (specific subclass) MUST precede IOException (superclass).
         */
        } catch (FileNotFoundException e) {
            System.err.println("[CAUGHT FileNotFoundException]");
            System.err.println("Error Message : File '" + fileName + "' was not found on disk!");
            System.err.println("Technical Info: " + e.getMessage());
            System.err.println("Actionable Tip: Check that the file path and name are spelled correctly.");

        } catch (IOException e) {
            System.err.println("[CAUGHT General IOException]");
            System.err.println("Error Message : An I/O error occurred while reading from the stream.");
            System.err.println("Technical Info: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("      EXCEPTION HANDLING WITH I/O OPERATIONS DEMONSTRATION       ");
        System.out.println("=================================================================\n");

        // 1. Successful Write Operation
        String sampleData = "Java I/O Best Practice: Always utilize Try-With-Resources for AutoCloseable streams.";
        writeToFile(VALID_FILE, sampleData);

        // 2. Successful Read Operation
        readFromFile(VALID_FILE);

        // 3. Exception Handling Scenario: Reading a Non-Existent File
        // This intentionally triggers FileNotFoundException to verify error handling
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("TESTING ERROR SCENARIO: Attempting to open a missing file...");
        System.out.println("-----------------------------------------------------------------");
        readFromFile(NON_EXISTENT_FILE);

        // Clean up the created demo file
        File cleanupFile = new File(VALID_FILE);
        if (cleanupFile.exists()) {
            cleanupFile.delete();
        }

        System.out.println("\n=================================================================");
        System.out.println("I/O Exception handling and Try-With-Resources test complete!    ");
        System.out.println("=================================================================");
    }
}
