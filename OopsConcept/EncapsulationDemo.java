
//Binding Logical Related data and function in common related place called Encapsulation
//by using Encapsulation we can achive data security
//Code Maintainability
//Easy to understand
//we cn manage or control by using getter() and setter() Method
//If the class contains Private variables and getter() and setter() method then that Class called Bean Class

public class EncapsulationDemo
{
    public static void main(String[] args)
    {
        Employee emp = new Employee();
        emp.setName("Happy");
        emp.setSalary(50000);

        System.out.println("Employee Name : "+emp.getName());
        System.out.println("Employee Salary : "+emp.getSalary());
    }
}
class Employee
{
    private String name;
    private int Salary;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSalary()
    {
        return Salary;
    }

    public void setSalary(int Salary)
    {
       this.Salary = Salary;
    }
}
