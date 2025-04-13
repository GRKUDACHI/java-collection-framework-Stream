
//Hiding implementation Details and Exposing Details which is required for users
//By using 'abstract' keyword we can create class
//We can't Create object for abstract class
// abstract class having both method concrete methods (method with body ) and abstract method (method without body)
// we can abstract 0 to 100% because we have both method concrete and abstract method 

public class AbstractionDemo
{
    public static void main(String [] args)
    {
        Dog d = new Dog();
        d.eat();
        d.sound();
    }
}
abstract class Animals
{
    abstract void sound();

    public void eat()
    {
        System.out.println("Animal eats food ");
    }
}
class Dog extends Animals
{
    public void sound()
    {
        System.out.println("Dog barks");
    }
}
