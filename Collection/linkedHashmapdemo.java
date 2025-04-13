
import java.util.LinkedHashMap;
import java.util.Map;

//Elements are returned in the order they were inserted.
//LinkedHashmap is Extend HashMap
//LinkedHashMap manages Order
//LinkedHashmap used Doubly linked list
//Faster Iteration than HashMap Because it uses a doubly-linked list to maintain the order of entries.
//for insert and remove o(n) Comparing to Hashmap LinkedHashMap is slow due to over head memory for Doubly linked List.
//If constructed with accessOrder = true, the map orders entries by access time (used in LRU caches)

public class linkedHashmapdemo
{
    public static void main(String[] args)
    {
        LinkedHashMap<String ,Integer> lnkhmap = new LinkedHashMap<>(11,0.3f,true);
        lnkhmap.put("Apple",50);
        lnkhmap.put("Water Melon",60);
        lnkhmap.put("Banana",50);

        lnkhmap.get("Apple");
        lnkhmap.get("Water Melon");

        System.out.println(lnkhmap);
        for(Map.Entry<String,Integer>lnmap : lnkhmap.entrySet())
        {
            System.out.println(lnmap.getKey()+" : "+lnmap.getValue());
        }

        for(String k: lnkhmap.keySet())
        {
            System.out.println(k);
        }

    }
}
