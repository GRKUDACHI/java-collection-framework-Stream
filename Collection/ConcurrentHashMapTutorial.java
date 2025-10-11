import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CONCURRENTHASHMAP COMPREHENSIVE TUTORIAL
 * =======================================
 * 
 * This tutorial explains ConcurrentHashMap - a thread-safe version of HashMap
 * designed for concurrent access without external synchronization.
 */

public class ConcurrentHashMapTutorial {
    
    public static void main(String[] args) {
        System.out.println("=== CONCURRENTHASHMAP COMPREHENSIVE TUTORIAL ===\n");
        
        whatIsConcurrentHashMap();
        whyConcurrentHashMap();
        internalWorking();
        basicOperations();
        threadSafetyDemo();
        performanceComparison();
        advancedFeatures();
        bestPractices();
    }
    
    /**
     * 1. WHAT IS CONCURRENTHASHMAP?
     * =============================
     */
    public static void whatIsConcurrentHashMap() {
        System.out.println("1. WHAT IS CONCURRENTHASHMAP?");
        System.out.println("=============================");
        
        System.out.println("""
            ConcurrentHashMap is a thread-safe implementation of the Map interface
            that allows multiple threads to read and write concurrently without
            external synchronization.
            
            Key Characteristics:
            • Thread-safe: Multiple threads can access simultaneously
            • High performance: Optimized for concurrent access
            • No external locking: Internal synchronization mechanisms
            • Non-blocking reads: Read operations don't block other reads
            • Segmented locking: Only locks specific segments, not entire map
            
            When to Use:
            + Multi-threaded applications
            + High-concurrency scenarios
            + Cache implementations
            + Shared data structures
            + Producer-consumer patterns
            
            When NOT to Use:
            - Single-threaded applications (HashMap is faster)
            - When you need ordered iteration (use LinkedHashMap)
            - When you need sorted keys (use TreeMap)
            """);
        
        System.out.println();
    }
    
    /**
     * 2. WHY CONCURRENTHASHMAP?
     * ========================
     */
    public static void whyConcurrentHashMap() {
        System.out.println("2. WHY CONCURRENTHASHMAP?");
        System.out.println("========================");
        
        System.out.println("""
            Problems with HashMap in Multi-threaded Environment:
            
            1. RACE CONDITIONS:
               - Multiple threads modifying same data simultaneously
               - Can cause data corruption and inconsistent state
               - Example: Two threads incrementing same counter
            
            2. INFINITE LOOPS:
               - HashMap can get into infinite loop during resizing
               - Happens when threads modify structure during iteration
               - Can cause application to hang
            
            3. DATA LOSS:
               - Concurrent modifications can cause lost updates
               - Thread A puts value, Thread B overwrites it
               - No visibility guarantees between threads
            
            4. PERFORMANCE ISSUES:
               - Synchronized HashMap blocks all operations
               - Only one thread can access at a time
               - Poor scalability in multi-threaded environment
            
            ConcurrentHashMap Solutions:
            - Segmented locking for better concurrency
            - Non-blocking read operations
            - Lock-free algorithms where possible
            - Optimized for high-throughput scenarios
            """);
        
        System.out.println();
    }
    
    /**
     * 3. INTERNAL WORKING
     * ===================
     */
    public static void internalWorking() {
        System.out.println("3. INTERNAL WORKING");
        System.out.println("===================");
        
        System.out.println("""
            ConcurrentHashMap Internal Architecture:
            
            Java 7 Implementation:
            - Uses segment-based locking
            - Each segment has its own lock
            - Better concurrency than synchronized HashMap
            
            Java 8+ Implementation:
            - Removed segment-based locking
            - Uses CAS (Compare-And-Swap) operations
            - Tree bins for high collision scenarios
            - Lock-free read operations
            - Fine-grained locking for write operations
            
            Key Improvements in Java 8+:
            - Better concurrency with CAS operations
            - Reduced memory overhead
            - Improved performance for mixed read/write workloads
            - Lock-free reads improve scalability
            """);
        
        System.out.println();
    }
    
    /**
     * 4. BASIC OPERATIONS
     * ===================
     */
    public static void basicOperations() {
        System.out.println("4. BASIC OPERATIONS");
        System.out.println("==================");
        
        // Creating ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // Basic operations
        System.out.println("Basic Operations:");
        
        // Put operation
        concurrentMap.put("Alice", 25);
        concurrentMap.put("Bob", 30);
        concurrentMap.put("Charlie", 35);
        System.out.println("After put operations: " + concurrentMap);
        
        // Get operation
        Integer aliceAge = concurrentMap.get("Alice");
        System.out.println("Alice's age: " + aliceAge);
        
        // Put if absent (atomic operation)
        Integer previousValue = concurrentMap.putIfAbsent("David", 28);
        System.out.println("Put if absent David: " + previousValue + ", Map: " + concurrentMap);
        
        // Replace operation
        boolean replaced = concurrentMap.replace("Alice", 25, 26);
        System.out.println("Replace Alice's age: " + replaced + ", Map: " + concurrentMap);
        
        // Remove operation
        Integer removedValue = concurrentMap.remove("Bob");
        System.out.println("Removed Bob: " + removedValue + ", Map: " + concurrentMap);
        
        // Size and empty check
        System.out.println("Map size: " + concurrentMap.size());
        System.out.println("Is empty: " + concurrentMap.isEmpty());
        
        // Contains operations
        System.out.println("Contains key 'Alice': " + concurrentMap.containsKey("Alice"));
        System.out.println("Contains value 35: " + concurrentMap.containsValue(35));
        
        System.out.println();
    }
    
    /**
     * 5. THREAD SAFETY DEMONSTRATION
     * ==============================
     */
    public static void threadSafetyDemo() {
        System.out.println("5. THREAD SAFETY DEMONSTRATION");
        System.out.println("==============================");
        
        // Demonstrate thread safety with multiple threads
        ConcurrentHashMap<String, AtomicInteger> threadSafeMap = new ConcurrentHashMap<>();
        
        // Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        System.out.println("Testing thread safety with 5 threads...");
        
        // Submit tasks to multiple threads
        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    String key = "counter-" + threadId;
                    
                    // Thread-safe increment using computeIfAbsent and getAndIncrement
                    threadSafeMap.computeIfAbsent(key, k -> new AtomicInteger(0))
                                 .getAndIncrement();
                }
            });
        }
        
        // Shutdown executor and wait for completion
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Display results
        System.out.println("Thread-safe map results:");
        threadSafeMap.forEach((key, value) -> 
            System.out.println("  " + key + ": " + value.get()));
        
        // Demonstrate atomic operations
        System.out.println("\nAtomic Operations Demo:");
        ConcurrentHashMap<String, Integer> atomicMap = new ConcurrentHashMap<>();
        
        // Atomic put if absent
        atomicMap.putIfAbsent("test", 1);
        atomicMap.putIfAbsent("test", 2); // Won't replace existing value
        System.out.println("Atomic putIfAbsent: " + atomicMap);
        
        // Atomic replace
        atomicMap.replace("test", 1, 10);
        atomicMap.replace("test", 1, 20); // Won't replace (current value is 10)
        System.out.println("Atomic replace: " + atomicMap);
        
        // Atomic compute operations
        atomicMap.compute("test", (key, value) -> value * 2);
        System.out.println("Atomic compute: " + atomicMap);
        
        atomicMap.computeIfAbsent("newKey", key -> 100);
        System.out.println("Atomic computeIfAbsent: " + atomicMap);
        
        atomicMap.computeIfPresent("test", (key, value) -> value + 5);
        System.out.println("Atomic computeIfPresent: " + atomicMap);
        
        System.out.println();
    }
    
    /**
     * 6. PERFORMANCE COMPARISON
     * =========================
     */
    public static void performanceComparison() {
        System.out.println("6. PERFORMANCE COMPARISON");
        System.out.println("========================");
        
        int iterations = 100000;
        int threadCount = 4;
        
        // Test HashMap with synchronization
        Map<String, Integer> synchronizedMap = new HashMap<>();
        long startTime = System.nanoTime();
        
        ExecutorService executor1 = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor1.submit(() -> {
                synchronized (synchronizedMap) {
                    for (int j = 0; j < iterations / threadCount; j++) {
                        synchronizedMap.put("key-" + j, j);
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
        
        long synchronizedTime = System.nanoTime() - startTime;
        
        // Test ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        startTime = System.nanoTime();
        
        ExecutorService executor2 = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor2.submit(() -> {
                for (int j = 0; j < iterations / threadCount; j++) {
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
        
        System.out.println("Performance Comparison (" + iterations + " operations, " + threadCount + " threads):");
        System.out.println("Synchronized HashMap: " + synchronizedTime / 1_000_000 + " ms");
        System.out.println("ConcurrentHashMap: " + concurrentTime / 1_000_000 + " ms");
        System.out.println("Performance ratio: " + (double) synchronizedTime / concurrentTime);
        
        System.out.println("\nPerformance Characteristics:");
        System.out.println("• ConcurrentHashMap: Better for high-concurrency scenarios");
        System.out.println("• Synchronized HashMap: Better for low-concurrency scenarios");
        System.out.println("• ConcurrentHashMap: Non-blocking reads");
        System.out.println("• Synchronized HashMap: All operations block each other");
        
        System.out.println();
    }
    
    /**
     * 7. ADVANCED FEATURES
     * ====================
     */
    public static void advancedFeatures() {
        System.out.println("7. ADVANCED FEATURES");
        System.out.println("===================");
        
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        System.out.println("Advanced Features Demo:");
        
        // 1. Atomic operations
        System.out.println("\n1. Atomic Operations:");
        System.out.println("Original map: " + map);
        
        // Atomic update
        map.compute("A", (key, value) -> value * 2);
        System.out.println("After compute A: " + map);
        
        // Atomic merge
        map.merge("B", 5, Integer::sum);
        System.out.println("After merge B: " + map);
        
        // 2. Bulk operations
        System.out.println("\n2. Bulk Operations:");
        
        // forEach - parallel iteration
        System.out.print("forEach: ");
        map.forEach((key, value) -> System.out.print(key + "=" + value + " "));
        System.out.println();
        
        // reduce - parallel reduction
        int sum = map.reduceValues(1, Integer::sum);
        System.out.println("Sum of all values: " + sum);
        
        // search - parallel search
        String foundKey = map.searchKeys(1, key -> key.equals("B") ? key : null);
        System.out.println("Found key 'B': " + foundKey);
        
        // 3. Parallel operations
        System.out.println("\n3. Parallel Operations:");
        
        // Parallel forEach
        System.out.print("Parallel forEach: ");
        map.forEach(1, (key, value) -> System.out.print(key + "=" + value + " "));
        System.out.println();
        
        // Parallel reduce
        int maxValue = map.reduceValues(1, Integer::max);
        System.out.println("Maximum value: " + maxValue);
        
        System.out.println();
    }
    
    /**
     * 8. BEST PRACTICES
     * =================
     */
    public static void bestPractices() {
        System.out.println("8. BEST PRACTICES");
        System.out.println("=================");
        
        System.out.println("""
            DO:
            - Use ConcurrentHashMap for multi-threaded applications
            - Prefer atomic operations (putIfAbsent, replace, compute)
            - Use parallel operations for bulk processing
            - Set appropriate initial capacity and load factor
            - Use computeIfAbsent for lazy initialization
            
            DON'T:
            - Don't use external synchronization with ConcurrentHashMap
            - Don't assume iteration is thread-safe (use entrySet())
            - Don't modify map during iteration
            - Don't use null keys or values
            - Don't use for single-threaded applications
            
            PERFORMANCE TIPS:
            - Use parallel operations for large datasets
            - Prefer atomic operations over synchronized blocks
            - Use appropriate concurrency level
            - Consider memory usage vs performance trade-offs
            
            COMMON MISTAKES:
            - Using HashMap in multi-threaded environment
            - External synchronization with ConcurrentHashMap
            - Assuming all operations are atomic
            - Not handling concurrent modification exceptions
            """);
        
        // Practical example
        System.out.println("\nPractical Example - Thread-Safe Cache:");
        
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        
        // Thread-safe cache operations
        String value1 = cache.computeIfAbsent("key1", key -> {
            System.out.println("Computing value for: " + key);
            return "computed-value-1";
        });
        
        String value2 = cache.computeIfAbsent("key1", key -> {
            System.out.println("This won't be called - key exists");
            return "different-value";
        });
        
        System.out.println("Cache: " + cache);
        System.out.println("Values: " + value1 + ", " + value2);
        
        System.out.println("\n=== CONCURRENTHASHMAP TUTORIAL COMPLETE ===");
    }
}