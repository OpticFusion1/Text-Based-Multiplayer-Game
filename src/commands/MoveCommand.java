package commands;

import console_gui.UserInformation;
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
    public static void move(UserInformation info, Direction d) {
        RoomNode potentialRoom = info.getCurrentRoom().getDirection(d);
        
        if (potentialRoom == null) {
            info.out.println("You can't go that way.");
        } else {
            info.setCurrentRoom(potentialRoom);
            arrive(info);
        }
    }
    
    /** A method to display information when the user changes rooms. */
    public static void arrive(UserInformation info) {
        LOOK.run(info);
    }
}
