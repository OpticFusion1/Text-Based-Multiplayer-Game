package commands;

import console_gui.UserInformation;
import model.Direction;

public class WestCommand extends MoveCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"WEST", "W"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        move(info, Direction.WEST);
    }

}
