//abstract ************
//abstract consisit both method without body and concreat method (method with body)
// we cannot create object for abstract class and we can access by creating child or sub class object 
// we cannot override
//we cannot achive 100 of abstraction because its contain both method without body and concreat method (method with body)

public class tut
{
    public static void main(String[] args)
    {
       impli2 im = new impli2();
       im.f1();
       
       humans hm = new humans();
       hm.run();
    }
}
abstract class impli
{
   public abstract void f1();
   public void f2()
   {
    System.out.println("This concreat method");
   }
}
class impli2 extends impli
{
    public void f1()
    {
        super.f2();
        System.out.println("Method with body in child or drivided class");
    }
}

 

abstract class animal
{
    abstract void run();
    void display()
    {
        System.out.println("Generation between animals to humans");
    }
}
class humans extends animal
{
    void run()
    {
        System.out.println("humns can rum with 2 legs");
    }
}
 