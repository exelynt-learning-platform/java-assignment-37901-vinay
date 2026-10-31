/**
 * =============================================================================
 * ThreadClassDemo demonstrates creating and managing threads by extending
 * the java.lang.Thread class:
 *
 * 1. EXTENDING THE THREAD CLASS:
 *    - In Java, one of the two primary ways to create a thread is by creating a
 *      class that extends java.lang.Thread and overriding its run() method.
 *    - The run() method contains the executable business logic for that thread.
 *
 * 2. STARTING MULTIPLE THREADS:
 *    - To execute the code in run() in a separate call stack, call start().
 *    - The JVM's Thread Scheduler manages the execution of active threads,
 *      interleaving their execution slices concurrently on CPU cores.
 *
 * 3. ADVANTAGES & LIMITATIONS:
 *    - Advantage: Direct access to Thread methods (e.g., getName(), getPriority(), sleep(), join()).
 *    - Limitation: Because Java only supports single class inheritance, extending
 *      Thread prevents the class from extending any other class.
 * =============================================================================
 */

// Worker thread class extending java.lang.Thread
class NumberPrinterThread extends Thread {

    private final int totalIterations;
    private final int sleepDurationMs;

    public NumberPrinterThread(String threadName, int totalIterations, int sleepDurationMs) {
        super(threadName); // Assigns name to Thread superclass
        this.totalIterations = totalIterations;
        this.sleepDurationMs = sleepDurationMs;
    }

    /**
     * Overridden run() method:
     * Contains the task logic to be executed concurrently.
     */
    @Override
    public void run() {
        System.out.println(">> [" + getName() + "] Execution STARTED.");

        for (int i = 1; i <= totalIterations; i++) {
            System.out.printf("   [%s] Count: %d / %d\n", getName(), i, totalIterations);

            try {
                // Thread.sleep() introduces delay, demonstrating concurrent interleaving
                Thread.sleep(sleepDurationMs);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
                break;
            }
        }

        System.out.println("<< [" + getName() + "] Execution COMPLETED.");
    }
}

public class ThreadClassDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   CREATING THREADS USING THREAD CLASS: MULTITHREADING DEMO      ");
        System.out.println("=================================================================\n");

        System.out.println("Main thread initializing child worker threads...\n");

        // 1. Instantiating two independent Thread objects
        NumberPrinterThread threadAlpha = new NumberPrinterThread("Thread-Alpha", 5, 250);
        NumberPrinterThread threadBeta = new NumberPrinterThread("Thread-Beta", 5, 250);

        // 2. Starting both threads concurrently
        // start() creates a new thread of execution and invokes run() asynchronously
        System.out.println("Calling start() on Thread-Alpha and Thread-Beta:");
        threadAlpha.start();
        threadBeta.start();

        System.out.println("Both threads started. Main thread waiting for completion via join()...\n");

        // 3. Main thread waits for both child threads to finish using join()
        try {
            threadAlpha.join(); // Blocks main until threadAlpha terminates
            threadBeta.join();  // Blocks main until threadBeta terminates
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for worker threads.");
        }

        System.out.println("\n=================================================================");
        System.out.println("Concurrent execution of both threads finished successfully!");
        System.out.println("=================================================================");
    }
}
