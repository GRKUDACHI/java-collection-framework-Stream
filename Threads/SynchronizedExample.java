
//When multiple threads access shared resources, synchronization is needed to avoid conflicts like race conditions or inconsistent data.
//Synchronization ensures that only one thread can access a block of code or an object at a time, making operations on shared resources atomic and thread-safe
//Using synchronized keyword we can create method


public class SynchronizedExample
{
    public static void main(String[] args)
    {
        Counter counter = new Counter();

        Runnable task = () -> {

            for (int i = 0; i < 1000; i++)
            {
                counter.increment();
                System.out.println("Current Thread is "+Thread.currentThread().getName());
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count of Both Thread : " + counter.getCount());
    }

}
    class Counter
    {
        private int count = 0;

        public synchronized void increment()
        {
            count++;
        }

        public int getCount()
        {
            return count;
        }
    }
