
import java.util.LinkedHashMap;
import java.util.Map;

//LinkedHashmap is Extend HashMap
//LinkedHashMap manages Order
//LinkedHashmap used Doubly linked list
//for insert and remove o(n) Comparing to Hashmap LinkedHashMap is slow due to over head memory for Doubly linked List

public class linkedHashmapdemo
{
    public static void main(String[] args)
    {
        LinkedHashMap<String ,Integer> lnkhmap = new LinkedHashMap<>(11,0.3f,true);
        lnkhmap.put("Apple",50);
        lnkhmap.put("Water Melon",60);
        lnkhmap.put("Banana",50);

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
