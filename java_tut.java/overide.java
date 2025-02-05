public class overide
{
    public static void main(String[] args)
    {
        human h=new human();
        h.run();
    }
}
class Animals
{
    public void run()
    {
        System.out.println("Animals Run with 4 legs");
    }   
}
class human extends Animals
{
    public void run()
    {
        super.run();
        System.out.println("Human Run with 2 legs");
    }
}
