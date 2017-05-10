package commands;

import console_gui.UserInformation;
import model.Direction;

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
    public void runCommand(UserInformation info, String[] args) {
        move(info, Direction.EAST);
    }

    @Override
    public String getShortHelpDescription() {
        return "Moves east by one room.";
    }
}
