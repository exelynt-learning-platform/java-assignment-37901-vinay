import java.util.Scanner;

/**
 * ConditionalDemo demonstrates conditional decision making in Java using
 * if-else-if-else ladder statements and a switch-case statement.
 */
public class ConditionalDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // =====================================================================
        // Part 1: If-Else-If Ladder (Positive / Negative / Zero Check)
        // =====================================================================
        System.out.println("=== Part 1: Number Sign Checker ===");
        System.out.print("Enter an integer number: ");
        int userNumber = scanner.nextInt();

        // Condition 1: Check if the number is strictly greater than 0
        if (userNumber > 0) {
            System.out.println("Positive");
        }
        // Condition 2: Check if the number is strictly less than 0
        else if (userNumber < 0) {
            System.out.println("Negative");
        }
        // Condition 3: If the number is neither greater nor less than 0, it must be zero
        else {
            System.out.println("Zero");
        }

        // =====================================================================
        // Part 2: Switch Statement (Day of the Week: 1 to 7)
        // =====================================================================
        System.out.println("\n=== Part 2: Day of the Week ===");
        System.out.print("Enter a number (1-7) to get the day of the week: ");
        int dayNumber = scanner.nextInt();

        // Switch statement evaluates dayNumber and matches against case values
        switch (dayNumber) {
            case 1:
                // Case 1 represents Monday (beginning of standard work week)
                System.out.println("Day 1: Monday");
                break;
            case 2:
                // Case 2 represents Tuesday
                System.out.println("Day 2: Tuesday");
                break;
            case 3:
                // Case 3 represents Wednesday
                System.out.println("Day 3: Wednesday");
                break;
            case 4:
                // Case 4 represents Thursday
                System.out.println("Day 4: Thursday");
                break;
            case 5:
                // Case 5 represents Friday
                System.out.println("Day 5: Friday");
                break;
            case 6:
                // Case 6 represents Saturday
                System.out.println("Day 6: Saturday");
                break;
            case 7:
                // Case 7 represents Sunday
                System.out.println("Day 7: Sunday");
                break;
            default:
                // Default block executes when dayNumber does not match any case (1-7)
                System.out.println("Invalid day number! Please enter a value between 1 and 7.");
                break;
        }

        // Close scanner resource
        scanner.close();
    }
}
