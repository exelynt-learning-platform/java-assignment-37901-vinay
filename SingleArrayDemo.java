import java.util.Scanner;

/**
 * =============================================================================
 * SingleArrayDemo demonstrates the fundamentals of single-dimensional arrays in Java:
 *
 * 1. Array Declaration & Instantiation:
 *    - An array is a fixed-size, indexed collection of elements of the same data type.
 *    - Syntax: dataType[] arrayName = new dataType[size];
 *    - Memory is allocated sequentially on the heap.
 *
 * 2. Array Indexing & Access:
 *    - Java arrays are 0-indexed (indices range from 0 to array.length - 1).
 *    - Elements are stored and retrieved using the bracket notation: arrayName[index].
 *
 * 3. Processing with Loops:
 *    - Traversed using standard 'for' loops and enhanced 'for-each' loops.
 * =============================================================================
 */
public class SingleArrayDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("      SINGLE-DIMENSIONAL ARRAY DEMO: STUDENT MARKS               ");
        System.out.println("=================================================================\n");

        // 1. Accept the number of students from user
        System.out.print("Enter the number of students: ");
        int studentCount = scanner.nextInt();

        // Input validation
        if (studentCount <= 0) {
            System.out.println("Invalid count! Setting default student count to 3.");
            studentCount = 3;
        }

        /*
         * ARRAY DECLARATION AND INSTANTIATION:
         * 'double[] marks' declares an array of primitive doubles.
         * 'new double[studentCount]' dynamically allocates memory for studentCount elements.
         */
        double[] studentMarks = new double[studentCount];

        // 2. Storing marks in the array using a for loop
        System.out.println("\nPlease enter marks (out of 100) for each student:");
        for (int i = 0; i < studentMarks.length; i++) {
            System.out.print("Enter marks for Student #" + (i + 1) + ": ");
            studentMarks[i] = scanner.nextDouble(); // Storing value at index i
        }

        // 3. Displaying all marks using an enhanced for loop (for-each)
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("                RECORDED STUDENT MARKS LIST                      ");
        System.out.println("-----------------------------------------------------------------");
        int index = 1;
        for (double mark : studentMarks) {
            System.out.printf("Student #%d : %.2f / 100.00\n", index++, mark);
        }

        // 4. Calculating Total and Average Marks
        double totalMarks = 0.0;
        for (int i = 0; i < studentMarks.length; i++) {
            totalMarks += studentMarks[i]; // Accumulate sum
        }

        double averageMarks = totalMarks / studentMarks.length;

        // 5. Displaying calculated total and average
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("Total Number of Students : %d\n", studentMarks.length);
        System.out.printf("Total Cumulative Marks   : %.2f\n", totalMarks);
        System.out.printf("Average Class Marks      : %.2f%%\n", averageMarks);
        System.out.println("=================================================================");

        scanner.close();
        System.out.println("Array processing completed successfully!");
    }
}
