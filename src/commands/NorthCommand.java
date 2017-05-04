package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class NorthCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"NORTH", "N"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.setCurrentRoom(info.getCurrentRoom().getNorth());
    }

}
