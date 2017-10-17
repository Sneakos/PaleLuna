/*
 * Room 1
 * 
 * SOLVE:
 * Enter a room (North is locked until key in room 2 is obtained)
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

public class Hallway
{
    boolean done;

    public Hallway()
    {

    }

    public int main()
    {
        //Initialize Command List
        C.commands.clear();
        C.commands.addCommand("Commands");
        C.commands.addCommand("Inventory");
        C.commands.addCommand("Use 'item' - replace 'item' with item from inventory");
        C.commands.addCommand("Enter west door"); //room 2
        C.commands.addCommand("Enter east door"); //room 3
        C.commands.addCommand("Enter north door"); //room 4

        //Initialize all game variables
        done = false;
        boolean alive = false;
        boolean item = false;
        int room = 0;
        String error = "Command not found. Type 'Commands' for a list of commands";
        String answer = "";

        //Intro
        C.spit.print("\nThe door slams shut behind you. Ahead of you, there is a door to the west, a door to the east, and a door at the end of the hallway.\n");

        //Begin Loop
        while(!done)
        {
            //Prompt
            C.spit.print("\nWhat do you want to do?\n");
            answer = C.scan.scan();

            //Respond to answer
            switch(answer)
            {
                case "inventory" : System.out.println(C.commands.getInventory()); break;

                case "commands" : System.out.println(C.commands.getCommands()); break;
                
                case "clear" : System.out.println("\f"); break;

                case "done" : done = true; break;

                //Spell check will append an s to the 'door', so we will need to compensate
                case "enter west doors" :
                C.spit.print("\nYou leave the hallway and enter the western room...\n");
                done = true;
                C.commands.clear();
                alive = true;
                room = 2;
                break;

                case "enter east doors" : 
                if(C.commands.inInventory("room key"))
                {
                    C.spit.print("\nYou leave the hallway and enter the eastern room...\n");
                    done = true;
                    C.commands.clear();
                    alive = true;
                    room = 3;
                }
                else
                {
                    C.spit.print("\nThe door is securely locked\n");
                }
                break;

                case "enter north doors" :
                if(C.commands.inInventory("hall key"))
                {
                    C.spit.print("\nYou leave the hallway and enter the northern room...\n");
                    done = true;
                    C.commands.clear();
                    alive = true;
                    room = 4;
                }
                else
                {
                    C.spit.print("\nThe door is securely locked\n");
                }
                break;

                case "die" : 
                C.spit.print("\nYou spontaneously burst into a giant ball of flames.\n");
                die(); 
                break;

                default : item = C.commands.checkDefaultItems(answer); //Check items that do nothing
                if(!item)
                    System.out.println(error); break;
            }
        }
        return room;
    }

    private void die()
    {
        try
        {
            TimeUnit.SECONDS.sleep(3);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("\nYou have died.");
        done = true;
    }
}
