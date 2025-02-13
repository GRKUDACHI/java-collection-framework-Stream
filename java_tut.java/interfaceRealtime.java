public class interfaceRealtime
{
    public static void main(String[] args)
    {
        car c = new car();
        c.enginetype();
        c.Transmissiontype();
    }
}
interface carengine
{
    public void enginetype();
}

interface transmission
{
    public void Transmissiontype();
}

class car implements carengine,transmission
{
    public void enginetype()
    {
        System.out.println("The SportsCar Engine have 8V Engine");
    }

    public void Transmissiontype()
    {
        System.out.println("SportsCar has an automatic transmission");
    }
}