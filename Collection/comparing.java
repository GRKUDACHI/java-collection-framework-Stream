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

        Comparator<Student> comparator = Comparator.comparing(Student::getScgpa).reversed().thenComparing(Student::getName);
        stu.sort(comparator);
        for(Student s : stu)
        {
            System.out.println(s.getName()+" : "+s.getScgpa());
            // System.out.println(s.getTotlaCgpa());

        }
    }
}
class Student
{
   private String name ;
   private int scgpa;
//    private int totlaCgpa =10;

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

//    public int getTotlaCgpa()
//    {
//     return totlaCgpa;
//    }
//    public void setTotlaCgpa(int c)
//    {
//      totlaCgpa = c;
//    } 
}