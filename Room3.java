/*
 * Room 3
 * 
 * SOLVE:
 * TBD
 * 
 * OPTIONAL:
 * TBD
 * 
 * OPTIONAL (But required for later):
 * TBD
 * 
 * WAYS TO DIE:
 * Type Die
 */

import java.util.concurrent.TimeUnit;

public class Room3 implements Room
{
    boolean done;
    
    public Room3()
    {
        
    }

    public boolean main()
    {
        //Initialize Command List
        C.commands.clear();
        C.commands.addCommand("Commands");
        C.commands.addCommand("Inventory");
        C.commands.addCommand("Use 'item' - replace 'item' with item from inventory");

        //Initialize all game variables      
        done = false;
        boolean alive = false;
        boolean item = false;
        String answer = "";
        String error = "Command not found. Type 'Commands' for a list of commands";

        //Intro
        C.spit.print("You find yourself in a room. There is a small table your right, a picture to your " +
            "left, a window at the end of the room, and a fan above you.");

        //Begin Loop
        while(!done)
        {
            //Prompt
            C.spit.print("\nWhat do you want to do? \n");
            answer = C.scan.scan();

            //Respond to answer
            switch(answer)
            {
                case "commands" : System.out.println(C.commands.getCommands()); break;

                case "inventory" : System.out.println(C.commands.getInventory()); break;
                
                case "clear" : System.out.println("\f");
                
                case "done" : done = true; break;
                
                case "die" : C.spit.print("You spontaneously burst into a giant ball of flames."); 
                done = true; 
                C.die(); 
                break;

                default : item = C.commands.checkDefaultItems(answer); //Check items that do nothing
                if(!item)
                    System.out.println(error); break;
            }
        }
        //End Loop
        
        return alive;
    }
}
