import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HASHMAP STEP-BY-STEP INTERNAL WORKING DEMONSTRATION
 * ==================================================
 * 
 * This class provides hands-on examples to understand HashMap internals
 * with detailed explanations of each step.
 */

public class HashMapStepByStepDemo {
    
    public static void main(String[] args) {
        System.out.println("=== HASHMAP STEP-BY-STEP INTERNAL WORKING ===\n");
        
        demonstratePutOperation();
        demonstrateGetOperation();
        demonstrateCollisionScenario();
        demonstrateCustomHashCode();
        demonstrateEqualsAndHashCode();
        performanceComparison();
    }
    
    /**
     * Step-by-step demonstration of PUT operation
     */
    public static void demonstratePutOperation() {
        System.out.println("1. PUT OPERATION STEP-BY-STEP");
        System.out.println("=============================");
        
        HashMap<String, Integer> map = new HashMap<>();
        
        // Step 1: Calculate hash code
        String key = "Hello";
        int hashCode = key.hashCode();
        System.out.println("Step 1 - Calculate hash code:");
        System.out.println("  Key: " + key);
        System.out.println("  HashCode: " + hashCode);
        
        // Step 2: Calculate index
        int capacity = 16; // Default initial capacity
        int index = hashCode & (capacity - 1);
        System.out.println("\nStep 2 - Calculate index:");
        System.out.println("  Capacity: " + capacity);
        System.out.println("  Index = " + hashCode + " & " + (capacity - 1) + " = " + index);
        System.out.println("  Binary: " + Integer.toBinaryString(hashCode) + " & " + Integer.toBinaryString(capacity - 1));
        System.out.println("  Result: " + Integer.toBinaryString(index) + " = " + index);
        
        // Step 3: Store in HashMap
        Integer value = 42;
        map.put(key, value);
        System.out.println("\nStep 3 - Store in HashMap:");
        System.out.println("  Stored at bucket index: " + index);
        System.out.println("  HashMap: " + map);
        
        System.out.println();
    }
    
    /**
     * Step-by-step demonstration of GET operation
     */
    public static void demonstrateGetOperation() {
        System.out.println("2. GET OPERATION STEP-BY-STEP");
        System.out.println("=============================");
        
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Hello", 42);
        map.put("World", 24);
        map.put("Java", 8);
        
        String searchKey = "Hello";
        System.out.println("Searching for key: " + searchKey);
        
        // Step 1: Calculate hash code
        int hashCode = searchKey.hashCode();
        System.out.println("Step 1 - Calculate hash code: " + hashCode);
        
        // Step 2: Calculate index
        int capacity = 16;
        int index = hashCode & (capacity - 1);
        System.out.println("Step 2 - Calculate index: " + index);
        
        // Step 3: Search in bucket
        Integer result = map.get(searchKey);
        System.out.println("Step 3 - Search in bucket at index " + index);
        System.out.println("Result: " + result);
        
        System.out.println();
    }
    
    /**
     * Demonstrate collision scenario with detailed explanation
     */
    public static void demonstrateCollisionScenario() {
        System.out.println("3. COLLISION SCENARIO DEMONSTRATION");
        System.out.println("===================================");
        
        // Create HashMap with small capacity to force collisions
        HashMap<String, String> collisionMap = new HashMap<>(4);
        
        // Keys that will cause collisions
        String[] keys = {"Aa", "BB", "C#", "D$"};
        
        System.out.println("Adding elements that cause collisions:");
        for (String key : keys) {
            int hashCode = key.hashCode();
            int index = hashCode & 3; // capacity - 1 = 4 - 1 = 3
            
            System.out.println("Key: " + key + 
                             " | HashCode: " + hashCode + 
                             " | Index: " + index);
            
            collisionMap.put(key, "Value_" + key);
        }
        
        System.out.println("\nFinal HashMap: " + collisionMap);
        
        System.out.println("\nCollision Resolution Process:");
        System.out.println("1. Multiple keys map to same bucket index");
        System.out.println("2. Java uses linked list to store colliding elements");
        System.out.println("3. Search requires traversing the linked list");
        System.out.println("4. Java 8+: If >8 collisions, converts to Red-Black tree");
        
        System.out.println();
    }
    
    /**
     * Demonstrate custom hashCode implementation
     */
    public static void demonstrateCustomHashCode() {
        System.out.println("4. CUSTOM HASHCODE DEMONSTRATION");
        System.out.println("================================");
        
        HashMap<Person, String> personMap = new HashMap<>();
        
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);
        Person person3 = new Person("John", 25); // Same as person1
        
        System.out.println("Person1 hash: " + person1.hashCode());
        System.out.println("Person2 hash: " + person2.hashCode());
        System.out.println("Person3 hash: " + person3.hashCode());
        System.out.println("Person1 equals Person3: " + person1.equals(person3));
        
        personMap.put(person1, "Engineer");
        personMap.put(person2, "Doctor");
        personMap.put(person3, "Manager"); // This will replace person1's value
        
        System.out.println("Person Map: " + personMap);
        System.out.println("Map size: " + personMap.size()); // Should be 2, not 3
        
        System.out.println();
    }
    
    /**
     * Demonstrate equals and hashCode relationship
     */
    public static void demonstrateEqualsAndHashCode() {
        System.out.println("5. EQUALS AND HASHCODE RELATIONSHIP");
        System.out.println("====================================");
        
        System.out.println("Critical Rules:");
        System.out.println("1. If two objects are equal (equals() returns true), they MUST have the same hashCode()");
        System.out.println("2. If two objects have the same hashCode(), they MAY or MAY NOT be equal");
        System.out.println("3. Violating rule 1 will break HashMap functionality");
        
        // Demonstrate correct implementation
        GoodKey goodKey1 = new GoodKey("test", 1);
        GoodKey goodKey2 = new GoodKey("test", 1);
        
        System.out.println("\nCorrect Implementation:");
        System.out.println("goodKey1.equals(goodKey2): " + goodKey1.equals(goodKey2));
        System.out.println("goodKey1.hashCode(): " + goodKey1.hashCode());
        System.out.println("goodKey2.hashCode(): " + goodKey2.hashCode());
        System.out.println("Hash codes equal: " + (goodKey1.hashCode() == goodKey2.hashCode()));
        
        // Demonstrate incorrect implementation
        BadKey badKey1 = new BadKey("test", 1);
        BadKey badKey2 = new BadKey("test", 1);
        
        System.out.println("\nIncorrect Implementation (violates rule 1):");
        System.out.println("badKey1.equals(badKey2): " + badKey1.equals(badKey2));
        System.out.println("badKey1.hashCode(): " + badKey1.hashCode());
        System.out.println("badKey2.hashCode(): " + badKey2.hashCode());
        System.out.println("Hash codes equal: " + (badKey1.hashCode() == badKey2.hashCode()));
        
        System.out.println();
    }
    
    /**
     * Performance comparison between different operations
     */
    public static void performanceComparison() {
        System.out.println("6. PERFORMANCE COMPARISON");
        System.out.println("=========================");
        
        HashMap<Integer, String> map = new HashMap<>();
        
        // Fill map with data
        for (int i = 0; i < 100000; i++) {
            map.put(i, "Value" + i);
        }
        
        // Test different operations
        int iterations = 100000;
        
        // Test get operation
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            map.get(i % 100000);
        }
        long getTime = System.nanoTime() - startTime;
        
        // Test containsKey operation
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            map.containsKey(i % 100000);
        }
        long containsKeyTime = System.nanoTime() - startTime;
        
        // Test containsValue operation
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            map.containsValue("Value" + (i % 100000));
        }
        long containsValueTime = System.nanoTime() - startTime;
        
        System.out.println("Performance for " + iterations + " operations:");
        System.out.println("Get operation: " + getTime / 1_000_000 + " ms");
        System.out.println("ContainsKey operation: " + containsKeyTime / 1_000_000 + " ms");
        System.out.println("ContainsValue operation: " + containsValueTime / 1_000_000 + " ms");
        
        System.out.println("\nTime Complexity Summary:");
        System.out.println("• get(): O(1) average, O(log n) worst case");
        System.out.println("• put(): O(1) average, O(log n) worst case");
        System.out.println("• remove(): O(1) average, O(log n) worst case");
        System.out.println("• containsKey(): O(1) average, O(log n) worst case");
        System.out.println("• containsValue(): O(n) - must check all values");
        
        System.out.println();
    }
}

/**
 * Person class with proper equals and hashCode implementation
 */
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && Objects.equals(name, person.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

/**
 * Good implementation of equals and hashCode
 */
class GoodKey {
    private String name;
    private int id;
    
    public GoodKey(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GoodKey goodKey = (GoodKey) obj;
        return id == goodKey.id && Objects.equals(name, goodKey.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}

/**
 * Bad implementation - violates equals/hashCode contract
 */
class BadKey {
    private String name;
    private int id;
    
    public BadKey(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BadKey badKey = (BadKey) obj;
        return id == badKey.id && Objects.equals(name, badKey.name);
    }
    
    @Override
    public int hashCode() {
        // BAD: Always returns same hash code regardless of object state
        return 42; // This violates the contract!
    }
}
