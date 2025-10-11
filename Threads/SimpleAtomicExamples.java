import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * SIMPLE ATOMIC OPERATIONS EXAMPLES
 * =================================
 * 
 * Easy-to-understand examples of atomic operations
 * Perfect for beginners!
 */

public class SimpleAtomicExamples {
    
    public static void main(String[] args) {
        System.out.println("=== SIMPLE ATOMIC OPERATIONS EXAMPLES ===\n");
        
        // Example 1: Basic AtomicInteger
        basicAtomicInteger();
        
        // Example 2: Thread-Safe Counter
        threadSafeCounter();
        
        // Example 3: AtomicBoolean Flag
        atomicBooleanFlag();
        
        // Example 4: Compare Synchronized vs Atomic
        synchronizedVsAtomic();
    }
    
    // ===========================================
    // EXAMPLE 1: BASIC ATOMIC INTEGER
    // ===========================================
    
    public static void basicAtomicInteger() {
        System.out.println("1. BASIC ATOMIC INTEGER");
        System.out.println("=======================");
        
        AtomicInteger counter = new AtomicInteger(0);
        
        System.out.println("Starting value: " + counter.get());
        
        // Different ways to increment
        System.out.println("incrementAndGet(): " + counter.incrementAndGet()); // Returns new value
        System.out.println("getAndIncrement(): " + counter.getAndIncrement()); // Returns old value
        System.out.println("Current value: " + counter.get());
        
        // Add a specific number
        System.out.println("addAndGet(5): " + counter.addAndGet(5));
        System.out.println("Current value: " + counter.get());
        
        // Compare and set (only set if current value matches expected)
        boolean success = counter.compareAndSet(7, 100);
        System.out.println("compareAndSet(7, 100): " + success + ", new value: " + counter.get());
        
        System.out.println("Basic AtomicInteger completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 2: THREAD-SAFE COUNTER
    // ===========================================
    
    public static void threadSafeCounter() {
        System.out.println("2. THREAD-SAFE COUNTER");
        System.out.println("======================");
        
        AtomicInteger sharedCounter = new AtomicInteger(0);
        
        // Create multiple threads that increment the same counter
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    sharedCounter.incrementAndGet(); // Thread-safe increment
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " finished");
            });
            threads[i].start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Expected count: 5000");
        System.out.println("Actual count: " + sharedCounter.get());
        System.out.println("Thread-safe counter completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 3: ATOMIC BOOLEAN FLAG
    // ===========================================
    
    public static void atomicBooleanFlag() {
        System.out.println("3. ATOMIC BOOLEAN FLAG");
        System.out.println("=====================");
        
        AtomicBoolean isProcessing = new AtomicBoolean(false);
        
        System.out.println("Initial processing state: " + isProcessing.get());
        
        // Simulate multiple threads trying to start processing
        Thread[] threads = new Thread[3];
        
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                // Try to start processing (only one should succeed)
                boolean started = isProcessing.compareAndSet(false, true);
                if (started) {
                    System.out.println("Thread " + threadId + " started processing");
                    try {
                        Thread.sleep(1000); // Simulate work
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isProcessing.set(false); // Finish processing
                    System.out.println("Thread " + threadId + " finished processing");
                } else {
                    System.out.println("Thread " + threadId + " could not start - already processing");
                }
            });
            threads[i].start();
        }
        
        // Wait for all threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Final processing state: " + isProcessing.get());
        System.out.println("AtomicBoolean flag completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 4: SYNCHRONIZED VS ATOMIC
    // ===========================================
    
    public static void synchronizedVsAtomic() {
        System.out.println("4. SYNCHRONIZED VS ATOMIC COMPARISON");
        System.out.println("===================================");
        
        int iterations = 100000;
        
        // Test AtomicInteger
        System.out.println("Testing AtomicInteger...");
        long startTime = System.currentTimeMillis();
        
        AtomicInteger atomicCounter = new AtomicInteger(0);
        Thread[] atomicThreads = new Thread[3];
        
        for (int i = 0; i < 3; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < iterations / 3; j++) {
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
        Thread[] syncThreads = new Thread[3];
        
        for (int i = 0; i < 3; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < iterations / 3; j++) {
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
}

// ===========================================
// SUPPORTING CLASSES
// ===========================================

/**
 * Synchronized counter for comparison
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

/*
 * ===========================================
 * QUICK REFERENCE - ATOMIC OPERATIONS
 * ===========================================
 * 
 * 1. ATOMIC INTEGER METHODS:
 *    - incrementAndGet(): ++counter (returns new value)
 *    - getAndIncrement(): counter++ (returns old value)
 *    - decrementAndGet(): --counter
 *    - getAndDecrement(): counter--
 *    - addAndGet(delta): counter += delta
 *    - getAndAdd(delta): temp = counter; counter += delta; return temp
 *    - compareAndSet(expected, new): if counter == expected, set to new
 *    - getAndSet(new): temp = counter; counter = new; return temp
 * 
 * 2. ATOMIC BOOLEAN METHODS:
 *    - set(value): Set to value
 *    - get(): Get current value
 *    - compareAndSet(expected, new): If current == expected, set to new
 *    - getAndSet(new): Set to new, return old value
 * 
 * 3. WHEN TO USE ATOMIC:
 *    ✓ Simple operations (increment, compare-and-swap)
 *    ✓ High performance requirements
 *    ✓ Lock-free programming
 *    ✓ Counters and flags
 * 
 * 4. WHEN NOT TO USE ATOMIC:
 *    ✗ Complex operations requiring multiple steps
 *    ✗ When you need to compose multiple operations
 *    ✗ When synchronized is more appropriate
 * 
 * 5. PERFORMANCE:
 *    - Atomic operations are usually faster than synchronized
 *    - No blocking or waiting
 *    - Optimized for concurrent access
 *    - Immediate memory visibility
 * 
 * 6. COMMON USE CASES:
 *    - Page view counters
 *    - Connection pool management
 *    - Cache status flags
 *    - Reference updates
 *    - Lock-free data structures
 */
