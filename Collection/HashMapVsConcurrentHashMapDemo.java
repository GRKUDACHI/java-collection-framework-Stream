import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HASHMAP VS CONCURRENTHASHMAP PRACTICAL DEMONSTRATION
 * ====================================================
 * 
 * This class demonstrates the differences between HashMap and ConcurrentHashMap
 * in multi-threaded environments with real examples.
 */

public class HashMapVsConcurrentHashMapDemo {
    
    public static void main(String[] args) {
        System.out.println("=== HASHMAP VS CONCURRENTHASHMAP DEMO ===\n");
        
        demonstrateHashMapProblems();
        demonstrateConcurrentHashMapSolution();
        performanceComparison();
        atomicOperationsDemo();
        threadSafetyTest();
    }
    
    /**
     * Demonstrate problems with HashMap in multi-threaded environment
     */
    public static void demonstrateHashMapProblems() {
        System.out.println("1. HASHMAP PROBLEMS IN MULTI-THREADED ENVIRONMENT");
        System.out.println("=================================================");
        
        System.out.println("""
            HashMap Issues:
            • Race conditions during concurrent modifications
            • Potential infinite loops during resizing
            • Data corruption and lost updates
            • No thread safety guarantees
            """);
        
        // Demonstrate race condition
        Map<String, Integer> unsafeMap = new HashMap<>();
        AtomicInteger raceConditionCount = new AtomicInteger(0);
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        // Multiple threads trying to increment same counter
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    // This is NOT thread-safe!
                    Integer current = unsafeMap.get("counter");
                    if (current == null) {
                        unsafeMap.put("counter", 1);
                    } else {
                        unsafeMap.put("counter", current + 1);
                    }
                    raceConditionCount.incrementAndGet();
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Race condition demonstration:");
        System.out.println("Expected counter value: " + raceConditionCount.get());
        System.out.println("Actual counter value: " + unsafeMap.get("counter"));
        System.out.println("Data loss occurred: " + (raceConditionCount.get() != unsafeMap.get("counter")));
        
        System.out.println();
    }
    
    /**
     * Demonstrate ConcurrentHashMap solution
     */
    public static void demonstrateConcurrentHashMapSolution() {
        System.out.println("2. CONCURRENTHASHMAP SOLUTION");
        System.out.println("==============================");
        
        ConcurrentHashMap<String, Integer> safeMap = new ConcurrentHashMap<>();
        AtomicInteger totalOperations = new AtomicInteger(0);
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        // Multiple threads safely incrementing counter
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    // Thread-safe atomic operation
                    safeMap.compute("counter", (key, value) -> {
                        totalOperations.incrementAndGet();
                        return value == null ? 1 : value + 1;
                    });
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("ConcurrentHashMap solution:");
        System.out.println("Total operations: " + totalOperations.get());
        System.out.println("Final counter value: " + safeMap.get("counter"));
        System.out.println("Data integrity maintained: " + (totalOperations.get() == safeMap.get("counter")));
        
        System.out.println();
    }
    
    /**
     * Performance comparison between HashMap and ConcurrentHashMap
     */
    public static void performanceComparison() {
        System.out.println("3. PERFORMANCE COMPARISON");
        System.out.println("=========================");
        
        int operations = 100000;
        int threadCount = 8;
        
        // Test synchronized HashMap
        Map<String, Integer> syncMap = new HashMap<>();
        long startTime = System.nanoTime();
        
        ExecutorService executor1 = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor1.submit(() -> {
                synchronized (syncMap) {
                    for (int j = 0; j < operations / threadCount; j++) {
                        syncMap.put("key-" + j, j);
                    }
                }
            });
        }
        executor1.shutdown();
        try {
            executor1.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long syncTime = System.nanoTime() - startTime;
        
        // Test ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        startTime = System.nanoTime();
        
        ExecutorService executor2 = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor2.submit(() -> {
                for (int j = 0; j < operations / threadCount; j++) {
                    concurrentMap.put("key-" + j, j);
                }
            });
        }
        executor2.shutdown();
        try {
            executor2.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long concurrentTime = System.nanoTime() - startTime;
        
        System.out.println("Performance Results (" + operations + " operations, " + threadCount + " threads):");
        System.out.println("Synchronized HashMap: " + syncTime / 1_000_000 + " ms");
        System.out.println("ConcurrentHashMap: " + concurrentTime / 1_000_000 + " ms");
        System.out.println("Performance improvement: " + String.format("%.2f", (double) syncTime / concurrentTime) + "x");
        
        System.out.println("\nWhy ConcurrentHashMap is faster:");
        System.out.println("• Segmented locking (Java 7) or CAS operations (Java 8+)");
        System.out.println("• Non-blocking read operations");
        System.out.println("• Better concurrency for mixed read/write workloads");
        System.out.println("• Optimized for multi-threaded environments");
        
        System.out.println();
    }
    
    /**
     * Demonstrate atomic operations in ConcurrentHashMap
     */
    public static void atomicOperationsDemo() {
        System.out.println("4. ATOMIC OPERATIONS DEMONSTRATION");
        System.out.println("===================================");
        
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        System.out.println("Atomic Operations:");
        
        // putIfAbsent - atomic put if key doesn't exist
        Integer result1 = map.putIfAbsent("counter", 1);
        System.out.println("putIfAbsent('counter', 1): " + result1 + ", Map: " + map);
        
        Integer result2 = map.putIfAbsent("counter", 2);
        System.out.println("putIfAbsent('counter', 2): " + result2 + ", Map: " + map);
        
        // replace - atomic replace if current value matches
        boolean replaced1 = map.replace("counter", 1, 10);
        System.out.println("replace('counter', 1, 10): " + replaced1 + ", Map: " + map);
        
        boolean replaced2 = map.replace("counter", 1, 20);
        System.out.println("replace('counter', 1, 20): " + replaced2 + ", Map: " + map);
        
        // remove - atomic remove if current value matches
        boolean removed1 = map.remove("counter", 10);
        System.out.println("remove('counter', 10): " + removed1 + ", Map: " + map);
        
        boolean removed2 = map.remove("counter", 10);
        System.out.println("remove('counter', 10): " + removed2 + ", Map: " + map);
        
        // compute operations
        map.put("test", 5);
        map.compute("test", (key, value) -> value * 2);
        System.out.println("After compute: " + map);
        
        map.computeIfAbsent("new", key -> 100);
        System.out.println("After computeIfAbsent: " + map);
        
        map.computeIfPresent("test", (key, value) -> value + 5);
        System.out.println("After computeIfPresent: " + map);
        
        System.out.println();
    }
    
    /**
     * Comprehensive thread safety test
     */
    public static void threadSafetyTest() {
        System.out.println("5. COMPREHENSIVE THREAD SAFETY TEST");
        System.out.println("====================================");
        
        ConcurrentHashMap<String, AtomicInteger> threadSafeMap = new ConcurrentHashMap<>();
        int threadCount = 10;
        int operationsPerThread = 1000;
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        System.out.println("Testing with " + threadCount + " threads, " + operationsPerThread + " operations each...");
        
        // Submit tasks to multiple threads
        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    String key = "thread-" + threadId;
                    
                    // Thread-safe operations
                    threadSafeMap.computeIfAbsent(key, k -> new AtomicInteger(0))
                                 .incrementAndGet();
                    
                    // Atomic update
                    threadSafeMap.compute(key, (k, v) -> {
                        v.incrementAndGet();
                        return v;
                    });
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results
        System.out.println("Thread safety test results:");
        int totalOperations = 0;
        for (Map.Entry<String, AtomicInteger> entry : threadSafeMap.entrySet()) {
            int value = entry.getValue().get();
            totalOperations += value;
            System.out.println("  " + entry.getKey() + ": " + value + " operations");
        }
        
        int expectedOperations = threadCount * operationsPerThread * 2; // Each thread does 2 operations per iteration
        System.out.println("Expected total operations: " + expectedOperations);
        System.out.println("Actual total operations: " + totalOperations);
        System.out.println("Thread safety verified: " + (expectedOperations == totalOperations));
        
        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
    }
}
