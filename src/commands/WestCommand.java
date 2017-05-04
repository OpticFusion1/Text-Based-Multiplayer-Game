package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class WestCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"WEST", "W"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.setCurrentRoom(info.getCurrentRoom().getWest());
    }

}
