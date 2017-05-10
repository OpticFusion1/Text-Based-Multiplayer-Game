package commands;

import console_gui.UserInformation;
import model.Direction;

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
    public void runCommand(UserInformation info, String[] args) {
        move(info, Direction.UP);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves up by one room.";
    }
}
