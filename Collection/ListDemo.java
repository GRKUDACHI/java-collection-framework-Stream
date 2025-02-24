import java.io.PrintStream;
import java.sql.ClientInfoStatus;
import java.util.*;

class sortFruit implements Comparator<String>
{

    @Override
    public int compare(String o1, String o2) {
        return o2.length()- o1.length();
    }
}

class numsort implements Comparator<Integer>
{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2-o1;
    }
}
public class ListDemo
{
    public static void main(String[] args)
    {
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(90);
        lst.add(80);
        lst.add(70);
        lst.add(60);
        lst.add(50);

        System.out.println(lst);
        System.out.println("ArrayList first Index is "+lst.get(1));
        int LengthIndex = lst.size();

        System.out.println("Arraylist Last index is "+LengthIndex);

        for(int j=0; j<lst.size(); j++)
        {
            System.out.println(lst.get(j));
        }
        System.out.println("ArrayList size is "+lst.size());

        for(int z : lst)
        {
            System.out.println(z);
        }
        lst.sort(new numsort());

        System.out.println("DESC Order numbers "+lst);


        String[] fruits = {"Banana", "Orange","Apple","WaterMelon"};
        List<String> fruit = new ArrayList<>(Arrays.asList(fruits));
        System.out.println(fruit);
        fruit.sort((o1,o2) ->o1.length()-o2.length());
        System.out.println("Asc order fruits"+fruit);
        fruit.sort(new sortFruit());
        System.out.println("Desc order fruits"+fruit);

         String[] weekdays = {"Monday", "Tuesday","Wednesday","Thursday"};
         List<String> wks = new ArrayList<>(Arrays.asList(weekdays));
         System.out.println(wks);

         List<String> weeknedays = new ArrayList<>(wks);
         weeknedays.add("Friday");
         weeknedays.add("Saturday");
         weeknedays.add("Sunday");

         System.out.println("FULL Days "+weeknedays);

         List<Employee> emp = new ArrayList<>();
         emp.add(new Employee("pintu",15000));
         emp.add(new Employee("happy",60000));
         emp.add(new Employee("sanga",87000));
         emp.add(new Employee("ganesh",15000));

         Comparator<Employee> empSort = Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName);
         emp.sort(empSort);
         for(Employee emps: emp)
         {
             System.out.println(emps.getName() +" : "+emps.getSalary());
         }

         // LinkedList is Linear Data Structure each Element having object called  node
         // Each node consist 2 part data and Pointer
         // Data part contains original Element and one pointer  represent/Addressed  next node
         // Another pointer represent/Addressed Previous Node
         // Linked List is better to Insert and deletion is Fast 0(1) because there in no Shifting method like ArrayList
         // Accessing LinkedList is slow 0(n) Because its traversing from begging to end node
         // LinkedList Memory overhead Because need Extra Memory  to store Represent nest ans previous node
         LinkedList<Integer> lnk = new LinkedList<>();
         lnk.add(20);
         lnk.add(30);
         lnk.add(40);
         System.out.println("Linked List Elements "+lnk);
         lnk.addFirst(10);
         lnk.addLast(50);
         int FirstElement = lnk.getFirst();
         int LastElement = lnk.getLast();
         System.out.println(lnk);
        System.out.println("LinkedList First Element is "+FirstElement);
        System.out.println("LinkedList First Element is "+LastElement);
        lnk.removeFirst();
        System.out.println(lnk);
        lnk.removeLast();
        System.out.println(lnk);
        System.out.println("linkedList totol size or length is "+lnk.size());
        for(int k =0; k<lnk.size(); k++)
        {
            System.out.println(lnk.get(k));
        }

        List<String> frts = new ArrayList<>(Arrays.asList("Orange","Apple","Banana","WaterMelon"));
        List<String> removefrts = new ArrayList<>(Arrays.asList("Apple","Banana"));
        frts.removeAll(removefrts);
        System.out.println(frts);

        // Vector  is part fo java collection and its capacity resizeable 2 time unlike  ArrayList is 1.5 time
        // we can Construct Specified Increment Capacity
        // Vector Also call Legacy class in java Because  Vector  is one of the Java released feature
        // Vector is thread-safe vector

        Vector<Integer> vtc = new Vector<>(5,3);
        vtc.add(100);
        vtc.add(200);
        vtc.add(300);
        vtc.add(400);
        vtc.add(500);
        vtc.add(600);
        System.out.println(vtc);
        System.out.println("Default Vector Capacity is "+vtc.capacity());
        System.out.println("after Specified Increment  Vector Capacity is "+vtc.capacity());

        // Stack is Part of java collection nd its Follows LIFO Principle
        // LIFO Last in First out which element  add Last when pop() then last element removed first
        // Stack is Extends Vector
        // Since Stack extends Vector then All vector Feature are Inherited in Stack Also and Thread-safe
        // Time Complexity ot Stack operation in 0(1)
        
        Stack<Integer> stk = new Stack<>();
        stk.push(50);
        stk.push(60);
        stk.push(70);
        stk.push(80);
        stk.push(90);
        System.out.println("Stack Elements "+stk);
        int pop = stk.pop();
        System.out.println("Stack removed last Add Element  First "+pop);
        int top_element = stk.peek();
        System.out.println("Stack TOP Element  Is "+top_element);
        stk.add(100);  // here Inherited vector feature also cze Stack Extends vector
        int stack_size = stk.size();
        System.out.println("The Stack size Is "+stack_size);
        System.out.println(stk);
        stk.pop();
        System.out.println(stk);


    }
}
class Employee
{
    private String name;
    private int salary;

    public Employee (String  name, int salary)
    {
        this.name = name;
        this.salary =salary;
    }

    public int getSalary()
    {
        return salary;
    }

    public String getName()
    {
        return name;
    }
}
