import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HASHMAP COMPREHENSIVE SUMMARY AND BEST PRACTICES
 * ================================================
 * 
 * This file provides a complete summary of HashMap internals
 * and best practices for using HashMap effectively.
 */

public class HashMapSummaryAndBestPractices {
    
    public static void main(String[] args) {
        System.out.println("=== HASHMAP COMPREHENSIVE SUMMARY ===\n");
        
        summarizeInternalWorking();
        explainKeyConcepts();
        provideBestPractices();
        commonMistakesAndSolutions();
        advancedTopics();
    }
    
    /**
     * Complete summary of HashMap internal working
     */
    public static void summarizeInternalWorking() {
        System.out.println("1. HASHMAP INTERNAL WORKING SUMMARY");
        System.out.println("===================================");
        
        System.out.println("""
            HashMap Internal Architecture:
            
            ┌─────────────────────────────────────────────────────────┐
            │                    HashMap Instance                      │
            ├─────────────────────────────────────────────────────────┤
            │  Node<K,V>[] table     │  int size                     │
            │  float loadFactor      │  int threshold                │
            │  int capacity          │  int modCount                 │
            └─────────────────────────────────────────────────────────┘
            
            Key Components:
            1. Bucket Array (Node<K,V>[] table)
               - Array of buckets where key-value pairs are stored
               - Each bucket can contain null, single node, or collision chain
               
            2. Hash Function
               - Uses key.hashCode() to generate hash code
               - Calculates index: hash & (capacity - 1)
               - Distributes keys evenly across buckets
               
            3. Collision Resolution
               - Java 7: Linked list in each bucket
               - Java 8+: Linked list → Red-Black tree (if >8 collisions)
               - Tree conversion improves worst-case performance
               
            4. Load Factor & Resizing
               - Default load factor: 0.75
               - Resize threshold: capacity * load factor
               - Resizing: double capacity and rehash all elements
            """);
        
        System.out.println();
    }
    
    /**
     * Explain key concepts in detail
     */
    public static void explainKeyConcepts() {
        System.out.println("2. KEY CONCEPTS EXPLAINED");
        System.out.println("=========================");
        
        System.out.println("""
            A. HASH FUNCTION:
               - Converts key to integer (hash code)
               - Should distribute keys uniformly
               - Formula: index = hashCode() & (capacity - 1)
               - Uses bitwise AND for efficient modulo operation
               
            B. COLLISION HANDLING:
               - When two keys have same hash code
               - Java uses separate chaining (linked list/tree)
               - Tree conversion threshold: 8 elements
               - Tree improves search from O(n) to O(log n)
               
            C. LOAD FACTOR:
               - Ratio of elements to capacity
               - Default: 0.75 (75% full)
               - Higher load factor: less memory, more collisions
               - Lower load factor: more memory, fewer collisions
               
            D. RESIZING (REHASHING):
               - Triggered when size > threshold
               - New capacity = old capacity * 2
               - All elements rehashed and redistributed
               - Expensive operation: O(n) time complexity
               
            E. EQUALS AND HASHCODE CONTRACT:
               - If a.equals(b), then a.hashCode() == b.hashCode()
               - If a.hashCode() == b.hashCode(), a may or may not equal b
               - Violating contract breaks HashMap functionality
            """);
        
        System.out.println();
    }
    
    /**
     * Best practices for using HashMap
     */
    public static void provideBestPractices() {
        System.out.println("3. BEST PRACTICES");
        System.out.println("================");
        
        System.out.println("""
            A. CUSTOM OBJECTS AS KEYS:
               ✅ Always override equals() and hashCode()
               ✅ Use Objects.hash() for hashCode implementation
               ✅ Make objects immutable if possible
               ❌ Don't modify key objects after adding to HashMap
               
            B. PERFORMANCE OPTIMIZATION:
               ✅ Set appropriate initial capacity if known
               ✅ Use appropriate load factor for your use case
               ✅ Prefer containsKey() over containsValue()
               ✅ Use entrySet() for iterating over key-value pairs
               
            C. MEMORY MANAGEMENT:
               ✅ Remove unused entries to prevent memory leaks
               ✅ Use WeakHashMap for cache-like scenarios
               ✅ Consider ConcurrentHashMap for thread safety
               
            D. THREAD SAFETY:
               ✅ Use ConcurrentHashMap for concurrent access
               ✅ Use Collections.synchronizedMap() for synchronization
               ❌ HashMap is NOT thread-safe
               
            E. NULL HANDLING:
               ✅ HashMap allows one null key and multiple null values
               ✅ Check for null keys if using custom objects
               ❌ Don't rely on null keys in concurrent scenarios
            """);
        
        // Practical examples
        demonstrateBestPractices();
        
        System.out.println();
    }
    
    /**
     * Demonstrate best practices with code examples
     */
    public static void demonstrateBestPractices() {
        System.out.println("\nBest Practices Examples:");
        
        // 1. Proper equals and hashCode implementation
        System.out.println("1. Proper equals and hashCode:");
        Employee emp1 = new Employee("John", 101);
        Employee emp2 = new Employee("John", 101);
        System.out.println("emp1.equals(emp2): " + emp1.equals(emp2));
        System.out.println("emp1.hashCode() == emp2.hashCode(): " + (emp1.hashCode() == emp2.hashCode()));
        
        // 2. Setting initial capacity
        System.out.println("\n2. Setting initial capacity:");
        HashMap<String, Integer> optimizedMap = new HashMap<>(1000, 0.75f);
        System.out.println("Created HashMap with initial capacity 1000");
        
        // 3. Using entrySet for iteration
        System.out.println("\n3. Efficient iteration:");
        HashMap<String, String> demoMap = new HashMap<>();
        demoMap.put("A", "Apple");
        demoMap.put("B", "Banana");
        demoMap.put("C", "Cherry");
        
        System.out.println("Using entrySet (recommended):");
        for (Map.Entry<String, String> entry : demoMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        System.out.println("Using keySet (less efficient):");
        for (String key : demoMap.keySet()) {
            System.out.println("  " + key + " -> " + demoMap.get(key));
        }
    }
    
    /**
     * Common mistakes and their solutions
     */
    public static void commonMistakesAndSolutions() {
        System.out.println("4. COMMON MISTAKES AND SOLUTIONS");
        System.out.println("===============================");
        
        System.out.println("""
            MISTAKE 1: Not overriding equals() and hashCode()
            Problem:   HashMap won't work correctly with custom objects
            Solution:  Always override both methods consistently
            
            MISTAKE 2: Modifying key objects after adding to HashMap
            Problem:   HashMap becomes inconsistent, keys may be lost
            Solution:  Make key objects immutable or don't modify them
            
            MISTAKE 3: Using HashMap in multi-threaded environment
            Problem:   Data corruption, infinite loops, exceptions
            Solution:  Use ConcurrentHashMap or proper synchronization
            
            MISTAKE 4: Not handling null keys properly
            Problem:   NullPointerException in custom hashCode() methods
            Solution:  Check for null in hashCode() and equals() methods
            
            MISTAKE 5: Using containsValue() for frequent lookups
            Problem:   O(n) time complexity, poor performance
            Solution:  Use containsKey() or maintain reverse mapping
            
            MISTAKE 6: Not considering memory usage
            Problem:   Memory leaks, excessive memory consumption
            Solution:  Remove unused entries, use appropriate load factor
            """);
        
        System.out.println();
    }
    
    /**
     * Advanced topics and optimizations
     */
    public static void advancedTopics() {
        System.out.println("5. ADVANCED TOPICS");
        System.out.println("==================");
        
        System.out.println("""
            A. HASHMAP OPTIMIZATION TECHNIQUES:
               - Custom hash functions for better distribution
               - Tuning load factor for specific use cases
               - Using specialized Map implementations
               
            B. MEMORY OPTIMIZATION:
               - WeakHashMap for automatic cleanup
               - IdentityHashMap for identity-based comparison
               - EnumMap for enum keys (more efficient)
               
            C. PERFORMANCE MONITORING:
               - Monitor collision rates
               - Track resizing frequency
               - Profile memory usage patterns
               
            D. ALTERNATIVE IMPLEMENTATIONS:
               - LinkedHashMap: maintains insertion order
               - TreeMap: sorted order, O(log n) operations
               - ConcurrentHashMap: thread-safe, better performance
               
            E. JAVA 8+ IMPROVEMENTS:
               - Red-Black tree for collision resolution
               - Better hash function distribution
               - Improved performance for high collision scenarios
            """);
        
        System.out.println();
        
        // Final summary
        System.out.println("6. FINAL SUMMARY");
        System.out.println("================");
        System.out.println("""
            HashMap is a powerful data structure that provides:
            • O(1) average time complexity for basic operations
            • Efficient key-value storage and retrieval
            • Automatic resizing and collision handling
            • Flexible and easy-to-use API
            
            Key takeaways:
            • Understand hash function and collision resolution
            • Always implement equals() and hashCode() correctly
            • Choose appropriate initial capacity and load factor
            • Use thread-safe alternatives for concurrent access
            • Monitor performance and optimize as needed
            
            HashMap is the go-to choice for most key-value storage needs
            in Java applications due to its excellent performance
            characteristics and ease of use.
            """);
    }
}

/**
 * Example of proper Employee class with correct equals and hashCode
 */
class Employee {
    private final String name;
    private final int id;
    
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id && Objects.equals(name, employee.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + "}";
    }
}
