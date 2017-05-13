package commands;

import console_gui.User;
import model.Direction;

/**
 * A command to move the user in a given direction.
 * 
 * @author Zachary Chandler
 */
public class UpCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"UP", "U"} ;
    }

    @Override
    public String getPreferredName() {
        return "up";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        move(info, Direction.UP);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves up by one room.";
    }
}
