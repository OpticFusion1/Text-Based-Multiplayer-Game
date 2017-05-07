package commands;

import console_gui.UserInformation;
import model.Direction;

public class EastCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"EAST", "E"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        move(info, Direction.EAST);
    }

}
