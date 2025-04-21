
import java.util.Hashtable;
import java.util.Map;


//Hashtable is a legacy class in Java that implements the Map interface
//It stores key-value pairs
//Hashtable not allowed null key and null Value
//Hashtable is Thread safe and synchronized
//Hashtable in legacy Class because 0.1 released feature before  collection
//it's slower than HashMap due to  Thread safe and synchronized
//Random access Elements 


public class HashtableDemo
{
    public static void main(String [] args)
    {
        Hashtable<Integer,String > htable = new Hashtable<>();

        htable.put(5,"Pink");
        htable.put(30,"Blue");
        htable.put(20,"Green");
        htable.put(10,"Black");

        System.out.println(htable);

        for(Map.Entry<Integer,String> hash_table : htable.entrySet())
        {
            System.out.println(hash_table.getKey()+" : "+hash_table.getValue());
        }

    }
}
