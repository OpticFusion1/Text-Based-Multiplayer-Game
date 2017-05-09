package commands;

import console_gui.UserInformation;
import model.Direction;

public class SouthCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"SOUTH", "S"} ;
    }

    @Override
    public String getPreferredName() {
        return "south";
    }
    
    @Override
    public void runCommand(UserInformation info, String[] args) {
        move(info, Direction.SOUTH);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves south by one room";
    }
}
