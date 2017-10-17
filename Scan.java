/*
 * Scans an item in, changes item to lowercase, checks it against common spelling errors, corrects it, then returns the fixed string 
 */
import java.util.Scanner;

public class Scan
{
    String response;
    
    public Scan()
    {
        
    }
    
    public String scan()
    {
        Scanner scan = new Scanner(System.in);
        Check check = new Check();
        String resp = scan.nextLine();
        response = resp.toLowerCase();
        response = check.correct(response);
        return response;
    }
}
