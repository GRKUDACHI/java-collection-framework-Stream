import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/*
 * ===============================================
 * FUNCTIONAL INTERFACES - EASY TUTORIAL
 * ===============================================
 * 
 * WHAT ARE FUNCTIONAL INTERFACES?
 * - Interfaces with exactly ONE abstract method
 * - Introduced in Java 8
 * - Enable functional programming in Java
 * - Can be used with Lambda expressions
 * 
 * THE BIG 4 FUNCTIONAL INTERFACES:
 * 
 * 1. PREDICATE<T> - "TESTER"
 *    - Takes input, returns boolean (true/false)
 *    - Use: Testing conditions, filtering
 *    - Method: boolean test(T t)
 * 
 * 2. FUNCTION<T,R> - "TRANSFORMER" 
 *    - Takes input of type T, returns output of type R
 *    - Use: Transforming data, mapping
 *    - Method: R apply(T t)
 * 
 * 3. SUPPLIER<T> - "PROVIDER"
 *    - Takes no input, returns output of type T
 *    - Use: Creating objects, generating values
 *    - Method: T get()
 * 
 * 4. CONSUMER<T> - "DOER"
 *    - Takes input of type T, returns nothing (void)
 *    - Use: Performing actions, side effects
 *    - Method: void accept(T t)
 * 
 * EASY WAY TO REMEMBER:
 * - Predicate: "Is this true?" (Question)
 * - Function: "Transform this to that" (Conversion)
 * - Supplier: "Give me something" (Provider)
 * - Consumer: "Do something with this" (Action)
 */

public class FunctionalInterfacesTutorial {
    public static void main(String[] args) {
        System.out.println("=== FUNCTIONAL INTERFACES EASY TUTORIAL ===\n");
        
        // 1. PREDICATE - THE TESTER
        predicateTutorial();
        
        // 2. FUNCTION - THE TRANSFORMER
        functionTutorial();
        
        // 3. SUPPLIER - THE PROVIDER
        supplierTutorial();
        
        // 4. CONSUMER - THE DOER
        consumerTutorial();
        
        // 5. COMBINING ALL TOGETHER
        combiningAll();
        
        // 6. REAL WORLD EXAMPLES
        realWorldExamples();
    }
    
    // 1. PREDICATE - THE TESTER
    public static void predicateTutorial() {
        System.out.println("1. PREDICATE<T> - THE TESTER:");
        System.out.println("==============================");
        System.out.println("Purpose: Test if something is true or false");
        System.out.println("Method: boolean test(T t)\n");
        
        // Basic Predicate examples
        System.out.println("--- BASIC PREDICATE EXAMPLES ---");
        
        // Example 1: Check if number is even
        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));  // true
        System.out.println("Is 7 even? " + isEven.test(7));    // false
        
        // Example 2: Check if string is long
        Predicate<String> isLong = text -> text.length() > 5;
        System.out.println("Is 'Hello' long? " + isLong.test("Hello"));     // false
        System.out.println("Is 'Welcome' long? " + isLong.test("Welcome"));  // true
        
        // Example 3: Check if person is adult
        Predicate<Integer> isAdult = age -> age >= 18;
        System.out.println("Is 25 adult? " + isAdult.test(25));  // true
        System.out.println("Is 16 adult? " + isAdult.test(16));  // false
        
        // Predicate with collections
        System.out.println("\n--- PREDICATE WITH COLLECTIONS ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter even numbers
        List<Integer> evenNumbers = numbers.stream()
            .filter(isEven)  // Using our predicate
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // Filter numbers greater than 5
        Predicate<Integer> greaterThan5 = n -> n > 5;
        List<Integer> bigNumbers = numbers.stream()
            .filter(greaterThan5)
            .collect(Collectors.toList());
        System.out.println("Numbers > 5: " + bigNumbers);
        
        // Combining predicates
        System.out.println("\n--- COMBINING PREDICATES ---");
        Predicate<Integer> evenAndGreaterThan5 = isEven.and(greaterThan5);
        List<Integer> evenBigNumbers = numbers.stream()
            .filter(evenAndGreaterThan5)
            .collect(Collectors.toList());
        System.out.println("Even numbers > 5: " + evenBigNumbers);
        
        // Using OR
        Predicate<Integer> lessThan3 = n -> n < 3;
        Predicate<Integer> evenOrSmall = isEven.or(lessThan3);
        List<Integer> evenOrSmallNumbers = numbers.stream()
            .filter(evenOrSmall)
            .collect(Collectors.toList());
        System.out.println("Even OR small numbers: " + evenOrSmallNumbers);
        
        // Using NOT
        Predicate<Integer> notEven = isEven.negate();
        List<Integer> oddNumbers = numbers.stream()
            .filter(notEven)
            .collect(Collectors.toList());
        System.out.println("Odd numbers: " + oddNumbers);
        
        System.out.println();
    }
    
    // 2. FUNCTION - THE TRANSFORMER
    public static void functionTutorial() {
        System.out.println("2. FUNCTION<T,R> - THE TRANSFORMER:");
        System.out.println("====================================");
        System.out.println("Purpose: Transform input of type T to output of type R");
        System.out.println("Method: R apply(T t)\n");
        
        // Basic Function examples
        System.out.println("--- BASIC FUNCTION EXAMPLES ---");
        
        // Example 1: Convert string to uppercase
        Function<String, String> toUpperCase = text -> text.toUpperCase();
        System.out.println("'hello' -> " + toUpperCase.apply("hello"));
        
        // Example 2: Convert string to length
        Function<String, Integer> getLength = text -> text.length();
        System.out.println("'Hello' length -> " + getLength.apply("Hello"));
        
        // Example 3: Convert number to string
        Function<Integer, String> numberToString = num -> "Number: " + num;
        System.out.println("5 -> " + numberToString.apply(5));
        
        // Example 4: Square a number
        Function<Integer, Integer> square = num -> num * num;
        System.out.println("5 squared -> " + square.apply(5));
        
        // Function with collections
        System.out.println("\n--- FUNCTION WITH COLLECTIONS ---");
        List<String> names = Arrays.asList("alice", "bob", "charlie", "david");
        
        // Transform all names to uppercase
        List<String> upperNames = names.stream()
            .map(toUpperCase)  // Using our function
            .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperNames);
        
        // Get lengths of all names
        List<Integer> nameLengths = names.stream()
            .map(getLength)
            .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);
        
        // Combining functions
        System.out.println("\n--- COMBINING FUNCTIONS ---");
        Function<String, String> addPrefix = text -> "Mr. " + text;
        Function<String, String> addSuffix = text -> text + " Jr.";
        
        // Compose functions: first addPrefix, then toUpperCase
        Function<String, String> prefixAndUpper = addPrefix.andThen(toUpperCase);
        System.out.println("'john' -> " + prefixAndUpper.apply("john"));
        
        // Compose functions: first toUpperCase, then addPrefix
        Function<String, String> upperAndPrefix = toUpperCase.andThen(addPrefix);
        System.out.println("'john' -> " + upperAndPrefix.apply("john"));
        
        // Chain multiple transformations
        Function<String, String> fullTransform = toUpperCase
            .andThen(addPrefix)
            .andThen(addSuffix);
        System.out.println("'john' -> " + fullTransform.apply("john"));
        
        System.out.println();
    }
    
    // 3. SUPPLIER - THE PROVIDER
    public static void supplierTutorial() {
        System.out.println("3. SUPPLIER<T> - THE PROVIDER:");
        System.out.println("===============================");
        System.out.println("Purpose: Provide/create values without taking input");
        System.out.println("Method: T get()\n");
        
        // Basic Supplier examples
        System.out.println("--- BASIC SUPPLIER EXAMPLES ---");
        
        // Example 1: Generate random number
        Supplier<Integer> randomNumber = () -> (int) (Math.random() * 100);
        System.out.println("Random number 1: " + randomNumber.get());
        System.out.println("Random number 2: " + randomNumber.get());
        System.out.println("Random number 3: " + randomNumber.get());
        
        // Example 2: Get current time
        Supplier<String> currentTime = () -> new Date().toString();
        System.out.println("Current time: " + currentTime.get());
        
        // Example 3: Create new person
        Supplier<Person> createPerson = () -> new Person("John", 25);
        Person person1 = createPerson.get();
        Person person2 = createPerson.get();
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);
        
        // Example 4: Generate UUID
        Supplier<String> generateId = () -> UUID.randomUUID().toString();
        System.out.println("Generated ID 1: " + generateId.get().substring(0, 8));
        System.out.println("Generated ID 2: " + generateId.get().substring(0, 8));
        
        // Supplier with collections
        System.out.println("\n--- SUPPLIER WITH COLLECTIONS ---");
        
        // Generate list of random numbers
        Supplier<List<Integer>> randomNumbers = () -> {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                nums.add((int) (Math.random() * 10));
            }
            return nums;
        };
        
        List<Integer> numbers1 = randomNumbers.get();
        List<Integer> numbers2 = randomNumbers.get();
        System.out.println("Random numbers 1: " + numbers1);
        System.out.println("Random numbers 2: " + numbers2);
        
        // Supplier for default values
        Supplier<String> defaultName = () -> "Unknown";
        Supplier<Integer> defaultAge = () -> 0;
        
        System.out.println("Default name: " + defaultName.get());
        System.out.println("Default age: " + defaultAge.get());
        
        System.out.println();
    }
    
    // 4. CONSUMER - THE DOER
    public static void consumerTutorial() {
        System.out.println("4. CONSUMER<T> - THE DOER:");
        System.out.println("===========================");
        System.out.println("Purpose: Perform actions with input, return nothing");
        System.out.println("Method: void accept(T t)\n");
        
        // Basic Consumer examples
        System.out.println("--- BASIC CONSUMER EXAMPLES ---");
        
        // Example 1: Print something
        Consumer<String> printer = text -> System.out.println("Printing: " + text);
        printer.accept("Hello World");
        printer.accept("Java is awesome");
        
        // Example 2: Save to file (simulated)
        Consumer<String> fileSaver = text -> {
            System.out.println("Saving to file: " + text);
            // In real code, you would write to actual file
        };
        fileSaver.accept("Important data");
        
        // Example 3: Send email (simulated)
        Consumer<String> emailSender = message -> {
            System.out.println("Sending email: " + message);
            // In real code, you would send actual email
        };
        emailSender.accept("Meeting at 3 PM");
        
        // Consumer with collections
        System.out.println("\n--- CONSUMER WITH COLLECTIONS ---");
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date");
        
        // Print all fruits
        System.out.print("All fruits: ");
        fruits.forEach(printer);
        
        // Custom consumer for formatting
        Consumer<String> formatter = fruit -> System.out.println("FRUIT: " + fruit.toUpperCase());
        System.out.println("Formatted fruits:");
        fruits.forEach(formatter);
        
        // Consumer for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Consumer<Integer> numberProcessor = num -> {
            int doubled = num * 2;
            System.out.println(num + " doubled = " + doubled);
        };
        System.out.println("Processing numbers:");
        numbers.forEach(numberProcessor);
        
        // Combining consumers
        System.out.println("\n--- COMBINING CONSUMERS ---");
        Consumer<String> logger = text -> System.out.println("LOG: " + text);
        Consumer<String> notifier = text -> System.out.println("NOTIFY: " + text);
        
        // Chain consumers
        Consumer<String> logAndNotify = logger.andThen(notifier);
        logAndNotify.accept("User logged in");
        
        System.out.println();
    }
    
    // 5. COMBINING ALL TOGETHER
    public static void combiningAll() {
        System.out.println("5. COMBINING ALL FUNCTIONAL INTERFACES:");
        System.out.println("=======================================");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 17),
            new Person("Charlie", 30),
            new Person("David", 16),
            new Person("Eve", 22)
        );
        
        System.out.println("Original people: " + people);
        
        // Step 1: PREDICATE - Filter adults
        Predicate<Person> isAdult = person -> person.getAge() >= 18;
        
        // Step 2: FUNCTION - Transform to names
        Function<Person, String> getName = Person::getName;
        
        // Step 3: CONSUMER - Print each name
        Consumer<String> printName = name -> System.out.println("Adult: " + name);
        
        // Step 4: SUPPLIER - Create summary
        Supplier<String> createSummary = () -> "Processing complete!";
        
        // Combine all together
        System.out.println("\nProcessing adults:");
        people.stream()
            .filter(isAdult)           // PREDICATE: Filter adults
            .map(getName)             // FUNCTION: Get names
            .forEach(printName);      // CONSUMER: Print names
        
        String summary = createSummary.get();  // SUPPLIER: Get summary
        System.out.println(summary);
        
        // Another example: Process and transform
        System.out.println("\n--- ANOTHER COMBINATION EXAMPLE ---");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<Integer, Integer> square = n -> n * n;
        Consumer<Integer> printResult = n -> System.out.println("Result: " + n);
        Supplier<String> doneMessage = () -> "All even squares calculated!";
        
        System.out.println("Calculating squares of even numbers:");
        numbers.stream()
            .filter(isEven)           // PREDICATE: Keep even numbers
            .map(square)              // FUNCTION: Square them
            .forEach(printResult);    // CONSUMER: Print results
        
        System.out.println(doneMessage.get());  // SUPPLIER: Done message
        
        System.out.println();
    }
    
    // 6. REAL WORLD EXAMPLES
    public static void realWorldExamples() {
        System.out.println("6. REAL WORLD EXAMPLES:");
        System.out.println("=======================");
        
        // Example 1: User Validation System
        System.out.println("--- USER VALIDATION SYSTEM ---");
        
        List<User> users = Arrays.asList(
            new User("john@email.com", "password123", 25),
            new User("jane@email.com", "weak", 16),
            new User("bob@email.com", "strongpass", 30),
            new User("alice@email.com", "123", 22)
        );
        
        // Predicates for validation
        Predicate<User> hasValidEmail = user -> user.getEmail().contains("@");
        Predicate<User> hasStrongPassword = user -> user.getPassword().length() >= 8;
        Predicate<User> isAdult = user -> user.getAge() >= 18;
        
        // Function to get user info
        Function<User, String> getUserInfo = user -> 
            user.getEmail() + " (age: " + user.getAge() + ")";
        
        // Consumer to send welcome email
        Consumer<String> sendWelcomeEmail = info -> 
            System.out.println("Welcome email sent to: " + info);
        
        // Supplier for validation report
        Supplier<String> generateReport = () -> "Validation report generated at " + new Date();
        
        // Process valid users
        System.out.println("Valid users:");
        users.stream()
            .filter(hasValidEmail.and(hasStrongPassword).and(isAdult))
            .map(getUserInfo)
            .forEach(sendWelcomeEmail);
        
        System.out.println(generateReport.get());
        
        // Example 2: Product Processing
        System.out.println("\n--- PRODUCT PROCESSING ---");
        
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1000.0, "Electronics"),
            new Product("Book", 20.0, "Education"),
            new Product("Phone", 800.0, "Electronics"),
            new Product("Pen", 2.0, "Stationery")
        );
        
        // Predicate: Expensive products
        Predicate<Product> isExpensive = product -> product.getPrice() > 100;
        
        // Function: Apply discount
        Function<Product, Product> applyDiscount = product -> {
            double discountedPrice = product.getPrice() * 0.9; // 10% discount
            return new Product(product.getName(), discountedPrice, product.getCategory());
        };
        
        // Consumer: Save to database
        Consumer<Product> saveToDatabase = product -> 
            System.out.println("Saved to DB: " + product.getName() + " - $" + product.getPrice());
        
        // Supplier: Generate invoice number
        Supplier<String> generateInvoiceNumber = () -> "INV-" + System.currentTimeMillis();
        
        System.out.println("Processing expensive products with discount:");
        String invoiceNumber = generateInvoiceNumber.get();
        System.out.println("Invoice Number: " + invoiceNumber);
        
        products.stream()
            .filter(isExpensive)
            .map(applyDiscount)
            .forEach(saveToDatabase);
        
        System.out.println("\n=== END OF FUNCTIONAL INTERFACES TUTORIAL ===");
    }
}

// Helper classes for examples
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

class User {
    private String email;
    private String password;
    private int age;
    
    public User(String email, String password, int age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }
    
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getAge() { return age; }
}

class Product {
    private String name;
    private double price;
    private String category;
    
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    
    @Override
    public String toString() {
        return name + " - $" + price + " (" + category + ")";
    }
}
