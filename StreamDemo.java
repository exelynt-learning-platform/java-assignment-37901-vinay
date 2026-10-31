import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * StreamDemo demonstrates core Java 8 Stream API pipeline operations:
 * - Intermediate Operations: filter(), map()
 * - Terminal Operations: collect(), reduce()
 *
 * Stream pipelines process sequences of elements in a declarative, functional manner.
 */
public class StreamDemo {

    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("          JAVA STREAM API: FILTER, MAP, REDUCE & COLLECT       ");
        System.out.println("==============================================================");

        // 1. Store initial student marks in a List
        List<Double> originalMarks = Arrays.asList(42.0, 78.5, 36.0, 88.0, 91.5, 53.0, 31.0, 67.5, 95.0, 48.0);
        System.out.println("\nInitial Student Marks List:");
        System.out.println("  " + originalMarks);
        System.out.println("  Total count: " + originalMarks.size());

        // -------------------------------------------------------------------------
        // 2. Filter passing marks (Passing threshold >= 50.0)
        // filter() is an intermediate operation that uses a Predicate
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 1: Filter Passing Students (Marks >= 50.0) ---");
        List<Double> passingMarks = originalMarks.stream()
                .filter(mark -> mark >= 50.0) // Keeps only marks >= 50.0
                .collect(Collectors.toList());  // Terminal operation collecting into List

        System.out.println("Passing marks: " + passingMarks);
        System.out.println("Passing students count: " + passingMarks.size());

        // -------------------------------------------------------------------------
        // 3. Increase marks using map() (Bonus +5 marks, capped at 100.0)
        // map() is an intermediate operation transforming each element
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 2: Increase Marks using map() (Add 5 Grace Marks, Cap 100) ---");
        List<Double> boostedMarks = passingMarks.stream()
                .map(mark -> Math.min(100.0, mark + 5.0)) // Increases mark by 5
                .collect(Collectors.toList());            // Collect results into a new List

        System.out.println("Boosted passing marks: " + boostedMarks);

        // -------------------------------------------------------------------------
        // 4. Calculate Total and Average using reduce()
        // reduce() is a terminal operation that combines stream elements into a single summary
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 3: Calculate Total and Average using reduce() ---");

        // Calculate total marks using reduce with identity and binary accumulator
        // reduce(initialValue, (accumulator, current) -> accumulator + current)
        double totalMarks = boostedMarks.stream()
                .reduce(0.0, (acc, mark) -> acc + mark);

        // Calculate average using reduced total divided by count
        double averageMarks = boostedMarks.isEmpty() ? 0.0 : totalMarks / boostedMarks.size();

        System.out.printf("Total of Boosted Marks   : %.2f%n", totalMarks);
        System.out.printf("Average of Boosted Marks : %.2f%n", averageMarks);

        // Demonstrating reduce with Optional (without identity parameter)
        Optional<Double> maxMark = boostedMarks.stream()
                .reduce((m1, m2) -> m1 > m2 ? m1 : m2);
        maxMark.ifPresent(max -> System.out.printf("Highest Mark in Cohort   : %.2f%n", max));

        Optional<Double> minMark = boostedMarks.stream()
                .reduce((m1, m2) -> m1 < m2 ? m1 : m2);
        minMark.ifPresent(min -> System.out.printf("Lowest Mark in Cohort    : %.2f%n", min));

        // -------------------------------------------------------------------------
        // 5. Complete Single Pipeline: Filter -> Map -> Collect
        // -------------------------------------------------------------------------
        System.out.println("\n--- Step 4: Chained Stream Pipeline (Filter -> Map -> Collect) ---");
        List<Double> finalCurvedMarks = originalMarks.stream()
                .filter(m -> m >= 50.0)                     // 1. Filter
                .map(m -> Math.min(100.0, m + 2.5))         // 2. Map (Curve)
                .sorted()                                   // 3. Sort ascending
                .collect(Collectors.toList());              // 4. Collect into new List

        System.out.println("Final Curved Passing Marks (Sorted):");
        System.out.println("  " + finalCurvedMarks);

        System.out.println("\n==============================================================");
        System.out.println("Stream API operations demo completed successfully!");
        System.out.println("==============================================================");
    }
}
