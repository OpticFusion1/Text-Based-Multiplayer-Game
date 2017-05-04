package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class LookCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"LOOK"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        System.out.println(info.getCurrentRoom().getDescription());
    }

}
