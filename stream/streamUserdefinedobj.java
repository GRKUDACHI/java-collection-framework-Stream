
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.*;


public class streamUserdefinedobj
{
    public static  void main(String[] args)
    {
        // List<Employee> emp = Arrays.asList(
        //         new Employee(202,"Marketing",20000,"Karnataka","Rakesh"),
        //         new Employee(203,"Developer",50000,"Delhi","Happy"),
        //         new Employee(292,"Developer",55000,"Karnataka","Girish"),
        //         new Employee(202,"Developer",35000,"tamil nadu","Himanshu"),
        //         new Employee(202,"Accounts",25000,"mumbai","sid"),
        //         new Employee(202,"HR",45000,"Karnataka","ram")

        // );

        List<Employee> emp = new ArrayList<>();

        emp.add(new Employee(202,"Marketing",20000,"Karnataka","Rakesh"));
        emp.add(new Employee(232,"Developer",55000,"Karnataka","Happy"));
        emp.add(new Employee(22,"Developer",58000,"Karnataka","Harry"));
        emp.add(new Employee(242,"Developer",60000,"Mumbai","Girish"));
        emp.add(new Employee(402,"Accounts",25000,"Delhi","Himanshu"));
        emp.add(new Employee(302,"Digital",45000,"Andhra","sid"));
        emp.add(new Employee(542,"HR",20000,"Mumbai","guru"));
        
        //Find the employees belong to Karnataka city 
        Predicate<Employee> pred  = em->em.getCity().equals("Karnataka");
        // emp.stream()
        // .filter(pred)
        // .forEach(System.out::println);

        //Find the Employee slary More than 40000
        Predicate<Employee>  salary  = sl->sl.getSalary()>40000;
         emp.stream().filter(salary)
        .forEach(System.out::println);
    }
}

class Employee
{
    private int e_id;
    private String name;
    private String city;
    private double salary;
    private String department;

    public Employee(int e_id, String department, double salary, String city, String name)
    {
        this.e_id = e_id;
        this.department = department;
        this.salary = salary;
        this.city = city;
        this.name = name;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) 
    {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "e_id=" + e_id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
