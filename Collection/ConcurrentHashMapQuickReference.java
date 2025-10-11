import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CONCURRENTHASHMAP QUICK REFERENCE AND COMPARISON
 * ================================================
 * 
 * Quick reference guide comparing HashMap and ConcurrentHashMap
 * with practical examples and use cases.
 */

public class ConcurrentHashMapQuickReference {
    
    public static void main(String[] args) {
        System.out.println("=== CONCURRENTHASHMAP QUICK REFERENCE ===\n");
        
        quickComparison();
        whenToUse();
        codeExamples();
        performanceTips();
    }
    
    /**
     * Quick comparison between HashMap and ConcurrentHashMap
     */
    public static void quickComparison() {
        System.out.println("1. QUICK COMPARISON");
        System.out.println("==================");
        
        System.out.println("""
            ┌─────────────────┬─────────────────┬─────────────────────────┐
            │   Feature       │    HashMap      │   ConcurrentHashMap     │
            ├─────────────────┼─────────────────┼─────────────────────────┤
            │ Thread Safety   │      ❌ No      │      ✅ Yes            │
            │ Performance     │   ✅ Fastest    │   ✅ Fast (concurrent) │
            │ Null Keys       │      ✅ Yes     │      ❌ No             │
            │ Null Values     │      ✅ Yes     │      ❌ No             │
            │ Synchronization │ External needed│    Internal (CAS/Locks)  │
            │ Read Operations │   ✅ O(1)       │   ✅ O(1) Non-blocking │
            │ Write Operations│   ✅ O(1)       │   ✅ O(1) Lock-free/CAS│
            │ Memory Usage    │   ✅ Lower      │   ✅ Similar           │
            │ Iteration       │   ❌ Not safe   │   ✅ Safe (snapshot)   │
            └─────────────────┴───────────────── ┴─────────────────────────┘
            """);
        
        System.out.println();
    }
    
    /**
     * When to use each implementation
     */
    public static void whenToUse() {
        System.out.println("2. WHEN TO USE");
        System.out.println("=============");
        
        System.out.println("""
            USE HASHMAP WHEN:
            ✅ Single-threaded applications
            ✅ Performance is critical and no concurrency
            ✅ Need null keys or values
            ✅ Simple key-value storage
            ✅ Memory usage is a concern
            
            USE CONCURRENTHASHMAP WHEN:
            ✅ Multi-threaded applications
            ✅ High-concurrency scenarios
            ✅ Cache implementations
            ✅ Shared data structures
            ✅ Producer-consumer patterns
            ✅ Need atomic operations
            ✅ Thread-safe iteration required
            
            COMMON USE CASES:
            • Web application session storage
            • Cache implementations
            • Configuration management
            • Counters and statistics
            • Shared lookup tables
            """);
        
        System.out.println();
    }
    
    /**
     * Practical code examples
     */
    public static void codeExamples() {
        System.out.println("3. CODE EXAMPLES");
        System.out.println("================");
        
        // Example 1: Basic usage
        System.out.println("Example 1: Basic Usage");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        System.out.println("Basic map: " + map);
        
        // Example 2: Atomic operations
        System.out.println("\nExample 2: Atomic Operations");
        map.putIfAbsent("Charlie", 35);
        map.putIfAbsent("Alice", 26); // Won't replace existing
        System.out.println("After putIfAbsent: " + map);
        
        boolean replaced = map.replace("Bob", 30, 31);
        System.out.println("Replace Bob: " + replaced + ", Map: " + map);
        
        // Example 3: Compute operations
        System.out.println("\nExample 3: Compute Operations");
        map.compute("Alice", (key, value) -> value + 1);
        System.out.println("After compute: " + map);
        
        map.computeIfAbsent("David", key -> 28);
        System.out.println("After computeIfAbsent: " + map);
        
        // Example 4: Thread-safe counter
        System.out.println("\nExample 4: Thread-Safe Counter");
        ConcurrentHashMap<String, Integer> counter = new ConcurrentHashMap<>();
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.compute("count", (key, value) -> value == null ? 1 : value + 1);
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Thread-safe counter: " + counter);
        
        System.out.println();
    }
    
    /**
     * Performance tips and best practices
     */
    public static void performanceTips() {
        System.out.println("4. PERFORMANCE TIPS");
        System.out.println("===================");
        
        System.out.println("""
            PERFORMANCE OPTIMIZATION:
            
            ✅ DO:
            • Use appropriate initial capacity
            • Prefer atomic operations over synchronized blocks
            • Use parallel operations for bulk processing
            • Use computeIfAbsent for lazy initialization
            • Use entrySet() for iteration
            
            ❌ AVOID:
            • External synchronization with ConcurrentHashMap
            • Frequent resizing (set initial capacity)
            • Unnecessary atomic operations
            • Iterating during concurrent modifications
            • Using null keys/values
            
            🔧 OPTIMIZATION TECHNIQUES:
            • Set initial capacity: new ConcurrentHashMap<>(1000)
            • Use parallel operations: map.forEach(1, action)
            • Batch operations when possible
            • Use appropriate concurrency level
            
            📊 PERFORMANCE CHARACTERISTICS:
            • Read operations: O(1) non-blocking
            • Write operations: O(1) with CAS/locking
            • Memory overhead: Minimal compared to HashMap
            • Scalability: Excellent for concurrent access
            """);
        
        // Performance example
        System.out.println("\nPerformance Example:");
        
        // Test with different initial capacities
        long startTime = System.nanoTime();
        ConcurrentHashMap<String, Integer> smallMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 10000; i++) {
            smallMap.put("key-" + i, i);
        }
        long smallMapTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        ConcurrentHashMap<String, Integer> largeMap = new ConcurrentHashMap<>(20000);
        for (int i = 0; i < 10000; i++) {
            largeMap.put("key-" + i, i);
        }
        long largeMapTime = System.nanoTime() - startTime;
        
        System.out.println("Small initial capacity: " + smallMapTime / 1_000_000 + " ms");
        System.out.println("Large initial capacity: " + largeMapTime / 1_000_000 + " ms");
        System.out.println("Performance improvement: " + String.format("%.2f", (double) smallMapTime / largeMapTime) + "x");
        
        System.out.println("\n=== QUICK REFERENCE COMPLETE ===");
    }
}
