import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * LoggingDemo demonstrates robust enterprise logging best practices in Java:
 * 1. Using java.util.logging (standard built-in Java logging framework).
 * 2. Proper selection of log levels:
 *    - SEVERE  : Unhandled critical errors, system failures, or caught fatal exceptions.
 *    - WARNING : Recoverable abnormalities, fallback behaviors, or deprecated usages.
 *    - INFO    : High-level operational milestones (lifecycle start, shutdown, major steps).
 *    - CONFIG  : Configuration details, startup parameters, environment settings.
 *    - FINE    : Fine-grained debugging information for troubleshooting.
 * 3. Structured log formatting with timestamp, logger name, level, and message.
 * 4. Contextual exception logging with stack traces (logger.log(Level.SEVERE, msg, throwable)).
 */
public class LoggingDemo {

    private static final Logger logger = Logger.getLogger(LoggingDemo.class.getName());

    public static void main(String[] args) {
        // Setup clean console formatting for readable demonstration output
        configureLogging();

        // ---------------------------------------------------------------------
        // 1. Application Startup Milestone (INFO & CONFIG)
        // ---------------------------------------------------------------------
        logger.info("Application starting up: Student & Employee Analytics Engine v3.2.0");
        logger.config("Configuration Loaded: maxConnections=50, timeoutSeconds=30, activeProfile=Production");

        // ---------------------------------------------------------------------
        // 2. Business Processing Phase (INFO, FINE, & WARNING)
        // ---------------------------------------------------------------------
        logger.info("Beginning batch processing of student records...");

        String[] batchIds = {"STU-101", "STU-102", "STU-MISSING-METADATA", "STU-104"};

        for (String studentId : batchIds) {
            processStudentRecord(studentId);
        }

        logger.info("Batch processing stage completed successfully.");

        // ---------------------------------------------------------------------
        // 3. Error Handling and Exception Logging (SEVERE)
        // ---------------------------------------------------------------------
        logger.info("Attempting external system synchronization...");
        simulateDatabaseFailure();

        // ---------------------------------------------------------------------
        // 4. Application Shutdown Milestone (INFO)
        // ---------------------------------------------------------------------
        logger.info("Application shutdown initiated. Releasing memory caches and connection pools.");
        logger.info("Application terminated cleanly.");
    }

    /**
     * Simulates processing of an individual student record.
     */
    private static void processStudentRecord(String studentId) {
        logger.fine("Entering processStudentRecord() for ID: " + studentId);

        if ("STU-MISSING-METADATA".equals(studentId)) {
            // WARNING: Non-fatal abnormality that allows processing to continue
            logger.warning("Record " + studentId + " is missing email address; applying default fallback placeholder.");
        } else {
            logger.info("Successfully validated and saved student record: " + studentId);
        }

        logger.fine("Exiting processStudentRecord() for ID: " + studentId);
    }

    /**
     * Simulates a failure and logs the exception with full context.
     */
    private static void simulateDatabaseFailure() {
        try {
            // Simulating an unexpected calculation / division by zero or network timeout
            int divisor = 0;
            int result = 100 / divisor;
            logger.info("Result: " + result);
        } catch (ArithmeticException ex) {
            // SEVERE: Critical error logged with the associated Throwable
            logger.log(Level.SEVERE, "Failed to compute financial tuition balance: Division by zero", ex);
        }

        try {
            throw new IOException("Connection refused by external payment gateway (192.168.1.100:8443)");
        } catch (IOException ex) {
            // SEVERE: External I/O exception
            logger.log(Level.SEVERE, "External payment gateway communication failure", ex);
        }
    }

    /**
     * Configures clean log formatting for the application logger.
     */
    private static void configureLogging() {
        // Prevent default duplicate parent console handlers
        logger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                String timestamp = java.time.ZonedDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("[%s] [%-7s] [%s] %s%n",
                        timestamp,
                        record.getLevel(),
                        record.getLoggerName(),
                        record.getMessage()));

                if (record.getThrown() != null) {
                    Throwable t = record.getThrown();
                    sb.append("  Exception: ").append(t.toString()).append("\n");
                    for (StackTraceElement element : t.getStackTrace()) {
                        sb.append("    at ").append(element.toString()).append("\n");
                        if (element.getClassName().contains("LoggingDemo")) {
                            break; // Compact stack trace for readability
                        }
                    }
                }
                return sb.toString();
            }
        });

        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL); // Capture all levels: FINE, CONFIG, INFO, WARNING, SEVERE
    }
}
