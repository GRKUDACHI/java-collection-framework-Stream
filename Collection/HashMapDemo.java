
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// in java Map is an object that  keys maps to value
//it cannot contain duplicate keys and each key map to at most one value
//Key-Value pair : Each entry in map consists of a key and a value
//Unique key : no two entries can have the same key
//one value per key : each key maps to a single value 

// ************************ HashMap Components  **********************

//HashMap having 4 component 1) key 2) Value 3)Bucket 4)Hash function

//1) Key -> Key Maps to the value (Associated with value)

//2) Value -> Data Associated with Key

//3)Bucket -> A place where key-value pairs are Stored think of bucket as call ina list(Array)

//4) HashFunction() ->a)Hash function calls hashCode() function to get a Hash
//                    b)Hashing to calculate the index in the Array(Called Bucket )
//                    c) If no Collision Value stored in directly
//                    d) If Collision having Linked List used where stored in bucket which elements are fet same index



// ************************  Hash function  Index Calculating  **********************


//Hash &(n-1)
// ex hashcode =69609650
//Capacity = 16
// index = Hashcode & (Capacity -1)
// index = 69609650 & (16-1)
// 69609650(in binary ) =00000100001010000111110000010
// i5 in binary = 000000000001111
//Bitwise AND = 000000000010 =2
//final we got 2 index
// In side bucked 2 index stored the hashMap value
//If collision happens used Linked list
//If collision Elements  are more than 8 then convert to RED-black tree 0(n) to 0(log n)


//**************Resizing factor (ReHashing)***************


//Load factor 0.75 (by default )
//Capacity initial 16
//Resize Happens when 16*0.75 =12
//when 12 element over filled 12 then HashMap itself Resized  2X(16 to 32)


public class HashMapDemo
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
