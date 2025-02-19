// In java LinkedList is Linear Data structure where each Element having sepreate Object called Node
// Each Node contains TWO part Data AND Pointer 
// Data Part contains Element AND One Pointer represent Next Element or node  Address
// Another Node Represent Previous Elemet or node Address


//**********Performance OR time complexity *************
// Elemet Inseration and Deletion is faster compared to ArrayList 0(1)
// Because there is no need to shifting Elements while delete and Insert 0(1) 
// Accessing Elemet is Slow Comparing to ArrayList because LinkedList is traversing  so we cant Access Directly 0(n)
// LinkeList haveing more Memory OverHead because LinkeList need more memory to Store next and previous poniter 

import java.util.*;
public class Linkedlist
{
    public static void main(String[] args)
    {
        LinkedList<Integer> lkst = new LinkedList<>();
        lkst.add(20);
        lkst.add(30);
        lkst.add(40);
        System.out.println(lkst);
        lkst.addFirst(10);
        lkst.addLast(50);
        System.out.println(lkst);
        System.out.println(lkst.get(3));
        System.out.println("The LinkedLIst Last Elemet IS "+lkst.getLast());
        System.out.println("The LinkedLIst First Elemet IS "+lkst.getFirst());

        for(int i =0; i<lklist.size(); i++)
        {
            System.out.println(lklist.get(i));
        }

        for(int z : lklist)
        {
            System.out.println(z);
        }
       
       

    }
}