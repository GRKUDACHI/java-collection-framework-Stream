
import java.io.StringReader;
import java.util.concurrent.*;


/*
ExecutorService is a part of Java's java.util.concurrent package.
It helps you manage threads easily without manually starting or managing them.

ExecutorService is a sub interface of Executor that provides more advanced features for managing and controlling the execution of tasks.
*/
public class ExecutorServiceDemo
{
    public static void main(String[] args)
    {
        ExecutorService execute = Executors.newFixedThreadPool(2);

        Callable<String>task = () -> {
            for(int i =0; i<100; i++)
            {
              System.out.println(Thread.currentThread().getName()+i);
            }
            Thread.sleep(1000);
            return "Task completed by " + Thread.currentThread().getName();
        };

        Future<String> fute = execute.submit(task);

        try
        {
            String str = fute.get();

        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        finally {
            execute.shutdown();
        }
    }
}
