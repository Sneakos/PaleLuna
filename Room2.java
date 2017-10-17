/*
 * Room 2
 * 
 * SOLVE:
 * Put on shirt found in wardrobe
 * Take room 3 key out of pocket
 * Take shirt off
 * Search plant to find watering can
 * Take leaf from plant
 * Fill bottle with water
 * Put leaf in bottle
 * Search shelf to find lamp
 * Turn lamp on and off four times
 * Place bottle under lamp
 * Drink bottle
 * Exit room
 * 
 * OPTIONAL:
 * Read book on shelf that tells how to make antidote
 * 
 * OPTIONAL (But required for later):
 * Open Box with key from room 3
 * Kill badger with either wrench or glass shard
 * Get key from inside badger
 * 
 * WAYS TO DIE:
 * Type Die
 * Don't kill badger after box is opened
 * Don't kill badger in time after box is opened
 * Leave room after wearing posioned shirt
 * Leave room while wearing posioned shirt
 * Water the plant 6 times
 * Take more than 6 leaves off the plant
 */

import java.util.concurrent.TimeUnit;

public class Room2 implements Room
{
    boolean done, lightOn;
    String answer;
    int lampCount;

    public Room2()
    {

    }

    public boolean main()
    {
        //Initialize Command List
        C.commands.clear();
        C.commands.addCommand("Commands");
        C.commands.addCommand("Inventory");        
        C.commands.addCommand("Search shelf");
        C.commands.addCommand("Search plant");
        C.commands.addCommand("Search wardrobe");
        C.commands.addCommand("Search box");
        C.commands.addCommand("Search door");
        C.commands.addCommand("Use 'item' - replace 'item' with item from inventory");

        //Initialize all game variables
        boolean alive = false;
        boolean item = false;
        lightOn = false;
        boolean shirtOn = false;
        boolean poisoned = false;
        lampCount = 0;
        answer = "";
        done = false;
        String error = "\nCommand not found. Type 'Commands' for a list of commands";
        int waterCount = 0; //lol
        int leafCount = 0; //lol

        //Intro
        C.spit.print("\nYou find yourself in a room. There is a plant to your left, a shelf to your " +
            "right, a wardrobe at the far end of the room, and right next to the wardrobe, a box " +
            "that is shaking violently.\n");

        //Begin Loop
        while(!done)
        {
            //Prompt
            C.spit.print("\nWhat do you want to do? \n");
            answer = C.scan.scan();

            //Respond to answer
            switch(answer)
            {
                case "search shelf" : C.search(3);
                if(!C.commands.inInventory("Book"))
                {
                    C.spit.print("\nYou find a book laying on the shelf, which you take.\n");
                    C.commands.addItem("Book");
                    C.spit.print("Do you wish to keep searching? (y/n)\n");
                    answer = C.scan.scan();
                    if(!answer.equals("y")){
                        C.spit.print("\nYou leave the shelf and continue to search the room\n");
                        break;
                    }
                }
                if(!C.commands.inList("Search Lamp"))
                    C.commands.addCommand("Search lamp");
                C.spit.print("\nYou notice a lamp sitting on the top shelf. It is currently turned ");
                lamp();
                break;

                case "search wardrobe" : C.search(3);
                if(!C.commands.inInventory("Shirt"))
                {
                    C.spit.print("\nYou find an old white shirt, with drops of what appears to be blood stains dotting the shirt randomly\n");
                    C.spit.print("\nDo you wish to take the shit? (y/n)\n");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou take the shirt out of the wardrobe\n");
                        C.commands.addItem("Shirt");
                    } else {
                        C.spit.print("\nYou choose not to take the shirt. There is nothing else in the wardrobe\n");
                    }
                } else {
                    C.spit.print("\nYou search the wardrobe, but find nothing\n");
                }
                break;

                case "search plant" : C.search(3);
                C.spit.print("\nThe plant is green and has plenty of leaves\n");
                if(!C.commands.inInventory("Watercan"))
                {
                    C.spit.print("\nThere is a watercan next to the plant. It is full of water\n");
                    C.spit.print("Would you like to take the watercan? (y/n)\n");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou take the watercan\n");
                        C.commands.addItem("Watercan");
                    }
                    else
                    {
                        C.spit.print("\nYou choose not to take the watercan\n");
                    }
                }
                C.spit.print("\nWould you like to take a leaf from the plant? (y/n)\n");
                answer = C.scan.scan();
                if(answer.equals("y"))
                {
                    if(C.commands.inInventory("Leaf"))
                    {
                        C.spit.print("\nBut you already have a leaf!\n");
                    } else {
                        C.spit.print("\nYou take a leaf from the plant\n");
                        C.commands.addItem("Leaf");
                        leafCount++;
                        if(leafCount >= 6)
                        {
                            C.spit.print("\nYou have taken all the leaves off the plant...\n");
                            C.sleep(2);
                            C.spit.print("\nMY");
                            C.sleep(2);
                            C.spit.print("\nPLANT");
                            C.sleep(2);
                            C.spit.print("\nSuddenly, the room goes dark. You try to adjust your eyes, but it is too dark to see anything. " + 
                                "Suddenly, you hear light footsteps slowly approaching you. You freeze, afraid to move. Afraid to breathe." +
                                "The footsteps continue to approach until they sound like they are feet from you. Then, silence...");
                            C.sleep(2);
                            C.spit.print("\nThe light flashes back on, and your view is filled with a pale looking girl standing right in front of you. " +
                                "The last thing you make out before the world fades is a small smile on her face, almost as though she knows something " + 
                                "you don't...\n");
                            C.die();
                            done = true;
                        }
                    }
                }
                break;

                case "search box" : C.search(3);
                C.spit.print("\nThe box is shaking violently, and you think you can hear scratching coming from inside the box. " + 
                    "However, it is locked securely, and you see no way of opening it.\n");
                break;

                case "search pockets" : if(!C.commands.inList(answer)) {System.out.println(error); break;}
                C.search(1);
                if(C.commands.inInventory("Room key"))
                {
                    C.spit.print("\nYou find nothing in either pocket\n");
                } else {
                    C.spit.print("\nYou find a small key in the right pocket\n");
                    C.commands.addItem("Room key");
                }
                break;

                case "search lamp" : if(!C.commands.inList(answer)) {System.out.println(error); break;}
                C.search(3);
                C.spit.print("\nThe lamp is sitting on the top of the shelf. It is currently turned ");
                lamp();
                break;
                
                case "exit":
                //Go to 'search door'
                case "search door" :
                C.spit.print("\nWould you like to exit the room? (y/n)\n");
                answer = C.scan.scan();
                if(answer.equals("y"))
                {
                    if(poisoned)
                    {
                        C.spit.print("\nYou exit the room, and enter the hallway. However, within second of exiting, you feel very faint. The world starts to " +
                            "spin around you, and you collapse to the floor. Through your blurry vision, you barely make out a pale girl with red eyes approaching you, " +
                            "with a sly smile on her face. The world fades to black...\n");
                        C.die();
                        done = true;
                    } else {
                        C.spit.print("\nYou exit the room, and enter the hallway...\n");
                        C.commands.clear();
                        done = true;
                        alive = true;
                    }
                } else {
                    C.spit.print("\nYou decide not to leave the room\n");
                }
                break;

                case "use shirt" : if(!C.commands.inInventory("Shirt")) {System.out.println(error); break;}
                if(shirtOn)
                {
                    C.spit.print("\nDo you wish to take the shirt off? (y/n)\n");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou take the shirt off\n");
                        shirtOn = false;
                        C.commands.removeCommand("search pockets");
                    }
                } else {
                    C.spit.print("\nDo you wish to put on the shirt? (y/n)\n");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou put the shirt on\n");
                        shirtOn = true;
                        poisoned = true;
                        C.commands.addCommand("Search pockets");
                        if(!C.commands.inInventory("Room key"))
                        {
                            C.spit.print("\nYou feel something in your pocket\n");
                        }
                    } else {
                        C.spit.print("\nYou choose not to put the shirt on\n");
                        C.spit.print("Would you like to search the pockets of the shirt? (y/n)\n");
                        answer = C.scan.scan();
                        if(answer.equals("y"))
                        {
                            C.search(3);
                            C.spit.print("\nYou search the pockets thouroughly, but find nothing\n");
                        }
                        else
                        {
                            C.spit.print("\nYou choose not to search the pockets\n");
                        }   
                    }
                }
                break;

                case "use watercan" : if(!C.commands.inInventory("Watercan")) {System.out.println(error); break;}
                if(C.commands.inInventory("Empty bottle"))
                {
                    C.spit.print("\nWould you like to fill your empty bottle with water? (y/n)\n");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou fill the empty bottle with water\n");
                        C.commands.removeItem("Empty bottle");
                        C.commands.addItem("Bottle of water");
                    }
                } else if(C.commands.inInventory("Leaf bottle"))
                {
                    C.spit.print("\nWould you like to fill your leaf bottle with water? (y/n)\n");       
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou fill the leaf bottle with water\n");
                        C.commands.removeItem("Leaf bottle");
                        C.commands.addItem("Partial mix");
                    }
                }
                C.spit.print("\nWould you like to water the plant? (y/n)\n");
                answer = C.scan.scan();
                if(answer.equals("y"))
                {
                    if(waterCount >= 6)
                    {
                        C.spit.print("\nTOO\n"); C.sleep(1);
                        C.spit.print("MUCH\n"); C.sleep(1);
                        C.spit.print("WATER\n"); C.sleep(1);
                        C.sleep(2);
                        C.spit.print("\nYou hear the door creak open behind you. You quickly turn around to see what " + 
                            "opened the door, only to find youself face to face with a pale looking girl with red eyes. " +
                            "The last thing you see as the world fades around you is her smiing...\n");
                        C.die();
                        done = true;
                    } else {
                        C.spit.print("\nYou water the plant. Nothing hapens\n");
                        waterCount++;
                    }
                }
                break;

                case "use book" : if(!C.commands.inInventory("Book")) {System.out.println(error); break;}
                C.spit.print("\nYou open the book to read it, only to find that all of the pages are empty. After skimming through " + 
                    "the pages for a bit, you find one page that has writing on it. It reads:\n\n\n");
                C.sleep(1);
                C.spit.print("Moringa leaf - 1\nWater - 16 oz\nLight - 3 sec*\n\n*Light must be properly prepared (on, mod 4)\n\n");
                C.sleep(1);
                C.spit.print("\nYou page through the rest of the book, but find nothing else. You close the book.\n");
                break;

                case "use leaf" : if(!C.commands.inInventory("Leaf")) {System.out.println(error); break;}
                if(C.commands.inInventory("Empty bottle"))
                {
                    C.spit.print("\nWould you like to fill your empty bottle with the leaf? (y/n)\n");
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou fill the empty bottle with the leaf\n");
                        C.commands.removeItem("Empty bottle");
                        C.commands.removeItem("Leaf");
                        C.commands.addItem("Leaf bottle");
                        break;
                    }
                } else if(C.commands.inInventory("Bottle of water"))
                {
                    C.spit.print("\nWould you like to put the leaf in the bottle of water? (y/n)\n");       
                    answer = C.scan.scan();
                    if(answer.equals("y"))
                    {
                        C.spit.print("\nYou put the leaf in the bottle water\n");
                        C.commands.removeItem("Bottle of water");
                        C.commands.removeItem("Leaf");
                        C.commands.addItem("Partial mix");
                        break;
                    }
                }
                C.spit.print("\nWould you like to eat the leaf? (y/n)\n");
                answer = C.scan.scan();
                if(answer.equals("y"))
                {
                    C.spit.print("\nYou eat the leaf. It is bitter to the taste\n");
                    C.commands.removeItem("Leaf");
                }
                break;

                case "use mix" : if(!C.commands.inInventory("Mix")) {System.out.println(error); break;}
                C.spit.print("\nWould you like to drink the mix you have created? (y/n)\n");
                answer = C.scan.scan();
                if(answer.equals("y"))
                {
                    C.spit.print("\nYou force the liquid down your throat. It is bitter to the taste. \n");
                    if(!shirtOn)
                    {
                        poisoned = false;
                        C.spit.print("Though you didn't feel ill before, you feel better\n");
                        C.commands.removeItem("Mix");
                        C.commands.addItem("Empty bottle");
                    }                            
                } else {
                    C.spit.print("\nYou decide not to drink the mix\n");
                }
                break;

                case "use partial mix" : if(!C.commands.inInventory("Partial mix")) {System.out.println(error); break;}
                C.spit.print("\nYou take a look at the mix of water and the leaf. It seems unfinished. You decide not to drink it yet.\n");
                break;

                case "commands" : System.out.println(C.commands.getCommands()); break;

                case "inventory" : System.out.println(C.commands.getInventory()); break;

                case "clear" : System.out.println("\f"); break;

                case "done" : done = true; break;

                case "die" : C.spit.print("\nYou spontaneously burst into a giant ball of flames.\n"); 
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

    //this method can be accessed through two different commands, so we'll just put it here
    public void lamp()
    {
        boolean done = false;
        while(!done)
        {
            if(lightOn)
            {
                C.spit.print("on\nDo you want to turn it off? (y/n)\n");
                answer = C.scan.scan();
                if(answer.equals("y"))
                {
                    C.spit.print("\nYou turn the lamp ");
                    lightOn = false;
                } else {
                    done = true;
                }
            }
            else
            {
                C.spit.print("off\nDo you want to turn it on? (y/n)\n");
                answer = C.scan.scan();
                if(answer.equals("y"))
                {
                    C.spit.print("\nYou turn the lamp ");
                    lightOn = true;
                    lampCount++;
                } else {
                    done = true;
                }
            }
        }
        if(C.commands.inInventory("Partial mix"))
        {
            C.spit.print("\nWould you like to place the partial mix under the lamp? (y/n)\n");
            answer = C.scan.scan();
            if(answer.equals("y"))
            {
                C.spit.print("\nYou place the partial mix under the lamp...\n");
                C.sleep(3);
                if(lampCount % 4 == 0)
                {
                    C.spit.print("\nAs you stare intently at the partial mix, it seems to turn a darker color, and becomes more appealing to the eye. " +
                        "You take the mix\n");
                    C.commands.removeItem("Partial mix");
                    C.commands.addItem("Mix");
                } else {
                    C.spit.print("\nNothing appears to be happening, and you grow impatient of waiting. You take the partial mix back, discouraged\n");
                }
            }
        }
    }
}
