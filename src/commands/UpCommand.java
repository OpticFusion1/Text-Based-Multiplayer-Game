package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class UpCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"UP", "U"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.setCurrentRoom(info.getCurrentRoom().getUp());
    }

}
