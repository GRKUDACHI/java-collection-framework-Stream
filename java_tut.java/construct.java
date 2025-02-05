public class test
{
    public static void main(String[] args)
    {
        doct d1=new doct("Rahul",21,"B.Tech");
        doct d2=new doct("Raj", 22, "B.Tech");
        d1.display();
        d2.display();
    }
}
class doct
{
    public String name ;
    public int age ;
    public String quly;

    public doct (String name, int age, String quly)
    {
        this.name = name;
        this.age = age;
        this. quly = quly;
    }
    public void display()
    {
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Qualification: "+quly);
    }
}
