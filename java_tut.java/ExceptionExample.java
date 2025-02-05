// Exception handal is a mechanism in Java to handle runtime error to handle romal flow fo our Application 
//run time error
import java.io.*;

public class ExceptionExample {
    public static void main(String[] args) 
    {
     int [] numerators = {20, 180, 40, 200};
     int [] denominators = {4, 2, 0, 4};

     for(int i =0; i < numerators.length; i++)
     {
        // System.out.println(numerators.length);
        System.out.println(divide(numerators[i], denominators[i]));
     }
    }
    public static int divide(int a, int b) // if we want return type then use data types not void for methdo or function
    {
        try
        {
            return a/b;
        }
        catch(ArithmeticException e)
        {
            System.out.println(e);
            return -1;
        }
    }
}
