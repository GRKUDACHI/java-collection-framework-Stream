
public class Prioritydemo
{
    public  static  void main(String[] args)
    {
        prior pr1 = new prior();
        prior pr2 = new prior();
        prior pr3 = new prior();

        pr1.setPriority(Thread.MIN_PRIORITY);
        pr2.setPriority(Thread.NORM_PRIORITY);
        pr3.setPriority(Thread.MAX_PRIORITY);

        pr1.start();
        pr2.start();
        pr3.start();

        withdrawn with = new withdrawn(456);
        new Thread(with).start();
        System.out.println(with.primenum);
    }
}

class prior extends Thread
{
    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() +" -Priority "+Thread.currentThread().getPriority());
    }
    public synchronized  void withdraw(int amount)
    {

    }
}
class withdrawn implements Runnable
{
    int primenum ;

    @Override
    public void run()
    {

    }
    public withdrawn(int primenum)
    {
        this.primenum = primenum;
    }
}
