
import java.security.PublicKey;

//Java 8 we can Create Default method inside Interface
//Default in interface Method with body
//we can also access Default method without override in child class  OR without Implement in child class
//if we  have 2 different  interface with Same method name when we Access the Ambiguity occur Then Java got conflict which one called first then we can Override with super() method
//here we have both interface having same method name then we can Override the Method And inside we can use Super() method
public class MultipleDefault
{
    public static void main(String [] args)
    {
        Multi m = new Multi();
        m.m1();
    }
}

interface A
{
    default void m1()
    {
        System.out.println("Default Interface Method 1");
    }
}

interface B
{
    default void m1()
    {
        System.out.println("Default Interface Method 2");
    }
}
class Multi implements A,B
{
    public void m1()
    {
        A.super.m1();  //Resolving ambiguity by specifying which interface's default method called first
        B.super.m1();
        System.out.println("My interface M1 Method");
    }
}
