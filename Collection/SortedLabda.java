
import java.util.*;
import java.util.stream.Stream;

public class SortedLabda {
    public static void main(String[] args)
    {

        Runnable task =()->{
        for(int i =0; i<5; i++)
        {
            System.out.println(Thread.currentThread().getName()+i);
        }
        };

        Thread th = new Thread(task);
        th.start();

        List<Integer>lst = new ArrayList<>();
        lst.add(89);
        lst.add(92);
        lst.add(2);
        lst.add(62);
        lst.add(42);
        lst.add(52);

        System.out.println(lst);

        for(int j =0; j<lst.size(); j++)
        {
            System.out.println(lst.get(j));
        }

        lst.sort((a,b)->b-a);

        System.out.println(lst);

        Map<Integer,String> mp = new TreeMap<>();

        mp.put(40,"apple");
        mp.put(10,"Water Melon");
        mp.put(60,"Banana");
        mp.put(8,"Orange");

        System.out.println(mp);

        for(Map.Entry<Integer,String> tmp :mp.entrySet())
        {
            System.out.println(tmp.getKey()+":"+tmp.getValue());
        }

        mp.entrySet().stream()
        .sorted(Map.Entry.comparingByKey((a,b)->b-a))
        .forEach(System.out::println);

        mp.entrySet().stream()
        .sorted(Map.Entry.comparingByValue((x,y)->y.length() - x.length()))
        .forEach(System.out::println);


        Set<Integer> st = new TreeSet<>((a,b)->b-a);
        st.add(98);
        st.add(18);
        st.add(28);
        st.add(148);
        st.add(68);
        st.add(86);
        System.out.println(st);


        st.stream()
        .sorted((a,b)->b-a)
        .forEach(System.out::println);


        Set<String >tst = new TreeSet<>((x,y)->y.length() - x.length());

        tst.add("Egg");
        tst.add("Bread");
        tst.add("Fruits");

        System.out.println(tst);
        tst.stream().sorted((a,b)->b.length() - a.length())
                .forEach(System.out::println);
    }

}
