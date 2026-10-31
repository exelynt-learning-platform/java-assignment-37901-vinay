import java.util.Scanner;

/**
 * UserInputDemo demonstrates reading and processing user input from the console
 * using the java.util.Scanner class, handling primitive data types, Strings,
 * and managing the newline buffer properly.
 */
public class UserInputDemo {

    public static void main(String[] args) {
        // Create a Scanner object attached to standard input (System.in)
        Scanner scanner = new Scanner(System.in);

        // 1. Prompt the user and read their full name (String)
        System.out.print("Enter your name: ");
        // nextLine() reads the entire line of text including spaces
        String userName = scanner.nextLine();

        // 2. Prompt the user and read their age (int)
        System.out.print("Enter your age: ");
        // nextInt() reads an integer token but leaves the trailing newline character in the buffer
        int userAge = scanner.nextInt();

        // 3. Clear the newline character left in the buffer by nextInt()
        // Calling scanner.nextLine() consumes the leftover newline so subsequent reads work correctly
        scanner.nextLine();

        // 4. Prompt the user and read their favorite number (can be int or double)
        System.out.print("Enter your favorite number: ");
        int favoriteNumber = scanner.nextInt();

        // Consume any remaining newline after favoriteNumber
        scanner.nextLine();

        // 5. Optional additional input to demonstrate buffer was cleared properly
        System.out.print("Enter your favorite hobby: ");
        String userHobby = scanner.nextLine();

        // 6. Print a personalized message using all collected user inputs
        System.out.println("\n========================================");
        System.out.println("         Personalized Summary           ");
        System.out.println("========================================");
        System.out.println("Hello, " + userName + "! Welcome to Java.");
        System.out.println("You are " + userAge + " years old.");
        System.out.println("Your favorite number is " + favoriteNumber + ".");
        System.out.println("Your favorite hobby is " + userHobby + ".");
        System.out.println("Thank you for sharing your details!");
        System.out.println("========================================");

        // Close the scanner resource to prevent memory/resource leaks
        scanner.close();
    }
}
