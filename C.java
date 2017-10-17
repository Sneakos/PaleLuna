/*
 * Static class to hold global variables and methods. Why call it C? Cause its ironic, that's why
 */
import java.util.concurrent.TimeUnit;

public class C
{
    static Commands commands = new Commands();    
    static Spit spit = new Spit();
    static Scan scan = new Scan();

    static void search(int seconds) //how many seconds to wait for
    {
        spit.print("\nSearching...\n");
        sleep(seconds);
    }   

    static void die()
    {
        sleep(3);
        System.out.println("\nYou have died.");
    }

    static void sleep(int seconds)
    {
        try
        {
            TimeUnit.SECONDS.sleep(seconds);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }   
    }
    
    static void sleepMillis(int milliseconds)
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }   
    }
}
