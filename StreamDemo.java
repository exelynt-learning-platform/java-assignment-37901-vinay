import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StreamDemo demonstrates processing student marks using Java 8 Stream API:
 * - Filtering passing students using filter()
 * - Increasing marks using map()
 * - Collecting processed marks into a new List using collect()
 * - Calculating total and average using reduce()
 */
public class StreamDemo {

    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("         STUDENT MARKS PROCESSING USING JAVA STREAM API        ");
        System.out.println("==============================================================");

        // 1. Store student marks in a list
        List<Integer> studentMarks = Arrays.asList(45, 78, 35, 90, 62, 28, 85, 52, 38, 95);
        System.out.println("\nOriginal Student Marks:");
        System.out.println("  " + studentMarks);
        System.out.println("  Total Students: " + studentMarks.size());

        // -------------------------------------------------------------------------
        // 2. Filter passing students (Passing score >= 40)
        // filter() accepts a Predicate and returns a stream with elements that match
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 1: Filter Passing Students (Mark >= 40) ---");
        List<Integer> passingMarks = studentMarks.stream()
                .filter(mark -> mark >= 40)
                .collect(Collectors.toList());

        System.out.println("Passing Student Marks:");
        System.out.println("  " + passingMarks);
        System.out.println("  Number of Passing Students: " + passingMarks.size());

        // -------------------------------------------------------------------------
        // 3. Increase marks using map()
        // map() transforms each element by applying grace/bonus marks (+5)
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 2: Increase Marks using map() (Add 5 Grace Marks) ---");
        List<Integer> increasedMarks = passingMarks.stream()
                .map(mark -> Math.min(100, mark + 5))
                .collect(Collectors.toList());

        System.out.println("Increased Marks (Bonus +5, capped at 100):");
        System.out.println("  " + increasedMarks);

        // -------------------------------------------------------------------------
        // 4. Collect results into a new list
        // Demonstrating an end-to-end stream pipeline: filter -> map -> collect
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 3: Combined Pipeline (Filter -> Map -> Collect) ---");
        List<Integer> finalProcessedMarks = studentMarks.stream()
                .filter(mark -> mark >= 40)           // Filter passing students
                .map(mark -> Math.min(100, mark + 5)) // Increase marks
                .collect(Collectors.toList());        // Collect into new list

        System.out.println("Final Collected List of Processed Marks:");
        System.out.println("  " + finalProcessedMarks);

        // -------------------------------------------------------------------------
        // 5. Calculate Total and Average using reduce()
        // reduce() combines elements of a stream using an associative accumulator
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 4: Calculate Total and Average using reduce() ---");

        // Total calculation using reduce(identity, BinaryOperator)
        int totalMarks = finalProcessedMarks.stream()
                .reduce(0, (accumulator, current) -> accumulator + current);

        // Alternatively using Integer::sum method reference with reduce
        int totalUsingMethodRef = finalProcessedMarks.stream()
                .reduce(0, Integer::sum);

        // Average calculation using reduced total divided by the count
        double averageMarks = finalProcessedMarks.isEmpty() ? 0.0 : (double) totalMarks / finalProcessedMarks.size();

        System.out.println("Total Marks (using reduce lambda)      : " + totalMarks);
        System.out.println("Total Marks (using reduce method ref) : " + totalUsingMethodRef);
        System.out.printf("Average Marks                         : %.2f%n", averageMarks);

        // Summary report
        System.out.println("\n==============================================================");
        System.out.println("                    EXECUTION SUMMARY                         ");
        System.out.println("==============================================================");
        System.out.println("  Total Students Evaluated  : " + studentMarks.size());
        System.out.println("  Passing Students Retained : " + finalProcessedMarks.size());
        System.out.println("  Cumulative Total Marks    : " + totalMarks);
        System.out.printf("  Mean / Average Mark       : %.2f%n", averageMarks);
        System.out.println("==============================================================");
        System.out.println("StreamDemo completed successfully!");
        System.out.println("==============================================================");
    }
}
