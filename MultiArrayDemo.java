import java.util.Scanner;

/**
 * =============================================================================
 * MultiArrayDemo demonstrates Two-Dimensional (2D) Arrays in Java:
 *
 * 1. 2D Array Concept:
 *    - In Java, a 2D array is an "array of arrays".
 *    - Structured like a matrix or table composed of rows and columns.
 *    - Syntax: dataType[][] arrayName = new dataType[rows][columns];
 *    - rows: represents the number of students.
 *    - columns: represents the number of subjects per student.
 *
 * 2. 2D Array Traversal:
 *    - Traversed using nested loops:
 *      * Outer loop iterates through each row (students).
 *      * Inner loop iterates through each column in that row (subjects).
 *    - Individual elements are accessed via: arrayName[rowIndex][columnIndex].
 * =============================================================================
 */
public class MultiArrayDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("      TWO-DIMENSIONAL ARRAY DEMO: STUDENT-SUBJECT MARKS MATRIX   ");
        System.out.println("=================================================================\n");

        // 1. Accept dimensions from the user
        System.out.print("Enter number of students (rows): ");
        int numStudents = scanner.nextInt();

        System.out.print("Enter number of subjects per student (columns): ");
        int numSubjects = scanner.nextInt();

        // Validation for array dimensions
        if (numStudents <= 0) numStudents = 3;
        if (numSubjects <= 0) numSubjects = 3;

        /*
         * 2D ARRAY DECLARATION AND INSTANTIATION:
         * rows: numStudents
         * cols: numSubjects
         */
        double[][] marksMatrix = new double[numStudents][numSubjects];

        // 2. Populating the 2D array using Nested Loops
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Enter marks for each student across subjects:");
        System.out.println("-----------------------------------------------------------------");

        /*
         * NESTED LOOP TRAVERSAL FOR INPUT:
         * Outer loop (i) controls row index (Student index: 0 to numStudents - 1)
         * Inner loop (j) controls column index (Subject index: 0 to numSubjects - 1)
         */
        for (int i = 0; i < marksMatrix.length; i++) {
            System.out.println("\n>>> Entering marks for Student #" + (i + 1) + ":");
            for (int j = 0; j < marksMatrix[i].length; j++) {
                System.out.print("  Subject " + (j + 1) + " Marks: ");
                marksMatrix[i][j] = scanner.nextDouble(); // Storing element at [row][column]
            }
        }

        // 3. Displaying marks in a clean Tabular Format with Row Totals
        System.out.println("\n=================================================================");
        System.out.println("                 STUDENT PERFORMANCE MATRIX (TABLE)              ");
        System.out.println("=================================================================");

        // Print table header
        System.out.printf("%-12s | ", "Student");
        for (int j = 0; j < numSubjects; j++) {
            System.out.printf("Sub %-4d | ", (j + 1));
        }
        System.out.printf("%-10s | %-8s\n", "Total", "Average");
        System.out.println("-----------------------------------------------------------------");

        /*
         * NESTED LOOP TRAVERSAL FOR OUTPUT & CALCULATION:
         * For each student (row), compute row sum and average.
         */
        for (int i = 0; i < marksMatrix.length; i++) {
            double studentTotal = 0.0;

            System.out.printf("Student #%-4d | ", (i + 1));

            // Traverse columns of the current row
            for (int j = 0; j < marksMatrix[i].length; j++) {
                double mark = marksMatrix[i][j];
                studentTotal += mark; // Accumulate student total
                System.out.printf("%8.2f | ", mark);
            }

            double studentAverage = studentTotal / numSubjects;
            System.out.printf("%10.2f | %7.2f%%\n", studentTotal, studentAverage);
        }

        System.out.println("=================================================================");
        scanner.close();
        System.out.println("2D Array processing completed successfully!");
    }
}
