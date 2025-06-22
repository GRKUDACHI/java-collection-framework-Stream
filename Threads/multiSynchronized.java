
public class multiSynchronized
{
    public static void main(String[] args)
    {
        sums sm = new sums();

        Runnable task = () -> {
            for(int j=0; j<2000; j++)
            {
                sm.increase_sum();
            }
        };

        Thread t1 = new Thread(task); //here two Threads are usimg same object sm mean both thread using same resource
        Thread t2 = new Thread(task); //then Synchronization ensures that only one thread can access a block of code

        t1.start();
        t2.start();

        try
        {
            t1.join();
            t2.join();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        System.out.println("My thread Counter is "+sm.getSum());
    }
}
class sums
{
    private int sum =0;

    public void increase_sum()
    {
        synchronized(this)    //multiple multi variables  for synchronized method 
        {
            sum++;
        }
    }

    public int getSum()
    {
        return sum;
    }
}
