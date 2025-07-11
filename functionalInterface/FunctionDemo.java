
import java.util.*;
import java.util.function.Function;

/*
   Function in Functional Interface That takes only one argument and Return Result

   <T> – the type of the input to the function
   <R> – the type of the result of the function

   @FunctionalInterface
   public interface Function<T, R>
   {
    R apply(T t);
   }

   when used to Transform or calculate Something
*/
public class FunctionDemo
{
    public static void main(String[] args)
    {

        Function<String, String> upperConvert = name -> name.toUpperCase();
        String res = upperConvert.apply("girish");
        System.out.println(res);


        List<String>Basket = Arrays.asList("apple","orange","banana","water melon");

        Basket.stream()
        .map(upperConvert)
        .forEach(System.out::println);

        Function<Integer,Integer>number = num->num *2 ;

        List<Integer>nums = new ArrayList<>();
        nums.add(8);
        nums.add(4);
        nums.add(6);
        nums.add(50);

        nums.stream()
        .map(number)
        .forEach(System.out::println);
    }


}
