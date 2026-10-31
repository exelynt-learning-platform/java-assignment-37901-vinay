import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 *             JAVA MEMORY MANAGEMENT & GARBAGE COLLECTION DEMO
 * =============================================================================
 *
 * 1. Java Heap Architecture:
 *    - Young Generation:
 *      * Eden Space: Where all newly instantiated objects are allocated.
 *      * Survivor Spaces (S0 & S1): Objects surviving minor GC cycles are moved here.
 *    - Old Generation (Tenured):
 *      * Long-surviving objects that exceed the tenuring threshold migrate here.
 *    - Metaspace:
 *      * Stores class metadata, bytecode definitions, and static variables.
 *
 * 2. When does an Object Become Eligible for Garbage Collection?
 *    - An object becomes eligible for GC when it is no longer reachable from any
 *      active GC Root (threads, local variables on execution stack, static fields).
 *    - Common triggers:
 *      a) Re-assigning reference variable to null (e.g., list = null).
 *      b) Reference going out of scope (e.g., exiting a block or method).
 *      c) Re-assigning reference to another object.
 *      d) Island of isolation: Two or more unreferenced objects referencing each other.
 *
 * 3. The Role of System.gc():
 *    - IMPORTANT: System.gc() (and Runtime.getRuntime().gc()) is ONLY A REQUEST
 *      to the Java Virtual Machine, NOT A COMMAND.
 *    - The JVM does not guarantee that the GC will run immediately or collect
 *      all eligible objects synchronously. The JVM scheduler decides whether and
 *      when to execute garbage collection based on heap pressure and GC algorithm
 *      (G1GC, ZGC, ParallelGC).
 * =============================================================================
 */
public class MemoryDemo {

    // Dummy class to allocate tangible heap memory
    static class StudentPayload {
        private final int id;
        private final String name;
        private final byte[] memoryChunk;

        public StudentPayload(int id, String name) {
            this.id = id;
            this.name = name;
            this.memoryChunk = new byte[1024]; // 1 KB per object payload
        }
    }

    /**
     * Calculates used heap memory: totalMemory() - freeMemory().
     */
    private static long getUsedMemoryBytes() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static double bytesToMB(long bytes) {
        return bytes / (1024.0 * 1024.0);
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       JAVA MEMORY MANAGEMENT & GARBAGE COLLECTION MONITOR                ");
        System.out.println("==========================================================================");

        Runtime runtime = Runtime.getRuntime();

        // ---------------------------------------------------------------------
        // Step 1: Baseline Heap Memory
        // ---------------------------------------------------------------------
        System.out.println("\n[1] Baseline Memory State (Before Allocation):");
        long initialUsed = getUsedMemoryBytes();
        long initialTotal = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();

        System.out.printf("    Max Available Heap Memory : %,10.2f MB%n", bytesToMB(maxMemory));
        System.out.printf("    Current Total Allocated   : %,10.2f MB%n", bytesToMB(initialTotal));
        System.out.printf("    Initial Used Memory       : %,10.2f MB (%,d bytes)%n",
                bytesToMB(initialUsed), initialUsed);

        // ---------------------------------------------------------------------
        // Step 2: Create and Allocate Objects Inside a Loop
        // ---------------------------------------------------------------------
        System.out.println("\n[2] Allocating 150,000 StudentPayload Objects in Heap...");
        List<StudentPayload> studentCache = new ArrayList<>();

        for (int i = 1; i <= 150_000; i++) {
            studentCache.add(new StudentPayload(i, "Student Record #" + i));
        }

        long afterAllocUsed = getUsedMemoryBytes();
        long afterAllocTotal = runtime.totalMemory();
        long memoryConsumed = afterAllocUsed - initialUsed;

        System.out.printf("    Total Heap after Allocation: %,10.2f MB%n", bytesToMB(afterAllocTotal));
        System.out.printf("    Used Memory After Creation : %,10.2f MB (%,d bytes)%n",
                bytesToMB(afterAllocUsed), afterAllocUsed);
        System.out.printf("    Memory Increase by Objects : %,10.2f MB%n", bytesToMB(memoryConsumed));

        // ---------------------------------------------------------------------
        // Step 3: Release Object References
        // ---------------------------------------------------------------------
        System.out.println("\n[3] Releasing References (Dereferencing objects to make them GC eligible)...");
        studentCache.clear();
        studentCache = null; // Removing strong GC root reference

        long afterDerefUsed = getUsedMemoryBytes();
        System.out.printf("    Used Memory After Dereference (Before GC): %,10.2f MB%n",
                bytesToMB(afterDerefUsed));
        System.out.println("    Notice: Memory is not instantly freed until the Garbage Collector runs.");

        // ---------------------------------------------------------------------
        // Step 4: Requesting Garbage Collection via System.gc()
        // ---------------------------------------------------------------------
        System.out.println("\n[4] Requesting Garbage Collection via System.gc()...");
        System.out.println("    NOTE: System.gc() is only a recommendation/request to the JVM,");
        System.out.println("          not an absolute guarantee that GC will run immediately.");

        long startTime = System.currentTimeMillis();
        System.gc(); // Request GC
        long duration = System.currentTimeMillis() - startTime;

        // Small pause to allow GC daemon threads to complete reclamation
        try {
            Thread.sleep(150);
        } catch (InterruptedException ignored) {}

        long afterGcUsed = getUsedMemoryBytes();
        long memoryReclaimed = afterAllocUsed - afterGcUsed;

        System.out.printf("    Used Memory After System.gc() : %,10.2f MB (%,d bytes)%n",
                bytesToMB(afterGcUsed), afterGcUsed);
        System.out.printf("    Total Memory Reclaimed        : %,10.2f MB (%,d bytes)%n",
                bytesToMB(memoryReclaimed), memoryReclaimed);
        System.out.printf("    GC invocation completed in    : %d ms%n", duration);

        // ---------------------------------------------------------------------
        // Step 5: Summary Report Table
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("                        MEMORY LIFECYCLE AUDIT                            ");
        System.out.println("==========================================================================");
        System.out.printf("  Stage                          | Used Memory (MB) | Raw Bytes%n");
        System.out.println("  -------------------------------+------------------+-----------------------");
        System.out.printf("  1. Baseline (Initial)          | %13.2f MB | %,d bytes%n", bytesToMB(initialUsed), initialUsed);
        System.out.printf("  2. Peak Allocation (150k objs) | %13.2f MB | %,d bytes%n", bytesToMB(afterAllocUsed), afterAllocUsed);
        System.out.printf("  3. Post-Dereferencing          | %13.2f MB | %,d bytes%n", bytesToMB(afterDerefUsed), afterDerefUsed);
        System.out.printf("  4. Post-GC Reclamation         | %13.2f MB | %,d bytes%n", bytesToMB(afterGcUsed), afterGcUsed);
        System.out.println("  -------------------------------+------------------+-----------------------");
        System.out.printf("  Effective Reclaim Efficiency   : %6.2f%%%n",
                (memoryReclaimed > 0 && memoryConsumed > 0) ? (memoryReclaimed * 100.0 / memoryConsumed) : 0.0);
        System.out.println("==========================================================================");
        System.out.println("Memory management and garbage collection demo completed successfully!");
        System.out.println("==========================================================================");
    }
}
