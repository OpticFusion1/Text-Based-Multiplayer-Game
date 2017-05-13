package commands;

import console.User;
import model.Direction;

public class WestCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"WEST", "W"} ;
    }

    @Override
    public String getPreferredName() {
        return "west";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        move(info, Direction.WEST);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves west by one room.";
    }
}
