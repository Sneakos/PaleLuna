
/**
 * Write a description of class Launcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Launcher
{
    public static void main(String[] args)
    {
        //Game Variables
        boolean alive;
        int room;
        
        //Initialize rooms
        Room1 room1 = new Room1();
        Room2 room2 = new Room2();
        Room3 room3 = new Room3();
        Room4 room4 = new Room4();
        Hallway hallway = new Hallway();
        
        //start
        alive = room1.main();
        
        while(alive == true) //hallway loop
        {
            room = hallway.main();
            switch(room)
            {
                case 1 : alive = room1.main(); break; //first room
                case 2 : alive = room2.main(); break; //left room
                case 3 : alive = room3.main(); break; //right room 
                case 4 : alive = room4.main(); break; //victory room
                default : alive = false; break; //If you somehow manage to die in the hallway
            }
        }
    }
}
