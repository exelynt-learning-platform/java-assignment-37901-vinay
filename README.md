# Java Memory Management and Garbage Collection

A Java program demonstrating heap memory monitoring, object lifecycle states, dereferencing, and Garbage Collection (GC) behavior.

---

## Key Memory Management Concepts

1. **Heap Memory Architecture**:
   - **Young Generation (Eden & Survivor S0/S1)**: Where short-lived objects are rapidly allocated.
   - **Old Generation (Tenured)**: Houses long-lived objects that survive multiple GC minor cycles.
   - **Metaspace**: Stores class metadata, bytecode representations, and reflection information.

2. **Garbage Collection Eligibility**:
   - An object becomes eligible for GC as soon as it becomes unreachable from any GC Root (active thread stacks, static references).
   - Clearing references (`studentCache.clear(); studentCache = null;`) leaves objects orphaned in the heap, ready for reclamation.

3. **Nature of `System.gc()`**:
   - Calling `System.gc()` or `Runtime.getRuntime().gc()` sends an advisory request/hint to the JVM.
   - It is **not a guaranteed command**; the JVM decides whether and when to trigger collection according to heap thresholds and the configured garbage collector (e.g., G1, ZGC, Parallel GC).

---

## Console Output (Sample Program Run)

```text
==========================================================================
       JAVA MEMORY MANAGEMENT & GARBAGE COLLECTION MONITOR                
==========================================================================

[1] Baseline Memory State (Before Allocation):
    Max Available Heap Memory :   4,096.00 MB
    Current Total Allocated   :     260.00 MB
    Initial Used Memory       :       2.79 MB (2,921,616 bytes)

[2] Allocating 150,000 StudentPayload Objects in Heap...
    Total Heap after Allocation:     780.00 MB
    Used Memory After Creation :     173.57 MB (181,996,128 bytes)
    Memory Increase by Objects :     170.78 MB

[3] Releasing References (Dereferencing objects to make them GC eligible)...
    Used Memory After Dereference (Before GC):     173.57 MB
    Notice: Memory is not instantly freed until the Garbage Collector runs.

[4] Requesting Garbage Collection via System.gc()...
    NOTE: System.gc() is only a recommendation/request to the JVM,
          not an absolute guarantee that GC will run immediately.
    Used Memory After System.gc() :       2.41 MB (2,530,072 bytes)
    Total Memory Reclaimed        :     171.15 MB (179,466,056 bytes)
    GC invocation completed in    : 3 ms

==========================================================================
                        MEMORY LIFECYCLE AUDIT                            
==========================================================================
  Stage                          | Used Memory (MB) | Raw Bytes
  -------------------------------+------------------+-----------------------
  1. Baseline (Initial)          |          2.79 MB | 2,921,616 bytes
  2. Peak Allocation (150k objs) |        173.57 MB | 181,996,128 bytes
  3. Post-Dereferencing          |        173.57 MB | 181,996,128 bytes
  4. Post-GC Reclamation         |          2.41 MB | 2,530,072 bytes
  -------------------------------+------------------+-----------------------
  Effective Reclaim Efficiency   : 100.22%
==========================================================================
Memory management and garbage collection demo completed successfully!
==========================================================================
```
