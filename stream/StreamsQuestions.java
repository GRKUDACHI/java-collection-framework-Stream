
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.*;

public class StreamsQuestions
{
    public static  void main(String[] args)
    {
        //Convert List ALL String Elements lowercase to uppercase and sorted

        List<String>fruits = Arrays.asList("apple","orange","banana","water melon","graphs");

        fruits.stream().map(str->str.toUpperCase())
                .sorted()
                .forEach(System.out::println);

        //Reference Method and List

        List<String>fruit = Arrays.asList("APPLE","ORANGE","BANANA","WATER MELON ","GRAPHS");
        fruit.stream()
                .map(String::toLowerCase)
                .sorted()
                .toList();
        System.out.println(fruit);

        //find string length more than 5 and removed duplicate string
        System.out.println("find string length more than 5 and removed duplicate string");

        List<String>length_fruits = Arrays.asList("apple","orange","banana","water melon","graphs", "banana","apple");

        length_fruits.stream().filter(str->str.length()>5)
                .distinct()
                .forEach(System.out::println);

       //filter using predicate
        List<String>lent_m2 = Arrays.asList("apple","orange","banana","water melon","graphs", "banana","apple");

        Predicate<String> pred = x->x.length()>5;

        lent_m2.stream()
                .filter(pred)
                .distinct()
                .forEach(System.out::println);

        //find the words from list
        System.out.println("find the words from list");

        List<String>find_fruit = Arrays.asList("apple","orange","banana","water melon","graphs","berry","strawberry","berry");

        Predicate<String>contain = cont->cont.contains("berry");
        find_fruit.stream().filter(ft->ft.contains("berry"))
                .forEach(System.out::println);

        //remove string start with b
        System.out.println("Removed String word Start with b ");

        List<String>start_word = Arrays.asList("apple","orange","banana","water melon","graphs","berry","strawberry","berry");
        start_word.stream().filter(str->!str.startsWith("b"))
                .forEach(System.out::println);


        //Start with word B
        System.out.println("string Start with word B ");
        List<String>start_with = Arrays.asList("apple","orange","banana","water melon","graphs","berry","strawberry","berry");

        start_with.stream().filter(str->str.startsWith("b"))
                .forEach(System.out::println);

        //Get the length of each element
        System.out.println("String Elements length with foreach");
        List<String>str_length = Arrays.asList("apple","orange","banana","water melon","graphs","berry","strawberry","berry");
        str_length.stream().map(str->str.length())
                .sorted()
                .forEach(System.out::println);

        //sorting Elements based on length
        List<String>sort = Arrays.asList("apple","orange","banana","water melon","graphs","berry","strawberry","berry");

                 sort.stream()
                //.sorted((o1,o2) ->o1.length() - o2.length())
                // .sorted(Comparator.comparing(String::length)
                 .sorted(Comparator.comparing(str->str.length()))
                  .forEach(System.out::println);

        //Find the longest Word in the list with uppercase

        System.out.println("Find the longest Word in the list");
        List<String>long_word = Arrays.asList("apple","orange","banana","water melon","graphs","berry","strawberry","berry");
        String longestWor = long_word.stream()
        .max(Comparator.comparing(str->str.length()))
        .map(String::toUpperCase)
        .orElse("not found words");

        System.out.println(longestWor);


        //Find the Vowels from this list 

        System.out.println("Find the Vowels from this list ");
        String[] vowel = {"apple","orange","banana","water melon","graphs","berry","strawberry","berry"};

        List<String> vowels = Arrays.asList(vowel);

        vowels.stream()
        .filter(vw->vw.matches(".*[aeiou]*."))
        .forEach(System.out::println);

        //Removed Null and Empty from this list using stream

        System.out.println("Removed Null and Empty Elements from List");
        String[] empty = {"apple","orange","banana"," ", "water melon","graphs","berry",null,"strawberry"," ","berry",null};

        List<String>lst = Arrays.asList(empty);

        lst.stream()
        .filter(str->str!=null && (!str.isEmpty()))
        .forEach(System.out::println);

        //Count Frequency of String
        System.out.println("Count Frequency of String");
        
        String[] word = {"apple","orange","banana","water melon","graphs","berry","strawberry","berry","apple"};

        Map<String,Integer> wd = new HashMap<>();

        for(String words : word)
        {
          wd.put(words, wd.getOrDefault(words,0)+1);
        }
        System.out.println(wd);

         //Removed Duplicate Numbers form Array
        System.out.println("Removed Duplicate Numbers form Array ");
        
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

        //first way
        Set<Integer> set_num = new HashSet<>(list);
        System.out.println(set_num);

        //Another way
        list.stream().distinct().forEach(System.out::println);

       //make Map values in sorted form
        System.out.println("make Map values in sorted form");

        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 3);
        map.put("Banana", 1);
        map.put("Orange", 2);

        map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(System.out::println);

        //Find Frequency for elements or  Numbers form Array
        System.out.println("Find Frequency for elements or  Numbers form Array");

        int[] arr = {1, 2, 2, 3, 1, 1, 4};
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : arr)
        {
            freq.put(num, freq.getOrDefault(num,0)+1);
        }
        System.out.println(freq);

        //Find Non-Repeating first Element  from Character or String
        System.out.println("Find Non-Repeating first Element  from Character or String ");

        String str = "aabbcde";
        Map<Character,Integer>mp = new LinkedHashMap<>();

        for(char st : str.toCharArray())
        {
            mp.put(st, mp.getOrDefault(st,0)+1);
        }

        for(Map.Entry<Character, Integer> entry :mp.entrySet())
        {
            if(entry.getValue() == 1)
            {
                System.out.println("first Non - Repeating "+entry.getKey());
                break;
            }
        }
    
    }
}
