/**
 * SINGLETON CLASS - EASY LEARNING TUTORIAL
 * ========================================
 * 
 * This tutorial will teach you Singleton class in the simplest way possible!
 * 
 * What is Singleton?
 * - A Singleton class can have only ONE instance (object) throughout the entire program
 * - No matter how many times you try to create an object, you'll always get the same instance
 * - It's like having only ONE manager in a company - no matter who you ask, you get the same person
 */

public class SingletonEasyTutorial {
    
    public static void main(String[] args) {
        System.out.println("=== SINGLETON CLASS - EASY LEARNING TUTORIAL ===\n");
        
        // Let's learn step by step
        whatIsSingleton();
        whyDoWeNeedSingleton();
        singletonBasicExample();
        singletonWithMethods();
        singletonRealWorldExample();
        differentSingletonTypes();
        singletonBestPractices();
    }
    
    // ===========================================
    // 1. WHAT IS SINGLETON?
    // ===========================================
    
    public static void whatIsSingleton() {
        System.out.println("1. WHAT IS SINGLETON?");
        System.out.println("=====================");
        
        System.out.println("""
            SINGLETON EXPLAINED SIMPLY:
            
            🏢 Think of it like a COMPANY MANAGER:
            - There's only ONE manager in the company
            - No matter which department you go to, you get the SAME manager
            - You can't have multiple managers
            
            💻 In Programming:
            - Singleton class can have only ONE instance (object)
            - No matter how many times you create an object, you get the SAME instance
            - It's useful when you need ONE shared resource
            
            🔑 KEY POINTS:
            ✓ Only ONE instance allowed
            ✓ Same instance returned every time
            ✓ Useful for shared resources
            ✓ Prevents multiple instances
            """);
        
        System.out.println();
    }
    
    // ===========================================
    // 2. WHY DO WE NEED SINGLETON?
    // ===========================================
    
    public static void whyDoWeNeedSingleton() {
        System.out.println("2. WHY DO WE NEED SINGLETON?");
        System.out.println("===========================");
        
        System.out.println("""
            REAL-WORLD EXAMPLES WHERE SINGLETON IS USEFUL:
            
            🗄️ DATABASE CONNECTION:
            - You don't want 100 database connections
            - You want ONE connection shared by everyone
            - Singleton ensures only ONE connection
            
            📝 LOGGING SYSTEM:
            - All parts of your program write to the SAME log file
            - You don't want multiple log files
            - Singleton ensures ONE logging system
            
            ⚙️ CONFIGURATION MANAGER:
            - Your program reads settings from ONE configuration file
            - You don't want multiple copies of settings
            - Singleton ensures ONE configuration manager
            
            🎮 GAME MANAGER:
            - In a game, you have ONE score, ONE level
            - You don't want multiple scores or levels
            - Singleton ensures ONE game state
            
            💰 BANK ACCOUNT MANAGER:
            - You have ONE bank account
            - You don't want multiple accounts for the same person
            - Singleton ensures ONE account manager
            """);
        
        System.out.println();
    }
    
    // ===========================================
    // 3. BASIC SINGLETON EXAMPLE
    // ===========================================
    
    public static void singletonBasicExample() {
        System.out.println("3. BASIC SINGLETON EXAMPLE");
        System.out.println("=========================");
        
        // Let's see what happens with a normal class first
        System.out.println("NORMAL CLASS (NOT SINGLETON):");
        NormalClass obj1 = new NormalClass();
        NormalClass obj2 = new NormalClass();
        System.out.println("obj1: " + obj1);
        System.out.println("obj2: " + obj2);
        System.out.println("Are they the same? " + (obj1 == obj2)); // false
        
        System.out.println("\nSINGLETON CLASS:");
        // Now let's see Singleton in action
        SingletonClass singleton1 = SingletonClass.getInstance();
        SingletonClass singleton2 = SingletonClass.getInstance();
        System.out.println("singleton1: " + singleton1);
        System.out.println("singleton2: " + singleton2);
        System.out.println("Are they the same? " + (singleton1 == singleton2)); // true
        
        System.out.println();
    }
    
    // ===========================================
    // 4. SINGLETON WITH METHODS
    // ===========================================
    
    public static void singletonWithMethods() {
        System.out.println("4. SINGLETON WITH METHODS");
        System.out.println("=========================");
        
        // Create singleton instances
        SingletonClass singleton1 = SingletonClass.getInstance();
        SingletonClass singleton2 = SingletonClass.getInstance();
        
        // Use methods
        singleton1.setName("John");
        singleton1.setAge(25);
        
        System.out.println("Using singleton1:");
        System.out.println("Name: " + singleton1.getName());
        System.out.println("Age: " + singleton1.getAge());
        
        System.out.println("\nUsing singleton2 (should show same data):");
        System.out.println("Name: " + singleton2.getName());
        System.out.println("Age: " + singleton2.getAge());
        
        // Change data using singleton2
        singleton2.setName("Jane");
        singleton2.setAge(30);
        
        System.out.println("\nAfter changing data using singleton2:");
        System.out.println("singleton1 Name: " + singleton1.getName());
        System.out.println("singleton1 Age: " + singleton1.getAge());
        System.out.println("singleton2 Name: " + singleton2.getName());
        System.out.println("singleton2 Age: " + singleton2.getAge());
        
        System.out.println("See? Both singleton1 and singleton2 are the SAME object!");
        System.out.println();
    }
    
    // ===========================================
    // 5. REAL-WORLD EXAMPLE
    // ===========================================
    
    public static void singletonRealWorldExample() {
        System.out.println("5. REAL-WORLD EXAMPLE - DATABASE MANAGER");
        System.out.println("========================================");
        
        // Simulate multiple parts of your program trying to connect to database
        System.out.println("Different parts of your program trying to connect to database:");
        
        DatabaseManager db1 = DatabaseManager.getInstance();
        DatabaseManager db2 = DatabaseManager.getInstance();
        DatabaseManager db3 = DatabaseManager.getInstance();
        
        System.out.println("db1: " + db1);
        System.out.println("db2: " + db2);
        System.out.println("db3: " + db3);
        
        System.out.println("\nAll are the same instance: " + (db1 == db2 && db2 == db3));
        
        // Use the database manager
        db1.connect();
        db2.executeQuery("SELECT * FROM users");
        db3.disconnect();
        
        System.out.println();
    }
    
    // ===========================================
    // 6. DIFFERENT SINGLETON TYPES
    // ===========================================
    
    public static void differentSingletonTypes() {
        System.out.println("6. DIFFERENT SINGLETON IMPLEMENTATION TYPES");
        System.out.println("==========================================");
        
        // Eager Singleton
        System.out.println("EAGER SINGLETON (created immediately):");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("eager1 == eager2: " + (eager1 == eager2));
        
        // Lazy Singleton
        System.out.println("\nLAZY SINGLETON (created only when needed):");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("lazy1 == lazy2: " + (lazy1 == lazy2));
        
        // Thread-Safe Singleton
        System.out.println("\nTHREAD-SAFE SINGLETON (safe for multiple threads):");
        ThreadSafeSingleton threadSafe1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton threadSafe2 = ThreadSafeSingleton.getInstance();
        System.out.println("threadSafe1 == threadSafe2: " + (threadSafe1 == threadSafe2));
        
        System.out.println();
    }
    
    // ===========================================
    // 7. SINGLETON BEST PRACTICES
    // ===========================================
    
    public static void singletonBestPractices() {
        System.out.println("7. SINGLETON BEST PRACTICES");
        System.out.println("==========================");
        
        System.out.println("""
            ✅ DO'S:
            - Use Singleton for shared resources (database, logging, configuration)
            - Make constructor private
            - Provide a static method to get instance
            - Consider thread safety if needed
            - Use meaningful names for your singleton
            
            ❌ DON'TS:
            - Don't use Singleton for everything
            - Don't make it too complex
            - Don't forget about thread safety in multi-threaded programs
            - Don't use Singleton for objects that should have multiple instances
            
            🎯 WHEN TO USE SINGLETON:
            ✓ Database connections
            ✓ Logging systems
            ✓ Configuration managers
            ✓ Cache managers
            ✓ Game state managers
            ✓ File system access
            
            🚫 WHEN NOT TO USE SINGLETON:
            ✗ Regular business objects
            ✗ Objects that need multiple instances
            ✗ Objects that change frequently
            ✗ Objects that are not shared resources
            """);
        
        System.out.println();
    }
}

// ===========================================
// SUPPORTING CLASSES
// ===========================================

/**
 * NORMAL CLASS (NOT SINGLETON)
 * This creates a new object every time
 */
class NormalClass {
    private String name;
    
    public NormalClass() {
        this.name = "Normal Object";
    }
    
    public String getName() {
        return name;
    }
}

/**
 * BASIC SINGLETON CLASS
 * This ensures only ONE instance exists
 */
class SingletonClass {
    // Step 1: Create a static instance variable
    private static SingletonClass instance;
    
    // Step 2: Make constructor private (so no one can create objects directly)
    private SingletonClass() {
        System.out.println("Singleton instance created!");
    }
    
    // Step 3: Provide a static method to get the instance
    public static SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass();
        }
        return instance;
    }
    
    // Add some methods to make it useful
    private String name;
    private int age;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
}

/**
 * REAL-WORLD EXAMPLE: DATABASE MANAGER
 * This simulates a database connection manager
 */
class DatabaseManager {
    private static DatabaseManager instance;
    private boolean isConnected = false;
    
    private DatabaseManager() {
        System.out.println("Database Manager created!");
    }
    
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    public void connect() {
        if (!isConnected) {
            System.out.println("Connected to database!");
            isConnected = true;
        } else {
            System.out.println("Already connected to database!");
        }
    }
    
    public void executeQuery(String query) {
        if (isConnected) {
            System.out.println("Executing query: " + query);
        } else {
            System.out.println("Not connected to database!");
        }
    }
    
    public void disconnect() {
        if (isConnected) {
            System.out.println("Disconnected from database!");
            isConnected = false;
        } else {
            System.out.println("Not connected to database!");
        }
    }
}

/**
 * EAGER SINGLETON
 * Instance is created immediately when class is loaded
 */
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    
    private EagerSingleton() {
        System.out.println("Eager Singleton created!");
    }
    
    public static EagerSingleton getInstance() {
        return instance;
    }
}

/**
 * LAZY SINGLETON
 * Instance is created only when first requested
 */
class LazySingleton {
    private static LazySingleton instance;
    
    private LazySingleton() {
        System.out.println("Lazy Singleton created!");
    }
    
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * THREAD-SAFE SINGLETON
 * Safe to use in multi-threaded programs
 */
class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {
        System.out.println("Thread-Safe Singleton created!");
    }
    
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}

/*
 * ===========================================
 * SINGLETON SUMMARY - EASY TO REMEMBER
 * ===========================================
 * 
 * 🎯 WHAT IS SINGLETON?
 * - A class that can have only ONE instance
 * - Like having only ONE manager in a company
 * 
 * 🔧 HOW TO MAKE SINGLETON?
 * 1. Make constructor private
 * 2. Create a static instance variable
 * 3. Provide a static method to get instance
 * 
 * 💡 WHEN TO USE?
 * - Database connections
 * - Logging systems
 * - Configuration managers
 * - Shared resources
 * 
 * ⚠️ WHEN NOT TO USE?
 * - Regular business objects
 * - Objects that need multiple instances
 * - Objects that change frequently
 * 
 * 🚀 BENEFITS:
 * ✓ Ensures only one instance
 * ✓ Saves memory
 * ✓ Provides global access point
 * ✓ Useful for shared resources
 * 
 * 🎓 KEY POINTS TO REMEMBER:
 * - Private constructor
 * - Static instance variable
 * - Static getInstance() method
 * - Use for shared resources only
 * - Consider thread safety if needed
 */
