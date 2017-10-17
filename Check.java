
/**
 * Write a description of class Check here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Check
{
    String[] wSearch, wUse, wYes, wInventory;

    public Check()
    {
        wSearch = new String[] { "serch", "earch", "sarch", "erch", "arch", "ssearch", "seearch", "seaarch", "searcch", "searchh"} ;
        wUse = new String[] { "us", "se", "usse", "uuse", "usee" };
        wYes = new String[] { "yes", "ye", "yess", "yse", "yas", "yep", "yup", "yeah", "sure", "yolo", "maybe", "yah", "si", "aye", "mhmm", "uh huh", "yerp", "meh", "ya" };
        wInventory = new String[] { "invent", "items", "backpack", "i" };
    }

    public String correct(String comm) //returns a fixed version of the string
    {
        String[] split = comm.split(" ");
        for(int i = 0; i < wSearch.length; i++)
        {
            if(wSearch[i].equals(split[0]))
            {
                split[0] = "search";
            }
        }
        for(int i = 0; i < wUse.length; i++)
        {
            if(wUse[i].equals(split[0]))
            {
                split[0] = "use";
            }
        }
        for(int i = 0; i < wYes.length; i++)
        {
            if(wYes[i].equals(split[0]))
            {
                split[0] = "y";
            }
        }
        for(int i = 0; i < wInventory.length; i++)
        {
            if(wInventory[i].equals(split[0]))
            {
                split[0] = "inventory";
            }
        }
        if(split.length > 1)
        {
            switch(split[1])
            {
                case "mirrors" : split[1] = "mirror"; break;
                case "mirors" : split[1] = "mirror"; break;
                case "sinkdoor" : split[1] = "sink doors"; break;
                case "sinkdoors" : split[1] = "sink doors"; break;
                case "bottles" : split[1] = "bottle"; break;
                case "rench" : split[1] = "wrench"; break;
                case "shard" : split[1] = "glass shard"; break;
                case "pocket" : split[1] = "pockets"; break;
                case "left" : split[1] = "west"; break;
                case "right" : split[1] = "east"; break;
                case "watering" : split[1] = "watercan"; break;
                case "wateringcan" : split[1] = "watercan"; break;
                case "bookshelf" : split[1] = "shelf"; break;
                case "small" : split[1] = "room"; break;
            }
        }
        if(split.length > 2)
        {
            switch(split[2])
            {
                case "door" : split[2] = "doors"; break;
                case "room" : split[2] = "doors"; break;
                case "bottles" : split[2] = "bottle"; break;
                case "can" : split[2] = ""; break;
            }
        }

        String comm1 = "";

        for(int i = 0; i < split.length; i++)
        {
            if(!split[i].equals("") || i == 0)
                comm1 += " " + split[i];
        }

        comm = comm1.substring(1, comm1.length());

        return comm;
    }
}
