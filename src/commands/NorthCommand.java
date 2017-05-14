package commands;

import console.User;
import model.Direction;

/**
 * A command to move the user in a given direction.
 * 
 * @author Zachary Chandler
 */
public class NorthCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"NORTH", "N"} ;
    }

    @Override
    public String getPreferredName() {
        return "north";
    }

    @Override
    public void runCommand(User info, String[] args) {
        move(info, Direction.NORTH);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves north by one room.";
    }
}
