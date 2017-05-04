package commands;

import console_gui.CurrentInformation;

public abstract class RunnableCommand {
    
   public abstract String[] getAliases();
   public abstract void runCommand(CurrentInformation info, String[] args);
}