import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * THREAD SAFETY AND SYNCHRONIZATION EXAMPLES
 * ==========================================
 * 
 * This file demonstrates:
 * 1. Race conditions and why synchronization is needed
 * 2. Different synchronization mechanisms
 * 3. Thread-safe alternatives
 * 4. Best practices for thread safety
 */

public class ThreadSafetyAndSynchronization {
    
    public static void main(String[] args) {
        System.out.println("=== THREAD SAFETY AND SYNCHRONIZATION ===\n");
        
        // Example 1: Race Condition Problem
        raceConditionExample();
        
        // Example 2: Synchronized Methods
        synchronizedMethodExample();
        
        // Example 3: Synchronized Blocks
        synchronizedBlockExample();
        
        // Example 4: Atomic Classes
        atomicClassExample();
        
        // Example 5: ReentrantLock
        reentrantLockExample();
        
        // Example 6: Thread-Safe Collections
        threadSafeCollectionsExample();
    }
    
    // ===========================================
    // EXAMPLE 1: RACE CONDITION PROBLEM
    // ===========================================
    
    public static void raceConditionExample() {
        System.out.println("1. RACE CONDITION PROBLEM");
        System.out.println("=========================");
        
        UnsafeCounter counter = new UnsafeCounter();
        
        // Create multiple threads that modify the same counter
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
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
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Race condition example completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 2: SYNCHRONIZED METHODS
    // ===========================================
    
    public static void synchronizedMethodExample() {
        System.out.println("2. SYNCHRONIZED METHODS");
        System.out.println("=======================");
        
        SafeCounter counter = new SafeCounter();
        
        // Create multiple threads that modify the same counter
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
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
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Synchronized method example completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 3: SYNCHRONIZED BLOCKS
    // ===========================================
    
    public static void synchronizedBlockExample() {
        System.out.println("3. SYNCHRONIZED BLOCKS");
        System.out.println("======================");
        
        BlockCounter counter = new BlockCounter();
        
        // Create multiple threads that modify the same counter
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
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
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Synchronized block example completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 4: ATOMIC CLASSES
    // ===========================================
    
    public static void atomicClassExample() {
        System.out.println("4. ATOMIC CLASSES");
        System.out.println("=================");
        
        AtomicCounter counter = new AtomicCounter();
        
        // Create multiple threads that modify the same counter
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
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
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Atomic class example completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 5: REENTRANT LOCK
    // ===========================================
    
    public static void reentrantLockExample() {
        System.out.println("5. REENTRANT LOCK");
        System.out.println("=================");
        
        LockCounter counter = new LockCounter();
        
        // Create multiple threads that modify the same counter
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
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
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Reentrant lock example completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 6: THREAD-SAFE COLLECTIONS
    // ===========================================
    
    public static void threadSafeCollectionsExample() {
        System.out.println("6. THREAD-SAFE COLLECTIONS");
        System.out.println("=========================");
        
        // Using ConcurrentHashMap for thread-safe operations
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        // Create multiple threads that modify the same map
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    String key = "key-" + (threadId * 1000 + j);
                    map.put(key, j);
                }
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
        
        System.out.println("Map size: " + map.size());
        System.out.println("Thread-safe collections example completed!\n");
    }
}

// ===========================================
// SUPPORTING CLASSES
// ===========================================

/**
 * Unsafe counter - demonstrates race condition
 */
class UnsafeCounter {
    private int count = 0;
    
    public void increment() {
        count++; // This is not thread-safe!
    }
    
    public int getCount() {
        return count;
    }
}

/**
 * Safe counter using synchronized method
 */
class SafeCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++; // This is thread-safe
    }
    
    public synchronized int getCount() {
        return count;
    }
}

/**
 * Safe counter using synchronized block
 */
class BlockCounter {
    private int count = 0;
    private final Object lock = new Object();
    
    public void increment() {
        synchronized (lock) {
            count++; // This is thread-safe
        }
    }
    
    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}

/**
 * Safe counter using AtomicInteger
 */
class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet(); // This is thread-safe and efficient
    }
    
    public int getCount() {
        return count.get();
    }
}

/**
 * Safe counter using ReentrantLock
 */
class LockCounter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            count++; // This is thread-safe
        } finally {
            lock.unlock(); // Always unlock in finally block
        }
    }
    
    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}

/*
 * ===========================================
 * SYNCHRONIZATION SUMMARY
 * ===========================================
 * 
 * 1. RACE CONDITIONS:
 *    - Occur when multiple threads access shared data
 *    - Can cause data corruption and inconsistent results
 *    - Need synchronization to prevent them
 * 
 * 2. SYNCHRONIZATION MECHANISMS:
 *    - synchronized methods: Simple but can cause performance issues
 *    - synchronized blocks: More flexible, better performance
 *    - Atomic classes: Best for simple operations (AtomicInteger, AtomicBoolean, etc.)
 *    - ReentrantLock: More control, supports tryLock, interruptible
 *    - Thread-safe collections: ConcurrentHashMap, CopyOnWriteArrayList, etc.
 * 
 * 3. WHEN TO USE WHAT:
 *    - Atomic classes: For simple operations (increment, compare-and-swap)
 *    - synchronized: For simple synchronization needs
 *    - ReentrantLock: When you need advanced features
 *    - Thread-safe collections: When working with collections
 * 
 * 4. BEST PRACTICES:
 *    - Always unlock in finally block when using locks
 *    - Keep synchronized blocks as small as possible
 *    - Use atomic classes when possible
 *    - Prefer thread-safe collections over synchronized collections
 *    - Avoid nested synchronization (can cause deadlocks)
 * 
 * 5. PERFORMANCE CONSIDERATIONS:
 *    - Atomic classes are usually faster than synchronized
 *    - ReentrantLock can be faster than synchronized in some cases
 *    - Thread-safe collections are optimized for concurrent access
 *    - Avoid unnecessary synchronization
 */




