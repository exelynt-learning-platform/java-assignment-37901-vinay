import java.util.Random;
import java.util.Scanner;

/**
 * =============================================================================
 * UtilityDemo demonstrates the practical usage of Java core utility classes:
 *
 * 1. java.util.Scanner (Enhanced Input Handling):
 *    - Validates user input before consumption using scanner.hasNextDouble().
 *    - Cleans newline tokens to prevent input skipping.
 *    - Guards against non-numeric input gracefully.
 *
 * 2. java.util.Random:
 *    - Generates pseudorandom numbers within specific bounds.
 *    - random.nextInt(10) + 1 yields a uniform random integer between 1 and 10 inclusive.
 *
 * 3. java.lang.Math:
 *    - Provides static mathematical utility methods.
 *    - Math.max(a, b): Finds the maximum of two values.
 *    - Math.min(a, b): Finds the minimum of two values (and caps marks at 100).
 *    - Math.round(val): Rounds floating-point numbers to standard precision.
 * =============================================================================
 */
public class UtilityDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=================================================================");
        System.out.println("     STUDENT BONUS MARKS SIMULATOR (UTILITY CLASSES DEMO)        ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // Step 1: Enhanced Scanner Input for Number of Students
        // ---------------------------------------------------------------------
        System.out.print("Enter number of students to evaluate (e.g., 3): ");

        /*
         * ENHANCED SCANNER HANDLING:
         * hasNextInt() inspects the input stream without consuming it,
         * verifying that the user provided an integer rather than letters.
         */
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid entry! Please enter a valid integer.");
            System.out.print("Enter number of students: ");
            scanner.next(); // Discard invalid token
        }
        int studentCount = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        if (studentCount <= 0) {
            studentCount = 3; // Fallback default
        }

        String[] studentNames = new String[studentCount];
        double[] originalMarks = new double[studentCount];
        int[] bonusMarks = new int[studentCount];
        double[] finalMarks = new double[studentCount];

        // ---------------------------------------------------------------------
        // Step 2: Collecting Marks & Generating Random Bonus Marks
        // ---------------------------------------------------------------------
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Student #" + (i + 1) + " Information:");
            System.out.println("--------------------------------------------------");

            System.out.print("Enter Student Name: ");
            studentNames[i] = scanner.nextLine();

            System.out.print("Enter Original Marks (0 to 100): ");
            // Enhanced input validation for floating point marks
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Please enter a valid numeric value for marks.");
                System.out.print("Enter Original Marks: ");
                scanner.next();
            }
            originalMarks[i] = scanner.nextDouble();
            scanner.nextLine(); // Clear newline buffer

            /*
             * USAGE OF java.util.Random:
             * random.nextInt(10) produces an integer in [0, 9].
             * Adding 1 shifts the range to [1, 10] inclusive.
             */
            bonusMarks[i] = random.nextInt(10) + 1;

            /*
             * USAGE OF Math.min():
             * Ensures that adding bonus marks does not exceed the maximum possible score (100.0).
             */
            finalMarks[i] = Math.min(100.0, originalMarks[i] + bonusMarks[i]);
        }

        // ---------------------------------------------------------------------
        // Step 3: Statistical Calculations using java.lang.Math
        // ---------------------------------------------------------------------
        double highestMarks = finalMarks[0];
        double lowestMarks = finalMarks[0];
        double totalMarksSum = 0.0;

        for (int i = 0; i < studentCount; i++) {
            /*
             * USAGE OF Math.max(a, b):
             * Updates highest marks by comparing running max with current student mark.
             */
            highestMarks = Math.max(highestMarks, finalMarks[i]);

            /*
             * USAGE OF Math.min(a, b):
             * Updates lowest marks by comparing running min with current student mark.
             */
            lowestMarks = Math.min(lowestMarks, finalMarks[i]);

            totalMarksSum += finalMarks[i];
        }

        double rawAverage = totalMarksSum / studentCount;
        /*
         * USAGE OF Math.round():
         * Multiplies by 100, rounds to nearest long, and divides by 100.0 to achieve 2 decimal places.
         */
        double roundedAverage = Math.round(rawAverage * 100.0) / 100.0;

        // ---------------------------------------------------------------------
        // Step 4: Display Formatted Results
        // ---------------------------------------------------------------------
        System.out.println("\n=================================================================");
        System.out.println("                STUDENT PERFORMANCE & BONUS REPORT               ");
        System.out.println("=================================================================");
        System.out.printf("%-20s | %-14s | %-12s | %-12s\n", "Student Name", "Original Marks", "Bonus Added", "Final Marks");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%-20s | %14.2f | %12s | %12.2f\n",
                    studentNames[i], originalMarks[i], "+" + bonusMarks[i] + " pts", finalMarks[i]);
        }
        System.out.println("=================================================================");

        System.out.println("\n--- Statistical Analysis (Calculated via java.lang.Math) ---");
        System.out.printf("Highest Mark in Class [Math.max] : %.2f\n", highestMarks);
        System.out.printf("Lowest Mark in Class  [Math.min] : %.2f\n", lowestMarks);
        System.out.printf("Average Marks         [Math.round]: %.2f%%\n", roundedAverage);
        System.out.println("=================================================================");

        // Close scanner
        scanner.close();
    }
}
