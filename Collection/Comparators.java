import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;


// Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
// Comparators are used to sort the ArrayList ASC AND DECS order

class Mycomparator implements Comparator<Integer>
{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
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
    }
}