import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;

/**
 * PRACTICAL MULTITHREADING EXAMPLES
 * =================================
 * 
 * This file shows real-world multithreading scenarios:
 * 1. Parallel processing
 * 2. Producer-Consumer pattern
 * 3. Task coordination
 * 4. Error handling in multithreading
 */

public class PracticalMultithreading {
    
    public static void main(String[] args) {
        System.out.println("=== PRACTICAL MULTITHREADING EXAMPLES ===\n");
        
        // Example 1: Parallel Data Processing
        parallelDataProcessing();
        
        // Example 2: Producer-Consumer Pattern
        producerConsumerExample();
        
        // Example 3: Task Coordination with CompletableFuture
        taskCoordinationExample();
        
        // Example 4: Error Handling in Multithreading
        errorHandlingExample();
    }
    
    // ===========================================
    // EXAMPLE 1: PARALLEL DATA PROCESSING
    // ===========================================
    
    public static void parallelDataProcessing() {
        System.out.println("1. PARALLEL DATA PROCESSING");
        System.out.println("===========================");
        
        // Create a list of numbers to process
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }
        
        // Process numbers in parallel using ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(4);
        AtomicInteger totalSum = new AtomicInteger(0);
        
        // Divide work among threads
        int chunkSize = numbers.size() / 4;
        
        for (int i = 0; i < 4; i++) {
            final int start = i * chunkSize;
            final int end = (i == 3) ? numbers.size() : (i + 1) * chunkSize;
            
            executor.submit(() -> {
                int localSum = 0;
                for (int j = start; j < end; j++) {
                    localSum += numbers.get(j);
                    System.out.println("Thread " + Thread.currentThread().getName() + 
                                     " processing number: " + numbers.get(j));
                }
                totalSum.addAndGet(localSum);
                System.out.println("Thread " + Thread.currentThread().getName() + 
                                 " local sum: " + localSum);
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Total sum: " + totalSum.get());
        System.out.println("Parallel data processing completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 2: PRODUCER-CONSUMER PATTERN
    // ===========================================
    
    public static void producerConsumerExample() {
        System.out.println("2. PRODUCER-CONSUMER PATTERN");
        System.out.println("============================");
        
        // Create a shared queue
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        
        // Create producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String item = "Item-" + i;
                    queue.put(item);
                    System.out.println("Produced: " + item);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // Create consumer
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String item = queue.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // Start producer and consumer
        producer.start();
        consumer.start();
        
        // Wait for completion
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Producer-Consumer example completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 3: TASK COORDINATION WITH COMPLETABLEFUTURE
    // ===========================================
    
    public static void taskCoordinationExample() {
        System.out.println("3. TASK COORDINATION WITH COMPLETABLEFUTURE");
        System.out.println("===========================================");
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Create independent tasks
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1: Fetching user data...");
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            return "User data fetched";
        }, executor);
        
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2: Fetching product data...");
            try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
            return "Product data fetched";
        }, executor);
        
        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 3: Fetching order data...");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            return "Order data fetched";
        }, executor);
        
        // Wait for all tasks to complete
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        
        // Process results when all tasks are done
        allTasks.thenRun(() -> {
            try {
                System.out.println("All tasks completed!");
                System.out.println("Result 1: " + task1.get());
                System.out.println("Result 2: " + task2.get());
                System.out.println("Result 3: " + task3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        
        // Wait for completion
        try {
            allTasks.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
        System.out.println("Task coordination example completed!\n");
    }
    
    // ===========================================
    // EXAMPLE 4: ERROR HANDLING IN MULTITHREADING
    // ===========================================
    
    public static void errorHandlingExample() {
        System.out.println("4. ERROR HANDLING IN MULTITHREADING");
        System.out.println("===================================");
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Create tasks with potential errors
        Future<String> task1 = executor.submit(() -> {
            System.out.println("Task 1: Processing data...");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            return "Task 1 completed successfully";
        });
        
        Future<String> task2 = executor.submit(() -> {
            System.out.println("Task 2: Processing data...");
            try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
            // Simulate an error
            throw new RuntimeException("Task 2 failed!");
        });
        
        Future<String> task3 = executor.submit(() -> {
            System.out.println("Task 3: Processing data...");
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            return "Task 3 completed successfully";
        });
        
        // Handle results and errors
        try {
            System.out.println("Result 1: " + task1.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task 1 failed: " + e.getCause().getMessage());
        }
        
        try {
            System.out.println("Result 2: " + task2.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task 2 failed: " + e.getCause().getMessage());
        }
        
        try {
            System.out.println("Result 3: " + task3.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task 3 failed: " + e.getCause().getMessage());
        }
        
        executor.shutdown();
        System.out.println("Error handling example completed!\n");
    }
}

/*
 * ===========================================
 * MULTITHREADING BEST PRACTICES
 * ===========================================
 * 
 * 1. THREAD SAFETY:
 *    - Use synchronized blocks/methods when needed
 *    - Use Atomic classes for simple operations
 *    - Use Concurrent collections (ConcurrentHashMap, etc.)
 * 
 * 2. RESOURCE MANAGEMENT:
 *    - Always shutdown ExecutorService
 *    - Use try-with-resources when possible
 *    - Set appropriate timeouts
 * 
 * 3. ERROR HANDLING:
 *    - Always handle InterruptedException
 *    - Use try-catch for ExecutionException
 *    - Implement proper logging
 * 
 * 4. PERFORMANCE CONSIDERATIONS:
 *    - Choose appropriate thread pool size
 *    - Avoid creating too many threads
 *    - Use CompletableFuture for complex coordination
 * 
 * 5. COMMON PATTERNS:
 *    - Producer-Consumer for data processing
 *    - Master-Worker for parallel computation
 *    - Pipeline for sequential processing
 * 
 * 6. DEBUGGING TIPS:
 *    - Use meaningful thread names
 *    - Add logging for thread activities
 *    - Use thread dumps for deadlock detection
 */



