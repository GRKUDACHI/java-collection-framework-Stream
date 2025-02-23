import java.util.Vector;
import java.util.Comparator;

import java.util.*;

class sortvector implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2-o1;
    }
}

class sortfrt implements Comparator<String>
{

    @Override
    public int compare(String o1, String o2) {
        return o2.length() - o1.length();
    }
}

public class VectorLegacy
{
    public static void main (String[] args)
    {

         //we can construct specific capacity increment List<Integer> vt = new Vector<>(5,3);
        // we can also construct with collection
        LinkedList<Integer> lkn = new LinkedList<>();
        lkn.add(89);
        lkn.add(90);
        lkn.add(78);

        List<Integer> vt = new Vector<>(lkn); // we can construct with specified collection
        vt.sort(new sortvector());
        System.out.println(vt);
        Vector<Integer> vtc = new Vector<>();
        vtc.add(40);
        vtc.add(90);
        vtc.add(70);
        vtc.add(20);
        int Vtclastelemet = vtc.get(3);
        System.out.println(vtc);
        System.out.println("The vector last element "+Vtclastelemet);
        vtc.sort((o1,o2) ->o1-o2); // positive return ACS order
        vtc.sort(new sortvector()); // negative return then desc order
        System.out.println(vtc);

        List<String> frt = new Vector<>();
        frt.add("Banana");
        frt.add("apple");
        frt.add("organe");
        frt.add("watermelon");
        frt.sort(new sortfrt()); //inside class we return negative so its DESC ORDER
        frt.sort((o1,o2) ->o1.length()-o2.length()); // here lambda we return positive now its sort with ASC order
        System.out.println(frt);


        String[] weeks = {"MON", "TUE", "WED", "THU"};
        List<String> wks = new Vector<>(List.of(weeks));
        System.out.println(wks);


        String[] weekend = {"FRI","SAT","SUN"};
        List<String> funwks = new Vector<>(Arrays.asList(weekend));
        System.out.println(funwks);

        





    }
}
