package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class SouthCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"SOUTH", "S"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.setCurrentRoom(info.getCurrentRoom().getSouth());
    }

}
