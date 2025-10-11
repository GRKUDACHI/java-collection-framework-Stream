import java.util.HashMap;
import java.util.Map;

/**
 * HASHMAP INTERNAL STRUCTURE VISUALIZATION
 * ========================================
 * 
 * This class demonstrates the internal structure of HashMap
 * with visual representations and step-by-step explanations.
 */

public class HashMapVisualizationDemo {
    
    public static void main(String[] args) {
        System.out.println("=== HASHMAP INTERNAL STRUCTURE VISUALIZATION ===\n");
        
        visualizeHashMapStructure();
        demonstrateHashCalculation();
        showCollisionHandling();
        demonstrateResizing();
    }
    
    /**
     * Visual representation of HashMap internal structure
     */
    public static void visualizeHashMapStructure() {
        System.out.println("1. HASHMAP INTERNAL STRUCTURE");
        System.out.println("=============================");
        
        System.out.println("""
            HashMap Internal Structure:
            
            ┌─────────────────────────────────────────────────────────┐
            │                    HashMap Instance                      │
            ├─────────────────────────────────────────────────────────┤
            │  Node<K,V>[] table     │  int size                     │
            │  float loadFactor      │  int threshold                │
            │  int capacity          │  int modCount                 │
            └─────────────────────────────────────────────────────────┘
                                    │
                                    ▼
            ┌─────────────────────────────────────────────────────────┐
            │                    Bucket Array                         │
            │  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  │
            │  │  0  │  │  1  │  │  2  │  │  3  │  │  4  │  │ ... │  │
            │  └─────┘  └─────┘  └─────┘  └─────┘  └─────┘  └─────┘  │
            └─────────────────────────────────────────────────────────┘
                    │         │         │         │         │
                    ▼         ▼         ▼         ▼         ▼
            ┌─────────────┐ ┌─────┐ ┌─────────────┐ ┌─────┐ ┌─────┐
            │   Node 1    │ │     │ │   Node 2    │ │     │ │     │
            │ Key: "A"    │ │     │ │ Key: "B"    │ │     │ │     │
            │ Value: 100  │ │     │ │ Value: 200  │ │     │ │     │
            │ Next: null  │ │     │ │ Next: null  │ │     │ │     │
            └─────────────┘ └─────┘ └─────────────┘ └─────┘ └─────┘
            
            Each bucket can contain:
            • null (empty bucket)
            • Single Node (no collision)
            • Linked List of Nodes (collision)
            • Red-Black Tree (Java 8+, >8 collisions)
            """);
        
        System.out.println();
    }
    
    /**
     * Demonstrate hash calculation process
     */
    public static void demonstrateHashCalculation() {
        System.out.println("2. HASH CALCULATION PROCESS");
        System.out.println("============================");
        
        String[] keys = {"Apple", "Banana", "Cherry", "Date"};
        int capacity = 8; // Small capacity for demonstration
        
        System.out.println("Hash Calculation Process:");
        System.out.println("Formula: index = hashCode() & (capacity - 1)");
        System.out.println("Capacity: " + capacity);
        System.out.println("Capacity - 1: " + (capacity - 1) + " (binary: " + Integer.toBinaryString(capacity - 1) + ")");
        System.out.println();
        
        for (String key : keys) {
            int hashCode = key.hashCode();
            int index = hashCode & (capacity - 1);
            
            System.out.println("Key: " + key);
            System.out.println("  HashCode: " + hashCode + " (binary: " + Integer.toBinaryString(hashCode) + ")");
            System.out.println("  Index: " + hashCode + " & " + (capacity - 1) + " = " + index);
            System.out.println("  Binary: " + Integer.toBinaryString(hashCode) + " & " + Integer.toBinaryString(capacity - 1));
            System.out.println("  Result: " + Integer.toBinaryString(index));
            System.out.println();
        }
    }
    
    /**
     * Show collision handling with visual representation
     */
    public static void showCollisionHandling() {
        System.out.println("3. COLLISION HANDLING");
        System.out.println("=====================");
        
        System.out.println("""
            Collision Handling Process:
            
            Step 1: Calculate hash and index
            ┌─────────────────────────────────────────────────────────┐
            │  Key: "Aa" → HashCode: 2112 → Index: 0                │
            │  Key: "BB" → HashCode: 2112 → Index: 0 (COLLISION!)   │
            └─────────────────────────────────────────────────────────┘
            
            Step 2: Handle collision with linked list
            ┌─────────────────────────────────────────────────────────┐
            │                    Bucket Array                         │
            │  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  │
            │  │  0  │  │  1  │  │  2  │  │  3  │  │  4  │  │ ... │  │
            │  └──┬──┘  └─────┘  └─────┘  └─────┘  └─────┘  └─────┘  │
            └─────┼─────────────────────────────────────────────────────┘
                  │
                  ▼
            ┌─────────────┐    ┌─────────────┐
            │   Node 1    │───▶│   Node 2    │
            │ Key: "Aa"   │    │ Key: "BB"   │
            │ Value: 100  │    │ Value: 200  │
            │ Next: Node2 │    │ Next: null  │
            └─────────────┘    └─────────────┘
            
            Step 3: Search process for collision
            • Calculate hash and index
            • Go to bucket at index
            • If single node, check key equality
            • If linked list, traverse and check each node
            • If tree, use tree search algorithm
            """);
        
        // Practical demonstration
        HashMap<String, Integer> collisionMap = new HashMap<>(4);
        collisionMap.put("Aa", 100);
        collisionMap.put("BB", 200);
        
        System.out.println("Practical Example:");
        System.out.println("HashMap: " + collisionMap);
        System.out.println("Both 'Aa' and 'BB' have same hash code: " + "Aa".hashCode());
        System.out.println("Both 'Aa' and 'BB' have same hash code: " + "BB".hashCode());
        
        System.out.println();
    }
    
    /**
     * Demonstrate HashMap resizing process
     */
    public static void demonstrateResizing() {
        System.out.println("4. HASHMAP RESIZING PROCESS");
        System.out.println("===========================");
        
        System.out.println("""
            Resizing Process (Rehashing):
            
            Initial State (Capacity: 4, Load Factor: 0.75, Threshold: 3):
            ┌─────────────────────────────────────────────────────────┐
            │  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐                    │
            │  │  0  │  │  1  │  │  2  │  │  3  │                    │
            │  └──┬──┘  └─────┘  └──┬──┘  └─────┘                    │
            │     │                 │                                │
            │     ▼                 ▼                                │
            │ ┌───────┐         ┌───────┐                            │
            │ │ Key:A │         │ Key:C │                            │
            │ │ Val:1 │         │ Val:3 │                            │
            │ └───────┘         └───────┘                            │
            └─────────────────────────────────────────────────────────┘
            
            After adding 4th element (exceeds threshold):
            
            Step 1: Create new array with double capacity (8)
            Step 2: Recalculate hash codes for all existing elements
            Step 3: Redistribute elements to new buckets
            
            New State (Capacity: 8):
            ┌─────────────────────────────────────────────────────────────────────────┐
            │  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  ┌─────┐  │
            │  │  0  │  │  1  │  │  2  │  │  3  │  │  4  │  │  5  │  │  6  │  │  7  │  │
            │  └─────┘  └──┬──┘  └─────┘  └──┬──┘  └─────┘  └─────┘  └─────┘  └─────┘  │
            │              │                 │                                        │
            │              ▼                 ▼                                        │
            │         ┌───────┐         ┌───────┐                                    │
            │         │ Key:A │         │ Key:C │                                    │
            │         │ Val:1 │         │ Val:3 │                                    │
            │         └───────┘         └───────┘                                    │
            └─────────────────────────────────────────────────────────────────────────┘
            
            Key Points:
            • Resizing happens when size > threshold
            • New capacity = old capacity * 2
            • All elements are rehashed and redistributed
            • This is an expensive operation (O(n))
            • Load factor helps balance space and time
            """);
        
        System.out.println();
    }
}

/**
 * Node class representing HashMap's internal node structure
 */
class HashMapNode<K, V> {
    final int hash;
    final K key;
    V value;
    HashMapNode<K, V> next;
    
    public HashMapNode(int hash, K key, V value, HashMapNode<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
    
    @Override
    public String toString() {
        return "Node{hash=" + hash + ", key=" + key + ", value=" + value + "}";
    }
}
