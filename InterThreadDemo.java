/**
 * =============================================================================
 * InterThreadDemo demonstrates Inter-Thread Communication using wait() and notify():
 *
 * =============================================================================
 * CORE CONCEPTS OF INTER-THREAD COMMUNICATION:
 * =============================================================================
 * 1. WHAT ARE wait(), notify(), and notifyAll()?
 *    - Methods belonging to java.lang.Object, NOT java.lang.Thread.
 *    - Designed for coordinating threads that share a common monitor lock.
 *    - MUST be called from within a synchronized context; calling outside triggers
 *      IllegalMonitorStateException.
 *
 * 2. HOW wait() WORKS:
 *    - Causes the current thread to temporarily RELEASE the monitor lock on the object
 *      and enter the WAITING state.
 *    - It remains waiting until another thread calls notify() or notifyAll() on the
 *      SAME monitor object.
 *
 * 3. HOW notify() WORKS:
 *    - Wakes up a single thread that is currently waiting on this object's monitor.
 *    - The awakened thread cannot resume execution immediately; it must re-acquire
 *      the monitor lock once the notifying thread exits its synchronized block.
 *
 * 4. WHY USE A WHILE LOOP WITH wait()?
 *    - To guard against "Spurious Wakeups" (a thread waking up without being notified)
 *      and race conditions where another thread changed the condition before this thread
 *      re-acquired the lock.
 * =============================================================================
 */

// Shared buffer demonstrating the classic Producer-Consumer pattern
class SharedBuffer {
    private int dataItem;
    private boolean hasData = false; // Flag indicating if item is available for consumption

    /**
     * Producer method: Places a data item into the buffer.
     * If the buffer already contains unconsumed data, the producer WAITS.
     */
    public synchronized void produce(int value) {
        String threadName = Thread.currentThread().getName();

        // While buffer is full, release lock and wait for consumer to empty it
        while (hasData) {
            try {
                System.out.println("  [" + threadName + "] Buffer is FULL. Waiting for consumer...");
                wait(); // Releases monitor lock on 'this' and sleeps
            } catch (InterruptedException e) {
                System.err.println(threadName + " was interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Place new data into buffer
        this.dataItem = value;
        this.hasData = true;
        System.out.printf("[%s] PRODUCED item -> [%d]\n", threadName, value);

        // Notify waiting consumer thread that new data is ready
        notify(); // Wakes up a waiting consumer
    }

    /**
     * Consumer method: Retrieves and consumes data from the buffer.
     * If the buffer is empty, the consumer WAITS for the producer to produce data.
     */
    public synchronized int consume() {
        String threadName = Thread.currentThread().getName();

        // While buffer is empty, release lock and wait for producer to fill it
        while (!hasData) {
            try {
                System.out.println("  [" + threadName + "] Buffer is EMPTY. Waiting for producer...");
                wait(); // Releases monitor lock on 'this' and sleeps
            } catch (InterruptedException e) {
                System.err.println(threadName + " was interrupted.");
                Thread.currentThread().interrupt();
                return -1;
            }
        }

        // Retrieve and consume item
        int consumedValue = this.dataItem;
        this.hasData = false;
        System.out.printf("  [%s] CONSUMED item <- [%d]\n", threadName, consumedValue);

        // Notify waiting producer thread that buffer space is now available
        notify(); // Wakes up waiting producer

        return consumedValue;
    }
}

public class InterThreadDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   INTER-THREAD COMMUNICATION: PRODUCER-CONSUMER DEMO (wait/notify)");
        System.out.println("=================================================================\n");

        // Single shared buffer instance
        SharedBuffer buffer = new SharedBuffer();
        final int totalItems = 5;

        // 1. Producer Thread
        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= totalItems; i++) {
                buffer.produce(i * 10);
                try {
                    Thread.sleep(250); // Simulate production time
                } catch (InterruptedException ignored) {}
            }
            System.out.println(">> Producer completed all 5 production cycles.");
        }, "Producer-Thread");

        // 2. Consumer Thread
        Thread consumerThread = new Thread(() -> {
            for (int i = 1; i <= totalItems; i++) {
                buffer.consume();
                try {
                    Thread.sleep(400); // Simulate consumption processing time
                } catch (InterruptedException ignored) {}
            }
            System.out.println(">> Consumer finished consuming all 5 items.");
        }, "Consumer-Thread");

        // 3. Start both communicating threads
        System.out.println("Starting Producer and Consumer threads...\n");
        producerThread.start();
        consumerThread.start();

        // 4. Wait for both threads to finish cleanly
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting.");
        }

        System.out.println("\n=================================================================");
        System.out.println("Inter-thread communication finished with zero deadlocks!         ");
        System.out.println("=================================================================");
    }
}
