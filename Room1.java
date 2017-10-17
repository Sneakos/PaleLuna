/*
 * Room 1
 * 
 * SOLVE:
 * Search sink to find sink doors
 * Search sink doors to find bottle
 * Empty bottle in sink to get key
 * Use key on door to escape
 * 
 * OPTIONAL:
 * Search dresser for note referring to room 2
 * 
 * OPTIONAL (But required for later):
 * Search dresser twice for wrench 
 * Use wrench to break mirror to get glass shard (used to kill badger in room 2)
 * 
 * WAYS TO DIE:
 * Type Die
 * Drink bottle
 */

import java.util.concurrent.TimeUnit;

public class Room1 implements Room
{
    boolean done;

    public Room1()
    {
        
    }

    public boolean main()
    {
        //Initialize Command List
        C.commands.clear();
        C.commands.addCommand("Commands");
        C.commands.addCommand("Inventory");
        C.commands.addCommand("Use 'item' - replace 'item' with item from inventory");
        C.commands.addCommand("Search dresser");
        C.commands.addCommand("Search sink");
        C.commands.addCommand("Search door");

        //Initialize all game variables
        int dresserCount = 0;
        int sinkCount = 0;
        int mirrorCount = 0;
        int sinkDoorCount = 0;
        int doorCount = 0;       
        done = false;
        boolean alive = false;
        boolean item = false;
        String answer = "";
        String error = "\nCommand not found. Type 'Commands' for a list of commands";

        //Intro
        C.spit.print("You find yourself in a room. There is a dresser to your left, a sink to your " +
            "right and a door in front of you.\n");

        //Begin Loop
        while(!done)
        {
            //Prompt
            C.spit.print("\nWhat do you want to do? \n");
            answer = C.scan.scan();

            //Respond to answer
            switch(answer)
            {
                case "search dresser" : if(!C.commands.inList(answer)) {System.out.println(error); break;}
                if(dresserCount == 0) //First time viewing dresser
                {
                    C.search(3); //halt system for 3 seconds
                    C.spit.print("\nYou find a note\n");
                    C.commands.addItem("Note");
                    dresserCount++;
                } else if(dresserCount == 1){
                    C.search(3);
                    C.spit.print("\nAfter carefully searching the dresser a second time, you find " +
                        "a wrench underneath it\n");
                    C.commands.addItem("Wrench");
                    dresserCount++;
                } else {
                    C.search(3);
                    C.spit.print("\nYou can't find anything on the dresser\n");
                }
                break;

                case "search sink" : if(!C.commands.inList(answer)) {System.out.println(error); break;}
                if(sinkCount == 0) //First time viewing sink
                {
                    C.search(3);
                    C.spit.print("\nThere is a mirror above the sink, and doors below. The water " +
                        "doesn't appear to be running.\n");
                    C.commands.addCommand("Search mirror");
                    C.commands.addCommand("Search sink doors");
                    sinkCount++;
                } else {
                    C.search(3);
                    C.spit.print("\nYou find nothing else in the sink\n");
                    if(C.commands.inInventory("Bottle")){ //If they have the bottle in their inventory
                        C.spit.print("\nDo you want to pour the contents of the bottle down the drain? (y\n)\n");
                        answer = C.scan.scan();
                        if(answer.equals("y")) //To pour the contents down the drain
                        {
                            C.spit.print("\nYou pour the contents of the bottle down the drain. As the last bit of " +
                                "liquid spills out, a small key falls out of the bottle. You quickly snatch it up\n");
                            C.commands.addItem("Key");
                            C.commands.removeItem("Bottle");
                            C.commands.addItem("Empty bottle");
                        }
                    }
                }
                break;

                case "search mirror" : if(!C.commands.inList(answer)) {System.out.println(error); break;}
                if(mirrorCount == 0) //First time viewing mirror
                {
                    C.search(3);
                    C.spit.print("\nThe mirror appears to be in tact. It is stuck to the wall\n");
                    mirrorCount++;
                } else {
                    C.search(3);
                    C.spit.print("\nYou find nothing on or around the mirror\n");
                }
                if(C.commands.inInventory("Wrench") && !C.commands.inInventory("Glass shard"))
                {
                    C.spit.print("\nDo you want to break the mirror with the wrench? (y/n)");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou swing the wrench at the mirror, causing it to shatter. Nothing " +
                            "is behind it, but you pocket a large shard of glass\n");
                        C.commands.addItem("Glass shard");
                    }
                }

                break;

                case "search sink doors" : if(!C.commands.inList(answer)) {System.out.println(error); break;}
                if(sinkDoorCount == 0) //First time viewing sink doors
                {
                    C.search(3);
                    C.spit.print("\nThe only thing you can find is a bottle of liquid, which you take\n");
                    C.commands.addItem("Bottle");
                    sinkDoorCount++;
                } else {
                    C.search(3);
                    C.spit.print("\nYou can't find anything else underneath the sink\n");
                }
                break;

                case "search door" : if(!C.commands.inList(answer)) {System.out.println(error); break;}
                if(doorCount == 0)
                {
                    C.search(3);
                    C.spit.print("\nThe door is locked shut. You see a key hole near the handle. The door itself" +
                        " seems unbreakable\n");
                    doorCount++;
                } else if(doorCount > 0){
                    C.search(3);
                    C.spit.print("\nYou can't find anything else on the door\n");
                }
                if(C.commands.inInventory("key"))
                {
                    C.spit.print("\nWould you like to try the key on the door? (y\n)\n");
                    answer = C.scan.scan(); 
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nThe key fits perfectly in the keyhole. You unlock the door, and open it." +
                            " The key dissolves as the door opens. There is a dark hallway outside of the door\n");
                        doorCount = -1;
                    }
                }
                if(doorCount == -1)
                {                        
                    C.spit.print("\nExit the room? (y\n)\n");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou leave the room and enter the dark hallway...\n");
                        done = true;
                        alive = true;
                    }
                }
                break;   

                case "use bottle" : if(!C.commands.inInventory("Bottle")) {System.out.println(error); break;}
                C.spit.print("\nDrink the bottle? (y\n)\n");
                answer = C.scan.scan();

                if(answer.equals("y")) //Drink the bottle
                {
                    C.spit.print("\nYou force the foul liquid down your throat. Before you can finish, you feel " +
                        "dizzy, and the world starts to turn black. The last thing you hear before you lose " +
                        "consciousness is a little girl laughing.\n");

                    C.commands.removeItem("Bottle");
                    C.commands.addItem("Half full bottle");
                    C.die();
                    done = true;
                }
                break;

                case "commands" : System.out.println(C.commands.getCommands()); break;

                case "inventory" : System.out.println(C.commands.getInventory()); break;
                
                case "clear" : System.out.println("\f"); break;

                case "done" : done = true; break;

                case "die" : C.spit.print("\nYou spontaneously burst into a giant ball of flames.\n");
                done = true;
                C.die();
                break;

                case "skip" : done = true;  //warning: as this is a debug feature, use at your own risk. weirdness may occur (such as item duplication)
                C.commands.addItem("Note"); C.commands.addItem("Glass shard");
                C.commands.addItem("Empty bottle"); C.commands.addItem("Wrench");
                alive = true; break;

                default : item = C.commands.checkDefaultItems(answer); //Check items that do nothing
                if(!item)
                    System.out.println(error); break;
            }            
        }
        //End Loop

        return alive;
    }
}
