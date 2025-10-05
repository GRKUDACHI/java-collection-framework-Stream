import java.util.EnumMap;
import java.util.Map;
import java.util.*;

/*
 * ===============================================
 * ENUMMAP COLLECTION - COMPREHENSIVE TUTORIAL
 * ===============================================
 * 
 * WHAT IS ENUMMAP?
 * - EnumMap is a specialized Map implementation designed to work with enum keys
 * - It's part of Java Collections Framework since Java 1.5
 * - Only enum types can be used as keys
 * - Values can be any type (String, Integer, Object, etc.)
 * 
 * KEY CHARACTERISTICS:
 * 1. ARRAY-BASED IMPLEMENTATION:
 *    - Uses array indexing instead of hash tables
 *    - Each enum constant has a fixed ordinal (position) in the enum
 *    - Array index = enum.ordinal()
 * 
 * 2. PERFORMANCE BENEFITS:
 *    - FASTER than HashMap for enum keys
 *    - No hash collision issues
 *    - O(1) constant time for get(), put(), remove()
 *    - No need to calculate hash codes
 * 
 * 3. MEMORY EFFICIENCY:
 *    - More memory efficient than HashMap
 *    - No hash table overhead
 *    - Compact storage using arrays
 * 
 * 4. ORDERING:
 *    - Maintains natural enum order (ordinal order)
 *    - Iteration follows enum declaration order
 * 
 * 5. RESTRICTIONS:
 *    - Only enum types as keys
 *    - No null keys allowed
 *    - Null values are allowed
 *    - All keys must be from the same enum type
 * 
 * WHEN TO USE ENUMMAP:
 * - When you have enum keys
 * - When performance is critical
 * - When you need predictable iteration order
 * - When working with configuration, settings, or mappings
 * 
 * COMMON USE CASES:
 * - Configuration mappings
 * - State machines
 * - Feature flags
 * - Day-to-activity mappings
 * - Status-to-action mappings
 */

public class EnumMapDemo
{
    public static void main(String [] args)
    {
        System.out.println("=== ENUMMAP COMPREHENSIVE TUTORIAL ===\n");
        
        // 1. BASIC ENUMMAP OPERATIONS
        basicEnumMapOperations();
        
        // 2. ENUMMAP WITH DIFFERENT VALUE TYPES
        enumMapWithDifferentTypes();
        
        // 3. PRACTICAL EXAMPLES
        practicalExamples();
        
        // 4. ENUMMAP VS HASHMAP COMPARISON
        enumMapVsHashMap();
        
        // 5. ADVANCED ENUMMAP FEATURES
        advancedFeatures();
    }
    
    // 1. BASIC ENUMMAP OPERATIONS
    public static void basicEnumMapOperations() {
        System.out.println("1. BASIC ENUMMAP OPERATIONS:");
        System.out.println("=============================");
        
        // Create EnumMap - MUST specify the enum class
        Map<Day, String> route = new EnumMap<>(Day.class);
        
        // Add key-value pairs
        route.put(Day.Monday, "Walking");
        route.put(Day.Tuesday, "Gym");
        route.put(Day.Thursday, "Yoga");
        route.put(Day.Friday, "Swimming");
        
        System.out.println("EnumMap contents: " + route);
        System.out.println("Size: " + route.size());
        
        // Access values
        System.out.println("Monday activity: " + route.get(Day.Monday));
        System.out.println("Contains Tuesday: " + route.containsKey(Day.Tuesday));
        System.out.println("Contains 'Gym': " + route.containsValue("Gym"));
        
        // Remove entry
        route.remove(Day.Thursday);
        System.out.println("After removing Thursday: " + route);
        
        // Iterate through entries (maintains enum order)
        System.out.println("\nIterating through entries:");
        for(Map.Entry<Day, String> entry : route.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        System.out.println();
    }
    
    // 2. ENUMMAP WITH DIFFERENT VALUE TYPES
    public static void enumMapWithDifferentTypes() {
        System.out.println("2. ENUMMAP WITH DIFFERENT VALUE TYPES:");
        System.out.println("=======================================");
        
        // EnumMap with Integer values
        EnumMap<Priority, Integer> priorityScores = new EnumMap<>(Priority.class);
        priorityScores.put(Priority.LOW, 1);
        priorityScores.put(Priority.MEDIUM, 5);
        priorityScores.put(Priority.HIGH, 10);
        priorityScores.put(Priority.CRITICAL, 20);
        
        System.out.println("Priority scores: " + priorityScores);
        
        // EnumMap with Boolean values (useful for flags)
        EnumMap<Feature, Boolean> featureFlags = new EnumMap<>(Feature.class);
        featureFlags.put(Feature.DARK_MODE, true);
        featureFlags.put(Feature.NOTIFICATIONS, false);
        featureFlags.put(Feature.AUTO_SAVE, true);
        
        System.out.println("Feature flags: " + featureFlags);
        
        // EnumMap with List values
        EnumMap<Department, List<String>> departmentEmployees = new EnumMap<>(Department.class);
        departmentEmployees.put(Department.IT, Arrays.asList("John", "Alice", "Bob"));
        departmentEmployees.put(Department.HR, Arrays.asList("Sarah", "Mike"));
        departmentEmployees.put(Department.FINANCE, Arrays.asList("Emma", "David", "Lisa"));
        
        System.out.println("Department employees: " + departmentEmployees);
        
        System.out.println();
    }
    
    // 3. PRACTICAL EXAMPLES
    public static void practicalExamples() {
        System.out.println("3. PRACTICAL EXAMPLES:");
        System.out.println("======================");
        
        // Example 1: Daily Schedule
        EnumMap<Day, String> dailySchedule = new EnumMap<>(Day.class);
        dailySchedule.put(Day.Monday, "Team Meeting");
        dailySchedule.put(Day.Tuesday, "Code Review");
        dailySchedule.put(Day.Wednesday, "Client Call");
        dailySchedule.put(Day.Thursday, "Training Session");
        dailySchedule.put(Day.Friday, "Project Demo");
        
        System.out.println("Daily Schedule:");
        dailySchedule.forEach((day, activity) -> 
            System.out.println(day + ": " + activity));
        
        // Example 2: Error Code Mapping
        EnumMap<ErrorCode, String> errorMessages = new EnumMap<>(ErrorCode.class);
        errorMessages.put(ErrorCode.SUCCESS, "Operation completed successfully");
        errorMessages.put(ErrorCode.NOT_FOUND, "Resource not found");
        errorMessages.put(ErrorCode.UNAUTHORIZED, "Access denied");
        errorMessages.put(ErrorCode.SERVER_ERROR, "Internal server error");
        
        System.out.println("\nError Messages:");
        errorMessages.forEach((code, message) -> 
            System.out.println(code + ": " + message));
        
        // Example 3: Configuration Settings
        EnumMap<Config, Object> appConfig = new EnumMap<>(Config.class);
        appConfig.put(Config.DATABASE_URL, "jdbc:mysql://localhost:3306/mydb");
        appConfig.put(Config.MAX_CONNECTIONS, 100);
        appConfig.put(Config.DEBUG_MODE, true);
        appConfig.put(Config.TIMEOUT_SECONDS, 30);
        
        System.out.println("\nApplication Configuration:");
        appConfig.forEach((key, value) -> 
            System.out.println(key + " = " + value));
        
        System.out.println();
    }
    
    // 4. ENUMMAP VS HASHMAP COMPARISON
    public static void enumMapVsHashMap() {
        System.out.println("4. ENUMMAP VS HASHMAP COMPARISON:");
        System.out.println("==================================");
        
        // Create both EnumMap and HashMap with same data
        EnumMap<Day, String> enumMap = new EnumMap<>(Day.class);
        Map<Day, String> hashMap = new HashMap<>();
        
        // Add same data to both
        Day[] days = {Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday, Day.Friday};
        String[] activities = {"Meeting", "Coding", "Testing", "Review", "Deploy"};
        
        for(int i = 0; i < days.length; i++) {
            enumMap.put(days[i], activities[i]);
            hashMap.put(days[i], activities[i]);
        }
        
        System.out.println("EnumMap iteration (maintains enum order):");
        enumMap.forEach((day, activity) -> 
            System.out.println(day + " -> " + activity));
        
        System.out.println("\nHashMap iteration (random order):");
        hashMap.forEach((day, activity) -> 
            System.out.println(day + " -> " + activity));
        
        // Performance comparison note
        System.out.println("\nPERFORMANCE NOTES:");
        System.out.println("- EnumMap: Faster for enum keys (no hashing)");
        System.out.println("- EnumMap: More memory efficient");
        System.out.println("- EnumMap: Predictable iteration order");
        System.out.println("- HashMap: Works with any key type");
        
        System.out.println();
    }
    
    // 5. ADVANCED ENUMMAP FEATURES
    public static void advancedFeatures() {
        System.out.println("5. ADVANCED ENUMMAP FEATURES:");
        System.out.println("==============================");
        
        // Create EnumMap from existing Map
        Map<Status, String> statusMap = new HashMap<>();
        statusMap.put(Status.ACTIVE, "User is active");
        statusMap.put(Status.INACTIVE, "User is inactive");
        statusMap.put(Status.SUSPENDED, "User is suspended");
        
        EnumMap<Status, String> enumMapFromMap = new EnumMap<>(statusMap);
        System.out.println("EnumMap created from HashMap: " + enumMapFromMap);
        
        // Copy constructor
        EnumMap<Status, String> copiedEnumMap = new EnumMap<>(enumMapFromMap);
        System.out.println("Copied EnumMap: " + copiedEnumMap);
        
        // Working with null values
        EnumMap<Priority, String> nullableMap = new EnumMap<>(Priority.class);
        nullableMap.put(Priority.HIGH, "High priority");
        nullableMap.put(Priority.MEDIUM, null); // null values are allowed
        nullableMap.put(Priority.LOW, "Low priority");
        
        System.out.println("Map with null values: " + nullableMap);
        System.out.println("Contains null value: " + nullableMap.containsValue(null));
        
        // Clear and check empty
        nullableMap.clear();
        System.out.println("After clear - isEmpty: " + nullableMap.isEmpty());
        
        System.out.println("\n=== END OF ENUMMAP TUTORIAL ===");
    }
}

// ENUM DEFINITIONS FOR EXAMPLES

enum Day
{
    Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday;
}

enum Priority
{
    LOW, MEDIUM, HIGH, CRITICAL
}

enum Feature
{
    DARK_MODE, NOTIFICATIONS, AUTO_SAVE, THEME_SWITCHER
}

enum Department
{
    IT, HR, FINANCE, MARKETING, SALES
}

enum ErrorCode
{
    SUCCESS, NOT_FOUND, UNAUTHORIZED, SERVER_ERROR, VALIDATION_ERROR
}

enum Config
{
    DATABASE_URL, MAX_CONNECTIONS, DEBUG_MODE, TIMEOUT_SECONDS, CACHE_SIZE
}

enum Status
{
    ACTIVE, INACTIVE, SUSPENDED, TERMINATED, PENDING
}