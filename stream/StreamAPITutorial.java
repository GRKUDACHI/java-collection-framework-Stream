import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/*
 * ===============================================
 * STREAM API - COMPLETE TUTORIAL FOR BEGINNERS
 * ===============================================
 * 
 * WHAT IS STREAM API?
 * - Introduced in Java 8
 * - Allows functional-style operations on collections
 * - Enables parallel processing
 * - Makes code more readable and concise
 * - Works with Collections, Arrays, and I/O
 * 
 * STREAM CHARACTERISTICS:
 * 1. NOT a data structure - doesn't store data
 * 2. Takes input from Collections, Arrays, or I/O
 * 3. Functional in nature - doesn't modify source
 * 4. Lazy evaluation - operations executed only when needed
 * 5. Can be consumed only once
 * 
 * STREAM OPERATIONS:
 * 1. INTERMEDIATE OPERATIONS:
 *    - Return new Stream
 *    - Lazy evaluation
 *    - Can be chained
 *    - Examples: filter(), map(), sorted(), distinct(), limit()
 * 
 * 2. TERMINAL OPERATIONS:
 *    - Return result or void
 *    - Trigger execution
 *    - Cannot be chained
 *    - Examples: collect(), forEach(), reduce(), count(), findFirst()
 * 
 * WHY USE STREAMS?
 * - More readable code
 * - Less boilerplate
 * - Easy parallel processing
 * - Functional programming style
 * - Better performance with large datasets
 */

public class StreamAPITutorial {
    public static void main(String[] args) {
        System.out.println("=== STREAM API COMPLETE TUTORIAL ===\n");
        
        // 1. BASIC STREAM CREATION
        basicStreamCreation();
        
        // 2. INTERMEDIATE OPERATIONS
        intermediateOperations();
        
        // 3. TERMINAL OPERATIONS
        terminalOperations();
        
        // 4. STREAM WITH COLLECTIONS
        streamWithCollections();
        
        // 5. PRACTICAL EXAMPLES
        practicalExamples();
        
        // 6. ADVANCED STREAM OPERATIONS
        advancedStreamOperations();
        
        // 7. PARALLEL STREAMS
        parallelStreams();
    }
    
    // 1. BASIC STREAM CREATION
    public static void basicStreamCreation() {
        System.out.println("1. BASIC STREAM CREATION:");
        System.out.println("==========================");
        
        // Create stream from List
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        System.out.println("Original list: " + names);
        
        // Convert to stream
        Stream<String> nameStream = names.stream();
        System.out.println("Stream created from list");
        
        // Create stream from Array
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream numberStream = Arrays.stream(numbers);
        System.out.println("Stream created from array: " + Arrays.toString(numbers));
        
        // Create stream using Stream.of()
        Stream<String> directStream = Stream.of("Java", "Python", "C++", "JavaScript");
        System.out.println("Direct stream creation");
        
        // Create empty stream
        Stream<String> emptyStream = Stream.empty();
        System.out.println("Empty stream created");
        
        // Create infinite stream (limited)
        Stream<Integer> infiniteStream = Stream.iterate(1, n -> n + 1).limit(5);
        System.out.println("Infinite stream (limited to 5): " + 
            infiniteStream.collect(Collectors.toList()));
        
        System.out.println();
    }
    
    // 2. INTERMEDIATE OPERATIONS
    public static void intermediateOperations() {
        System.out.println("2. INTERMEDIATE OPERATIONS:");
        System.out.println("============================");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original numbers: " + numbers);
        
        // FILTER - Keep only elements that match condition
        System.out.println("\n--- FILTER OPERATION ---");
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)  // Keep only even numbers
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // MAP - Transform each element
        System.out.println("\n--- MAP OPERATION ---");
        List<Integer> squaredNumbers = numbers.stream()
            .map(n -> n * n)  // Square each number
            .collect(Collectors.toList());
        System.out.println("Squared numbers: " + squaredNumbers);
        
        // SORTED - Sort elements
        System.out.println("\n--- SORTED OPERATION ---");
        List<String> fruits = Arrays.asList("banana", "apple", "cherry", "date");
        List<String> sortedFruits = fruits.stream()
            .sorted()  // Natural order
            .collect(Collectors.toList());
        System.out.println("Sorted fruits: " + sortedFruits);
        
        // DISTINCT - Remove duplicates
        System.out.println("\n--- DISTINCT OPERATION ---");
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 5);
        List<Integer> uniqueNumbers = duplicates.stream()
            .distinct()  // Remove duplicates
            .collect(Collectors.toList());
        System.out.println("Unique numbers: " + uniqueNumbers);
        
        // LIMIT - Limit number of elements
        System.out.println("\n--- LIMIT OPERATION ---");
        List<Integer> limitedNumbers = numbers.stream()
            .limit(5)  // Take only first 5 elements
            .collect(Collectors.toList());
        System.out.println("First 5 numbers: " + limitedNumbers);
        
        // SKIP - Skip first n elements
        System.out.println("\n--- SKIP OPERATION ---");
        List<Integer> skippedNumbers = numbers.stream()
            .skip(3)  // Skip first 3 elements
            .collect(Collectors.toList());
        System.out.println("Numbers after skipping first 3: " + skippedNumbers);
        
        // PEEK - Debug operation (see elements without consuming)
        System.out.println("\n--- PEEK OPERATION ---");
        List<Integer> peekedNumbers = numbers.stream()
            .peek(n -> System.out.println("Processing: " + n))  // Debug
            .filter(n -> n > 5)
            .collect(Collectors.toList());
        System.out.println("Numbers greater than 5: " + peekedNumbers);
        
        System.out.println();
    }
    
    // 3. TERMINAL OPERATIONS
    public static void terminalOperations() {
        System.out.println("3. TERMINAL OPERATIONS:");
        System.out.println("=======================");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original numbers: " + numbers);
        
        // COLLECT - Collect results into collection
        System.out.println("\n--- COLLECT OPERATION ---");
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // FOREACH - Perform action on each element
        System.out.println("\n--- FOREACH OPERATION ---");
        System.out.print("Printing each number: ");
        numbers.stream()
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // COUNT - Count elements
        System.out.println("\n--- COUNT OPERATION ---");
        long count = numbers.stream()
            .filter(n -> n > 5)
            .count();
        System.out.println("Count of numbers > 5: " + count);
        
        // REDUCE - Reduce to single value
        System.out.println("\n--- REDUCE OPERATION ---");
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);  // Sum all numbers
        System.out.println("Sum of all numbers: " + sum);
        
        int product = numbers.stream()
            .reduce(1, (a, b) -> a * b);  // Product of all numbers
        System.out.println("Product of all numbers: " + product);
        
        // FIND FIRST - Find first element
        System.out.println("\n--- FIND FIRST OPERATION ---");
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        System.out.println("First even number: " + firstEven.orElse(-1));
        
        // FIND ANY - Find any element
        System.out.println("\n--- FIND ANY OPERATION ---");
        Optional<Integer> anyOdd = numbers.stream()
            .filter(n -> n % 2 != 0)
            .findAny();
        System.out.println("Any odd number: " + anyOdd.orElse(-1));
        
        // ALL MATCH - Check if all elements match condition
        System.out.println("\n--- ALL MATCH OPERATION ---");
        boolean allPositive = numbers.stream()
            .allMatch(n -> n > 0);
        System.out.println("All numbers positive: " + allPositive);
        
        // ANY MATCH - Check if any element matches condition
        System.out.println("\n--- ANY MATCH OPERATION ---");
        boolean anyGreaterThan5 = numbers.stream()
            .anyMatch(n -> n > 5);
        System.out.println("Any number > 5: " + anyGreaterThan5);
        
        // NONE MATCH - Check if no element matches condition
        System.out.println("\n--- NONE MATCH OPERATION ---");
        boolean noneNegative = numbers.stream()
            .noneMatch(n -> n < 0);
        System.out.println("No negative numbers: " + noneNegative);
        
        // MIN/MAX - Find minimum and maximum
        System.out.println("\n--- MIN/MAX OPERATION ---");
        Optional<Integer> min = numbers.stream()
            .min(Integer::compareTo);
        Optional<Integer> max = numbers.stream()
            .max(Integer::compareTo);
        System.out.println("Minimum: " + min.orElse(-1));
        System.out.println("Maximum: " + max.orElse(-1));
        
        System.out.println();
    }
    
    // 4. STREAM WITH COLLECTIONS
    public static void streamWithCollections() {
        System.out.println("4. STREAM WITH COLLECTIONS:");
        System.out.println("===========================");
        
        // List operations
        System.out.println("--- LIST OPERATIONS ---");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Alice");
        
        // Filter names starting with 'A'
        List<String> namesStartingWithA = names.stream()
            .filter(name -> name.startsWith("A"))
            .collect(Collectors.toList());
        System.out.println("Names starting with 'A': " + namesStartingWithA);
        
        // Convert to uppercase
        List<String> upperCaseNames = names.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperCaseNames);
        
        // Set operations
        System.out.println("\n--- SET OPERATIONS ---");
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 2, 3));
        System.out.println("Original set: " + numbers);
        
        // Filter even numbers
        Set<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toSet());
        System.out.println("Even numbers: " + evenNumbers);
        
        // Map operations
        System.out.println("\n--- MAP OPERATIONS ---");
        Map<String, Integer> ageMap = new HashMap<>();
        ageMap.put("Alice", 25);
        ageMap.put("Bob", 30);
        ageMap.put("Charlie", 35);
        ageMap.put("David", 28);
        
        // Filter people older than 28
        Map<String, Integer> olderPeople = ageMap.entrySet().stream()
            .filter(entry -> entry.getValue() > 28)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ));
        System.out.println("People older than 28: " + olderPeople);
        
        // Get all names
        List<String> allNames = ageMap.keySet().stream()
            .collect(Collectors.toList());
        System.out.println("All names: " + allNames);
        
        // Get all ages
        List<Integer> allAges = ageMap.values().stream()
            .collect(Collectors.toList());
        System.out.println("All ages: " + allAges);
        
        System.out.println();
    }
    
    // 5. PRACTICAL EXAMPLES
    public static void practicalExamples() {
        System.out.println("5. PRACTICAL EXAMPLES:");
        System.out.println("======================");
        
        // Example 1: Employee Management
        System.out.println("--- EMPLOYEE MANAGEMENT ---");
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 25, "IT", 50000),
            new Employee("Bob", 30, "HR", 45000),
            new Employee("Charlie", 35, "IT", 60000),
            new Employee("David", 28, "Finance", 55000),
            new Employee("Eve", 32, "IT", 52000)
        );
        
        // Find IT employees
        List<Employee> itEmployees = employees.stream()
            .filter(emp -> emp.getDepartment().equals("IT"))
            .collect(Collectors.toList());
        System.out.println("IT Employees: " + itEmployees);
        
        // Get names of employees earning more than 50000
        List<String> highEarners = employees.stream()
            .filter(emp -> emp.getSalary() > 50000)
            .map(Employee::getName)
            .collect(Collectors.toList());
        System.out.println("High earners: " + highEarners);
        
        // Calculate average salary
        double avgSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .orElse(0.0);
        System.out.println("Average salary: " + avgSalary);
        
        // Group employees by department
        Map<String, List<Employee>> employeesByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Employees by department: " + employeesByDept);
        
        // Example 2: Number Processing
        System.out.println("\n--- NUMBER PROCESSING ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Find sum of squares of even numbers
        int sumOfSquares = numbers.stream()
            .filter(n -> n % 2 == 0)
            .mapToInt(n -> n * n)
            .sum();
        System.out.println("Sum of squares of even numbers: " + sumOfSquares);
        
        // Find largest number divisible by 3
        Optional<Integer> largestDivisibleBy3 = numbers.stream()
            .filter(n -> n % 3 == 0)
            .max(Integer::compareTo);
        System.out.println("Largest number divisible by 3: " + 
            largestDivisibleBy3.orElse(-1));
        
        System.out.println();
    }
    
    // 6. ADVANCED STREAM OPERATIONS
    public static void advancedStreamOperations() {
        System.out.println("6. ADVANCED STREAM OPERATIONS:");
        System.out.println("==============================");
        
        // FlatMap - Flatten nested collections
        System.out.println("--- FLATMAP OPERATION ---");
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("Java", "Python"),
            Arrays.asList("C++", "JavaScript"),
            Arrays.asList("Ruby", "Go")
        );
        
        List<String> flattenedList = nestedList.stream()
            .flatMap(List::stream)  // Flatten the nested lists
            .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattenedList);
        
        // Collect with custom collector
        System.out.println("\n--- CUSTOM COLLECTOR ---");
        List<String> words = Arrays.asList("hello", "world", "java", "stream");
        
        // Join strings with custom separator
        String joined = words.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined words: " + joined);
        
        // Partitioning - Split into two groups
        System.out.println("\n--- PARTITIONING ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Partitioned (even/odd): " + partitioned);
        
        // Grouping by - Group by some criteria
        System.out.println("\n--- GROUPING BY ---");
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        Map<Integer, List<String>> groupedByLength = fruits.stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by length: " + groupedByLength);
        
        // Mapping with downstream collector
        System.out.println("\n--- MAPPING WITH DOWNSTREAM ---");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        Map<Integer, Long> countByLength = names.stream()
            .collect(Collectors.groupingBy(
                String::length,
                Collectors.counting()
            ));
        System.out.println("Count by length: " + countByLength);
        
        System.out.println();
    }
    
    // 7. PARALLEL STREAMS
    public static void parallelStreams() {
        System.out.println("7. PARALLEL STREAMS:");
        System.out.println("====================");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Sequential stream
        System.out.println("--- SEQUENTIAL STREAM ---");
        long startTime = System.currentTimeMillis();
        int sequentialSum = numbers.stream()
            .mapToInt(n -> n * n)
            .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        System.out.println("Sequential sum: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
        
        // Parallel stream
        System.out.println("\n--- PARALLEL STREAM ---");
        startTime = System.currentTimeMillis();
        int parallelSum = numbers.parallelStream()
            .mapToInt(n -> n * n)
            .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        System.out.println("Parallel sum: " + parallelSum + " (Time: " + parallelTime + "ms)");
        
        // When to use parallel streams
        System.out.println("\n--- WHEN TO USE PARALLEL STREAMS ---");
        System.out.println("✓ Use when you have large datasets");
        System.out.println("✓ Use when operations are CPU-intensive");
        System.out.println("✓ Use when operations are independent");
        System.out.println("✗ Don't use for small datasets (overhead)");
        System.out.println("✗ Don't use when operations have side effects");
        
        System.out.println();
    }
}

// Employee class for examples
class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;
    
    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return name + "(" + department + ", $" + salary + ")";
    }
}
