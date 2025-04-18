
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

//A SortedMap is a Map that maintains its keys in a sorted (ascending) order
//It is an interface in Java, and its most commonly used implementation is
//Keys are sorted automatically in natural order
//It does not allow null keys and Allow multiple Null values

public class TreeSortedMApDemo
{
    public static void main(String[] args)
    {
        SortedMap<Integer,String > sortmap = new TreeMap<>();

        sortmap.put(99,"Apples");
        sortmap.put(70,"Orange");
        sortmap.put(90,"Water Melon");
        sortmap.put(50,"Banana");

        System.out.println(sortmap);

        for(Map.Entry<Integer,String> tremp : sortmap.entrySet())
        {
            System.out.println(tremp.getKey() +" : "+ tremp.getValue());
        }

        System.out.println("First key "+sortmap.firstKey());
        System.out.println("Second Key "+sortmap.lastKey());
        //System.out.println("Head key "+sortmap.headMap());

    }
}
