import java.util.EnumMap;
import java.util.Map;
import java.util.*;
//EnumMap used array index
//EnumMap is Ordinal/ array is used 
//No Hashing faster than HashMap
//Memory Efficient 

public class EnumMapDemo
{
    public static void main(String [] args)
    {
        Map<Day, String>route = new EnumMap<>(Day.class);

        route.put(Day.Monday,"WalKing");
        route.put(Day.Tuesday,"gym");
        route.put(Day.Thursday,"Yoga");
        
        System.out.println(route);

        for(Map.Entry<Day,String>d_route: route.entrySet())
        {
            System.out.println(d_route);
        }
        
    }
}

enum Day
{
    Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday;
}