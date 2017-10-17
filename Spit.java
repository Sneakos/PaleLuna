/*
 * Spits out letters one word at a time
 */
import java.util.concurrent.TimeUnit;
public class Spit
{
    final int MAX = 70;
    int speed;
    
    public Spit()
    {
        speed = 30;
    }
    
    public void print(String word)
    {
        int count = 0;
        String[] split = word.split("");
        for(int i = 0; i < split.length; i++)
        {
            C.sleepMillis(speed);         
            System.out.print(split[i]);
            count++;
            if(count >= MAX && split[i].equals(" "))
            {
                System.out.println("");
                count = 0;
            }
        }
    }
}
