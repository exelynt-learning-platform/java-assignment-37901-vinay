/**
 * Program 2: Demonstrates basic arithmetic calculations in Java following
 * standard coding conventions, descriptive naming, indentation, and comments.
 */
public class ArithmeticOperations {

    /**
     * Performs and displays basic arithmetic operations on two numbers.
     *
     * @param firstNumber  First operand
     * @param secondNumber Second operand
     */
    public static void calculateAndDisplay(int firstNumber, int secondNumber) {
        // Descriptive variable names using camelCase
        int sumResult = firstNumber + secondNumber;
        int differenceResult = firstNumber - secondNumber;
        int productResult = firstNumber * secondNumber;
        int quotientResult = firstNumber / secondNumber;
        int remainderResult = firstNumber % secondNumber;

        System.out.println("=== Arithmetic Operations ===");
        System.out.println("Numbers: " + firstNumber + " and " + secondNumber);
        System.out.println("Addition: " + sumResult);
        System.out.println("Subtraction: " + differenceResult);
        System.out.println("Multiplication: " + productResult);
        System.out.println("Division: " + quotientResult);
        System.out.println("Remainder (Modulo): " + remainderResult);
    }

    /**
     * Main method: Entry point of ArithmeticOperations application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        int initialValue = 20;
        int secondaryValue = 4;

        calculateAndDisplay(initialValue, secondaryValue);
    }
}
