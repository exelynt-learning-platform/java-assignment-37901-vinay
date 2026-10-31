import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * =============================================================================
 * WriterReaderDemo demonstrates character stream file operations using:
 * - java.io.FileWriter
 * - java.io.PrintWriter
 * - java.io.FileReader
 *
 * =============================================================================
 * EXPLANATION OF CLASSES USED:
 * =============================================================================
 * 1. java.io.FileWriter:
 *    - A convenience class for writing streams of 16-bit characters to a file.
 *    - Automatically handles character-to-byte encoding using the platform's
 *      default charset.
 *    - Can be opened in overwrite mode (default) or append mode (FileWriter(file, true)).
 *
 * 2. java.io.PrintWriter:
 *    - A high-level character output stream that provides formatted printing methods
 *      (print, println, printf, format) identical to System.out.
 *    - Wraps an underlying Writer (like FileWriter) to format primitive data types,
 *      Strings, and Objects cleanly without needing manual String conversions.
 *    - Automatically flushes when configured, and suppresses direct IOExceptions
 *      (providing checkError() instead).
 *
 * 3. java.io.FileReader:
 *    - A convenience class for reading streams of 16-bit characters from a file.
 *    - Decodes bytes into characters using the platform's default charset.
 *    - The read() method reads characters one by one (returning -1 at EOF) or
 *      into a char[] buffer.
 * =============================================================================
 */
public class WriterReaderDemo {

    public static void main(String[] args) {
        String fileName = "student_records.txt";

        System.out.println("=================================================================");
        System.out.println("   CHARACTER STREAMS DEMO: FileWriter, PrintWriter & FileReader  ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // STEP 1: WRITING FORMATTED DATA USING FileWriter & PrintWriter
        // ---------------------------------------------------------------------
        System.out.println("--- 1. Writing Formatted Student Records via PrintWriter & FileWriter ---");

        /*
         * Wrapping FileWriter inside PrintWriter:
         * FileWriter handles the low-level character connection to the file.
         * PrintWriter provides powerful formatting methods like printf() and println().
         */
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {

            // Writing formatted header
            printWriter.println("=================================================================");
            printWriter.println("                 OFFICIAL STUDENT GRADE REPORT                   ");
            printWriter.println("=================================================================");
            printWriter.printf("%-10s | %-22s | %-8s | %-12s | %-8s\n",
                    "Roll No", "Student Name", "Grade", "Marks (/100)", "Status");
            printWriter.println("-----------------------------------------------------------------");

            // Writing formatted records using printf()
            printWriter.printf("%-10d | %-22s | %-8s | %12.2f | %-8s\n",
                    101, "Vinay Madarkhandi", "A+", 96.50, "PASS");

            printWriter.printf("%-10d | %-22s | %-8s | %12.2f | %-8s\n",
                    102, "Alice Johnson", "A", 89.75, "PASS");

            printWriter.printf("%-10d | %-22s | %-8s | %12.2f | %-8s\n",
                    103, "Bob Smith", "B", 74.20, "PASS");

            printWriter.printf("%-10d | %-22s | %-8s | %12.2f | %-8s\n",
                    104, "Charlie Davis", "C", 58.00, "PASS");

            printWriter.printf("%-10d | %-22s | %-8s | %12.2f | %-8s\n",
                    105, "Diana Prince", "O", 99.10, "PASS");

            printWriter.println("=================================================================");

            System.out.println(">> Formatted student records successfully written to '" + fileName + "'.");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // ---------------------------------------------------------------------
        // STEP 2: READING FILE CONTENT USING FileReader
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. Reading and Displaying File Content via FileReader ---\n");

        File targetFile = new File(fileName);
        if (!targetFile.exists()) {
            System.out.println("Target file '" + fileName + "' not found.");
            return;
        }

        /*
         * FileReader reads characters sequentially from the file on disk.
         * read() returns the character code point as an integer, or -1 at EOF.
         */
        try (FileReader fileReader = new FileReader(targetFile)) {
            int character;
            int totalCharacters = 0;

            // Reading character by character until End-of-File (-1)
            while ((character = fileReader.read()) != -1) {
                // Cast integer code point back to char and print to console
                System.out.print((char) character);
                totalCharacters++;
            }

            System.out.println("\n>> Successfully read " + totalCharacters + " characters from '" + fileName + "'.");

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        System.out.println("\n=================================================================");
        System.out.println("FileWriter, PrintWriter, and FileReader operations complete!    ");
        System.out.println("=================================================================");
    }
}
