
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

       //Stream Map() method
        List<String> fruit = Arrays.asList("Apple", "Banana", "Orange");
        System.out.println("String list "+fruit);
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

        List<Integer>numbers = Arrays.asList(90,30,40,90,50,40,60,10);
        System.out.println("list numbesr "+numbers);
        List<Integer>removed_duplicate = numbers.stream().distinct().toList();
        System.out.println("Removed duplicate number "+removed_duplicate);


    }
}
