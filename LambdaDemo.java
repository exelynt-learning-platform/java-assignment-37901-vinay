import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * LambdaDemo demonstrates the usage of Java 8 Lambda Expressions
 * and built-in Functional Interfaces from the java.util.function package.
 *
 * Lambda Expression Syntax:
 * (parameters) -> expression
 * OR
 * (parameters) -> { statements; }
 *
 * A lambda expression provides a clear and concise way to implement
 * a Functional Interface (an interface with exactly one abstract method - SAM).
 */
public class LambdaDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   DEMONSTRATION OF JAVA LAMBDA EXPRESSIONS       ");
        System.out.println("==================================================");

        // =========================================================================
        // 1. Predicate<T> Functional Interface
        // Represents a predicate (boolean-valued function) of one argument.
        // Method signature: boolean test(T t)
        // Lambda syntax: num -> num % 2 == 0
        // =========================================================================
        System.out.println("\n--- 1. Checking Even/Odd with Predicate<Integer> ---");

        // Lambda expression to check if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Lambda expression to check if a number is odd (or using negate())
        Predicate<Integer> isOdd = num -> num % 2 != 0;

        int[] testNumbers = {4, 7, 12, 19, 20, 33};

        for (int n : testNumbers) {
            String parity = isEven.test(n) ? "Even" : "Odd";
            System.out.printf("Number: %2d -> isEven: %-5b | isOdd: %-5b | Parity: %s%n",
                    n, isEven.test(n), isOdd.test(n), parity);
        }

        // =========================================================================
        // 2. Function<T, R> Functional Interface
        // Represents a function that accepts one argument of type T and produces
        // a result of type R.
        // Method signature: R apply(T t)
        // Lambda syntax: x -> x * x
        // =========================================================================
        System.out.println("\n--- 2. Calculating Square with Function<Integer, Integer> ---");

        // Lambda expression to calculate the square of a number
        Function<Integer, Integer> squareFunction = x -> x * x;

        // Lambda expression to calculate cube of a number
        Function<Integer, Integer> cubeFunction = x -> x * x * x;

        int[] numbersToSquare = {1, 3, 5, 8, 10, 12};

        for (int num : numbersToSquare) {
            int square = squareFunction.apply(num);
            int cube = cubeFunction.apply(num);
            System.out.printf("Number: %2d -> Square: %-4d | Cube: %-5d%n",
                    num, square, cube);
        }

        // =========================================================================
        // 3. Combining Lambdas with Collections & Consumer<T>
        // Consumer<T> accepts a single input argument and returns no result.
        // Method signature: void accept(T t)
        // =========================================================================
        System.out.println("\n--- 3. Filtering & Mapping a List using Lambdas ---");

        List<Integer> values = Arrays.asList(2, 5, 8, 11, 14, 17, 20);
        Consumer<String> printer = msg -> System.out.println("  " + msg);

        printer.accept("Original values: " + values);

        System.out.print("  Even numbers and their squares: ");
        for (Integer val : values) {
            if (isEven.test(val)) {
                System.out.print(val + "^2=" + squareFunction.apply(val) + " ");
            }
        }
        System.out.println();

        System.out.println("\n==================================================");
        System.out.println("Lambda expressions demo completed successfully!");
        System.out.println("==================================================");
    }
}
