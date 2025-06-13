
public class multipleInheritance
{
    public static  void main(String [] args)
    {
        C ci = new C();

        ci.showA();
        ci.showB();

        D dc = new D();
        dc.showA();
        dc.showB();
        dc.Shows();
    }
}
interface  A
{
    public void showA();
}

interface B
{
    public void showB();
}

class C implements A, B
{
    public void showA()
    {
        System.out.println("Interface Method A");
    }

    public void showB()
    {
        System.out.println("Interface Method B");
    }
}
class D extends C
{
    public void Shows()
    {
        System.out.println("Multiple inheritance and implements in one place ");
    }
}
