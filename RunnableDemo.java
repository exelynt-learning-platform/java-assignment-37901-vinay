/**
 * =============================================================================
 * RunnableDemo demonstrates creating threads using java.lang.Runnable:
 *
 * =============================================================================
 * BENEFITS OF IMPLEMENTING RUNNABLE OVER EXTENDING THREAD:
 * =============================================================================
 * 1. OVERCOMES SINGLE INHERITANCE LIMITATION:
 *    - In Java, a class can only extend ONE superclass.
 *    - If a class extends Thread, it cannot inherit from any other class.
 *    - Implementing Runnable leaves the class free to extend any business superclass
 *      (e.g., class StudentService extends BaseService implements Runnable).
 *
 * 2. SEPARATION OF CONCERNS (JOB vs WORKER):
 *    - Runnable defines the TASK (what needs to be done).
 *    - Thread provides the WORKER / EXECUTION MECHANISM (how and when to run it).
 *    - Decouples task logic from thread lifecycle management.
 *
 * 3. REUSABILITY & COMPATIBILITY WITH THREAD POOLS:
 *    - The same Runnable instance can be executed by multiple Threads.
 *    - Runnable is the foundation of the Java Concurrency Utilities
 *      (ExecutorService, ThreadPoolExecutor, ScheduledExecutorService).
 *
 * 4. FUNCTIONAL INTERFACE (LAMBDA COMPATIBLE):
 *    - Since Runnable has only one abstract method (run()), it is a @FunctionalInterface
 *      and can be written cleanly as a lambda expression: () -> { ... }.
 * =============================================================================
 */

// Task class implementing java.lang.Runnable
class StudentTaskRunner implements Runnable {

    private final String taskDescription;
    private final int steps;

    public StudentTaskRunner(String taskDescription, int steps) {
        this.taskDescription = taskDescription;
        this.steps = steps;
    }

    /**
     * Implementation of the abstract run() method from the Runnable interface.
     */
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(">> [" + threadName + "] Started task: " + taskDescription);

        for (int i = 1; i <= steps; i++) {
            System.out.printf("   [%s] Step %d/%d for '%s'\n", threadName, i, steps, taskDescription);
            try {
                Thread.sleep(300); // Simulate processing time
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupted.");
                break;
            }
        }

        System.out.println("<< [" + threadName + "] Completed task: " + taskDescription);
    }
}

public class RunnableDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("       CREATING THREADS USING RUNNABLE INTERFACE DEMO            ");
        System.out.println("=================================================================\n");

        // 1. Create Runnable task instances (defining the tasks)
        Runnable gradeTask = new StudentTaskRunner("Grade Calculation", 4);
        Runnable attendanceTask = new StudentTaskRunner("Attendance Compilation", 4);

        // 2. Pass Runnable objects to Thread constructors (assigning tasks to workers)
        Thread thread1 = new Thread(gradeTask, "Worker-Thread-1");
        Thread thread2 = new Thread(attendanceTask, "Worker-Thread-2");

        // 3. Demonstrating Lambda Expression with Runnable (Modern Java)
        Runnable notificationTask = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(">> [" + name + "] Sending student email notifications...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
            System.out.println("<< [" + name + "] Notifications sent!");
        };
        Thread thread3 = new Thread(notificationTask, "Worker-Thread-3");

        // 4. Start all threads concurrently
        System.out.println("Starting threads via thread.start()...\n");
        thread1.start();
        thread2.start();
        thread3.start();

        // 5. Wait for all threads to conclude
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted.");
        }

        System.out.println("\n=================================================================");
        System.out.println("All Runnable tasks completed their execution successfully!       ");
        System.out.println("=================================================================");
    }
}
