package commands;

import console.User;
import model.Direction;

/**
 * A command to move the user in a given direction.
 * 
 * @author Zachary Chandler
 */
public class DownCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"DOWN", "D"} ;
    }

    @Override
    public String getPreferredName() {
        return "down";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        move(info, Direction.DOWN);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves down by one room.";
    }
}
