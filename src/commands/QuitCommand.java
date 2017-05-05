package commands;

import console_gui.UserInformation;

public class QuitCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"QUIT", "Q"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        
    }

}
