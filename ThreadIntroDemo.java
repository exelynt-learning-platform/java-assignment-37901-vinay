/**
 * =============================================================================
 * ThreadIntroDemo demonstrates the fundamentals of multithreading in Java:
 *
 * 1. WHAT IS A THREAD?
 *    - A thread is a lightweight sub-process; the smallest independent unit
 *      of code execution within a process.
 *    - Java provides built-in multithreading support through java.lang.Thread
 *      and java.lang.Runnable.
 *
 * 2. THE MAIN THREAD:
 *    - Every standalone Java application begins with at least one thread, known
 *      as the "main thread", created automatically by the JVM to execute the main() method.
 *
 * 3. CHILD THREAD CREATION & STARTING:
 *    - A child thread can be created by subclassing Thread (or implementing Runnable).
 *    - Overriding run() defines the task executed by the thread.
 *    - Calling thread.start() registers the thread with the OS thread scheduler
 *      and allocates a new call stack. (Calling run() directly runs on the current thread!).
 *
 * 4. Thread.currentThread():
 *    - Static method that returns a reference to the currently executing thread object.
 *    - getName() returns the name of the thread (e.g., "main", "ChildThread-1").
 * =============================================================================
 */

// Custom thread class extending java.lang.Thread
class ChildWorkerThread extends Thread {

    public ChildWorkerThread(String name) {
        super(name); // Sets custom thread name
    }

    /**
     * The run() method acts as the entry point for the new thread.
     * Code inside run() executes concurrently alongside the main thread.
     */
    @Override
    public void run() {
        // Retrieve and display thread identity
        Thread current = Thread.currentThread();
        System.out.println("[Child Thread Initialized] Name: " + current.getName() + " | State: " + current.getState());

        for (int i = 1; i <= 5; i++) {
            System.out.printf("  >> [%s] Processing task iteration #%d\n", current.getName(), i);
            try {
                // Thread.sleep() pauses execution temporarily to allow other threads to execute
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(current.getName() + " was interrupted.");
            }
        }

        System.out.println("[Child Thread Finished] Name: " + current.getName());
    }
}

public class ThreadIntroDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("       INTRODUCTION TO THREADS IN JAVA: THREAD BASICS DEMO       ");
        System.out.println("=================================================================\n");

        // 1. Inspecting the Main Thread
        Thread mainThread = Thread.currentThread();
        System.out.println("[Main Thread Info] Name: " + mainThread.getName() +
                " | Priority: " + mainThread.getPriority() +
                " | State: " + mainThread.getState());

        // 2. Creating a Child Thread
        ChildWorkerThread childThread = new ChildWorkerThread("Worker-Thread-1");

        /*
         * STARTING THE THREAD:
         * Calling start() triggers the JVM to spawn a new asynchronous execution
         * stack and invoke run() in parallel.
         */
        System.out.println("\nSpawning child thread via childThread.start()...\n");
        childThread.start();

        // 3. Execution concurrently within the Main Thread
        for (int i = 1; i <= 5; i++) {
            System.out.printf("  ** [%s] Main thread task step #%d\n", mainThread.getName(), i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
        }

        // Wait for child thread to finish using join()
        try {
            childThread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting for child thread.");
        }

        System.out.println("\n=================================================================");
        System.out.println("Both main and child threads completed their concurrent execution!");
        System.out.println("=================================================================");
    }
}
