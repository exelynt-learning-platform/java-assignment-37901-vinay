import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * =============================================================================
 * IODemo demonstrates the fundamental concepts of Java I/O Streams:
 *
 * 1. WHAT IS AN I/O STREAM?
 *    - In Java, a "Stream" is an ordered sequence of data flowing from a source
 *      to a destination.
 *    - Java uses streams to perform input and output operations efficiently,
 *      decoupling internal program logic from underlying hardware devices.
 *
 * 2. WHAT IS AN INPUT STREAM?
 *    - An Input Stream is used to read data FROM a data source (e.g., keyboard,
 *      file on disk, network socket) INTO the Java application.
 *    - Base Byte Class: java.io.InputStream
 *    - Example: System.in is a predefined static InputStream connected to keyboard input.
 *    - Character counterpart: java.io.Reader (reads 16-bit Unicode characters).
 *
 * 3. WHAT IS AN OUTPUT STREAM?
 *    - An Output Stream is used to write data FROM the Java application OUT TO a
 *      destination (e.g., console display, file, network connection).
 *    - Base Byte Class: java.io.OutputStream
 *    - Example: System.out is a static PrintStream (subclass of OutputStream)
 *      representing standard console output.
 *    - Character counterpart: java.io.Writer (writes 16-bit Unicode characters).
 * =============================================================================
 */
public class IODemo {

    public static void main(String[] args) {

        /*
         * ---------------------------------------------------------------------
         * DEMONSTRATION 1: OUTPUT STREAMS
         * Writing data from the Java program out to the console destination.
         * ---------------------------------------------------------------------
         */

        // System.out is an instance of PrintStream, which inherits from FilterOutputStream -> OutputStream
        PrintStream standardOut = System.out;
        standardOut.println("=================================================================");
        standardOut.println("           JAVA INPUT AND OUTPUT (I/O) STREAMS DEMO              ");
        standardOut.println("=================================================================\n");

        standardOut.println("--- 1. Demonstrating Output Streams ---");
        standardOut.println("Message displayed via standard output stream (System.out.println).");

        // Demonstrating low-level byte-by-byte writing via OutputStream.write()
        try {
            OutputStream rawOut = System.out;
            String streamNotice = "Low-level byte stream output: Data sent directly through OutputStream.write()\n";
            // Convert string text to byte array and write to stream
            rawOut.write(streamNotice.getBytes());
            rawOut.flush(); // Forces any buffered output bytes to be written out
        } catch (IOException e) {
            System.err.println("Error writing to output stream: " + e.getMessage());
        }

        /*
         * ---------------------------------------------------------------------
         * DEMONSTRATION 2: INPUT STREAMS & USER INPUT WITH SCANNER
         * Reading data from an external input source (System.in) into Java variables.
         * ---------------------------------------------------------------------
         */
        System.out.println("\n--- 2. Demonstrating Input Stream (System.in wrapped by Scanner) ---");

        // Scanner wraps the byte-oriented System.in InputStream and parses formatted tokens
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Please enter your favorite programming topic: ");
        String userTopic = scanner.nextLine();

        System.out.print("Please enter a rating for Java I/O streams (1 to 10): ");
        int userRating = scanner.nextInt();

        /*
         * ---------------------------------------------------------------------
         * DEMONSTRATION 3: DISPLAYING INPUTTED DATA BACK THROUGH OUTPUT STREAM
         * ---------------------------------------------------------------------
         */
        System.out.println("\n=================================================================");
        System.out.println("              ECHOING RECEIVED STREAM DATA TO OUTPUT             ");
        System.out.println("=================================================================");
        System.out.println("User Name Received      : " + userName);
        System.out.println("Favorite Topic Received : " + userTopic);
        System.out.println("Stream Rating Received  : " + userRating + "/10");
        System.out.println("Data flow: Keyboard -> System.in (InputStream) -> Program -> System.out (OutputStream) -> Console");
        System.out.println("=================================================================");

        // Close scanner (which closes underlying input stream when appropriate)
        scanner.close();
        System.out.println("I/O Stream demonstration completed successfully!");
    }
}
