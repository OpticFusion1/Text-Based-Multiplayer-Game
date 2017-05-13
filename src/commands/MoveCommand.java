package commands;

import console_gui.Helper;
import console_gui.User;
import model.Direction;
import model.RoomNode;

/**
 * An abstract command to handle movements. For consistency all basic movements should go through this in order to look
 * the same way. Or at least call MoveCommand.arrive().
 *
 * @author Zachary Chandler
 */
public abstract class MoveCommand extends Command {

    /** A simple static command we can use to run look right after moving. */
    private static final RunnableCommand LOOK = new RunnableCommand(new String[] {"LOOK"}, new LookCommand());
    
    /** A method to allow subclasses to automatically move given a direction. */
    public static void move(User info, Direction d) {
        RoomNode potentialRoom = info.getCurrentRoom().getDirection(d);
        
        if (potentialRoom == null) {
            info.println("You can't go that way.");
        } else {
            leaveMessage(info, d);
            info.setCurrentRoom(potentialRoom);
            arrive(info, d);
        }
    }

    /** A method to display information when the user changes rooms. */
    public static void arrive(User info) {
        LOOK.run(info);
        
        String message = info.getUsername() + " arrived";
        
        info.printlnToOthersInRoom(message);
    }
    
    /** A method to display information when the user changes rooms. */
    public static void arrive(User info, Direction d) {
        LOOK.run(info);

        String message = Helper.buildString(info.getUsername(), " arrived from the ", d.lowercaseName);
        
        info.printlnToOthersInRoom(message);
    }
    
    /** A method to display information when the user changes rooms. */
    public static void leaveMessage(User info, Direction d) {
        String message = Helper.buildString(info.getUsername(), " left to the ", d.lowercaseName);

        info.printlnToOthersInRoom(message);
    }
}
