import java.util.*;

class comparing
{
    public static void main (String[] args)
    {
        List<Student> stu = new ArrayList<>();
        
        stu.add(new Student("happy",8));
        stu.add(new Student("harry",9));
        stu.add(new Student("raj",6));
        stu.add(new Student("pratik",7));

        Comparator<Student> compare = Comparator.comparing(Student::getScgpa).reversed();

        for(Student s : stu)
        {
            System.out.println(s.getName()+" : "+s.getScgpa());
        }
    }
}
class Student
{
   private String name ;
   private int scgpa;

   public Student(String name, int scgpa)
   {
    this.name = name;
    this.scgpa = scgpa;
   }

   public String getName()
   {
    return name;
   }

   public int getScgpa()
   {
    return scgpa;
   }
}