import java.util.*;
public class demothread
{
    public static void main(String[] args)
    {
        wrds wd = new wrds();
        wd.start();

        walk wk = new walk();
        Thread t = new Thread(wk);
        t.start();

        MyThread the = new MyThread();
        MyThread the1 = new MyThread();
        the.start();
        the1.start();;


    }
}
class wrds extends Thread
{
    public void run()
    {
        System.out.println("my thread ");
    }
}
class walk implements Runnable
{

    @Override
    public void run()
    {
        System.out.println("this is runnable thread method ");

    }
}

class MyThread extends Thread
{
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try
            {
                Thread.sleep(1000); // Sleep for 1 second
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

