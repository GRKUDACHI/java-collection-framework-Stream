import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class CopyOnWriteListDemo
{
    public static void main(String[] args)
    {
        CopyOnWriteArrayList<String> lst = new CopyOnWriteArrayList<>();

        lst.add("Milk");
        lst.add("Eggs");
        lst.add("Bread");
        System.out.println(lst);

        for(String items: lst)
        {
            if(items == "Eggs")
            {
                lst.add("Butter");
                System.out.println("ADD butter while reading");
            }
        }
        System.out.println("Add shopping List"+lst);

    }
}