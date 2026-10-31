/**
 * =============================================================================
 * ThreadLifecycleDemo demonstrates:
 * 1. Thread Lifecycle States (java.lang.Thread.State enum):
 *    - NEW           : Thread object created, but start() has not been called.
 *    - RUNNABLE      : Thread is executing in the JVM (running or ready to run on CPU).
 *    - TIMED_WAITING : Thread is sleeping or waiting with a specified timeout (e.g. sleep(ms)).
 *    - WAITING       : Thread is waiting indefinitely for another thread (e.g. join(), wait()).
 *    - TERMINATED    : Thread has completed execution of its run() method.
 *
 * 2. Thread Priorities (1 to 10):
 *    - Thread.MIN_PRIORITY  = 1
 *    - Thread.NORM_PRIORITY = 5 (default)
 *    - Thread.MAX_PRIORITY  = 10
 *    - IMPORTANT NOTE: Thread priorities in Java are merely HINTS (advisory recommendations)
 *      to the underlying Operating System's native thread scheduler. The OS is not
 *      obligated to honor them strictly, so execution order is NEVER guaranteed!
 * =============================================================================
 */

// Worker task to demonstrate thread state transitions
class LifecycleWorker implements Runnable {

    @Override
    public void run() {
        System.out.println("  [Inside run()] Thread '" + Thread.currentThread().getName() +
                "' state while running: " + Thread.currentThread().getState());

        try {
            // Transitioning to TIMED_WAITING state
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }

        System.out.println("  [Exiting run()] Thread '" + Thread.currentThread().getName() +
                "' finished its work.");
    }
}

public class ThreadLifecycleDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("     THREAD LIFECYCLE STATES & THREAD PRIORITIES DEMO            ");
        System.out.println("=================================================================\n");

        // =====================================================================
        // PART 1: DEMONSTRATING THREAD LIFECYCLE STATES WITH getState()
        // =====================================================================
        System.out.println("--- 1. Tracking Thread Lifecycle States (NEW -> RUNNABLE -> TIMED_WAITING -> TERMINATED) ---");

        Thread worker = new Thread(new LifecycleWorker(), "Lifecycle-Thread");

        // STATE 1: NEW (Thread created, not yet started)
        System.out.println("1. State immediately after creation: " + worker.getState() +
                " (NEW: start() has not been called yet)");

        // Starting the worker thread
        worker.start();

        // STATE 2: RUNNABLE (Thread has started and is eligible to run on CPU)
        System.out.println("2. State immediately after start() : " + worker.getState() +
                " (RUNNABLE: Active or queued in CPU run queue)");

        // Brief pause in main thread to allow the worker thread to enter its sleep() block
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}

        // STATE 3: TIMED_WAITING (Thread is currently inside Thread.sleep(800))
        System.out.println("3. State while thread is sleeping  : " + worker.getState() +
                " (TIMED_WAITING: Paused in sleep(800))");

        // Using join() to wait for worker thread termination
        try {
            worker.join(); // Main thread waits until worker terminates
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting.");
        }

        // STATE 4: TERMINATED (Thread run() method has concluded)
        System.out.println("4. State after completion & join() : " + worker.getState() +
                " (TERMINATED: run() method completed)\n");

        // =====================================================================
        // PART 2: DEMONSTRATING THREAD PRIORITIES
        // =====================================================================
        System.out.println("--- 2. Setting & Inspecting Thread Priorities ---");

        Runnable priorityTask = () -> {
            Thread current = Thread.currentThread();
            System.out.printf("  Executing: %-18s | Priority: %-2d | State: %s\n",
                    current.getName(), current.getPriority(), current.getState());
        };

        Thread lowPriorityThread = new Thread(priorityTask, "Low-Priority-Thread");
        Thread normalPriorityThread = new Thread(priorityTask, "Normal-Priority-Thread");
        Thread highPriorityThread = new Thread(priorityTask, "High-Priority-Thread");

        // Setting thread priorities
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);   // 1
        normalPriorityThread.setPriority(Thread.NORM_PRIORITY); // 5
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);   // 10

        System.out.println("Configured Priorities:");
        System.out.println("  " + lowPriorityThread.getName() + " -> Priority: " + lowPriorityThread.getPriority() + " (MIN_PRIORITY)");
        System.out.println("  " + normalPriorityThread.getName() + " -> Priority: " + normalPriorityThread.getPriority() + " (NORM_PRIORITY)");
        System.out.println("  " + highPriorityThread.getName() + " -> Priority: " + highPriorityThread.getPriority() + " (MAX_PRIORITY)");

        /*
         * EXPLANATION OF THREAD PRIORITY BEHAVIOR:
         * In Java, thread priorities serve solely as hints/suggestions to the host
         * OS thread scheduler. Different operating systems (Windows, Linux, macOS)
         * map Java priorities onto native scheduler priorities differently.
         * High priority threads are given preference for CPU time, but execution order
         * is NOT guaranteed and should NEVER be relied upon for deterministic logic!
         */
        System.out.println("\nStarting threads with different priorities (Execution order depends on OS scheduler):");
        lowPriorityThread.start();
        normalPriorityThread.start();
        highPriorityThread.start();

        try {
            lowPriorityThread.join();
            normalPriorityThread.join();
            highPriorityThread.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted waiting for priority threads.");
        }

        System.out.println("\n=================================================================");
        System.out.println("Thread lifecycle and priority demonstration concluded!");
        System.out.println("=================================================================");
    }
}
