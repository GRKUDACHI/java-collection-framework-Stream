
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// in java Map is an object that maps keys to value
//it cannot contain duplicate keys and each key map to at most one value
//Key-Value pair : Each entry in map consists of a key and a value
//Unique key : no two entries can have the same key
//one value per key : each key maps to a single key

public class demohashmap
{
    public static void main(String[] args)
    {
        HashMap<Integer, String> hmp = new HashMap<>();
        hmp.put(110,"Happy");
        hmp.put(120,"Ganesh");
        hmp.put(130,"Rakesh");

        System.out.println(hmp);


        String vlue = hmp.get(110);
        System.out.println(vlue);
        System.out.println(hmp.containsKey(120));
        System.out.println(hmp.containsValue("Happy"));

        Set<Integer> hmpset = hmp.keySet();

        for(int hm : hmpset)
        {
            System.out.println(hmp.get(hm));
        }
        Set<Map.Entry<Integer, String>> entermap = hmp.entrySet();

        for(Map.Entry<Integer, String> entryk : entermap)
        {
          System.out.println(entryk.getKey() +" : "+entryk.getValue());
        }

        for (int j :hmp.keySet())
        {
            System.out.println(hmp.get(j));
        }
    }
}
