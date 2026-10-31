import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * =============================================================================
 *          JAVA PERFORMANCE OPTIMIZATION TECHNIQUES DEMONSTRATION
 * =============================================================================
 *
 * This benchmark compares common inefficient coding patterns vs. optimized alternatives:
 *
 * 1. String Concatenation in Loops (String '+' vs StringBuilder):
 *    - Inefficient: Strings in Java are immutable. Using '+' in a loop creates a new
 *      StringBuilder, allocates a new char[] array, copies old content, and creates a
 *      new String object on every iteration, leading to O(N^2) time complexity and
 *      heavy GC allocation pressure.
 *    - Optimized: StringBuilder maintains a resizable internal buffer, appending in
 *      amortized O(1) time complexity and O(N) overall.
 *
 * 2. Collection Membership Lookup (ArrayList vs HashSet):
 *    - Inefficient: ArrayList.contains() performs a linear scan O(N) from start to finish.
 *      For M lookups across N items, total complexity is O(M * N).
 *    - Optimized: HashSet.contains() uses hash bucket indexing achieving O(1) average
 *      lookup time, reducing total complexity to O(M).
 *
 * 3. Collection Sizing Overhead (Default Capacity vs Pre-sized Initial Capacity):
 *    - Inefficient: ArrayList() starts with default capacity of 10. Adding large volumes
 *      forces repeated array reallocations and System.arraycopy operations as it resizes.
 *    - Optimized: ArrayList(initialCapacity) allocates the target array once, eliminating
 *      resizing overhead and internal copy operations.
 * =============================================================================
 */
public class PerformanceOptimizationDemo {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("          JAVA PERFORMANCE BENCHMARK & OPTIMIZATION DEMO                  ");
        System.out.println("==========================================================================");

        // ---------------------------------------------------------------------
        // BENCHMARK 1: String Concatenation in Loops (20,000 iterations)
        // ---------------------------------------------------------------------
        System.out.println("\n--- Benchmark 1: String Concatenation (20,000 Iterations) ---");
        int stringIterations = 20_000;

        // Inefficient: String '+' concatenation
        long start1 = System.nanoTime();
        String inefficientString = "";
        for (int i = 0; i < stringIterations; i++) {
            inefficientString += "x";
        }
        long timeStringConcat = System.nanoTime() - start1;

        // Optimized: StringBuilder
        long start2 = System.nanoTime();
        StringBuilder optimizedSb = new StringBuilder(stringIterations);
        for (int i = 0; i < stringIterations; i++) {
            optimizedSb.append("x");
        }
        String optimizedString = optimizedSb.toString();
        long timeStringBuilder = System.nanoTime() - start2;

        printComparison("String '+' (Immutable Copying)", timeStringConcat,
                        "StringBuilder (Buffer Append)", timeStringBuilder);

        // ---------------------------------------------------------------------
        // BENCHMARK 2: Collection Lookup / Membership (10,000 lookups in 50,000 items)
        // ---------------------------------------------------------------------
        System.out.println("\n--- Benchmark 2: Collection Lookup (10,000 Queries in 50,000 Items) ---");
        int datasetSize = 50_000;
        int queryCount = 10_000;

        List<Integer> list = new ArrayList<>(datasetSize);
        Set<Integer> set = new HashSet<>(datasetSize);

        for (int i = 0; i < datasetSize; i++) {
            list.add(i);
            set.add(i);
        }

        Random random = new Random(42);
        int[] queries = new int[queryCount];
        for (int i = 0; i < queryCount; i++) {
            queries[i] = random.nextInt(datasetSize * 2); // Half inside, half outside
        }

        // Inefficient: ArrayList.contains (O(N) linear search)
        long start3 = System.nanoTime();
        int listHits = 0;
        for (int q : queries) {
            if (list.contains(q)) {
                listHits++;
            }
        }
        long timeListLookup = System.nanoTime() - start3;

        // Optimized: HashSet.contains (O(1) constant-time hash lookup)
        long start4 = System.nanoTime();
        int setHits = 0;
        for (int q : queries) {
            if (set.contains(q)) {
                setHits++;
            }
        }
        long timeSetLookup = System.nanoTime() - start4;

        printComparison("ArrayList.contains() (O(N) Scan)", timeListLookup,
                        "HashSet.contains()   (O(1) Hash)", timeSetLookup);

        // ---------------------------------------------------------------------
        // BENCHMARK 3: Collection Pre-Sizing (500,000 elements)
        // ---------------------------------------------------------------------
        System.out.println("\n--- Benchmark 3: ArrayList Pre-Sizing (500,000 Elements) ---");
        int elementsCount = 500_000;

        // Inefficient: Default capacity (Repeated resizing & array copying)
        long start5 = System.nanoTime();
        List<Integer> defaultList = new ArrayList<>();
        for (int i = 0; i < elementsCount; i++) {
            defaultList.add(i);
        }
        long timeDefaultList = System.nanoTime() - start5;

        // Optimized: Pre-sized capacity (Single allocation)
        long start6 = System.nanoTime();
        List<Integer> presizedList = new ArrayList<>(elementsCount);
        for (int i = 0; i < elementsCount; i++) {
            presizedList.add(i);
        }
        long timePresizedList = System.nanoTime() - start6;

        printComparison("ArrayList() (Dynamic Resizing)", timeDefaultList,
                        "ArrayList(N) (Pre-allocated)", timePresizedList);

        // ---------------------------------------------------------------------
        // Comprehensive Performance Summary Table
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("                   BENCHMARK TIMING SUMMARY TABLE                         ");
        System.out.println("==========================================================================");
        System.out.printf("  %-30s | %-15s | %-15s | %-10s%n",
                "Optimization Domain", "Inefficient (ms)", "Optimized (ms)", "Speedup");
        System.out.println("  -------------------------------+-----------------+-----------------+-----------");
        printTableRow("String Concatenation", timeStringConcat, timeStringBuilder);
        printTableRow("Collection Membership", timeListLookup, timeSetLookup);
        printTableRow("ArrayList Sizing", timeDefaultList, timePresizedList);
        System.out.println("==========================================================================");
        System.out.println("Performance optimization benchmark completed successfully!");
        System.out.println("==========================================================================");
    }

    private static void printComparison(String label1, long nanos1, String label2, long nanos2) {
        double ms1 = nanos1 / 1_000_000.0;
        double ms2 = nanos2 / 1_000_000.0;
        double speedup = (nanos2 > 0) ? ((double) nanos1 / nanos2) : 1.0;

        System.out.printf("  [Inefficient] %-32s : %8.3f ms (%,12d ns)%n", label1, ms1, nanos1);
        System.out.printf("  [Optimized]   %-32s : %8.3f ms (%,12d ns)%n", label2, ms2, nanos2);
        System.out.printf("  >>> Result: Optimized version is %.1fx faster!%n", speedup);
    }

    private static void printTableRow(String domain, long nanos1, long nanos2) {
        double ms1 = nanos1 / 1_000_000.0;
        double ms2 = nanos2 / 1_000_000.0;
        double speedup = (nanos2 > 0) ? ((double) nanos1 / nanos2) : 1.0;

        System.out.printf("  %-30s | %12.3f ms | %12.3f ms | %8.1fx%n",
                domain, ms1, ms2, speedup);
    }
}
