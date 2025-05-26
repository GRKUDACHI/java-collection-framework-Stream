
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

//Stream is a sequence of elements that can be processed in a functional style
//by using Lambda Expression

//there are two types of methods we used 1) Intermediate Operations and 2) Terminal Operations

//1)Intermediate methods are operations that transform a stream into another stream
//These methods are lazy
//the operations are not executed until a terminal operation is invoked

public class streamDemo
{
    public static void main (String[] args)
    {
        //Stream filter method

        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango");
        List<String> filteredFruits = fruits.stream()
                .filter(fruit -> fruit.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(filteredFruits);

        fruits.stream().forEach(System.out::println);

       //Stream Map() method
        List<String> fruit = Arrays.asList("Banana", "Apple", "Orange");
        System.out.println("String list "+fruit);
        fruit.sort(new sort_string());
        List<Integer>fruitLength = fruit.stream().map(String::length)
                .collect(Collectors.toList());
        System.out.println("string convert with there length "+fruitLength);

        List<String>Weeks = Arrays.asList("monday","tuesday","wednesday","thursday","friday","saturday","sunday");
        System.out.println("Lowercase string list "+Weeks);
        List<String>stream_week = Weeks.stream().map(String::toUpperCase).toList();
        System.out.println("Uppercase string list "+stream_week);

        //Sorted ()
        List<Integer> listnumber = new ArrayList<>();
        listnumber.add(90);
        listnumber.add(80);
        listnumber.add(40);
        listnumber.add(70);
        listnumber.add(20);
        listnumber.add(10);

        System.out.println("unsorted list "+listnumber);

        List<Integer>sort_num = listnumber.stream().sorted().toList();
        System.out.println("sorted list"+sort_num);

       //distinct() toremoved duplicate from collection 

        List<Integer>numbers = Arrays.asList(90,30,40,90,50,40,60,10);
        System.out.println("list numbesr "+numbers);
        List<Integer>removed_duplicate = numbers.stream().distinct().toList();
        System.out.println("Removed duplicate number "+removed_duplicate);

        //treminal Operations 

        
        List<String>shop = Arrays.asList("Milk","Egg","bread");
        shop.stream().forEach(System.out::println);

         HashMap<Integer,String > student = new HashMap<>();
        student.put(101,"girish");
        student.put(102,"Happy");
        student.put(103,"Rakes");

        System.out.println(student);

        for(Map.Entry<Integer,String > st : student.entrySet())
        {
            System.out.println(st.getKey()+ ":" +st.getValue());
        }

        student.entrySet().stream().forEach(System.out::println);


        LinkedHashMap<Integer,String >basket = new LinkedHashMap<>();
        basket.put(50,"Apple");
        basket.put(10,"Orange");
        basket.put(5,"Water Melon");
        basket.put(30,"Banana");
        System.out.println(basket);

        for(Map.Entry<Integer,String> shops : basket.entrySet())
        {
            System.out.println(shops.getKey()+" : "+shops.getValue());
        }

        //Stream Terminal method

        basket.entrySet().stream().forEach(System.out::println);

        basket.entrySet().stream().map(entry-> entry.getValue().toUpperCase()).forEach(System.out::println);


        TreeMap<Integer,String >dept = new TreeMap<>();
        dept.put(100,"Development team");
        dept.put(20,"hr team");
        dept.put(35,"Tester team");
        System.out.println(dept);

        for(Map.Entry<Integer,String >dp :dept.entrySet())
        {
            System.out.println(dp.getKey()+" : "+dp.getValue());
        }

        dept.entrySet().stream()
        .map(d->d.getValue().toUpperCase())
        .forEach(System.out::println);

        Hashtable<String ,Integer> wek = new Hashtable<>();
        wek.put("Mon",1);
        wek.put("tue",2);
        wek.put("wed",3);
        wek.put("thu",4);

        System.out.println(wek);
       
       //stream with terminal operation 
        wek.entrySet().stream().forEach(System.out::println);

        List<Integer> list_num = Arrays.asList(2,4,8,9,20,45,37,300,69,92,4,6,1,19,38,62,58);
        List<Integer> even_numbers = list_num.stream()
                .filter(num -> num % 2 == 0)
                .sorted()
                .distinct()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(even_numbers);
        
        even_numbers.stream().forEach(System.out::println);

       //Common Controller 

        List<String >names = Arrays.asList("Java ","stream ","API");
        String name = names.stream()
                .collect(Collectors.joining());
        System.out.println(name);

        List<String> lang = Arrays.asList("Java","hibernate","Spring boot");
        String lng = String.valueOf(lang.stream()
        .collect(Collectors.counting()));
        System.out.println(lng);

         //removed duplicate Elements the used toSet

        List<String>all_name = Arrays.asList("Happy","girish","Rakesh","Happy");

        Set<String> user_name = all_name.stream().collect(Collectors.toSet());
        
        System.out.println(user_name);


    }
}

//Comparator is interface 
//Comparator to used Custome sort method for list and set interface 
//if return positive then sorted with ASC
//if return Negative then sorted with DESC
class sort implements Comparator<Integer>
{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1-o2;
    }
}

class sort_string implements Comparator<String>
{
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}
