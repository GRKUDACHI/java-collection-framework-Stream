
import java.util.LinkedHashSet;

//LinkedHashSet is Maintained Insertion Order
//LinkedHAshSet allowed One Null key and Multiple Null Value
//LinkedHashSet Not Thread Safe
//We cannot Access based on index

public class LinkedHashSetDemo
{
    public static void main(String [] args)
    {
        LinkedHashSet<Integer> lnkset = new LinkedHashSet<>();

        lnkset.add(90);
        lnkset.add(40);
        lnkset.add(80);
        lnkset.add(60);

        System.out.println(lnkset);

        //System.out.println(lnkset.getClass());

        for(int HashLinkset : lnkset)
        {
            System.out.println(HashLinkset);
        }
    }
}
