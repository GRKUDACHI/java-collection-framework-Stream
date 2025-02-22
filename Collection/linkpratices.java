import java.util.*;
class linkpratices
{
    public static void main(String[] atgs)
    {

        LinkedList<String> weeks = new LinkedList<>(Arrays.asList("monday","tuesday","wednesday","thursday"));
        System.out.println(weeks);

        LinkedList<String> addfunweeks = new LinkedList<>(weeks);
        addfunweeks.add("friday");
        addfunweeks.add("saturday");
        addfunweeks.add("sunday");
        System.out.println("full weeks"+addfunweeks);

        LinkedList<String> removedays = new LinkedList<>(Arrays.asList("monday","tuesday"));
        weeks.removeAll(removedays);
        System.out.println(weeks);
        LinkedList<Integer> link = new LinkedList<>();
        link.add(20);
        link.add(30);
        link.add(40);
        link.addFirst(10);
        link.addFirst(100);
        link.removeFirst();
        link.addLast(50);
        System.out.println("all linked list"+link);
        System.out.println("all linked list 1st element "+link.getFirst());
        System.out.println("all linked list 1st element "+link.getLast());

        for(int j =0; j<link.size(); j++)
        {
            System.out.println(link.get(j));
        }
        
    }
}