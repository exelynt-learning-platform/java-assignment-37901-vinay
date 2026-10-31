# Multithreading and Concurrency - Student Thread Manager

A concurrent Java application demonstrating core multithreading concepts, thread synchronization, inter-thread communication (`wait()` and `notify()`), and the modern Java `ExecutorService` framework.

---

## Concurrency Concepts Demonstrated

1. **Inter-Thread Communication (`wait()` and `notify()`)**:
   - Implemented via `StudentRecordBuffer` acting as a synchronized bounded buffer.
   - A producer thread adds student records to the shared queue and notifies waiting consumer threads using `notifyAll()`.
   - A consumer thread retrieves records and waits using `wait()` while the buffer is empty.

2. **Synchronization and Thread Safety (`synchronized`)**:
   - `SharedGradeSummary` aggregates totals, student counts, and grade breakdowns across multiple concurrent threads.
   - Synchronized methods ensure that access to shared mutable state is serialized, preventing race conditions and lost updates.

3. **Executor Framework (`ExecutorService`)**:
   - Thread pool managed using `Executors.newFixedThreadPool(3)`.
   - Student batches are submitted as independent tasks (`Runnable`) to the pool.
   - Proper lifecycle management using `shutdown()` and `awaitTermination()` to ensure all tasks finish gracefully before reporting results.

---

## Sample Program Run

```text
=================================================================
   STUDENT CONCURRENCY MANAGER (SYNCHRONIZATION & EXECUTORS)     
=================================================================

--- 1. Inter-Thread Communication Stage (wait() & notify()) ---
[Producer] Pushed record to buffer: Vinay Madarkhandi
  [Consumer] Retrieved record from buffer: Vinay Madarkhandi
[Producer] Pushed record to buffer: Alice Johnson
  [Consumer] Retrieved record from buffer: Alice Johnson
[Producer] Pushed record to buffer: Bob Smith
  [Consumer] Retrieved record from buffer: Bob Smith
>> Inter-thread communication stage completed successfully!

--- 2. Concurrent Batch Processing via ExecutorService (Fixed Pool) ---
Submitting Batch 1, Batch 2, and Batch 3 to ExecutorService pool...
Waiting for all pool workers to complete (awaitTermination)...
  [Thread: pool-1-thread-3 ] Accumulated Record ID: 207  (Current Count: 1)
  [Thread: pool-1-thread-2 ] Accumulated Record ID: 204  (Current Count: 2)
  [Thread: pool-1-thread-1 ] Accumulated Record ID: 201  (Current Count: 3)
  [Thread: pool-1-thread-3 ] Accumulated Record ID: 208  (Current Count: 4)
  [Thread: pool-1-thread-2 ] Accumulated Record ID: 205  (Current Count: 5)
  [Thread: pool-1-thread-1 ] Accumulated Record ID: 202  (Current Count: 6)
  [Thread: pool-1-thread-1 ] Accumulated Record ID: 203  (Current Count: 7)
  [Thread: pool-1-thread-3 ] Accumulated Record ID: 209  (Current Count: 8)
  [Thread: pool-1-thread-2 ] Accumulated Record ID: 206  (Current Count: 9)
All thread pool tasks finished cleanly? true

=================================================================
            SYNCHRONIZED FINAL GRADE AUDIT REPORT                
=================================================================
Total Students Processed : 9
Cumulative Total Marks   : 659.00
Overall Class Average    : 73.22%
-----------------------------------------------------------------
Grade Distribution:
  Grade A (>= 90%)       : 2
  Grade B (>= 75%)       : 3
  Grade C (>= 50%)       : 2
  Grade F (< 50%)        : 2
=================================================================
Student Concurrency Manager program completed successfully!
```
