import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * =============================================================================
 * StudentGrades integrates all Week 6 topics into a comprehensive program:
 * 1. Exception Handling (try-catch-finally)
 * 2. Custom Exception (InvalidMarksException)
 * 3. Multiple Catch Blocks (NumberFormatException, InputMismatchException, InvalidMarksException)
 * 4. String Manipulation (trim, substring, toUpperCase, toLowerCase, replace)
 * 5. Wrapper Classes & Autoboxing/Unboxing (Double.parseDouble, Integer.parseInt)
 * 6. Utility Classes (Math, Random, Scanner enhancements)
 * =============================================================================
 */
public class StudentGrades {

    /**
     * String Manipulation Helper:
     * Capitalizes the first letter and converts the remainder to lowercase.
     *
     * @param rawName Raw student name string
     * @return Formatted TitleCase name
     */
    public static String formatStudentName(String rawName) {
        if (rawName == null) return "Unknown";

        // 1. trim() removes leading and trailing whitespace
        String cleaned = rawName.trim();

        // 2. replace() eliminates unwanted symbols
        cleaned = cleaned.replace("@", "").replace("#", "").replace("_", " ");

        if (cleaned.isEmpty()) return "Unknown";

        String[] parts = cleaned.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String part : parts) {
            if (part.length() > 0) {
                // 3. substring() and toUpperCase() / toLowerCase()
                String firstChar = part.substring(0, 1).toUpperCase();
                String rest = part.substring(1).toLowerCase();
                formattedName.append(firstChar).append(rest).append(" ");
            }
        }
        return formattedName.toString().trim();
    }

    /**
     * Validates marks range and throws custom checked exception if invalid.
     *
     * @param marks Numeric marks to validate
     * @throws InvalidMarksException if marks are < 0 or > 100
     */
    public static void validateMarks(double marks) throws InvalidMarksException {
        if (marks < 0.0 || marks > 100.0) {
            throw new InvalidMarksException(String.format(
                    "InvalidMarksException: Marks (%.2f) must be between 0.00 and 100.00!", marks));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=================================================================");
        System.out.println("   STUDENT GRADES SYSTEM (EXCEPTION HANDLING & CORE JAVA APIS)   ");
        System.out.println("=================================================================\n");

        int studentCount = 0;

        /*
         * ENHANCED INPUT HANDLING & TRY-CATCH-FINALLY
         */
        try {
            System.out.print("Enter number of students to process (as String or int): ");
            String countInput = scanner.nextLine().trim();

            // Wrapper Class: Integer.parseInt converts string input to primitive int
            studentCount = Integer.parseInt(countInput);

            if (studentCount <= 0) {
                System.out.println("Notice: Count must be positive. Defaulting to 2 students.");
                studentCount = 2;
            }

        } catch (NumberFormatException nfe) {
            System.out.println("[Handled NumberFormatException]: Non-integer entered for student count. Defaulting to 2.");
            studentCount = 2;
        }

        String[] formattedNames = new String[studentCount];
        // Wrapper class array: Double objects
        Double[] originalScores = new Double[studentCount];
        Double[] finalScores = new Double[studentCount];
        int[] bonusScores = new int[studentCount];

        int successfulEntries = 0;

        // Process each student
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Processing Student #" + (i + 1) + ":");
            System.out.println("--------------------------------------------------");

            /*
             * TRY-CATCH-FINALLY WITH MULTIPLE CATCH BLOCKS
             */
            try {
                System.out.print("Enter Student Full Name: ");
                String rawName = scanner.nextLine();
                // String Manipulation formatting
                String formattedName = formatStudentName(rawName);

                System.out.print("Enter Marks as String (e.g. \"82.5\"): ");
                String marksStr = scanner.nextLine().trim();

                // Wrapper Class Method: Double.parseDouble()
                double rawMarks = Double.parseDouble(marksStr);

                // Input Validation: Throws custom InvalidMarksException
                validateMarks(rawMarks);

                /*
                 * AUTOBOXING DEMONSTRATION:
                 * Primitive double 'rawMarks' automatically converted to 'Double' wrapper object.
                 */
                Double boxedMarks = rawMarks; // Autoboxing
                originalScores[successfulEntries] = boxedMarks;

                /*
                 * UTILITY CLASS: java.util.Random
                 * Generates bonus marks between 1 and 10
                 */
                int bonus = random.nextInt(10) + 1; // 1 to 10
                bonusScores[successfulEntries] = bonus;

                /*
                 * UNBOXING & MATH UTILITY DEMONSTRATION:
                 * boxedMarks is unboxed to primitive double to calculate addition.
                 * Math.min caps total score at 100.0.
                 */
                double unboxedMarks = boxedMarks; // Unboxing
                double calculatedFinal = Math.min(100.0, unboxedMarks + bonus);

                // Autoboxing result back into Double array
                finalScores[successfulEntries] = calculatedFinal;
                formattedNames[successfulEntries] = formattedName;

                successfulEntries++;
                System.out.println(">> Student Record Successfully Validated & Stored!");

            /*
             * MULTIPLE CATCH BLOCKS:
             * Specific exceptions handled individually in proper order.
             */
            } catch (InvalidMarksException ime) {
                System.out.println("[CAUGHT CUSTOM EXCEPTION]: " + ime.getMessage());
                System.out.println("Recovery: This invalid student record was skipped.");

            } catch (NumberFormatException nfe) {
                System.out.println("[CAUGHT NUMBER FORMAT EXCEPTION]: The entered marks string is not a valid decimal number!");
                System.out.println("Recovery: This invalid student record was skipped.");

            } catch (InputMismatchException ime) {
                System.out.println("[CAUGHT INPUT MISMATCH EXCEPTION]: Stream token did not match expected type.");

            } catch (Exception e) {
                System.out.println("[CAUGHT GENERAL EXCEPTION]: Unexpected error: " + e.getMessage());

            } finally {
                System.out.println("[FINALLY BLOCK]: Iteration #" + (i + 1) + " evaluation completed.");
            }
        }

        // ---------------------------------------------------------------------
        // Results Summary & Math Utility Analysis
        // ---------------------------------------------------------------------
        if (successfulEntries > 0) {
            System.out.println("\n=================================================================");
            System.out.println("                  FINAL STUDENT GRADES REPORT                    ");
            System.out.println("=================================================================");
            System.out.printf("%-20s | %-14s | %-10s | %-12s\n", "Student Name", "Original Score", "Bonus", "Final Score");
            System.out.println("-----------------------------------------------------------------");

            double maxScore = finalScores[0]; // Unboxed automatically
            double minScore = finalScores[0];
            double totalScoreSum = 0.0;

            for (int i = 0; i < successfulEntries; i++) {
                double scoreVal = finalScores[i]; // Unboxing Double to double
                System.out.printf("%-20s | %14.2f | %10s | %12.2f\n",
                        formattedNames[i], originalScores[i], "+" + bonusScores[i], scoreVal);

                // Math utility methods
                maxScore = Math.max(maxScore, scoreVal);
                minScore = Math.min(minScore, scoreVal);
                totalScoreSum += scoreVal;
            }

            double averageScore = totalScoreSum / successfulEntries;
            double roundedAverage = Math.round(averageScore * 100.0) / 100.0;

            System.out.println("=================================================================");
            System.out.printf("Highest Final Score [Math.max]  : %.2f\n", maxScore);
            System.out.printf("Lowest Final Score  [Math.min]  : %.2f\n", minScore);
            System.out.printf("Average Class Score [Math.round]: %.2f%%\n", roundedAverage);
            System.out.println("=================================================================");
        } else {
            System.out.println("\nNo valid student records were processed.");
        }

        scanner.close();
        System.out.println("Program finished successfully.");
    }
}
