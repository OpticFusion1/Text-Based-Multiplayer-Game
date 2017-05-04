package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class DownCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"DOWN", "D"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.setCurrentRoom(info.getCurrentRoom().getDown());
    }

}
