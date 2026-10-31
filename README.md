# Java Performance Optimization Techniques

A comparative performance benchmark evaluating inefficient vs. optimized algorithms and data structure usage in Java.

---

## Optimization Techniques Explained

1. **String Manipulation (`StringBuilder` vs. String `+`)**:
   - **Inefficient**: Strings in Java are immutable. Concatenating with `+` inside a loop repeatedly allocates new `String` instances and copies arrays on every step, producing $O(N^2)$ quadratic complexity and triggering heavy GC pressure.
   - **Optimized**: `StringBuilder` maintains a resizable character buffer with amortized $O(1)$ appends, yielding $O(N)$ linear complexity and eliminating intermediate allocations.

2. **Collection Membership Lookup (`HashSet` vs. `ArrayList`)**:
   - **Inefficient**: `ArrayList.contains()` performs a linear scan across $N$ elements ($O(N)$ time per lookup). For $M$ queries, the cost is $O(M \times N)$.
   - **Optimized**: `HashSet.contains()` computes a hash code and looks up buckets in average $O(1)$ constant time, reducing total cost to $O(M)$ (over 300x faster).

3. **Collection Pre-sizing**:
   - Allocating `new ArrayList<>(initialCapacity)` avoids repeated internal array copying (`System.arraycopy`) and resizing overhead as the collection grows.

---

## Benchmark Results (Measured via `System.nanoTime()`)

```text
==========================================================================
          JAVA PERFORMANCE BENCHMARK & OPTIMIZATION DEMO                  
==========================================================================

--- Benchmark 1: String Concatenation (20,000 Iterations) ---
  [Inefficient] String '+' (Immutable Copying)   :   24.266 ms (  24,266,458 ns)
  [Optimized]   StringBuilder (Buffer Append)    :    1.383 ms (   1,382,792 ns)
  >>> Result: Optimized version is 17.5x faster!

--- Benchmark 2: Collection Lookup (10,000 Queries in 50,000 Items) ---
  [Inefficient] ArrayList.contains() (O(N) Scan) :  268.056 ms ( 268,055,625 ns)
  [Optimized]   HashSet.contains()   (O(1) Hash) :    0.721 ms (     720,541 ns)
  >>> Result: Optimized version is 372.0x faster!

--- Benchmark 3: ArrayList Pre-Sizing (500,000 Elements) ---
  [Inefficient] ArrayList() (Dynamic Resizing)   :    4.835 ms (   4,835,291 ns)
  [Optimized]   ArrayList(N) (Pre-allocated)     :    5.232 ms (   5,232,417 ns)
  >>> Result: Optimized version is 0.9x faster!

==========================================================================
                   BENCHMARK TIMING SUMMARY TABLE                         
==========================================================================
  Optimization Domain            | Inefficient (ms) | Optimized (ms)  | Speedup   
  -------------------------------+-----------------+-----------------+-----------
  String Concatenation           |       24.266 ms |        1.383 ms |     17.5x
  Collection Membership          |      268.056 ms |        0.721 ms |    372.0x
  ArrayList Sizing               |        4.835 ms |        5.232 ms |      0.9x
==========================================================================
Performance optimization benchmark completed successfully!
==========================================================================
```
