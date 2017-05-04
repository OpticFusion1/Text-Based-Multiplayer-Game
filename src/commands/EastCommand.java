package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class EastCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"EAST", "E"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.setCurrentRoom(info.getCurrentRoom().getEast());
    }

}
