
//Java 8 Interface Enhancements
// 1)Default method and Static method in interface
//default method is method with body by using keyword default
//These enhancements allow interfaces to have method bodies without breaking existing code.
//You cannot override or inherit static methods from an interface

public class  Interface_java8
{
    public static void main (String[] args)
    {
        car c = new car();
        c.mycar();
        test.mybike();

    }
}
interface vechial
{
    default void mycar()
    {
        System.out.println("Default method in new Interface feature in java 8");
    }

}
class car implements vechial
{

}

interface test
{
    static void mybike()
    {
        System.out.println("Static method in interface new  feature in java 8");
    }
}
