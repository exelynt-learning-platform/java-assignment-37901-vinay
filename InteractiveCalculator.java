import java.util.Scanner;

/**
 * InteractiveCalculator brings together all Week 3 Java core fundamentals:
 * - Data types & Variables: int, double, char, boolean, String
 * - User Input/Output: java.util.Scanner with buffer management
 * - Operators: Arithmetic (+, -, *, /, %), Relational, Logical
 * - Conditionals: if, if-else, and switch statements
 * - Loops: while loop for continuous interactive calculations until user exits
 * - Input validation: Handling division and modulo by zero gracefully
 */
public class InteractiveCalculator {

    public static void main(String[] args) {
        // Scanner resource for user input
        Scanner scanner = new Scanner(System.in);

        // String variable for greeting and user feedback
        String appTitle = "=== Interactive Java Calculator ===";
        System.out.println(appTitle);
        System.out.println("Perform arithmetic operations with automatic input validation.\n");

        // boolean variable to control the main calculation loop
        boolean keepRunning = true;

        // int variable to track total calculations performed
        int calculationCount = 0;

        // while loop repeats calculations until the user chooses to exit
        while (keepRunning) {
            System.out.println("--------------------------------------------------");
            System.out.println("Calculation #" + (calculationCount + 1));
            System.out.println("Available Operations: + (Add), - (Subtract), * (Multiply), / (Divide), % (Modulo)");

            // double variables to store the operands
            System.out.print("Enter first number: ");
            double firstNumber = scanner.nextDouble();

            // char variable to store the selected operator
            System.out.print("Enter operator (+, -, *, /, %): ");
            char operator = scanner.next().charAt(0);

            System.out.print("Enter second number: ");
            double secondNumber = scanner.nextDouble();

            // double variable to store computation result
            double calculationResult = 0.0;

            // boolean variable to track whether operation was valid
            boolean isOperationValid = true;

            // switch statement determines the operation based on the operator character
            switch (operator) {
                case '+':
                    calculationResult = firstNumber + secondNumber;
                    break;

                case '-':
                    calculationResult = firstNumber - secondNumber;
                    break;

                case '*':
                    calculationResult = firstNumber * secondNumber;
                    break;

                case '/':
                    // Input validation: prevent division by zero using an if-else check
                    if (secondNumber == 0.0) {
                        System.out.println("Error: Division by zero is undefined.");
                        isOperationValid = false;
                    } else {
                        calculationResult = firstNumber / secondNumber;
                    }
                    break;

                case '%':
                    // Input validation: prevent modulo by zero using an if-else check
                    if (secondNumber == 0.0) {
                        System.out.println("Error: Modulo by zero is undefined.");
                        isOperationValid = false;
                    } else {
                        calculationResult = firstNumber % secondNumber;
                    }
                    break;

                default:
                    // Handles unrecognized operator characters
                    System.out.println("Error: Invalid operator '" + operator + "'. Please use +, -, *, /, or %.");
                    isOperationValid = false;
                    break;
            }

            // Conditional statement to display result if operation was valid
            if (isOperationValid) {
                calculationCount++; // Increment successful calculation counter
                System.out.printf("Result: %.4f %c %.4f = %.4f\n", firstNumber, operator, secondNumber, calculationResult);
            }

            // Prompt user whether to continue or exit
            System.out.print("\nDo you want to perform another calculation? (yes/no): ");
            String userChoice = scanner.next();

            // if-else condition to check continuation preference
            if (userChoice.equalsIgnoreCase("no") || userChoice.equalsIgnoreCase("n")) {
                keepRunning = false; // Ends the while loop
            }
        }

        // Final summary message
        System.out.println("\n==================================================");
        System.out.println("Calculator closed. Total successful calculations performed: " + calculationCount);
        System.out.println("Thank you for using the Interactive Calculator!");
        System.out.println("==================================================");

        // Close scanner
        scanner.close();
    }
}
