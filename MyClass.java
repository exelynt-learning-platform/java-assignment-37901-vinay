/**
 * MyClass demonstrates a Java program containing multiple methods.
 * It includes custom methods that print distinct messages and a main
 * method that serves as the entry point to invoke these methods.
 */
public class MyClass {

    /**
     * First custom method: Prints a welcome greeting message to the console.
     * This method demonstrates defining and executing a custom method in Java.
     */
    public static void methodOne() {
        System.out.println("Hello from the first custom method! Welcome to Java programming.");
    }

    /**
     * Second custom method: Prints an informational message to the console.
     * This method explains the benefit of organizing code into reusable methods.
     */
    public static void methodTwo() {
        System.out.println("Greetings from the second custom method! Methods make code reusable and structured.");
    }

    /**
     * The main method serves as the entry point of the Java application.
     * The Java Virtual Machine (JVM) calls this method to start program execution.
     * From here, the custom methods are called in sequence.
     *
     * @param args Command-line arguments passed to the application
     */
    public static void main(String[] args) {
        // Call the first custom method
        methodOne();

        // Call the second custom method
        methodTwo();
    }
}
