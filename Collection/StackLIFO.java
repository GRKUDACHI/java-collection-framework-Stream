import java.util;

//Stack is java collection Extends Vector
//Stack follows LIFO principle Last in First Out
//Stack is Sub Class of vector its inherit all vector Feature
//Since its extends Vector then its Thred-safe class

public class StackLIFO
{
    public static void main (String[] args)
    {
        Stack<Integer> stk = new Stack<>();
        stk.push(90);
        stk.push(89);
        stk.push(78);
        System.out.println(stk);
        stk.add(76);// we can also use add() because Since Stack extends Vector them its sub class of vector so it can also inherit all vector features
        System.out.println(stk);
        System.out.println("The seach elemet from Stck "+stk.search(2));
        int remove_last_add_elemet_firt = stk.pop();
        System.out.println("Last inser elemet removed first  LIFO principle "+remove_last_add_elemet_firt);
        int peek = stk.peek();
        System.out.println("The peek Element or top Elemet from Stack "+peek);
    }
}
//Stack is java collection Extends Vector
//Stack follows LIFO principle Last in First Out
//Stack is Sub Class of vector its inherit all vector Feature
//Since its extends Vector then its Thred-safe class
