import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//In Java, Predicate  functional interfaces that are part of the java.util.function package
//They are used to represent conditions or predicates that can be evaluated to return a boolean value
//A Predicate<T> is a functional interface that takes a single argument of type T and returns a boolean value
//It is often used for filtering or matching conditions
class predicate
{
    public static void main(String[] arge)
    {
         List<String > sports = Arrays.asList("Cricket","Football","hockey");

        sports.parallelStream().forEach(System.out::println);

        List<Integer>even_num = Arrays.asList(24,5,78,90,3,24,56,89,6,1,55,7,37,89);

        Predicate<Integer>isEven = x->x %2 == 0;

        even_num.stream()
            .filter(isEven)
            .collect(Collectors.toList())
            .forEach(System.out::println);

            //Combine Predicate
            Predicate<Integer>isGreaterThan = number->number >10;

            even_num.stream()
            .filter(isEven.and(isGreaterThan))
            .forEach(System.out::println);
    }
}