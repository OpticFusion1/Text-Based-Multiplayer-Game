package commands;

import console.User;
import model.Direction;

/**
 * A command to move the user in a given direction.
 * 
 * @author Zachary Chandler
 */
public class EastCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"EAST", "E"} ;
    }

    @Override
    public String getPreferredName() {
        return "east";
    }

    @Override
    public void runCommand(User info, String[] args) {
        move(info, Direction.EAST);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves east by one room.";
    }
}
