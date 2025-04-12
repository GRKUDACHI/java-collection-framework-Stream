import java.util.ArrayList;
import java.util.Collections;

// public class coll { 
//   public static void main(String[] args) { 
//  ArrayList<Integer> Num = new ArrayList<>();
//  Num.add(45);
//  Num.add(89);
//  Num.add(34);
//  Num.add(23);
//  Num.add(90);
//  Num.add(67);
 
//  Collections.sort(Num);
//  for(int n : Num)
//  {
//  System.out.println(n);
//  }
//  } 
// }
public class coll
{
    public static void main(String  args[])
    {
        ArrayList<Integer> num = new ArrayList<>();
        
        num.add(78);
        num.add(34);
        num.add(45);
        num.add(90);
        num.add(12);

        Collections.sort(num);

        // Collections.sort(num, Collections.reverseOrder());
       

        for(int N : num)
        {
            System.out.println(N);
        }
    }
}
