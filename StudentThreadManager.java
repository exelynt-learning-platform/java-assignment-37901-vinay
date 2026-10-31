import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * =============================================================================
 * StudentThreadManager integrates all core Java Multithreading & Concurrency concepts:
 *
 * 1. Thread Creation & Execution:
 *    - Implemented using Runnable tasks executed via worker threads.
 *
 * 2. Thread Synchronization (Thread Safety):
 *    - Shared summary resource protected by synchronized methods and monitor locks
 *      to prevent race conditions and lost updates during parallel accumulation.
 *
 * 3. Inter-Thread Communication (wait() / notify()):
 *    - Implements a Producer-Consumer pattern using a synchronized buffer.
 *    - Producer pauses with wait() when buffer is full; Consumer pauses with wait()
 *      when empty. notify() signals waiting threads when state transitions occur.
 *
 * 4. Modern Concurrency Utilities (Executor Framework):
 *    - Uses java.util.concurrent.ExecutorService with a Fixed Thread Pool.
 *    - Manages thread lifecycle, reuses worker threads, and provides graceful
 *      shutdown handling with shutdown() and awaitTermination().
 * =============================================================================
 */

// Model representing an individual student record
class StudentRecord {
    int id;
    String name;
    double marks;
    char grade;

    public StudentRecord(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.grade = (marks >= 90.0) ? 'A' : (marks >= 75.0) ? 'B' : (marks >= 50.0) ? 'C' : 'F';
    }

    @Override
    public String toString() {
        return String.format("[ID: %-4d | Name: %-18s | Marks: %5.2f | Grade: %c]", id, name, marks, grade);
    }
}

/**
 * Thread-safe shared summary resource demonstrating SYNCHRONIZATION.
 */
class SharedGradeSummary {
    private int totalStudents = 0;
    private double totalMarksSum = 0.0;
    private int countGradeA = 0;
    private int countGradeB = 0;
    private int countGradeC = 0;
    private int countGradeF = 0;

    /**
     * Synchronized method:
     * Only one thread can update the shared summary at a time, preventing race conditions.
     */
    public synchronized void recordStudent(StudentRecord record) {
        totalStudents++;
        totalMarksSum += record.marks;

        switch (record.grade) {
            case 'A': countGradeA++; break;
            case 'B': countGradeB++; break;
            case 'C': countGradeC++; break;
            default:  countGradeF++; break;
        }

        System.out.printf("  [Thread: %-16s] Accumulated Record ID: %-4d (Current Count: %d)\n",
                Thread.currentThread().getName(), record.id, totalStudents);
    }

    public synchronized void displayFinalSummary() {
        double averageMarks = (totalStudents > 0) ? (totalMarksSum / totalStudents) : 0.0;
        System.out.println("\n=================================================================");
        System.out.println("            SYNCHRONIZED FINAL GRADE AUDIT REPORT                ");
        System.out.println("=================================================================");
        System.out.println("Total Students Processed : " + totalStudents);
        System.out.printf("Cumulative Total Marks   : %.2f\n", totalMarksSum);
        System.out.printf("Overall Class Average    : %.2f%%\n", averageMarks);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Grade Distribution:");
        System.out.println("  Grade A (>= 90%)       : " + countGradeA);
        System.out.println("  Grade B (>= 75%)       : " + countGradeB);
        System.out.println("  Grade C (>= 50%)       : " + countGradeC);
        System.out.println("  Grade F (< 50%)        : " + countGradeF);
        System.out.println("=================================================================");
    }
}

/**
 * Synchronized buffer demonstrating INTER-THREAD COMMUNICATION (wait / notify).
 */
class StudentRecordBuffer {
    private StudentRecord record;
    private boolean hasData = false;

    public synchronized void produce(StudentRecord item) {
        while (hasData) {
            try {
                wait(); // Wait until consumer consumes the record
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        this.record = item;
        this.hasData = true;
        System.out.println("[Producer] Pushed record to buffer: " + item.name);
        notify(); // Notify waiting consumer
    }

    public synchronized StudentRecord consume() {
        while (!hasData) {
            try {
                wait(); // Wait until producer produces a record
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        StudentRecord consumed = this.record;
        this.hasData = false;
        System.out.println("  [Consumer] Retrieved record from buffer: " + consumed.name);
        notify(); // Notify waiting producer
        return consumed;
    }
}

public class StudentThreadManager {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   STUDENT CONCURRENCY MANAGER (SYNCHRONIZATION & EXECUTORS)     ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // STEP 1: INTER-THREAD COMMUNICATION DEMO (Producer-Consumer with wait/notify)
        // ---------------------------------------------------------------------
        System.out.println("--- 1. Inter-Thread Communication Stage (wait() & notify()) ---");
        StudentRecordBuffer buffer = new StudentRecordBuffer();

        Thread producerThread = new Thread(() -> {
            StudentRecord[] batch = {
                new StudentRecord(101, "Vinay Madarkhandi", 94.5),
                new StudentRecord(102, "Alice Johnson", 82.0),
                new StudentRecord(103, "Bob Smith", 67.5)
            };
            for (StudentRecord r : batch) {
                buffer.produce(r);
                try { Thread.sleep(150); } catch (InterruptedException ignored) {}
            }
        }, "Buffer-Producer");

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                StudentRecord r = buffer.consume();
                try { Thread.sleep(250); } catch (InterruptedException ignored) {}
            }
        }, "Buffer-Consumer");

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.err.println("Communication interrupted.");
        }
        System.out.println(">> Inter-thread communication stage completed successfully!\n");

        // ---------------------------------------------------------------------
        // STEP 2: CONCURRENT BATCH PROCESSING WITH EXECUTORSERVICE & SYNCHRONIZATION
        // ---------------------------------------------------------------------
        System.out.println("--- 2. Concurrent Batch Processing via ExecutorService (Fixed Pool) ---");

        SharedGradeSummary sharedSummary = new SharedGradeSummary();

        // Sample data batches
        StudentRecord[] batch1 = {
            new StudentRecord(201, "David Miller", 91.0),
            new StudentRecord(202, "Emma Watson", 78.5),
            new StudentRecord(203, "Frank Castle", 48.0)
        };

        StudentRecord[] batch2 = {
            new StudentRecord(204, "Grace Hopper", 99.5),
            new StudentRecord(205, "Henry Ford", 84.0),
            new StudentRecord(206, "Ivy League", 73.0)
        };

        StudentRecord[] batch3 = {
            new StudentRecord(207, "Jack Ryan", 88.0),
            new StudentRecord(208, "Karen Smith", 55.0),
            new StudentRecord(209, "Leo Tolstoy", 42.0)
        };

        /*
         * EXECUTOR FRAMEWORK:
         * Creates a fixed thread pool with 3 worker threads.
         * Decouples task submission from thread management and reuse.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable taskBatch1 = () -> {
            for (StudentRecord r : batch1) {
                sharedSummary.recordStudent(r);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        };

        Runnable taskBatch2 = () -> {
            for (StudentRecord r : batch2) {
                sharedSummary.recordStudent(r);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        };

        Runnable taskBatch3 = () -> {
            for (StudentRecord r : batch3) {
                sharedSummary.recordStudent(r);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        };

        // Submitting batch tasks to the thread pool
        System.out.println("Submitting Batch 1, Batch 2, and Batch 3 to ExecutorService pool...");
        executorService.submit(taskBatch1);
        executorService.submit(taskBatch2);
        executorService.submit(taskBatch3);

        /*
         * PROPER SHUTDOWN OF EXECUTOR SERVICE:
         * 1. shutdown(): Disallows new incoming tasks, executes remaining queued tasks.
         * 2. awaitTermination(): Waits up to the specified timeout for tasks to complete.
         */
        executorService.shutdown();
        try {
            System.out.println("Waiting for all pool workers to complete (awaitTermination)...");
            boolean finished = executorService.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("All thread pool tasks finished cleanly? " + finished);
        } catch (InterruptedException e) {
            System.err.println("ExecutorService interrupted during shutdown.");
        }

        // ---------------------------------------------------------------------
        // STEP 3: DISPLAY FINAL SYNCHRONIZED AUDIT SUMMARY
        // ---------------------------------------------------------------------
        sharedSummary.displayFinalSummary();
        System.out.println("Student Concurrency Manager program completed successfully!");
    }
}
