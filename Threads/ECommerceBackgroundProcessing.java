import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * REAL-WORLD E-COMMERCE BACKGROUND PROCESSING SYSTEM
 * ===================================================
 * 
 * 🎯 MARKET SCENARIO: E-commerce Platform Background Processing
 * 
 * This example simulates a real e-commerce platform that needs to handle:
 * 1. Order Processing (Single Thread)
 * 2. Multiple Customer Orders (Multiple Threads)
 * 3. Background Tasks (ExecutorService)
 * 4. Different Types of Background Operations
 * 
 * 🏪 REAL-WORLD USE CASES:
 * - Amazon order processing
 * - Payment gateway integration
 * - Inventory management
 * - Email notifications
 * - Analytics and reporting
 * - Image processing for products
 * - Database operations
 * - API integrations
 */

public class ECommerceBackgroundProcessing {
    
    // Shared data structures for the e-commerce system
    private static final AtomicInteger orderCounter = new AtomicInteger(1);
    private static final AtomicInteger totalRevenue = new AtomicInteger(0);
    private static final List<String> processedOrders = new ArrayList<>();
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        System.out.println("🏪 === E-COMMERCE BACKGROUND PROCESSING SYSTEM === 🏪\n");
        
        // Run all examples
        singleOrderProcessing();
        multipleOrdersProcessing();
        allExecutorServiceTypes();
        comprehensiveBackgroundSystem();
    }
    
    // ===========================================
    // 1. SINGLE ORDER PROCESSING (Single Thread)
    // ===========================================
    
    /**
     * 🎯 REAL-WORLD SCENARIO: Processing a single customer order
     * 
     * This represents a simple order processing system where:
     * - Customer places an order
     * - System processes the order step by step
     * - Each step takes time (simulated with sleep)
     * - Order is completed and customer is notified
     */
    public static void singleOrderProcessing() {
        System.out.println("1. 📦 SINGLE ORDER PROCESSING (Single Thread)");
        System.out.println("==============================================");
        
        // Create a single thread to process one order
        Thread orderProcessor = new Thread(() -> {
            int orderId = orderCounter.getAndIncrement();
            System.out.println("🛒 Processing Order #" + orderId + " for Customer");
            
            try {
                // Step 1: Validate order
                System.out.println("Validating order details...");
                Thread.sleep(1000);
                
                // Step 2: Check inventory
                System.out.println("Checking product availability...");
                Thread.sleep(800);
                
                // Step 3: Process payment
                System.out.println("Processing payment...");
                Thread.sleep(1200);
                
                // Step 4: Update inventory
                System.out.println("Updating inventory...");
                Thread.sleep(600);
                
                // Step 5: Generate shipping label
                System.out.println("Generating shipping label...");
                Thread.sleep(900);
                
                // Step 6: Send confirmation email
                System.out.println("Sending order confirmation...");
                Thread.sleep(500);
                
                System.out.println("Order #" + orderId + " processed successfully!");
                processedOrders.add("Order #" + orderId);
                
            } catch (InterruptedException e) {
                System.out.println(" Order processing interrupted!");
                e.printStackTrace();
            }
        });
        
        // Start processing the order
        orderProcessor.start();
        
        // Wait for order to complete
        try {
            orderProcessor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Single order processing completed!\n");
    }
    
    // ===========================================
    // 2. MULTIPLE ORDERS PROCESSING (Multiple Threads)
    // ===========================================
    
    /**
     * 🎯 REAL-WORLD SCENARIO: Black Friday Sale - Multiple customers placing orders simultaneously
     * 
     * This represents a high-traffic scenario where:
     * - Multiple customers place orders at the same time
     * - Each order is processed in parallel
     * - System handles multiple orders simultaneously
     * - Each thread represents a different customer order
     */
    public static void multipleOrdersProcessing() {
        System.out.println("2.  MULTIPLE ORDERS PROCESSING (Multiple Threads)");
        System.out.println("=================================================");
        System.out.println(" Black Friday Sale - Multiple customers ordering simultaneously!\n");
        
        // Create multiple threads for different customer orders
        Thread[] customerThreads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            final int customerId = i + 1;
            customerThreads[i] = new Thread(() -> {
                int orderId = orderCounter.getAndIncrement();
                int orderValue = random.nextInt(500) + 50; // Random order value $50-$550
                
                System.out.println("👤 Customer " + customerId + " placing Order #" + orderId + " ($" + orderValue + ")");
                
                try {
                    // Simulate order processing time (varies per customer)
                    int processingTime = random.nextInt(2000) + 1000; // 1-3 seconds
                    Thread.sleep(processingTime);
                    
                    // Update revenue
                    totalRevenue.addAndGet(orderValue);
                    processedOrders.add("Order #" + orderId + " - Customer " + customerId);
                    
                    System.out.println("Order #" + orderId + " completed for Customer " + customerId + " ($" + orderValue + ")");
                    
                } catch (InterruptedException e) {
                    System.out.println(" Order processing interrupted for Customer " + customerId);
                    e.printStackTrace();
                }
            });
        }
        
        // Start all customer threads simultaneously
        System.out.println("🚀 Starting parallel order processing...");
        for (Thread thread : customerThreads) {
            thread.start();
        }
        
        // Wait for all orders to complete
        try {
            for (Thread thread : customerThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n💰 Total Revenue: $" + totalRevenue.get());
        System.out.println("📊 Total Orders Processed: " + processedOrders.size());
        System.out.println("Multiple orders processing completed!\n");
    }
    
    // ===========================================
    // 3. ALL EXECUTOR SERVICE TYPES
    // ===========================================
    
    /**
     * 🎯 REAL-WORLD SCENARIO: Different types of background operations in e-commerce
     * 
     * This demonstrates all ExecutorService types:
     * 1. Fixed Thread Pool - For consistent workload (payment processing)
     * 2. Cached Thread Pool - For variable workload (customer support)
     * 3. Single Thread Executor - For sequential operations (inventory updates)
     * 4. Scheduled Thread Pool - For periodic tasks (reports, backups)
     */
    public static void allExecutorServiceTypes() {
        System.out.println("3. 🔧 ALL EXECUTOR SERVICE TYPES DEMONSTRATION");
        System.out.println("=============================================");
        
        // 1. FIXED THREAD POOL - Payment Processing
        System.out.println("💳 1. FIXED THREAD POOL - Payment Processing");
        System.out.println("   (Consistent workload, 3 payment processors)");
        ExecutorService paymentProcessor = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 6; i++) {
            final int paymentId = i;
            paymentProcessor.submit(() -> {
                System.out.println("   💳 Processing Payment #" + paymentId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                    System.out.println("   ✅ Payment #" + paymentId + " completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 2. CACHED THREAD POOL - Customer Support
        System.out.println("\n🎧 2. CACHED THREAD POOL - Customer Support");
        System.out.println("   (Variable workload, creates threads as needed)");
        ExecutorService customerSupport = Executors.newCachedThreadPool();
        
        for (int i = 1; i <= 4; i++) {
            final int supportTicket = i;
            customerSupport.submit(() -> {
                System.out.println("   🎧 Handling Support Ticket #" + supportTicket + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(800);
                    System.out.println("   ✅ Support Ticket #" + supportTicket + " resolved");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 3. SINGLE THREAD EXECUTOR - Inventory Management
        System.out.println("\n📊 3. SINGLE THREAD EXECUTOR - Inventory Management");
        System.out.println("   (Sequential operations, prevents race conditions)");
        ExecutorService inventoryManager = Executors.newSingleThreadExecutor();
        
        for (int i = 1; i <= 3; i++) {
            final int inventoryUpdate = i;
            inventoryManager.submit(() -> {
                System.out.println("   📊 Updating Inventory #" + inventoryUpdate + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(600);
                    System.out.println("   ✅ Inventory #" + inventoryUpdate + " updated");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 4. SCHEDULED THREAD POOL - Periodic Tasks
        System.out.println("\n⏰ 4. SCHEDULED THREAD POOL - Periodic Tasks");
        System.out.println("   (Scheduled operations, reports and backups)");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        
        // Schedule a task to run after 2 seconds
        scheduler.schedule(() -> {
            System.out.println("   📈 Daily Sales Report generated on " + Thread.currentThread().getName());
        }, 2, TimeUnit.SECONDS);
        
        // Schedule a task to run every 3 seconds (for demo purposes)
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("   💾 Database backup completed on " + Thread.currentThread().getName());
        }, 1, 3, TimeUnit.SECONDS);
        
        // Shutdown all executors
        paymentProcessor.shutdown();
        customerSupport.shutdown();
        inventoryManager.shutdown();
        
        // Wait for completion
        try {
            paymentProcessor.awaitTermination(10, TimeUnit.SECONDS);
            customerSupport.awaitTermination(10, TimeUnit.SECONDS);
            inventoryManager.awaitTermination(10, TimeUnit.SECONDS);
            
            // Let scheduled tasks run for a bit
            Thread.sleep(8000);
            scheduler.shutdown();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nAll ExecutorService types demonstration completed!\n");
    }
    
    // ===========================================
    // 4. COMPREHENSIVE BACKGROUND SYSTEM
    // ===========================================
    
    /**
     * 🎯 REAL-WORLD SCENARIO: Complete E-commerce Background Processing System
     * 
     * This simulates a real e-commerce platform with multiple background services:
     * - Order Processing Pipeline
     * - Payment Gateway Integration
     * - Inventory Management
     * - Email Notifications
     * - Analytics and Reporting
     * - Image Processing
     * - Database Operations
     * - API Integrations
     */
    public static void comprehensiveBackgroundSystem() {
        System.out.println("4. 🏪 COMPREHENSIVE E-COMMERCE BACKGROUND SYSTEM");
        System.out.println("================================================");
        System.out.println("🚀 Starting complete e-commerce background processing system...\n");
        
        // Create different thread pools for different types of work
        ExecutorService orderProcessor = Executors.newFixedThreadPool(4);
        ExecutorService paymentGateway = Executors.newFixedThreadPool(2);
        ExecutorService inventorySystem = Executors.newFixedThreadPool(3);
        ExecutorService notificationService = Executors.newCachedThreadPool();
        ExecutorService analyticsEngine = Executors.newSingleThreadExecutor();
        ExecutorService imageProcessor = Executors.newFixedThreadPool(2);
        ExecutorService databaseOperations = Executors.newFixedThreadPool(3);
        ExecutorService apiIntegrations = Executors.newCachedThreadPool();
        
        // 1. ORDER PROCESSING PIPELINE
        System.out.println("📦 1. ORDER PROCESSING PIPELINE");
        for (int i = 1; i <= 8; i++) {
            final int orderId = i;
            orderProcessor.submit(() -> {
                System.out.println("   📦 Processing Order #" + orderId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                    System.out.println("   ✅ Order #" + orderId + " processed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 2. PAYMENT GATEWAY INTEGRATION
        System.out.println("\n💳 2. PAYMENT GATEWAY INTEGRATION");
        for (int i = 1; i <= 4; i++) {
            final int paymentId = i;
            paymentGateway.submit(() -> {
                System.out.println("   💳 Processing Payment #" + paymentId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1200);
                    System.out.println("   ✅ Payment #" + paymentId + " completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 3. INVENTORY MANAGEMENT
        System.out.println("\n📊 3. INVENTORY MANAGEMENT");
        for (int i = 1; i <= 6; i++) {
            final int inventoryId = i;
            inventorySystem.submit(() -> {
                System.out.println("   📊 Updating Inventory #" + inventoryId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(800);
                    System.out.println("   ✅ Inventory #" + inventoryId + " updated");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 4. EMAIL NOTIFICATIONS
        System.out.println("\n📧 4. EMAIL NOTIFICATIONS");
        for (int i = 1; i <= 5; i++) {
            final int emailId = i;
            notificationService.submit(() -> {
                System.out.println("   📧 Sending Email #" + emailId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(600);
                    System.out.println("   ✅ Email #" + emailId + " sent");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 5. ANALYTICS AND REPORTING
        System.out.println("\n📈 5. ANALYTICS AND REPORTING");
        analyticsEngine.submit(() -> {
            System.out.println("   📈 Generating Sales Analytics on " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
                System.out.println("   ✅ Sales Analytics generated");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // 6. IMAGE PROCESSING
        System.out.println("\n🖼️ 6. IMAGE PROCESSING");
        for (int i = 1; i <= 4; i++) {
            final int imageId = i;
            imageProcessor.submit(() -> {
                System.out.println("   🖼️ Processing Product Image #" + imageId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1500);
                    System.out.println("   ✅ Image #" + imageId + " processed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 7. DATABASE OPERATIONS
        System.out.println("\n🗄️ 7. DATABASE OPERATIONS");
        for (int i = 1; i <= 6; i++) {
            final int dbOpId = i;
            databaseOperations.submit(() -> {
                System.out.println("   🗄️ Database Operation #" + dbOpId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(900);
                    System.out.println("   ✅ Database Operation #" + dbOpId + " completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 8. API INTEGRATIONS
        System.out.println("\n🌐 8. API INTEGRATIONS");
        for (int i = 1; i <= 3; i++) {
            final int apiId = i;
            apiIntegrations.submit(() -> {
                System.out.println("   🌐 API Integration #" + apiId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1100);
                    System.out.println("   ✅ API Integration #" + apiId + " completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // Main thread continues with other work
        System.out.println("\n🎯 Main Thread: E-commerce platform remains responsive!");
        System.out.println("🎯 Main Thread: Handling user interactions and UI updates...");
        
        // Shutdown all executors
        orderProcessor.shutdown();
        paymentGateway.shutdown();
        inventorySystem.shutdown();
        notificationService.shutdown();
        analyticsEngine.shutdown();
        imageProcessor.shutdown();
        databaseOperations.shutdown();
        apiIntegrations.shutdown();
        
        // Wait for all background operations to complete
        try {
            orderProcessor.awaitTermination(15, TimeUnit.SECONDS);
            paymentGateway.awaitTermination(15, TimeUnit.SECONDS);
            inventorySystem.awaitTermination(15, TimeUnit.SECONDS);
            notificationService.awaitTermination(15, TimeUnit.SECONDS);
            analyticsEngine.awaitTermination(15, TimeUnit.SECONDS);
            imageProcessor.awaitTermination(15, TimeUnit.SECONDS);
            databaseOperations.awaitTermination(15, TimeUnit.SECONDS);
            apiIntegrations.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n🎉 COMPREHENSIVE E-COMMERCE BACKGROUND SYSTEM COMPLETED!");
        System.out.println("📊 System Statistics:");
        System.out.println("   - Total Orders Processed: " + processedOrders.size());
        System.out.println("   - Total Revenue: $" + totalRevenue.get());
        System.out.println("   - All background services completed successfully!");
    }
}

/*
 * ===========================================
 * 🏪 REAL-WORLD E-COMMERCE THREADING PATTERNS
 * ===========================================
 * 
 * 🎯 COMMON E-COMMERCE SCENARIOS:
 * 
 * 1. SINGLE THREAD USE CASES:
 *    - Order validation
 *    - Payment processing
 *    - Inventory updates
 *    - User authentication
 * 
 * 2. MULTIPLE THREADS USE CASES:
 *    - Multiple customer orders
 *    - Parallel product searches
 *    - Concurrent user sessions
 *    - Simultaneous API calls
 * 
 * 3. EXECUTOR SERVICE PATTERNS:
 * 
 *    a) FIXED THREAD POOL:
 *       - Payment processing (consistent workload)
 *       - Order processing (predictable capacity)
 *       - Image processing (CPU-intensive tasks)
 * 
 *    b) CACHED THREAD POOL:
 *       - Customer support (variable workload)
 *       - API integrations (sporadic requests)
 *       - Email notifications (burst traffic)
 * 
 *    c) SINGLE THREAD EXECUTOR:
 *       - Inventory management (sequential updates)
 *       - Analytics generation (ordered processing)
 *       - Database migrations (one at a time)
 * 
 *    d) SCHEDULED THREAD POOL:
 *       - Daily reports
 *       - Database backups
 *       - Cache invalidation
 *       - Health checks
 * 
 * 🚀 PERFORMANCE BENEFITS:
 * 
 * 1. RESPONSIVENESS:
 *    - UI remains responsive during heavy operations
 *    - Users can continue shopping while orders process
 *    - Real-time updates without blocking
 * 
 * 2. SCALABILITY:
 *    - Handle multiple customers simultaneously
 *    - Process orders in parallel
 *    - Scale with demand
 * 
 * 3. EFFICIENCY:
 *    - Better resource utilization
 *    - Reduced waiting times
 *    - Improved throughput
 * 
 * 💡 BEST PRACTICES FOR E-COMMERCE:
 * 
 * 1. THREAD POOL SIZING:
 *    - Payment processing: 2-4 threads
 *    - Order processing: 4-8 threads
 *    - Image processing: 2-4 threads
 *    - API calls: Cached thread pool
 * 
 * 2. ERROR HANDLING:
 *    - Always handle InterruptedException
 *    - Implement retry mechanisms
 *    - Log all failures
 *    - Graceful degradation
 * 
 * 3. MONITORING:
 *    - Track thread pool usage
 *    - Monitor response times
 *    - Alert on failures
 *    - Performance metrics
 * 
 * 🎉 CONCLUSION:
 * This example demonstrates how real e-commerce platforms use threading
 * to handle multiple operations simultaneously while keeping the system
 * responsive and efficient. The patterns shown here are used by major
 * e-commerce platforms like Amazon, eBay, and Shopify.
 */
