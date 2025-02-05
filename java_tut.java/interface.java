  // we can use static method in interface AND WE can't override the method
  // we can achive 0 to 100% abstraction
  // we can achive multiple inheritance by using interface
  // its contain final class or vatibles 
  // we can use static method in interface AND WE can't override the method


public class faces
{
    public static void main(String args[])
    {
      login.sigin("Hello, World!");

      humans hm = new humans();
      hm.run();
    }
}

interface login
{
    // Method withod body 
    public void run();

    static void sigin(String message)
    {
        System.out.println("Logging "+message);
    }
}