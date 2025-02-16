import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class arraylist
{
    public static void main(String[] args)
    {
         ArrayList<Integer> list = new ArrayList<>();
        list.add(90);
        list.add(45);
        list.add(89);
        list.add(95);
        list.add(39);
        int lastIndex = list.size()-1;
        System.out.println("The Array List Last Index is "+lastIndex);
        System.out.println(list.get(2));      // we can ACCESS Array List Elements By using variableName.get(index);
        System.out.println(list.size());      // we can check ArrayList Length by using size() VariableName.size();

        list.remove(2);                  // removed 2nd index element
        list.add(2, 87);        //Add data 2nd index and element 87
        list.set(4,86);                       // replace 4th index value into 86

        for(int i=0; i<list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        System.out.println("FOREACH LOOP");

        for(int z:list)                      //foreach Loop
        {
            System.out.println(z);
        }

        System.out.println(list);

    // This is Arrays asList method this method is Fixed Size list we can just Replace or modified the elements
    //(M1)Another Method to Create ArrayList by using Parent Interface

        List<String> lst = Arrays.asList("Apple","Baana");
        System.out.println(lst);
        System.out.println(lst.getClass().getName());


        //(M2) Another Method

        String[] weeks = {"Monday", "Tuesday","Wednesday"}; // Create one Array AND add that Array variable inside Arrays.asList()
        List<String> wks = Arrays.asList(weeks);
        System.out.print(wks);
        System.out.println(wks.getClass().getName());


        //Modified by using collection list in place of Capacity
        List<String> fun = new ArrayList<>(wks); // WE can add here Element Capacity  OR Collection List
        fun.add("thursDay");
        fun.add("Friday");
        fun.add("Saturday");
        fun.add("Sunday");
        System.out.println(fun);
    }
}