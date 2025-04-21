import java.util.HashSet;

//HashSet in not Allowed Duplicate value
//Hashset is Random Access and not index based Access
//Allowed one null key and multiple null values
//HashSet not maintained Insertion Order
//It's Fast performance
//HashSet not a Thread safe and Not Sorted order


public class HashSetDemo
{
    public static void main(String [] args)
    {
        HashSet<String> set_colors = new HashSet<>();

        set_colors.add("black");
        set_colors.add("Blue");
        set_colors.add("Red");
        set_colors.add("Yellow");

        System.out.println("HashSet Elements "+set_colors);

        for(String set_items : set_colors)
        {
            System.out.println(set_items);
        }

    }
}
