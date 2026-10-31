import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * DownloadSimulator demonstrates real-world concurrent execution using Multithreading:
 *
 * HOW MULTITHREADING APPLIES IN REAL-WORLD DOWNLOAD MANAGERS:
 * 1. Concurrent Independent I/O Operations:
 *    - In a single-threaded program, downloading 4 files sequentially would require
 *      waiting for File 1 to finish completely before File 2 could even start.
 *    - With multithreading, each file download is allocated its own independent Thread.
 *    - The Operating System and CPU schedule execution slices across available cores,
 *      allowing all files to download in parallel, dramatically reducing total latency.
 *
 * 2. Thread Coordination using join():
 *    - The main application thread initiates all worker threads and calls join() on each.
 *    - This guarantees that the main thread does NOT terminate prematurely while downloads
 *      are still in progress.
 * =============================================================================
 */

// Runnable task representing an individual file download
class DownloadTask implements Runnable {

    private final String fileName;
    private final int fileSizeMB;
    private final int downloadDurationMs;

    public DownloadTask(String fileName, int fileSizeMB, int downloadDurationMs) {
        this.fileName = fileName;
        this.fileSizeMB = fileSizeMB;
        this.downloadDurationMs = downloadDurationMs;
    }

    /**
     * Executes the download simulation logic on a separate thread.
     */
    @Override
    public void run() {
        String workerName = Thread.currentThread().getName();

        // Download Start Notice
        System.out.printf("[START]  %-20s | File: %-32s | Size: %4d MB\n",
                workerName, fileName, fileSizeMB);

        // Simulate multi-stage download progress
        int intervals = 4;
        int sleepPerInterval = downloadDurationMs / intervals;

        for (int i = 1; i <= intervals; i++) {
            try {
                // Thread.sleep() simulates network latency and data transfer time
                Thread.sleep(sleepPerInterval);
            } catch (InterruptedException e) {
                System.err.println("Download interrupted for: " + fileName);
                Thread.currentThread().interrupt();
                return;
            }

            int percent = (i * 100) / intervals;
            System.out.printf("  >> [%s] Progress for '%s': %3d%% completed\n",
                    workerName, fileName, percent);
        }

        // Download Completion Notice
        System.out.printf("[FINISH] %-20s | File: %-32s | Download Successful!\n",
                workerName, fileName);
    }
}

public class DownloadSimulator {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("     MULTI-THREADED FILE DOWNLOAD SIMULATOR (CONCURRENCY DEMO)   ");
        System.out.println("=================================================================\n");

        long startTime = System.currentTimeMillis();

        // 1. Define files to be downloaded
        DownloadTask[] tasks = {
            new DownloadTask("Java_JDK_21_Setup.tar.gz", 195, 1200),
            new DownloadTask("Spring_Framework_Doc.pdf", 45, 600),
            new DownloadTask("IntelliJ_IDEA_Ultimate.dmg", 850, 1500),
            new DownloadTask("MachineLearning_Dataset.csv", 320, 900)
        };

        // 2. Create and start threads concurrently
        List<Thread> downloadThreads = new ArrayList<>();

        System.out.println("Launching concurrent download threads...\n");
        for (int i = 0; i < tasks.length; i++) {
            Thread thread = new Thread(tasks[i], "Downloader-Thread-" + (i + 1));
            downloadThreads.add(thread);
            thread.start(); // Initiates parallel download
        }

        // 3. Thread Coordination: Wait for all download threads to conclude
        System.out.println("\nAll downloads launched! Main thread waiting for completion via join()...\n");
        for (Thread thread : downloadThreads) {
            try {
                // join() pauses main thread execution until the specific thread terminates
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Main thread interrupted while waiting for downloads.");
            }
        }

        long endTime = System.currentTimeMillis();
        double elapsedSeconds = (endTime - startTime) / 1000.0;

        // 4. Final summary
        System.out.println("\n=================================================================");
        System.out.println("                   DOWNLOAD SESSION REPORT                       ");
        System.out.println("=================================================================");
        System.out.println("Total Files Downloaded : " + tasks.length + " files");
        System.out.printf("Total Real Time Elapsed: %.2f seconds (Concurrent Execution)\n", elapsedSeconds);
        System.out.println("All threads synchronized and safely terminated before program exit.");
        System.out.println("=================================================================");
    }
}
