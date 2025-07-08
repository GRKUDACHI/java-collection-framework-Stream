
//Interface inside Static method
//In java 8 we have interface with default methods and Static methods
//Static method in interface method with body oR method with defined body
// We cannot Override Static method in child class OR implements class
//if we override Method then its Treated Another Method Because Child class cannot see oR access static method in child class
//We can Access Static method by using Interface name Because Static methods and Static variables are Refers Class Name and interface names

//In java 8 Interface Also have Manin Method
//Means we can write Main Method inside Interface
public class StaticInterface
{
    public static void main(String[] args)
    {
        test.sn();
        res.sn();
        test t = new test();
        t.ts();
    }
}

interface def
{
    public static void main(String[] args)
    {
        System.out.println("Interface with Main Methods in java 8");
    }
}
interface res
{
    static void sn()
    {
        System.out.println("Static method 1");
    }
    default void ts()
    {
        System.out.println("default method");
    }
}
class test implements res
{
    static void sn()
    {
        System.out.println("Child static method");
    }

}