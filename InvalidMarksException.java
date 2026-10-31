/**
 * =============================================================================
 * Custom Exception: InvalidMarksException
 *
 * HOW CUSTOM EXCEPTIONS WORK:
 * - A custom (user-defined) exception allows developers to define domain-specific
 *   error conditions that standard Java exceptions cannot adequately describe.
 * - By extending java.lang.Exception, InvalidMarksException becomes a CHECKED
 *   exception. This forces callers to either handle it via try-catch or declare it
 *   using the 'throws' keyword in their method signature.
 * - Subclasses can provide overloaded constructors to capture custom messages or
 *   error codes.
 * =============================================================================
 */
public class InvalidMarksException extends Exception {

    /**
     * Default constructor with a standard default error message.
     */
    public InvalidMarksException() {
        super("Invalid marks provided! Marks must be between 0 and 100.");
    }

    /**
     * Parameterized constructor that accepts a custom explanatory error message.
     *
     * @param message Detailed reason for the exception
     */
    public InvalidMarksException(String message) {
        super(message); // Passes message to the Exception superclass
    }
}
