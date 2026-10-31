/**
 * LoopsDemo demonstrates the implementation and behavior of different loop structures
 * in Java: standard for loop, while loop, do-while loop, and enhanced for loop (for-each),
 * along with loop control statements (break and continue).
 */
public class LoopsDemo {

    public static void main(String[] args) {

        // =====================================================================
        // 1. Standard for Loop (1 to 10)
        // A for loop is ideal when the exact number of iterations is known in advance.
        // It consists of initialization (i = 1), condition (i <= 10), and increment (i++).
        // =====================================================================
        System.out.println("=== 1. For Loop: Numbers from 1 to 10 ===");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println(); // New line

        // =====================================================================
        // 2. While Loop (10 to 1)
        // A while loop checks the condition before executing the loop body.
        // It continues executing as long as the boolean condition remains true.
        // =====================================================================
        System.out.println("\n=== 2. While Loop: Numbers from 10 to 1 ===");
        int count = 10;
        while (count >= 1) {
            System.out.print(count + " ");
            count--; // Decrement counter towards loop termination
        }
        System.out.println(); // New line

        // =====================================================================
        // 3. Do-While Loop (Numbers divisible by 2 between 1 and 10)
        // A do-while loop is an exit-controlled loop; it executes the loop body
        // at least once before testing the condition at the end.
        // Demonstrates continue to skip odd numbers.
        // =====================================================================
        System.out.println("\n=== 3. Do-While Loop: Numbers divisible by 2 (1 to 10) ===");
        int num = 1;
        do {
            // Check if current number is divisible by 2 (even number)
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
            num++;
        } while (num <= 10);
        System.out.println(); // New line

        // =====================================================================
        // 4. Enhanced for Loop (For-Each Loop)
        // Introduced in Java 5, the enhanced for loop provides a simple, clean
        // syntax to sequentially iterate over elements in arrays or collections.
        // Demonstrates break and continue control statements.
        // =====================================================================
        System.out.println("\n=== 4. Enhanced For Loop: String Array Iteration ===");
        String[] programmingLanguages = {"Java", "Python", "C++", "JavaScript", "SkipMe", "Go", "StopBeforeMe"};

        for (String language : programmingLanguages) {
            // Using 'continue' to skip a specific element
            if (language.equals("SkipMe")) {
                continue; // Skips current iteration
            }

            // Using 'break' to terminate the loop early
            if (language.equals("StopBeforeMe")) {
                break; // Exits the loop completely
            }

            System.out.println("Language: " + language);
        }
    }
}
