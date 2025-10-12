/**
 * SINGLETON CLASS - SUPER SIMPLE EXPLANATION
 * ==========================================
 * 
 * This is the SIMPLEST way to understand Singleton class!
 * 
 * Think of Singleton like this:
 * 🏢 There's only ONE BOSS in a company
 * 🏠 There's only ONE HOUSE for your family
 * 🚗 There's only ONE CAR in your garage
 * 
 * In programming, Singleton means:
 * - Only ONE object can be created from a class
 * - No matter how many times you try to create it, you get the SAME object
 */

public class SingletonSuperSimple {
    
    public static void main(String[] args) {
        System.out.println("=== SINGLETON - SUPER SIMPLE EXPLANATION ===\n");
        
        // Let's see the difference between normal class and singleton
        showDifference();
        
        // Let's see singleton in action
        showSingletonInAction();
        
        // Real example - Game Score Manager
        showGameScoreExample();
        
        // Simple steps to create singleton
        showHowToCreateSingleton();
    }
    
    public static void showDifference() {
        System.out.println("1. NORMAL CLASS vs SINGLETON CLASS");
        System.out.println("==================================");
        
        System.out.println("NORMAL CLASS (creates NEW object each time):");
        // Normal class - creates new object each time
        NormalCar car1 = new NormalCar("Red");
        NormalCar car2 = new NormalCar("Blue");
        
        System.out.println("car1 color: " + car1.getColor());
        System.out.println("car2 color: " + car2.getColor());
        System.out.println("Are they the same car? " + (car1 == car2)); // false
        
        System.out.println("\nSINGLETON CLASS (always gives SAME object):");
        // Singleton class - always gives same object
        SingletonCar singletonCar1 = SingletonCar.getInstance();
        SingletonCar singletonCar2 = SingletonCar.getInstance();
        
        singletonCar1.setColor("Green");
        System.out.println("singletonCar1 color: " + singletonCar1.getColor());
        System.out.println("singletonCar2 color: " + singletonCar2.getColor());
        System.out.println("Are they the same car? " + (singletonCar1 == singletonCar2)); // true
        
        System.out.println();
    }
    
    public static void showSingletonInAction() {
        System.out.println("2. SINGLETON IN ACTION");
        System.out.println("======================");
        
        // Create multiple references to singleton
        SingletonCar car1 = SingletonCar.getInstance();
        SingletonCar car2 = SingletonCar.getInstance();
        SingletonCar car3 = SingletonCar.getInstance();
        
        System.out.println("Creating 3 car references...");
        System.out.println("car1: " + car1);
        System.out.println("car2: " + car2);
        System.out.println("car3: " + car3);
        
        // Change color using car1
        car1.setColor("Red");
        System.out.println("\nSetting color to Red using car1...");
        
        // Check color using car2 and car3
        System.out.println("car2 color: " + car2.getColor());
        System.out.println("car3 color: " + car3.getColor());
        
        System.out.println("See? All three are the SAME car!");
        System.out.println();
    }
    
    public static void showGameScoreExample() {
        System.out.println("3. REAL EXAMPLE - GAME SCORE MANAGER");
        System.out.println("=====================================");
        
        // In a game, you want only ONE score manager
        System.out.println("Creating game score manager...");
        
        GameScoreManager score1 = GameScoreManager.getInstance();
        GameScoreManager score2 = GameScoreManager.getInstance();
        GameScoreManager score3 = GameScoreManager.getInstance();
        
        System.out.println("score1: " + score1);
        System.out.println("score2: " + score2);
        System.out.println("score3: " + score3);
        
        // Add points using different references
        score1.addPoints(100);
        System.out.println("\nAdded 100 points using score1");
        
        score2.addPoints(50);
        System.out.println("Added 50 points using score2");
        
        score3.addPoints(25);
        System.out.println("Added 25 points using score3");
        
        // Check total score using any reference
        System.out.println("\nTotal score (using score1): " + score1.getTotalScore());
        System.out.println("Total score (using score2): " + score2.getTotalScore());
        System.out.println("Total score (using score3): " + score3.getTotalScore());
        
        System.out.println("All three are the SAME score manager!");
        System.out.println();
    }
    
    public static void showHowToCreateSingleton() {
        System.out.println("4. HOW TO CREATE SINGLETON - SIMPLE STEPS");
        System.out.println("==========================================");
        
        System.out.println("""
            STEP-BY-STEP GUIDE TO CREATE SINGLETON:
            
            STEP 1: Make constructor private
            --------------------------------
            private MySingleton() {
                // This prevents others from creating objects directly
            }
            
            STEP 2: Create a static instance variable
            ----------------------------------------
            private static MySingleton instance;
            
            STEP 3: Create a static method to get instance
            ----------------------------------------------
            public static MySingleton getInstance() {
                if (instance == null) {
                    instance = new MySingleton();
                }
                return instance;
            }
            
            THAT'S IT! Now you have a Singleton class!
            
            HOW TO USE:
            MySingleton obj1 = MySingleton.getInstance();
            MySingleton obj2 = MySingleton.getInstance();
            // obj1 and obj2 are the SAME object!
            """);
        
        System.out.println();
    }
}

// ===========================================
// SUPPORTING CLASSES
// ===========================================

/**
 * NORMAL CAR CLASS (NOT SINGLETON)
 * Creates a new car each time
 */
class NormalCar {
    private String color;
    
    public NormalCar(String color) {
        this.color = color;
        System.out.println("New Normal Car created with color: " + color);
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}

/**
 * SINGLETON CAR CLASS
 * Always gives the same car instance
 */
class SingletonCar {
    // Step 1: Create a static instance variable
    private static SingletonCar instance;
    
    // Step 2: Make constructor private
    private SingletonCar() {
        System.out.println("Singleton Car created!");
    }
    
    // Step 3: Create a static method to get instance
    public static SingletonCar getInstance() {
        if (instance == null) {
            instance = new SingletonCar();
        }
        return instance;
    }
    
    // Add some properties and methods
    private String color = "White";
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}

/**
 * REAL-WORLD EXAMPLE: GAME SCORE MANAGER
 * In a game, you want only ONE score manager
 */
class GameScoreManager {
    private static GameScoreManager instance;
    private int totalScore = 0;
    
    private GameScoreManager() {
        System.out.println("Game Score Manager created!");
    }
    
    public static GameScoreManager getInstance() {
        if (instance == null) {
            instance = new GameScoreManager();
        }
        return instance;
    }
    
    public void addPoints(int points) {
        totalScore += points;
        System.out.println("Added " + points + " points. Total: " + totalScore);
    }
    
    public int getTotalScore() {
        return totalScore;
    }
    
    public void resetScore() {
        totalScore = 0;
        System.out.println("Score reset to 0");
    }
}

/*
 * ===========================================
 * SINGLETON - EASY TO REMEMBER
 * ===========================================
 * 
 * 🎯 WHAT IS SINGLETON?
 * - A class that can have only ONE instance
 * - Like having only ONE house, ONE car, ONE boss
 * 
 * 🔧 HOW TO CREATE SINGLETON?
 * 1. Make constructor private
 * 2. Create static instance variable
 * 3. Create static getInstance() method
 * 
 * 💡 WHEN TO USE?
 * - Game score managers
 * - Database connections
 * - Logging systems
 * - Configuration managers
 * - Any shared resource
 * 
 * 🚀 BENEFITS:
 * ✓ Only one instance exists
 * ✓ Saves memory
 * ✓ Shared data across your program
 * ✓ Easy to access from anywhere
 * 
 * 🎓 REMEMBER:
 * - Private constructor
 * - Static instance
 * - Static getInstance() method
 * - Use for shared resources
 * 
 * EXAMPLE:
 * MySingleton obj1 = MySingleton.getInstance();
 * MySingleton obj2 = MySingleton.getInstance();
 * // obj1 and obj2 are the SAME object!
 */
