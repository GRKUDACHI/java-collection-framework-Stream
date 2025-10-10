import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * COMPLETE JAVA THREADS TUTORIAL
 * ==============================
 * 
 * This tutorial covers:
 * 1. What are Threads?
 * 2. Types of Threads
 * 3. Creating Threads (Traditional vs Lambda)
 * 4. ExecutorService for Background Processing
 * 5. Synchronization and Thread Safety
 * 6. Practical Examples
 */

public class CompleteThreadTutorial {
    
    public static void main(String[] args) {
        System.out.println("=== COMPLETE JAVA THREADS TUTORIAL ===\n");
        
        // Run all examples
        basicThreadExample();
        lambdaThreadExample();
        executorServiceExample();
        backgroundProcessingExample();
        synchronizationExample();
        practicalExamples();
    }
    
    // ===========================================
    // 1. BASIC THREAD CONCEPTS
    // ===========================================
    
    /**
     * WHAT IS A THREAD?
     * - A thread is a lightweight process
     * - Multiple threads can run concurrently in a single program
     * - Each thread has its own execution path
     * - Threads share the same memory space
     */
    public static void basicThreadExample() {
        System.out.println("1. BASIC THREAD EXAMPLE");
        System.out.println("=======================");
        
        // Method 1: Extending Thread class
        MyThread thread1 = new MyThread("Thread-1");
        thread1.start();
        
        // Method 2: Implementing Runnable interface
        MyRunnable runnable = new MyRunnable();
        Thread thread2 = new Thread(runnable, "Thread-2");
        thread2.start();
        
        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Basic thread example completed!\n");
    }
    
    // ===========================================
    // 2. LAMBDA EXPRESSIONS WITH THREADS
    // ===========================================
    
    /**
     * LAMBDA EXPRESSIONS MAKE THREAD CREATION EASIER
     * - No need to create separate classes
     * - Cleaner and more readable code
     * - Perfect for simple tasks
     */
    public static void lambdaThreadExample() {
        System.out.println("2. LAMBDA THREAD EXAMPLE");
        System.out.println("========================");
        
        // Creating thread with lambda expression
        Thread lambdaThread1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda Thread 1: Count " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Lambda-Thread-1");
        
        Thread lambdaThread2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda Thread 2: Count " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Lambda-Thread-2");
        
        // Start both threads
        lambdaThread1.start();
        lambdaThread2.start();
        
        // Wait for completion
        try {
            lambdaThread1.join();
            lambdaThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Lambda thread example completed!\n");
    }
    
    // ===========================================
    // 3. EXECUTOR SERVICE - BACKGROUND PROCESSING
    // ===========================================
    
    /**
     * EXECUTOR SERVICE BENEFITS:
     * - Manages thread pool automatically
     * - Better resource management
     * - Easy to control thread lifecycle
     * - Perfect for background processing
     */
    public static void executorServiceExample() {
        System.out.println("3. EXECUTOR SERVICE EXAMPLE");
        System.out.println("===========================");
        
        // Create different types of thread pools
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        
        // Submit tasks to fixed pool
        System.out.println("Using Fixed Thread Pool (3 threads):");
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixedPool.submit(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // Shutdown the pool
        fixedPool.shutdown();
        
        // Wait for all tasks to complete
        try {
            fixedPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Executor Service example completed!\n");
    }
    
    // ===========================================
    // 4. BACKGROUND PROCESSING WITH CALLABLE
    // ===========================================
    
    /**
     * CALLABLE vs RUNNABLE:
     * - Callable can return a value
     * - Callable can throw checked exceptions
     * - Perfect for background processing with results
     */
    public static void backgroundProcessingExample() {
        System.out.println("4. BACKGROUND PROCESSING EXAMPLE");
        System.out.println("================================");
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Create callable tasks that return results
        Callable<String> task1 = () -> {
            System.out.println("Background Task 1: Processing data...");
            Thread.sleep(2000);
            return "Task 1 completed: Processed 1000 records";
        };
        
        Callable<String> task2 = () -> {
            System.out.println("Background Task 2: Sending emails...");
            Thread.sleep(1500);
            return "Task 2 completed: Sent 50 emails";
        };
        
        Callable<Integer> task3 = () -> {
            System.out.println("Background Task 3: Calculating sum...");
            Thread.sleep(1000);
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };
        
        // Submit tasks and get Future objects
        Future<String> future1 = executor.submit(task1);
        Future<String> future2 = executor.submit(task2);
        Future<Integer> future3 = executor.submit(task3);
        
        // Do other work while tasks run in background
        System.out.println("Main thread: Doing other work while background tasks run...");
        
        // Get results when needed
        try {
            System.out.println("Result 1: " + future1.get());
            System.out.println("Result 2: " + future2.get());
            System.out.println("Result 3: Sum = " + future3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
        System.out.println("Background processing example completed!\n");
    }
    
    // ===========================================
    // 5. SYNCHRONIZATION - THREAD SAFETY
    // ===========================================
    
    /**
     * SYNCHRONIZATION IS IMPORTANT WHEN:
     * - Multiple threads access shared resources
     * - You need to prevent race conditions
     * - Data consistency is critical
     */
    public static void synchronizationExample() {
        System.out.println("5. SYNCHRONIZATION EXAMPLE");
        System.out.println("==========================");
        
        SharedCounter counter = new SharedCounter();
        
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
        
        System.out.println("Final counter value: " + counter.getCount());
        System.out.println("Expected: 5000, Actual: " + counter.getCount());
        System.out.println("Synchronization example completed!\n");
    }
    
    // ===========================================
    // 6. PRACTICAL EXAMPLES
    // ===========================================
    
    /**
     * REAL-WORLD SCENARIOS:
     * - File processing
     * - API calls
     * - Database operations
     * - Image processing
     */
    public static void practicalExamples() {
        System.out.println("6. PRACTICAL EXAMPLES");
        System.out.println("=====================");
        
        // Example 1: File Processing Simulation
        fileProcessingExample();
        
        // Example 2: API Calls Simulation
        apiCallsExample();
        
        // Example 3: Database Operations Simulation
        databaseOperationsExample();
    }
    
    private static void fileProcessingExample() {
        System.out.println("File Processing Example:");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        String[] files = {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt"};
        
        for (String file : files) {
            executor.submit(() -> {
                System.out.println("Processing " + file + " on " + Thread.currentThread().getName());
                // Simulate file processing
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Completed processing " + file);
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
    
    private static void apiCallsExample() {
        System.out.println("API Calls Example:");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Simulate multiple API calls
        CompletableFuture<String> api1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calling API 1...");
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            return "API 1 Response: User data";
        }, executor);
        
        CompletableFuture<String> api2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calling API 2...");
            try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
            return "API 2 Response: Product data";
        }, executor);
        
        // Combine results
        CompletableFuture<String> combined = api1.thenCombine(api2, (result1, result2) -> {
            return result1 + " + " + result2;
        });
        
        try {
            System.out.println("Combined result: " + combined.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
        System.out.println();
    }
    
    private static void databaseOperationsExample() {
        System.out.println("Database Operations Example:");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Simulate database operations
        executor.submit(() -> {
            System.out.println("Inserting user data...");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("User data inserted successfully");
        });
        
        executor.submit(() -> {
            System.out.println("Updating product prices...");
            try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Product prices updated successfully");
        });
        
        executor.submit(() -> {
            System.out.println("Generating reports...");
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Reports generated successfully");
        });
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

// ===========================================
// SUPPORTING CLASSES
// ===========================================

/**
 * Thread class example
 */
class MyThread extends Thread {
    private String threadName;
    
    public MyThread(String name) {
        this.threadName = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(threadName + ": Count " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Runnable interface example
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Runnable Thread: Count " + i);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Thread-safe counter using synchronization
 */
class SharedCounter {
    private int count = 0;
    
    // Synchronized method - only one thread can access at a time
    public synchronized void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
}

/**
 * Thread-safe counter using AtomicInteger (better performance)
 */
class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet();
    }
    
    public int getCount() {
        return count.get();
    }
}

/*
 * ===========================================
 * SUMMARY - KEY CONCEPTS
 * ===========================================
 * 
 * 1. THREAD TYPES:
 *    - User Threads: Created by application
 *    - Daemon Threads: Background threads that die when main thread dies
 * 
 * 2. THREAD CREATION METHODS:
 *    - Extending Thread class
 *    - Implementing Runnable interface
 *    - Using Lambda expressions (recommended for simple tasks)
 * 
 * 3. EXECUTOR SERVICE TYPES:
 *    - newFixedThreadPool(n): Fixed number of threads
 *    - newCachedThreadPool(): Creates threads as needed
 *    - newSingleThreadExecutor(): Only one thread
 *    - newScheduledThreadPool(n): For scheduled tasks
 * 
 * 4. SYNCHRONIZATION:
 *    - synchronized keyword
 *    - Atomic classes (AtomicInteger, AtomicBoolean, etc.)
 *    - Locks (ReentrantLock, ReadWriteLock)
 * 
 * 5. BEST PRACTICES:
 *    - Use ExecutorService instead of creating threads manually
 *    - Use lambda expressions for simple tasks
 *    - Always handle InterruptedException
 *    - Use appropriate synchronization mechanisms
 *    - Shutdown ExecutorService when done
 * 
 * 6. COMMON USE CASES:
 *    - Background processing
 *    - Parallel processing
 *    - Asynchronous operations
 *    - Resource-intensive tasks
 */
