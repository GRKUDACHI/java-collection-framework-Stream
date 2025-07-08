package DefaultInterface.com;

//Function interface in interface have only one abstract Method and many numbers of default and static methods
//Function interface use Annotation "@FunctionalInterface" above the interface name
//Function interface use to invoked Lambda Expression
public class functioninter
{
    public static void main(String [] args)
    {
        rts rs = new rts();
        rs.tst();
        rs.def();
        rs.tst();
        rest.st();      // static method access by use Interface name
        rest.ts();   // static method access by use Interface name
    }

}
@FunctionalInterface
interface rest
{
  public void tst();

  default void sm()
  {
      System.out.println("Default method Inter ");
  }

    default void def()
    {
        System.out.println("Default method Inter 2 ");
    }

    static void st()
    {
        System.out.println("Static  method in interface 1");
    }

    static void ts()
    {
        System.out.println("Static Methods in interface 2");
    }

}

class rts implements rest
{
    public void tst()
    {
        System.out.println("Function Interface Imp method");
    }
}
