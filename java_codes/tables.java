public class tables
{
    public static void main(String[] args)
    {
        int number = 2;

        for(int i=0; i<=10; i++)
        {
            System.out.println(number+" x "+i+" = "+number*i);
        }

        String[] bike = {"ROyal","ktm","tvs","BMW"};
        //foreach loop
        for(String b : bike)
        {
            System.out.println(b);
        }

        for(int s=0; s<=10; s++)
        {
            if(s == 5)
            {
                continue;
            }
            System.out.println(s);
        }
    }
}