import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * =============================================================================
 * BufferedDemo demonstrates efficient character stream I/O using:
 * - java.io.BufferedWriter
 * - java.io.BufferedReader
 *
 * =============================================================================
 * WHAT IS BUFFERING AND WHY IS IT IMPORTANT?
 * =============================================================================
 * 1. THE PROBLEM WITH UNBUFFERED I/O:
 *    - Standard character streams (FileReader, FileWriter) communicate directly
 *      with the underlying operating system and physical storage disk.
 *    - Writing or reading characters one by one causes a separate disk access
 *      or system call for every single character. Disk I/O is thousands of
 *      times slower than RAM access, causing significant performance degradation.
 *
 * 2. HOW BUFFERING WORKS:
 *    - Buffering places an intermediate memory buffer (a fast in-memory array,
 *      typically 8KB) between the Java program and the physical storage device.
 *
 *    - BufferedWriter:
 *      * Collects written characters inside its internal memory buffer.
 *      * Only writes out to the physical file in large, efficient chunks when
 *        the buffer fills up, or when flush() / close() is invoked.
 *      * Provides convenient newLine() method for platform-independent line breaks.
 *
 *    - BufferedReader:
 *      * Pre-fetches large blocks of characters from the physical file into
 *        RAM in a single read operation.
 *      * Subsequent read requests are served instantly from fast RAM.
 *      * Provides readLine() to efficiently read complete lines of text until EOF.
 * =============================================================================
 */
public class BufferedDemo {

    public static void main(String[] args) {
        String fileName = "students.txt";

        System.out.println("=================================================================");
        System.out.println("     BUFFERED STREAM I/O DEMO (BufferedReader & BufferedWriter)  ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // STEP 1: WRITING STUDENT DATA USING BufferedWriter
        // Using try-with-resources ensures the writer is automatically flushed & closed.
        // ---------------------------------------------------------------------
        System.out.println("--- 1. Writing Student Records to '" + fileName + "' via BufferedWriter ---");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Header
            writer.write("==================================================");
            writer.newLine(); // Platform-independent newline separator
            writer.write("           STUDENT ACADEMIC RECORDS DATABASE      ");
            writer.newLine();
            writer.write("==================================================");
            writer.newLine();

            // Writing individual student records
            writer.write("ID: 101 | Name: Vinay Madarkhandi | Grade: A+ | Major: Computer Science");
            writer.newLine();

            writer.write("ID: 102 | Name: Alice Johnson     | Grade: A  | Major: Data Science");
            writer.newLine();

            writer.write("ID: 103 | Name: Bob Smith         | Grade: B+ | Major: Information Systems");
            writer.newLine();

            writer.write("ID: 104 | Name: Charlie Davis     | Grade: A  | Major: Software Engineering");
            writer.newLine();

            writer.write("ID: 105 | Name: Diana Prince      | Grade: O  | Major: Artificial Intelligence");
            writer.newLine();

            System.out.println(">> Success: Student records buffered and written to file efficiently.");

        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }

        // ---------------------------------------------------------------------
        // STEP 2: READING & DISPLAYING STORED DATA USING BufferedReader
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. Reading Stored Data from '" + fileName + "' via BufferedReader ---\n");

        File fileToRead = new File(fileName);
        if (!fileToRead.exists()) {
            System.out.println("Target file '" + fileName + "' does not exist.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String currentLine;
            int lineNumber = 1;

            /*
             * readLine() reads a line of text up to the next newline.
             * It returns the String content (excluding the newline character),
             * or 'null' when the end of the file (EOF) is reached.
             */
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
                lineNumber++;
            }

            System.out.println("\n>> Success: End of file reached. All lines read from memory buffer.");

        } catch (IOException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
        }

        System.out.println("\n=================================================================");
        System.out.println("Buffered I/O operations and performance buffering explained!    ");
        System.out.println("=================================================================");
    }
}
