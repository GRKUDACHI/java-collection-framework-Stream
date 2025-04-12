// by using Constructor we can initialize object
// Constructor Don't have Return type
//Constructor use this keyword to initialize current object
//we have private Constructor
// we can't override Constructor Because Constructor don't have return type

public class ConstructorDemo
{
    public static void main(String[] args)
    {
        Doctor d1 = new Doctor("Happy","MBBS",30);
        Doctor d2 = new Doctor("Rakesh","BHMS",30);
        Doctor d3 = new Doctor("Manju","MD",30);

        d1.display();
        d2.display();
        d3.display();

        Test t1 = new Test();
        System.out.println(t1);

        Rest s = new Rest(50);
        System.out.println(s);
    }
}

class Doctor
{
    String name ;
    String qua;
    int age;

    public Doctor(String name, String qua, int age)
    {
        this.name = name;
        this.qua = qua;
        this.age = age;
    }

    public void display()
    {
        System.out.println(name);
        System.out.println(qua);
        System.out.println(age);
    }
}

//Parameter Less Constructor
class Test
{
    public Test ()
    {
        System.out.println("This is Parameter Less Constructor ");
    }
}

//With Parameter Constructor

class Rest
{
    public Rest(int a)
    {
        System.out.println("This is Parameter with  Constructor ");
    }
}
