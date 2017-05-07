package commands;

import console_gui.UserInformation;

public class EmptyCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        // do nothing
    }

}
