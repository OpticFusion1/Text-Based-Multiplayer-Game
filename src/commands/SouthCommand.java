package commands;

import console_gui.UserInformation;
import model.Direction;

public class SouthCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"SOUTH", "S"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        move(info, Direction.SOUTH);
    }

}
