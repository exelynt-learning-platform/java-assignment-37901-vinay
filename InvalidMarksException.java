/**
 * Custom Exception: InvalidMarksException
 * Thrown when student marks fall outside the valid domain range of [0 to 100].
 */
public class InvalidMarksException extends Exception {

    public InvalidMarksException() {
        super("Invalid marks provided: Marks must fall within the range [0.0 - 100.0].");
    }

    public InvalidMarksException(String message) {
        super(message);
    }
}
