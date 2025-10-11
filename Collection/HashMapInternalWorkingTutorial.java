import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;

/**
 * COMPREHENSIVE HASHMAP INTERNAL WORKING TUTORIAL
 * ===============================================
 * 
 * This tutorial explains how HashMap works internally in Java,
 * covering all the important concepts with practical examples.
 */

public class HashMapInternalWorkingTutorial {
    
    public static void main(String[] args) {
        System.out.println("=== HASHMAP INTERNAL WORKING TUTORIAL ===\n");
        
        // 1. Basic HashMap Operations
        basicHashMapOperations();
        
        // 2. Understanding Hash Codes
        understandingHashCodes();
        
        // 3. Collision Handling Demonstration
        collisionHandlingDemo();
        
        // 4. Load Factor and Resizing
        loadFactorAndResizing();
        
        // 5. Performance Analysis
        performanceAnalysis();
        
        // 6. Custom Object as Key
        customObjectAsKey();
        
        // 7. HashMap vs Other Map Implementations
        mapImplementationsComparison();
    }
    
    /**
     * 1. BASIC HASHMAP OPERATIONS
     * ==========================
     * Understanding the fundamental operations of HashMap
     */
    public static void basicHashMapOperations() {
        System.out.println("1. BASIC HASHMAP OPERATIONS");
        System.out.println("===========================");
        
        // Creating HashMap
        HashMap<String, Integer> studentMarks = new HashMap<>();
        
        // PUT operation - How it works internally:
        // 1. Calculate hash code of key using hashCode()
        // 2. Calculate index using: hash & (capacity - 1)
        // 3. Store key-value pair at calculated index
        studentMarks.put("Alice", 95);
        studentMarks.put("Bob", 87);
        studentMarks.put("Charlie", 92);
        
        System.out.println("HashMap after adding elements: " + studentMarks);
        
        // GET operation - How it works internally:
        // 1. Calculate hash code of key
        // 2. Calculate index using: hash & (capacity - 1)
        // 3. Search in bucket (array or tree) at that index
        Integer aliceMark = studentMarks.get("Alice");
        System.out.println("Alice's mark: " + aliceMark);
        
        // ContainsKey operation
        boolean hasBob = studentMarks.containsKey("Bob");
        System.out.println("Contains Bob: " + hasBob);
        
        // ContainsValue operation (slower - O(n))
        boolean hasMark95 = studentMarks.containsValue(95);
        System.out.println("Contains mark 95: " + hasMark95);
        
        System.out.println();
    }
    
    /**
     * 2. UNDERSTANDING HASH CODES
     * ===========================
     * Demonstrating how hash codes work and affect HashMap behavior
     */
    public static void understandingHashCodes() {
        System.out.println("2. UNDERSTANDING HASH CODES");
        System.out.println("===========================");
        
        // String hash codes
        String str1 = "Hello";
        String str2 = "World";
        String str3 = "Hello"; // Same as str1
        
        System.out.println("Hash code of 'Hello': " + str1.hashCode());
        System.out.println("Hash code of 'World': " + str2.hashCode());
        System.out.println("Hash code of 'Hello' (duplicate): " + str3.hashCode());
        System.out.println("str1.equals(str3): " + str1.equals(str3));
        
        // Integer hash codes
        Integer num1 = 100;
        Integer num2 = 200;
        System.out.println("Hash code of 100: " + num1.hashCode());
        System.out.println("Hash code of 200: " + num2.hashCode());
        
        // Demonstrating hash code calculation for index
        int capacity = 16;
        System.out.println("\nIndex calculation for capacity " + capacity + ":");
        System.out.println("Index for 'Hello': " + (str1.hashCode() & (capacity - 1)));
        System.out.println("Index for 'World': " + (str2.hashCode() & (capacity - 1)));
        System.out.println("Index for 100: " + (num1.hashCode() & (capacity - 1)));
        System.out.println("Index for 200: " + (num2.hashCode() & (capacity - 1)));
        
        System.out.println();
    }
    
    /**
     * 3. COLLISION HANDLING DEMONSTRATION
     * ===================================
     * Showing how HashMap handles hash collisions
     */
    public static void collisionHandlingDemo() {
        System.out.println("3. COLLISION HANDLING DEMONSTRATION");
        System.out.println("===================================");
        
        // Creating HashMap with small capacity to force collisions
        HashMap<String, String> collisionMap = new HashMap<>(4); // Small capacity
        
        // Adding elements that might cause collisions
        collisionMap.put("Aa", "Value1"); // Hash code: 2112
        collisionMap.put("BB", "Value2"); // Hash code: 2112 (same!)
        collisionMap.put("CC", "Value3"); // Different hash code
        
        System.out.println("HashMap with potential collisions: " + collisionMap);
        
        // Demonstrating collision resolution
        System.out.println("\nCollision Resolution Process:");
        System.out.println("1. Calculate hash code for key");
        System.out.println("2. Calculate index: hash & (capacity - 1)");
        System.out.println("3. If bucket is empty, store directly");
        System.out.println("4. If collision occurs:");
        System.out.println("   - Java 8+: Use linked list (up to 8 elements)");
        System.out.println("   - If >8 elements: Convert to Red-Black Tree");
        System.out.println("   - Search: O(1) for direct, O(n) for list, O(log n) for tree");
        
        // Retrieving values (demonstrates collision resolution)
        System.out.println("\nRetrieving values:");
        System.out.println("Value for 'Aa': " + collisionMap.get("Aa"));
        System.out.println("Value for 'BB': " + collisionMap.get("BB"));
        System.out.println("Value for 'CC': " + collisionMap.get("CC"));
        
        System.out.println();
    }
    
    /**
     * 4. LOAD FACTOR AND RESIZING
     * ===========================
     * Understanding HashMap resizing mechanism
     */
    public static void loadFactorAndResizing() {
        System.out.println("4. LOAD FACTOR AND RESIZING");
        System.out.println("===========================");
        
        // Default load factor is 0.75
        HashMap<String, Integer> resizeMap = new HashMap<>(16, 0.75f);
        
        System.out.println("Initial capacity: 16");
        System.out.println("Load factor: 0.75");
        System.out.println("Resize threshold: 16 * 0.75 = 12");
        System.out.println("When 12th element is added, HashMap resizes to 32");
        
        // Adding elements to demonstrate resizing
        for (int i = 1; i <= 15; i++) {
            resizeMap.put("key" + i, i);
            if (i == 12) {
                System.out.println("\nAfter adding 12th element - HashMap resizes!");
            }
        }
        
        System.out.println("Final HashMap size: " + resizeMap.size());
        System.out.println("HashMap capacity after resizing: " + getCapacity(resizeMap));
        
        System.out.println("\nResizing Process:");
        System.out.println("1. Create new array with double capacity");
        System.out.println("2. Recalculate hash codes for all existing elements");
        System.out.println("3. Redistribute elements to new buckets");
        System.out.println("4. This is called 'rehashing'");
        
        System.out.println();
    }
    
    /**
     * 5. PERFORMANCE ANALYSIS
     * =======================
     * Understanding HashMap performance characteristics
     */
    public static void performanceAnalysis() {
        System.out.println("5. PERFORMANCE ANALYSIS");
        System.out.println("=======================");
        
        HashMap<Integer, String> perfMap = new HashMap<>();
        
        // Measuring put operation
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfMap.put(i, "Value" + i);
        }
        long putTime = System.nanoTime() - startTime;
        
        // Measuring get operation
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfMap.get(i);
        }
        long getTime = System.nanoTime() - startTime;
        
        System.out.println("Performance for 10,000 operations:");
        System.out.println("Put operations time: " + putTime / 1_000_000 + " ms");
        System.out.println("Get operations time: " + getTime / 1_000_000 + " ms");
        
        System.out.println("\nTime Complexity:");
        System.out.println("• Put: O(1) average, O(log n) worst case (tree)");
        System.out.println("• Get: O(1) average, O(log n) worst case (tree)");
        System.out.println("• Remove: O(1) average, O(log n) worst case (tree)");
        System.out.println("• ContainsKey: O(1) average, O(log n) worst case (tree)");
        System.out.println("• ContainsValue: O(n) - must check all values");
        
        System.out.println();
    }
    
    /**
     * 6. CUSTOM OBJECT AS KEY
     * =======================
     * Demonstrating how to use custom objects as HashMap keys
     */
    public static void customObjectAsKey() {
        System.out.println("6. CUSTOM OBJECT AS KEY");
        System.out.println("=======================");
        
        HashMap<Student, String> studentMap = new HashMap<>();
        
        Student student1 = new Student("Alice", 101);
        Student student2 = new Student("Bob", 102);
        Student student3 = new Student("Alice", 101); // Same as student1
        
        studentMap.put(student1, "Grade A");
        studentMap.put(student2, "Grade B");
        studentMap.put(student3, "Grade A+"); // This will replace student1's value
        
        System.out.println("Student map: " + studentMap);
        System.out.println("student1.equals(student3): " + student1.equals(student3));
        System.out.println("student1.hashCode(): " + student1.hashCode());
        System.out.println("student3.hashCode(): " + student3.hashCode());
        
        System.out.println("\nImportant Rules for Custom Objects as Keys:");
        System.out.println("1. Override equals() method");
        System.out.println("2. Override hashCode() method");
        System.out.println("3. If two objects are equal, they must have same hash code");
        System.out.println("4. If two objects have same hash code, they may or may not be equal");
        
        System.out.println();
    }
    
    /**
     * 7. HASHMAP VS OTHER MAP IMPLEMENTATIONS
     * =======================================
     * Comparing HashMap with other Map implementations
     */
    public static void mapImplementationsComparison() {
        System.out.println("7. HASHMAP VS OTHER MAP IMPLEMENTATIONS");
        System.out.println("======================================");
        
        System.out.println("HashMap:");
        System.out.println("• No order guarantee");
        System.out.println("• Allows one null key, multiple null values");
        System.out.println("• Not thread-safe");
        System.out.println("• O(1) average time complexity");
        
        System.out.println("\nLinkedHashMap:");
        System.out.println("• Maintains insertion order");
        System.out.println("• Slightly slower than HashMap");
        System.out.println("• Uses doubly-linked list");
        
        System.out.println("\nTreeMap:");
        System.out.println("• Maintains sorted order");
        System.out.println("• O(log n) time complexity");
        System.out.println("• Uses Red-Black tree");
        System.out.println("• No null keys allowed");
        
        System.out.println("\nHashtable:");
        System.out.println("• Thread-safe (synchronized)");
        System.out.println("• No null keys or values");
        System.out.println("• Legacy class, use ConcurrentHashMap instead");
        
        System.out.println();
    }
    
    /**
     * Helper method to get HashMap capacity (reflection-based)
     * Note: This is for demonstration only, not recommended for production
     */
    private static int getCapacity(HashMap<?, ?> map) {
        try {
            java.lang.reflect.Field field = HashMap.class.getDeclaredField("table");
            field.setAccessible(true);
            Object[] table = (Object[]) field.get(map);
            return table == null ? 0 : table.length;
        } catch (Exception e) {
            return -1; // Unable to determine capacity
        }
    }
}

/**
 * Custom Student class demonstrating proper equals() and hashCode() implementation
 */
class Student {
    private String name;
    private int id;
    
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id && name.equals(student.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode() * 31 + id;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', id=" + id + "}";
    }
}
