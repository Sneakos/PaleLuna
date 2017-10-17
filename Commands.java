
/**
 * Write a description of class Commands here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class Commands
{
    ArrayList<String> commands;
    ArrayList<String> inventory;
    Scan scan = new Scan();
    Spit spit = new Spit();          
    String error = "Command not found. Type 'Commands' for a list of commands";

    public Commands()
    {
        commands = new ArrayList<String>();
        inventory = new ArrayList<String>();
    }

    public String getCommands()
    {         
        String command = "{\n";
        for(int i = 0; i < commands.size(); i++)
        {
            command += commands.get(i) + "\n";
        }
        command += "}";
        return command;
    }

    public String getInventory()
    {
        String list = "{\n";
        for(int i = 0; i < inventory.size(); i++)
        {
            list += inventory.get(i) + "\n";
        }
        list += "}";
        return list;
    }

    public boolean inList(String test)
    {
        for(int i = 0; i < commands.size(); i++)
        {
            if(commands.get(i).equalsIgnoreCase(test))
                return true;
        }
        return false;
    }

    public boolean inInventory(String test)
    {
        for(int i = 0; i < inventory.size(); i++)
        {
            if(inventory.get(i).equalsIgnoreCase(test))
                return true;
        }
        return false;
    }

    public boolean checkDefaultItems(String query)
    {        
        boolean item = true;
        switch(query)
        {
            case "use key" : if(!C.commands.inInventory("Key")) {System.out.println(error); break;}
            spit.print("You take a look at the key. It's rather small\n");
            break;

            case "use wrench" : if(!C.commands.inInventory("Wrench")) {System.out.println(error); break;}
            spit.print("You exmaine the wrench. It's rather sturdy looking.\n");
            break;

            case "use glass shard" : if(!C.commands.inInventory("Glass shard")) {System.out.println(error); break;}
            spit.print("You look at the glass shard. It is sharp, and you can see your reflection in it\n");
            break;

            case "use empty bottle" : if(!C.commands.inInventory("Empty bottle")) {System.out.println(error); break;}
            spit.print("You shake the empty bottle. There is nothing in it.\n");
            break;

            case "use note" : if(!C.commands.inInventory("Note")) {System.out.println(error); break;}
            spit.print("The note says: \nThe shirt gives. The shirt kills.\n");
            break;
            
            case "use room key" : if(!C.commands.inInventory("Room key")) {System.out.println(error); break;}
            spit.print("You examine the key. It's slightly cold to the touch");
            break;

            default : item = false;
        }
        return item;
    }

    public void addItem(String item)
    {
        inventory.add(item);
    }

    public void addCommand(String command)
    {
        commands.add(command);
    }
    
    public void removeCommand(String command)
    {
        commands.remove(command);
    }

    public void removeItem(String item)
    {
        inventory.remove(item);
    }

    public void clear()
    {
        commands.clear();        
        addCommand("Commands");
        addCommand("Inventory");
        addCommand("Use 'item' - replace 'item' with item from inventory");
    }
}
