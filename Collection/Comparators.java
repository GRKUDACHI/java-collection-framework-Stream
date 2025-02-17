import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;


// Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
// Comparators are used to sort the ArrayList ASC AND DECS order

class Mycomparator implements Comparator<Integer>
{

    @Override
    public int compare(Integer o1, Integer o2) 
    {
        return o1 - o2; // if the result positive the Sorted order in ASC else DESC order
    }
}



class fruitcompare implements Comparator<String>
{

    @Override
    public String compare(String o1, String o2)
    {

        return  o1.length() - o2.length() ; // if the result positive the Sorted order in ASC

        //  return   o2.length() - o1.length() ;  //if the result negative  then sorted DESC
    }
}

public class Comparators
{
    public static void main(String[] args)
    {
        ArrayList<Integer> num = new ArrayList<>();
        num.add(89);
        num.add(45);
        num.add(90);
        num.add(70);
        num.add(20);   

        num.sort(new Mycomparator());
        System.out.println(num); 

        String[] fruit = {"Orange", "Banana", "Apple", "Pineapple", "Watermelon"};

        List<String> frut = Arrays.asList(fruit);
        frut.sort(new fruitcompare());
        // frut.sort((String a, String b) -> a.length() - b.length());
        System.out.println(frut);
    }
}
