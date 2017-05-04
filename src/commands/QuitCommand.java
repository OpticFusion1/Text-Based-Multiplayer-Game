package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class QuitCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"QUIT", "Q"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        
    }

}
