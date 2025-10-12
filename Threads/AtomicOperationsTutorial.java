import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * COMPREHENSIVE ATOMIC OPERATIONS TUTORIAL
 * ========================================
 * 
 * This tutorial covers:
 * 1. What are Atomic Operations?
 * 2. Different Atomic Classes
 * 3. Common Atomic Methods
 * 4. Performance Comparison
 * 5. Real-world Examples
 * 6. Best Practices
 */

public class AtomicOperationsTutorial {
    
    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE ATOMIC OPERATIONS TUTORIAL ===\n");
        
        // Run all examples
        whatAreAtomicOperations();
        atomicIntegerExamples();
        atomicBooleanExamples();
        atomicReferenceExamples();
        performanceComparison();
        realWorldExamples();
        bestPractices();
    }
    
    // ===========================================
    // 1. WHAT ARE ATOMIC OPERATIONS?
    // ===========================================
    
    public static void whatAreAtomicOperations() {
        System.out.println("1. WHAT ARE ATOMIC OPERATIONS?");
        System.out.println("==============================");
        
        System.out.println("""
            ATOMIC OPERATIONS:
            • Operations that complete entirely or not at all
            • Cannot be interrupted by other threads
            • Thread-safe without external synchronization
            • High performance for concurrent access
            • Immediate memory visibility to other threads
            
            KEY CHARACTERISTICS:
            ✓ Indivisible - happens as single unit
            ✓ Thread-Safe - no race conditions
            ✓ Non-Blocking - no locks required
            ✓ Fast - optimized for concurrency
            ✓ Reliable - consistent results
            
            COMMON USE CASES:
            • Counters and accumulators
            • Flags and status indicators
            • Reference updates
            • Compare-and-swap operations
            • Lock-free data structures
            """);
        
        System.out.println();
    }
    
    // ===========================================
    // 2. ATOMIC INTEGER EXAMPLES
    // ===========================================
    
    public static void atomicIntegerExamples() {
        System.out.println("2. ATOMIC INTEGER EXAMPLES");
        System.out.println("==========================");
        
        AtomicInteger counter = new AtomicInteger(0);
        
        System.out.println("Initial value: " + counter.get());
        
        // Basic operations
        System.out.println("incrementAndGet(): " + counter.incrementAndGet()); // 1
        System.out.println("decrementAndGet(): " + counter.decrementAndGet()); // 0
        System.out.println("addAndGet(5): " + counter.addAndGet(5)); // 5
        
        // Get-then-operate methods
        System.out.println("getAndIncrement(): " + counter.getAndIncrement()); // 5, then becomes 6
        System.out.println("getAndDecrement(): " + counter.getAndDecrement()); // 6, then becomes 5
        System.out.println("getAndAdd(3): " + counter.getAndAdd(3)); // 5, then becomes 8
        
        System.out.println("Current value: " + counter.get()); // 8
        
        // Compare and swap
        boolean swapped = counter.compareAndSet(8, 100);
        System.out.println("compareAndSet(8, 100): " + swapped + ", new value: " + counter.get());
        
        // Get and set
        int oldValue = counter.getAndSet(50);
        System.out.println("getAndSet(50): old=" + oldValue + ", new=" + counter.get());
        
        System.out.println("AtomicInteger examples completed!\n");
    }
    
    // ===========================================
    // 3. ATOMIC BOOLEAN EXAMPLES
    // ===========================================
    
    public static void atomicBooleanExamples() {
        System.out.println("3. ATOMIC BOOLEAN EXAMPLES");
        System.out.println("=========================");
        
        AtomicBoolean flag = new AtomicBoolean(false);
        
        System.out.println("Initial value: " + flag.get());
        
        // Set value
        flag.set(true);
        System.out.println("After set(true): " + flag.get());
        
        // Compare and set
        boolean swapped1 = flag.compareAndSet(true, false);
        System.out.println("compareAndSet(true, false): " + swapped1 + ", new value: " + flag.get());
        
        boolean swapped2 = flag.compareAndSet(true, false); // Should fail
        System.out.println("compareAndSet(true, false) [should fail]: " + swapped2 + ", value: " + flag.get());
        
        // Get and set
        boolean oldValue = flag.getAndSet(true);
        System.out.println("getAndSet(true): old=" + oldValue + ", new=" + flag.get());
        
        System.out.println("AtomicBoolean examples completed!\n");
    }
    
    // ===========================================
    // 4. ATOMIC REFERENCE EXAMPLES
    // ===========================================
    
    public static void atomicReferenceExamples() {
        System.out.println("4. ATOMIC REFERENCE EXAMPLES");
        System.out.println("============================");
        
        AtomicReference<String> message = new AtomicReference<>("Hello");
        
        System.out.println("Initial value: " + message.get());
        
        // Set value
        message.set("World");
        System.out.println("After set('World'): " + message.get());
        
        // Compare and set
        boolean swapped1 = message.compareAndSet("World", "Java");
        System.out.println("compareAndSet('World', 'Java'): " + swapped1 + ", new value: " + message.get());
        
        boolean swapped2 = message.compareAndSet("World", "Python"); // Should fail
        System.out.println("compareAndSet('World', 'Python') [should fail]: " + swapped2 + ", value: " + message.get());
        
        // Get and set
        String oldValue = message.getAndSet("Atomic Operations");
        System.out.println("getAndSet('Atomic Operations'): old='" + oldValue + "', new='" + message.get() + "'");
        
        System.out.println("AtomicReference examples completed!\n");
    }
    
    // ===========================================
    // 5. PERFORMANCE COMPARISON
    // ===========================================
    
    public static void performanceComparison() {
        System.out.println("5. PERFORMANCE COMPARISON");
        System.out.println("========================");
        
        int iterations = 1000000;
        int threadCount = 5;
        
        // Test AtomicInteger
        System.out.println("Testing AtomicInteger...");
        long startTime = System.currentTimeMillis();
        
        AtomicInteger atomicCounter = new AtomicInteger(0);
        Thread[] atomicThreads = new Thread[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < iterations / threadCount; j++) {
                    atomicCounter.incrementAndGet();
                }
            });
            atomicThreads[i].start();
        }
        
        for (Thread thread : atomicThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        long atomicTime = System.currentTimeMillis() - startTime;
        System.out.println("AtomicInteger result: " + atomicCounter.get() + " in " + atomicTime + "ms");
        
        // Test Synchronized Counter
        System.out.println("Testing Synchronized Counter...");
        startTime = System.currentTimeMillis();
        
        SynchronizedCounter syncCounter = new SynchronizedCounter();
        Thread[] syncThreads = new Thread[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < iterations / threadCount; j++) {
                    syncCounter.increment();
                }
            });
            syncThreads[i].start();
        }
        
        for (Thread thread : syncThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        long syncTime = System.currentTimeMillis() - startTime;
        System.out.println("Synchronized Counter result: " + syncCounter.getCount() + " in " + syncTime + "ms");
        
        System.out.println("Performance comparison completed!\n");
    }
    
    // ===========================================
    // 6. REAL-WORLD EXAMPLES
    // ===========================================
    
    public static void realWorldExamples() {
        System.out.println("6. REAL-WORLD EXAMPLES");
        System.out.println("======================");
        
        // Example 1: Thread-Safe Counter
        threadSafeCounterExample();
        
        // Example 2: Connection Pool Status
        connectionPoolExample();
        
        // Example 3: Cache with Atomic Reference
        cacheExample();
    }
    
    private static void threadSafeCounterExample() {
        System.out.println("Thread-Safe Counter Example:");
        
        AtomicInteger pageViews = new AtomicInteger(0);
        AtomicInteger uniqueVisitors = new AtomicInteger(0);
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        // Simulate multiple users visiting pages
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                // Simulate page view
                pageViews.incrementAndGet();
                
                // Simulate unique visitor (simplified)
                if (Math.random() < 0.3) { // 30% chance of unique visitor
                    uniqueVisitors.incrementAndGet();
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Total page views: " + pageViews.get());
        System.out.println("Unique visitors: " + uniqueVisitors.get());
        System.out.println();
    }
    
    private static void connectionPoolExample() {
        System.out.println("Connection Pool Status Example:");
        
        AtomicBoolean isPoolHealthy = new AtomicBoolean(true);
        AtomicInteger activeConnections = new AtomicInteger(0);
        AtomicInteger maxConnections = new AtomicInteger(10);
        
        // Simulate connection usage
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 20; i++) {
            executor.submit(() -> {
                // Try to get connection
                int current = activeConnections.get();
                if (current < maxConnections.get()) {
                    activeConnections.incrementAndGet();
                    System.out.println("Connection acquired. Active: " + activeConnections.get());
                    
                    // Simulate work
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    // Release connection
                    activeConnections.decrementAndGet();
                    System.out.println("Connection released. Active: " + activeConnections.get());
                } else {
                    System.out.println("Connection pool full!");
                    isPoolHealthy.set(false);
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Pool healthy: " + isPoolHealthy.get());
        System.out.println("Final active connections: " + activeConnections.get());
        System.out.println();
    }
    
    private static void cacheExample() {
        System.out.println("Cache with Atomic Reference Example:");
        
        AtomicReference<String> cachedData = new AtomicReference<>(null);
        AtomicInteger cacheHits = new AtomicInteger(0);
        AtomicInteger cacheMisses = new AtomicInteger(0);
        
        // Simulate cache access
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 20; i++) {
            executor.submit(() -> {
                String data = cachedData.get();
                if (data != null) {
                    cacheHits.incrementAndGet();
                    System.out.println("Cache hit! Data: " + data);
                } else {
                    cacheMisses.incrementAndGet();
                    // Simulate expensive data fetch
                    String newData = "Expensive data fetched at " + System.currentTimeMillis();
                    cachedData.set(newData);
                    System.out.println("Cache miss! Fetched: " + newData);
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Cache hits: " + cacheHits.get());
        System.out.println("Cache misses: " + cacheMisses.get());
        System.out.println();
    }
    
    // ===========================================
    // 7. BEST PRACTICES
    // ===========================================
    
    public static void bestPractices() {
        System.out.println("7. BEST PRACTICES");
        System.out.println("=================");
        
        System.out.println("""
            ATOMIC OPERATIONS BEST PRACTICES:
            
            1. CHOOSE THE RIGHT ATOMIC CLASS:
               • AtomicInteger for counters
               • AtomicBoolean for flags
               • AtomicReference for object references
               • AtomicLong for large numbers
            
            2. USE APPROPRIATE METHODS:
               • incrementAndGet() for ++counter
               • getAndIncrement() for counter++
               • compareAndSet() for conditional updates
               • getAndSet() for atomic swaps
            
            3. PERFORMANCE CONSIDERATIONS:
               • Atomic operations are faster than synchronized
               • Use for simple operations (increment, compare-and-swap)
               • Avoid complex operations in atomic context
            
            4. MEMORY VISIBILITY:
               • Atomic operations provide immediate visibility
               • No need for volatile keyword
               • Changes are visible to all threads
            
            5. COMPOUND OPERATIONS:
               • Atomic operations are not composable
               • For complex operations, use synchronized or locks
               • Example: if (atomic.get() > 0) atomic.decrement() is NOT atomic
            
            6. ERROR HANDLING:
               • Atomic operations don't throw checked exceptions
               • Handle InterruptedException in surrounding code
               • Use try-catch for ExecutionException with Future
            
            7. TESTING:
               • Test with multiple threads
               • Verify expected results
               • Use stress testing for race conditions
            
            8. COMMON MISTAKES TO AVOID:
               ❌ Don't use atomic for complex operations
               ❌ Don't assume atomic operations are composable
               ❌ Don't forget about memory visibility
               ❌ Don't use atomic when synchronized is more appropriate
            """);
        
        System.out.println("Best practices completed!\n");
    }
}

// ===========================================
// SUPPORTING CLASSES
// ===========================================

/**
 * Synchronized counter for performance comparison
 */
class SynchronizedCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}

/**
 * Thread-safe counter using AtomicInteger
 */
class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet();
    }
    
    public int getCount() {
        return count.get();
    }
    
    public void add(int value) {
        count.addAndGet(value);
    }
    
    public boolean compareAndSet(int expected, int newValue) {
        return count.compareAndSet(expected, newValue);
    }
}

/**
 * Thread-safe flag using AtomicBoolean
 */
class AtomicFlag {
    private AtomicBoolean flag = new AtomicBoolean(false);
    
    public void setFlag(boolean value) {
        flag.set(value);
    }
    
    public boolean getFlag() {
        return flag.get();
    }
    
    public boolean toggleFlag() {
        boolean current = flag.get();
        return flag.compareAndSet(current, !current);
    }
}

/**
 * Thread-safe reference using AtomicReference
 */
class AtomicReference<T> {
    private java.util.concurrent.atomic.AtomicReference<T> reference;
    
    public AtomicReference(T initialValue) {
        this.reference = new java.util.concurrent.atomic.AtomicReference<>(initialValue);
    }
    
    public void set(T newValue) {
        reference.set(newValue);
    }
    
    public T get() {
        return reference.get();
    }
    
    public T getAndSet(T newValue) {
        return reference.getAndSet(newValue);
    }
    
    public boolean compareAndSet(T expected, T newValue) {
        return reference.compareAndSet(expected, newValue);
    }
}

/*
 * ===========================================
 * SUMMARY - ATOMIC OPERATIONS
 * ===========================================
 * 
 * 1. WHAT ARE ATOMIC OPERATIONS?
 *    - Operations that complete entirely or not at all
 *    - Cannot be interrupted by other threads
 *    - Thread-safe without external synchronization
 * 
 * 2. COMMON ATOMIC CLASSES:
 *    - AtomicInteger: For integer counters
 *    - AtomicLong: For long counters
 *    - AtomicBoolean: For boolean flags
 *    - AtomicReference: For object references
 * 
 * 3. COMMON METHODS:
 *    - incrementAndGet(): ++counter
 *    - getAndIncrement(): counter++
 *    - addAndGet(delta): counter += delta
 *    - compareAndSet(expected, new): conditional update
 *    - getAndSet(new): atomic swap
 * 
 * 4. WHEN TO USE:
 *    ✓ Simple operations (increment, compare-and-swap)
 *    ✓ High-performance requirements
 *    ✓ Lock-free programming
 *    ✓ Counters and accumulators
 * 
 * 5. WHEN NOT TO USE:
 *    ✗ Complex operations requiring multiple steps
 *    ✗ When you need to compose multiple atomic operations
 *    ✗ When synchronized is more appropriate
 * 
 * 6. PERFORMANCE:
 *    - Atomic operations are faster than synchronized
 *    - No blocking or waiting
 *    - Optimized for concurrent access
 *    - Immediate memory visibility
 * 
 * 7. BEST PRACTICES:
 *    - Choose the right atomic class
 *    - Use appropriate methods
 *    - Test with multiple threads
 *    - Avoid complex operations
 *    - Consider memory visibility
 */
