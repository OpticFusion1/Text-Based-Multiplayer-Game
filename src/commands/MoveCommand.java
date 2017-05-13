package commands;

import console.Helper;
import console.User;
import model.Direction;
import model.RoomNode;

/**
 * An abstract command to handle movements. For consistency all basic movements should go through this in order to look
 * the same way. Or at least call MoveCommand.arrive().
 *
 * @author Zachary Chandler
 */
public abstract class MoveCommand extends Command {
    
    /** A method to allow subclasses to automatically move given a direction. */
    public static void move(User info, Direction d) {
        RoomNode potentialRoom = info.getCurrentRoom().getDirection(d);
        
        if (potentialRoom == null) {
            info.println("You can't go that way.");
        } else {
            leaveMessage(info, d);
            info.setCurrentRoom(potentialRoom);
            arriveMessage(info);

            info.input.insertNextCommand(LookCommand.instance);
        }
    }

    /** A method to display information when the user changes rooms. */
    public static void arriveMessage(User info) {
        String message = info.getUsername() + " arrived";
        User.chat.printlnToOthersInRoom(info, message);
    }
    
    /** A method to display information when the user changes rooms. */
    public static void arriveMessage(User info, Direction d) {
        String message = Helper.buildString(info.getUsername(), " arrived ", d.lowercaseName);
        User.chat.printlnToOthersInRoom(info, message);
    }
    
    /** A method to display information when the user changes rooms. */
    public static void leaveMessage(User info, Direction d) {
        String message = Helper.buildString(info.getUsername(), " left ", d.lowercaseName);
        User.chat.printlnToOthersInRoom(info, message);
    }
}
