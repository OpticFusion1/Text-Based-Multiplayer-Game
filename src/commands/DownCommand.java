package commands;

import console_gui.UserInformation;
import model.Direction;

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
    public void runCommand(UserInformation info, String[] args) {
        move(info, Direction.DOWN);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves down by one room.";
    }
}
