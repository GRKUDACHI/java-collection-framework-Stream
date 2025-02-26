 import java.util.*;
 import java.uti.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;
class AllListClass
{
   
//Comparator is interface
//Comparator is a part fo java collection used custom sort principle in java collection
//Comparator interface having one method  public int compare(T o1, T o2);
//if argument return with positive then sorted with ASC order
//if argument return with negative the sorted in DESC order
//if its equal then its compare both arguments and retun as it is

class sortnum implements Comparator<Integer>
{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1-o2;
    }
}
class sortfruit implements Comparator<String>
{

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}
public class deomlisall
{
    public static void main(String[] args)
    {
        //*********** ArrayList   AND Comparator *****************

        // Arraylist is java collection its dynamic  resizable array and order collection
        // by default capacity 10 internal construct
        // we can also construct default capacity
        // by access elements is very fast 0(1)
        // insert and delete internal working

        //(1) initial capacity is 10
        //(2) check initial capacity if is over then its increased 1.5 time of resize the capacity
        //(3) after resize the capacity all old array  element are copied to new capacity

        //removing the elements for arraylist
        //(1) checking the Index out of bound if the all elements  are there in within range of size
        //(2) after check range the removing elements from list and shift all right elements in lift to fill the gap
        //(3) decrement the 1 size element

         ArrayList<Integer> lst = new ArrayList<>();
         lst.add(48);
         lst.add(89);
         lst.add(90);
         lst.add(80);
         System.out.println(lst);
         lst.add(2,99);
         System.out.println(lst);
         int listsize = lst.size();
         System.out.println("second index  element "+lst.get(2));
         System.out.println("ArrayList total size "+listsize);

         lst.remove(4);
         lst.sort(new sortnum());
         System.out.println("ArrayLis with SOrt acs order "+lst);

         lst.sort((o1,o2) -> o2-o1);
        System.out.println("ArrayLis with SOrt with desc order "+lst);


        for(int j=0 ; j<lst.size(); j++)
         {
             System.out.println(lst.get(j));
         }

         System.out.println("arrayList with foreach");

         for(int z :lst)
         {
             System.out.println(z);
         }

         List<String > fruits = new ArrayList<>();

         fruits.add("watermelon");
         fruits.add("apple");
         fruits.add("banana");
         fruits.add("orange");
         fruits.sort(new sortfruit());
        System.out.println("Fruits sorted with asc order based on string length "+fruits);

        fruits.sort((o1,o2) -> o2.length() - o1.length());
        System.out.println("Fruits sorted with desc order "+fruits);


        for(int k =0; k<fruits.size(); k++)
        {
            System.out.println(fruits.get(k));
        }

        String[] weekdays = {"Monday","Tuesday","wednesday","thursday"};

        List<String> wks = new ArrayList<>(Arrays.asList(weekdays));
        System.out.println(wks);

        List<String> fulldays = new ArrayList<>(wks);
        fulldays.add("friday");
        fulldays.add("saturday");
        fulldays.add("sunday");

        System.out.println(fulldays);

        List<employee> emp = new ArrayList<>();
        emp.add(new employee("pintu ",15000));
        emp.add(new employee("happy ",25000));
        emp.add(new employee("ram ",50000));
        emp.add(new employee("sangam",90000));
        emp.add(new employee("ganesh ",15000));

        Comparator<employee> sortemp = Comparator.comparing(employee::getSalary).reversed().thenComparing(employee::getName);
        emp.sort(sortemp);
          for(employee employees : emp)
          {
              System.out.println(employees.getName()+" : "+employees.getSalary());
          }

          //*************Lin kedList********************

        //linked list is linear data structure each element having object called node
        //node containing 2 part 1)Data 2)pointer
        // data part consist element value
        //1 pointer represent next node and another pointer represented previous node or addressing previous node

        //data structure in better frequent insert and delete because there is no element shifting process like ArrayList 0(1)
        // accessing element is very slow due to linked list traversing one node to next node pointers
        // memory  overhear each node need to more memory to represent next and previous node

        LinkedList<Integer> lnk = new LinkedList<>();
          lnk.add(20);
          lnk.add(30);
          lnk.add(40);
          System.out.println("LinkedList elements "+lnk);
          lnk.addFirst(10);
          lnk.addLast(50);
        System.out.println("LinkedList elements "+lnk);
        lnk.add(0,89);
        System.out.println("LinkedList elements "+lnk);
        lnk.removeFirst();
        System.out.println("LinkedList elements "+lnk);
        System.out.println("LinkedList elements  size is "+lnk.size());
        int fstele = lnk.getFirst();
        System.out.println("LinkedList elements  first element  "+fstele);
        int latele = lnk.getLast();
        System.out.println("LinkedList elements  first element  "+latele);
         lnk.sort((o1,o2) ->o2 -o1);
        System.out.println(lnk);

        for(int f =0; f<lnk.size(); f++)
        {
            System.out.println(lnk.get(f));
        }

        //************* Vector Legacy Class *************

        //vector  is part of java collection and also called vector legacy class because vector is  one of java release
        //vector is thread safe environment
        // we can construct vector with specific capacity to resize capacity
        // if the list is over then its resize 2.5 time of previous capacity
        // features remaining same as ArrayList

        Vector<Integer> vtc = new Vector<>(5,3);
        vtc.add(50);
        vtc.add(70);
        vtc.add(60);
        vtc.add(80);
        System.out.println("Vector capacity "+vtc.capacity());
        vtc.add(100);
        vtc.add(90);
        System.out.println("Vector capacity after specific increment capacity "+vtc.capacity());
        System.out.println("Vector Elements before sort "+vtc);
        vtc.sort(new sortnum());
        System.out.println("Vector Elements is "+vtc);



        //****************Stack collection *******************
        //Stack is java collection its follows LIFo principle (Last in first out)
        //last add element removed first
        // operation  add element push()
        // to removed element pop()
        // to find top element peek()
        //Stack is extends Vector
        //Since Stack Is extends vector inherited all vector operation and thread safe also

        Stack<String> fruitsship = new Stack<>();
        fruitsship.push("Apple");
        fruitsship.push("Banana");
        fruitsship.push("Orange");
        fruitsship.push("Watermelon");
        fruitsship.push("Apple");
        System.out.println(fruitsship);
        fruitsship.pop();
        System.out.println(fruitsship);
        String  topele = fruitsship.peek();
        System.out.println("Stack top element is "+topele);
        fruitsship.add("mango");
        System.out.println(fruitsship);

        for(int s= 0; s<fruitsship.size(); s++)
        {
            System.out.println(fruitsship.get(s));
        }

        fruitsship.sort(new sortfruit());
        System.out.println(fruitsship);

        //*************CopyOnWriteArrayLis ************************
        // copyOnWriteArrayLis is one java collection used to modification while read and write List
        // right now we are modified on original ArrayList existing arrayList
        //when we modified or read and write with CopyOnWriteArrayList  of instead   modified on existing arrayList
        //Created on copy ArrayList and modification Applied on that copied ArrayList

        CopyOnWriteArrayList<String > itm = new CopyOnWriteArrayList<>();
        itm.add("milk");
        itm.add("Breads");
        itm.add("Eggs");
        System.out.println(itm);

        for(String items :itm)
        {
          if(items == "Breads")
          {
              itm.add("butter");
          }
        }
        System.out.println(itm);




    }

}
class employee
{
    private String name;
    private int salary;

    public employee(String name, int salary)
    {
        this.name = name;
        this.salary = salary;
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
    
}