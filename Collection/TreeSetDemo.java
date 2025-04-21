
import java.util.TreeSet;


//TreeSet in maintained Automatically Sorted Order
//TreeSet Not Allowed Null key AND Null values
//Not Thread safe

public class TreeSetDemo
{
    public static void main (String[] args)
    {
        TreeSet<Integer> tree_set = new TreeSet<>();
        tree_set.add(89);
        tree_set.add(40);
        tree_set.add(80);
        tree_set.add(60);
        tree_set.add(20);

        System.out.println("Tree Set Elements "+tree_set);

        for(int Set_tree : tree_set)
        {
            System.out.println(Set_tree);
        }


    }
}
