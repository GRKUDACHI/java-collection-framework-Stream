import java.util.EnumSet;
import java.util.Set;
import java.util.Iterator;

/*
 * ENUMSET COLLECTION TUTORIAL
 * 
 * EnumSet is a specialized Set implementation designed to work with enum types.
 * It's extremely efficient and provides high performance for enum collections.
 * 
 * KEY FEATURES:
 * 1. Only works with enum types
 * 2. Uses bit vectors internally (very memory efficient)
 * 3. Extremely fast performance (faster than HashSet)
 * 4. Maintains enum order
 * 5. Thread-safe when used properly
 * 6. No null elements allowed
 * 7. All elements must be from the same enum type
 * 
 * COMMON USE CASES:
 * - Representing flags or options
 * - Managing states or configurations
 * - Working with enum collections efficiently
 */

public class EnumSetDemo {
    public static void main(String[] args) {
        System.out.println("=== ENUMSET COLLECTION TUTORIAL ===\n");
        
        // 1. BASIC ENUMSET CREATION AND OPERATIONS
        basicEnumSetOperations();
        
        // 2. ENUMSET FACTORY METHODS
        enumSetFactoryMethods();
        
        // 3. ENUMSET WITH RANGES
        enumSetWithRanges();
        
        // 4. PRACTICAL EXAMPLE - USER PERMISSIONS
        practicalExample();
        
        // 5. ENUMSET OPERATIONS AND COMPARISONS
        enumSetOperations();
    }
    
    // 1. BASIC ENUMSET CREATION AND OPERATIONS
    public static void basicEnumSetOperations() {
        System.out.println("1. BASIC ENUMSET OPERATIONS:");
        System.out.println("=============================");
        
        // Create an empty EnumSet
        EnumSet<Priority> priorities = EnumSet.noneOf(Priority.class);
        
        // Add elements
        priorities.add(Priority.HIGH);
        priorities.add(Priority.MEDIUM);
        priorities.add(Priority.LOW);
        
        System.out.println("EnumSet contents: " + priorities);
        System.out.println("Size: " + priorities.size());
        System.out.println("Contains HIGH: " + priorities.contains(Priority.HIGH));
        
        // Remove element
        priorities.remove(Priority.LOW);
        System.out.println("After removing LOW: " + priorities);
        
        // Clear all elements
        priorities.clear();
        System.out.println("After clear: " + priorities);
        System.out.println("Is empty: " + priorities.isEmpty());
        
        System.out.println();
    }
    
    // 2. ENUMSET FACTORY METHODS
    public static void enumSetFactoryMethods() {
        System.out.println("2. ENUMSET FACTORY METHODS:");
        System.out.println("============================");
        
        // allOf() - contains all enum constants
        EnumSet<Status> allStatuses = EnumSet.allOf(Status.class);
        System.out.println("All statuses: " + allStatuses);
        
        // noneOf() - empty set
        EnumSet<Status> emptyStatuses = EnumSet.noneOf(Status.class);
        System.out.println("Empty statuses: " + emptyStatuses);
        
        // of() - specific elements
        EnumSet<Status> specificStatuses = EnumSet.of(Status.ACTIVE, Status.PENDING);
        System.out.println("Specific statuses: " + specificStatuses);
        
        // complementOf() - all except specified
        EnumSet<Status> complementStatuses = EnumSet.complementOf(specificStatuses);
        System.out.println("Complement (all except ACTIVE, PENDING): " + complementStatuses);
        
        System.out.println();
    }
    
    // 3. ENUMSET WITH RANGES
    public static void enumSetWithRanges() {
        System.out.println("3. ENUMSET WITH RANGES:");
        System.out.println("=======================");
        
        // range() - from one enum to another (inclusive)
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("Weekdays: " + weekdays);
        
        EnumSet<Day> weekend = EnumSet.range(Day.SATURDAY, Day.SUNDAY);
        System.out.println("Weekend: " + weekend);
        
        // Combine ranges
        EnumSet<Day> workDays = EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY);
        System.out.println("Work days: " + workDays);
        
        System.out.println();
    }
    
    // 4. PRACTICAL EXAMPLE - USER PERMISSIONS
    public static void practicalExample() {
        System.out.println("4. PRACTICAL EXAMPLE - USER PERMISSIONS:");
        System.out.println("========================================");
        
        // Create different user permission sets
        EnumSet<Permission> adminPermissions = EnumSet.allOf(Permission.class);
        EnumSet<Permission> userPermissions = EnumSet.of(Permission.READ, Permission.WRITE);
        EnumSet<Permission> guestPermissions = EnumSet.of(Permission.READ);
        
        System.out.println("Admin permissions: " + adminPermissions);
        System.out.println("User permissions: " + userPermissions);
        System.out.println("Guest permissions: " + guestPermissions);
        
        // Check permissions
        System.out.println("\nPermission checks:");
        System.out.println("User can READ: " + userPermissions.contains(Permission.READ));
        System.out.println("User can DELETE: " + userPermissions.contains(Permission.DELETE));
        System.out.println("Admin can EXECUTE: " + adminPermissions.contains(Permission.EXECUTE));
        
        // Add permission
        userPermissions.add(Permission.EXECUTE);
        System.out.println("User permissions after adding EXECUTE: " + userPermissions);
        
        System.out.println();
    }
    
    // 5. ENUMSET OPERATIONS AND COMPARISONS
    public static void enumSetOperations() {
        System.out.println("5. ENUMSET OPERATIONS AND COMPARISONS:");
        System.out.println("======================================");
        
        EnumSet<Color> primaryColors = EnumSet.of(Color.RED, Color.BLUE, Color.YELLOW);
        EnumSet<Color> warmColors = EnumSet.of(Color.RED, Color.YELLOW, Color.ORANGE);
        EnumSet<Color> coolColors = EnumSet.of(Color.BLUE, Color.GREEN, Color.PURPLE);
        
        System.out.println("Primary colors: " + primaryColors);
        System.out.println("Warm colors: " + warmColors);
        System.out.println("Cool colors: " + coolColors);
        
        // Set operations
        System.out.println("\nSet Operations:");
        
        // Union (addAll)
        EnumSet<Color> union = EnumSet.copyOf(primaryColors);
        union.addAll(warmColors);
        System.out.println("Primary ∪ Warm: " + union);
        
        // Intersection (retainAll)
        EnumSet<Color> intersection = EnumSet.copyOf(primaryColors);
        intersection.retainAll(warmColors);
        System.out.println("Primary ∩ Warm: " + intersection);
        
        // Difference (removeAll)
        EnumSet<Color> difference = EnumSet.copyOf(primaryColors);
        difference.removeAll(warmColors);
        System.out.println("Primary - Warm: " + difference);
        
        // Iteration
        System.out.println("\nIterating through EnumSet:");
        Iterator<Color> iterator = primaryColors.iterator();
        while (iterator.hasNext()) {
            System.out.println("Color: " + iterator.next());
        }
        
        // Enhanced for loop
        System.out.println("\nEnhanced for loop:");
        for (Color color : primaryColors) {
            System.out.println("Processing: " + color);
        }
        
        System.out.println("\n=== END OF ENUMSET TUTORIAL ===");
    }
}

// ENUM DEFINITIONS

enum Priority {
    LOW, MEDIUM, HIGH, CRITICAL
}

enum Status {
    PENDING, ACTIVE, INACTIVE, SUSPENDED, TERMINATED
}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum Permission {
    READ, WRITE, DELETE, EXECUTE, ADMIN
}

enum Color {
    RED, BLUE, GREEN, YELLOW, ORANGE, PURPLE, BLACK, WHITE
}
