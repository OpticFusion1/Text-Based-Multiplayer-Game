package commands;

import console_gui.UserInformation;
import model.Direction;
import model.RoomNode;

public abstract class MoveCommand extends RunnableCommand {

    /** A simple static command we can use to run look right after moving. */
    public static final Command LOOK = new Command(new String[] {"LOOK"}, new LookCommand());
    
    /** A method to allow subclasses to automatically move given a direction. */
    public void move(UserInformation info, Direction d) {
        RoomNode potentialRoom = info.getCurrentRoom().getDirection(d);
        
        if (potentialRoom == null) {
            info.out.println("You can't go that way.");
        } else {
            info.setCurrentRoom(potentialRoom);
            LOOK.run(info);
        }
    }
}
