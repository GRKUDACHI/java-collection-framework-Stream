import java.util.concurrent.*;

/**
 * EASY THREAD EXAMPLES - SIMPLIFIED VERSION
 * =========================================
 * 
 * This file contains simple, easy-to-understand examples of:
 * 1. Creating threads with lambda expressions
 * 2. Using ExecutorService for background processing
 * 3. Multithreading basics
 */

public class EasyThreadExamples {
    
    public static void main(String[] args) {
        System.out.println("=== EASY THREAD EXAMPLES ===\n");
        
        // Example 1: Simple Lambda Thread
        simpleLambdaThread();
        
        // Example 2: Multiple Lambda Threads
        multipleLambdaThreads();
        
        // Example 3: ExecutorService for Background Tasks
        executorServiceBackground();
        
        // Example 4: Real-world Background Processing
        realWorldBackgroundProcessing();
    }
    
    // ===========================================
    // EXAMPLE 1: SIMPLE LAMBDA THREAD
    // ===========================================
    
    public static void simpleLambdaThread() {
        System.out.println("1. SIMPLE LAMBDA THREAD");
        System.out.println("=======================");
        
        // Create a thread using lambda expression
        Thread myThread = new Thread(() -> {
            System.out.println("Hello from lambda thread!");
            System.out.println("Thread name: " + Thread.currentThread().getName());
        });
        
        // Start the thread
        myThread.start();
        
        // Wait for thread to finish
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Simple lambda thread completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 2: MULTIPLE LAMBDA THREADS
    // ===========================================
    
    public static void multipleLambdaThreads() {
        System.out.println("2. MULTIPLE LAMBDA THREADS");
        System.out.println("==========================");
        
        // Create multiple threads using lambda
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread 1: Count " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread 2: Count " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        // Start both threads
        thread1.start();
        thread2.start();
        
        // Wait for both to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Multiple lambda threads completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 3: EXECUTOR SERVICE BACKGROUND
    // ===========================================
    
    public static void executorServiceBackground() {
        System.out.println("3. EXECUTOR SERVICE BACKGROUND PROCESSING");
        System.out.println("==========================================");
        
        // Create a thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit tasks to run in background
        executor.submit(() -> {
            System.out.println("Background Task 1: Processing data...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Background Task 1: Completed!");
        });
        
        executor.submit(() -> {
            System.out.println("Background Task 2: Sending emails...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Background Task 2: Completed!");
        });
        
        // Main thread continues doing other work
        System.out.println("Main thread: Doing other work while background tasks run...");
        
        // Shutdown executor when done
        executor.shutdown();
        
        // Wait for all tasks to complete
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Executor service background processing completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 4: REAL-WORLD BACKGROUND PROCESSING
    // ===========================================
    
    public static void realWorldBackgroundProcessing() {
        System.out.println("4. REAL-WORLD BACKGROUND PROCESSING");
        System.out.println("===================================");
        
        // Create executor service
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Simulate different background tasks
        System.out.println("Starting background tasks...");
        
        // Task 1: File processing
        executor.submit(() -> {
            System.out.println("📁 Processing files in background...");
            for (int i = 1; i <= 5; i++) {
                System.out.println("   Processing file " + i + ".txt");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("✅ File processing completed!");
        });
        
        // Task 2: Database operations
        executor.submit(() -> {
            System.out.println("🗄️ Database operations in background...");
            try {
                Thread.sleep(1000);
                System.out.println("   Inserting user data...");
                Thread.sleep(500);
                System.out.println("   Updating records...");
                Thread.sleep(500);
                System.out.println("✅ Database operations completed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // Task 3: API calls
        executor.submit(() -> {
            System.out.println("🌐 API calls in background...");
            try {
                Thread.sleep(800);
                System.out.println("   Calling external API...");
                Thread.sleep(600);
                System.out.println("   Processing API response...");
                Thread.sleep(400);
                System.out.println("✅ API calls completed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // Main thread continues with other work
        System.out.println("Main thread: User interface remains responsive!");
        System.out.println("Main thread: Handling user interactions...");
        
        // Shutdown and wait
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("All background processing completed!\n");
    }
}

/*
 * ===========================================
 * QUICK REFERENCE GUIDE
 * ===========================================
 * 
 * 1. CREATE THREAD WITH LAMBDA:
 *    Thread t = new Thread(() -> {
 *        // Your code here
 *    });
 *    t.start();
 * 
 * 2. CREATE EXECUTOR SERVICE:
 *    ExecutorService executor = Executors.newFixedThreadPool(3);
 * 
 * 3. SUBMIT TASK TO EXECUTOR:
 *    executor.submit(() -> {
 *        // Your background task
 *    });
 * 
 * 4. SHUTDOWN EXECUTOR:
 *    executor.shutdown();
 *    executor.awaitTermination(10, TimeUnit.SECONDS);
 * 
 * 5. WAIT FOR THREAD TO FINISH:
 *    thread.join();
 * 
 * 6. COMMON EXECUTOR TYPES:
 *    - newFixedThreadPool(n): Fixed number of threads
 *    - newCachedThreadPool(): Creates threads as needed
 *    - newSingleThreadExecutor(): Only one thread
 * 
 * 7. WHEN TO USE THREADS:
 *    - Background processing
 *    - Long-running tasks
 *    - Multiple operations at once
 *    - Keeping UI responsive
 * 
 * 8. WHEN TO USE EXECUTOR SERVICE:
 *    - Managing multiple threads
 *    - Background processing
 *    - Better resource management
 *    - Easy thread lifecycle control
 */



