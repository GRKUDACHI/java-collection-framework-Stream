public class threaddemo
{
    public static void main (String[] args)
    {

      test t1 = new test();
      t1.start();
      
      result res = new result();
      Thread tr1 = new Thread(res);
      tr1.start();
    }
}

//extends Thread
class test extends Thread
{
    public void run()
    {
        for(int i=0; i<=5; i++)
        {
            System.out.println(Thread.currentThread().getName()+" count "+i);
        }
    }

}

//implements Runnable

class result implements Runnable
{
    public void run()
    {
        for(int i=0; i<=5; i++)
        {
            System.out.println(Thread.currentThread().getName()+" Count "+i);
        }
    }
}