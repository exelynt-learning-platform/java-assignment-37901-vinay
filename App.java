import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * App is a standalone, runnable Java application packaged into an executable JAR.
 *
 * Demonstrates:
 * - Standalone execution from command line via 'java -jar app.jar'
 * - Main-Class manifest entry configuration
 * - Environment and runtime diagnostic inspection
 */
public class App {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("      STANDALONE EXECUTABLE APPLICATION (PACKAGED IN JAR)                 ");
        System.out.println("==========================================================================");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(dtf);

        System.out.println("\nApplication Status: RUNNING SUCCESSFULLY FROM EXECUTABLE JAR");
        System.out.println("Execution Timestamp : " + currentTime);

        // System and JVM Details
        System.out.println("\n--- Runtime Environment Diagnostic ---");
        System.out.println("Java Runtime Version : " + System.getProperty("java.version"));
        System.out.println("Java Vendor          : " + System.getProperty("java.vendor"));
        System.out.println("Operating System     : " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")");
        System.out.println("Available Processors : " + Runtime.getRuntime().availableProcessors());
        System.out.printf("Free Memory in JVM   : %,.2f MB%n", Runtime.getRuntime().freeMemory() / (1024.0 * 1024.0));

        // Command-line arguments inspection
        System.out.println("\n--- Command-Line Arguments ---");
        if (args.length == 0) {
            System.out.println("No command-line arguments passed. (Pass arguments via: java -jar app.jar arg1 arg2)");
        } else {
            for (int i = 0; i < args.length; i++) {
                System.out.printf("  Argument [%d]: %s%n", i, args[i]);
            }
        }

        System.out.println("\n==========================================================================");
        System.out.println("JAR execution demo finished cleanly.");
        System.out.println("==========================================================================");
    }
}
