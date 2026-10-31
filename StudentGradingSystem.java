import java.util.Scanner;

/**
 * StudentGradingSystem integrates all foundational Week 3 Java concepts:
 * - Scanner input / output handling
 * - Variables and primitive data types
 * - Arithmetic operators for total and average calculations
 * - Conditional statements for letter grade assignment
 * - Loops for processing multiple students
 */
public class StudentGradingSystem {

    public static void main(String[] args) {
        // Initialize Scanner object to capture console input
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("           STUDENT GRADING SYSTEM                ");
        System.out.println("==================================================");

        // Prompt user for number of students to process (e.g., 3 to 5 students)
        System.out.print("Enter number of students to evaluate (e.g., 3): ");
        int numberOfStudents = scanner.nextInt();

        // Consume trailing newline character after nextInt()
        scanner.nextLine();

        // Loop to process each student sequentially
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Entering details for Student #" + i + ":");
            System.out.println("--------------------------------------------------");

            // 1. Input: Student Name (String)
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            // 2. Input: Marks in three subjects (double)
            System.out.print("Enter marks for Subject 1 (out of 100): ");
            double subjectOneMarks = scanner.nextDouble();

            System.out.print("Enter marks for Subject 2 (out of 100): ");
            double subjectTwoMarks = scanner.nextDouble();

            System.out.print("Enter marks for Subject 3 (out of 100): ");
            double subjectThreeMarks = scanner.nextDouble();

            // Consume newline character after reading the last numeric value
            scanner.nextLine();

            // 3. Arithmetic Calculations: Total and Average
            // Total marks is the arithmetic sum of the three subjects
            double totalMarks = subjectOneMarks + subjectTwoMarks + subjectThreeMarks;

            // Average marks is calculated by dividing total by the number of subjects (3.0)
            double averageMarks = totalMarks / 3.0;

            // 4. Conditional Evaluation: Determine Letter Grade based on Average
            char grade;
            if (averageMarks >= 90.0) {
                // Average >= 90 -> Grade A
                grade = 'A';
            } else if (averageMarks >= 75.0) {
                // Average >= 75 -> Grade B
                grade = 'B';
            } else if (averageMarks >= 50.0) {
                // Average >= 50 -> Grade C
                grade = 'C';
            } else {
                // Average < 50 -> Grade F
                grade = 'F';
            }

            // 5. Output: Display Student Performance Report
            System.out.println("\n>>> Student Result Summary <<<");
            System.out.println("Student Name : " + studentName);
            System.out.printf("Total Marks  : %.2f / 300.00\n", totalMarks);
            System.out.printf("Average Marks: %.2f%%\n", averageMarks);
            System.out.println("Final Grade  : Grade " + grade);
        }

        System.out.println("\n==================================================");
        System.out.println(" All student records have been successfully evaluated! ");
        System.out.println("==================================================");

        // Close scanner resource to prevent memory leaks
        scanner.close();
    }
}
