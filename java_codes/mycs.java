public class Mycs 
{
    public static void main(String[] args)
    {
        person p =new person();
        p.name="Rahul";
        p.age=21;
        p.speak();
        student s=new student();
        s.div();
        System.out.println(s.city);
        horse h=new horse();    
        h.walk();
        h.eats();
        leap l=new leap();
        l.lep();
    }
}
class person
{
    String name;
    int age;
    String city = "Bgm";
    public void speak()
    {
        System.out.println("My name is "+name+" and my age is "+age);
    }

}
class student extends person
{
    String name="raj";
    int age=21;
        public void div()
    {
        System.out.println("My name is "+name+" and my age is "+age);
    }
}

abstract class animal
{
    abstract void walk(); 

    public void eats()
    {
        System.out.println("Eats");
    }
}
class horse extends animal
{
    public void walk()
    {
        System.out.println("Walks on 4 legs");
    }
}

class leap
{
    public void lep()
    {
        int year= 2025;
        if(year%4==0)
        {
            if(year%100==0)
            {
                if(year%400==0)
                {
                    System.out.println("the year "+year+" is a Leap year");
                }
                else
                {
                    System.out.println("the year "+year+" is not a Leap year");
                }
            }
            System.out.println("the year "+year+" is a Leap year");
        }
        else
        {
            System.out.println("the year "+year+" is not a Leap year");
        }
    
    }
}

