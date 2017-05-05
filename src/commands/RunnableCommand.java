package commands;

import console_gui.UserInformation;

public abstract class RunnableCommand {
    
   public abstract String[] getAliases();
   public abstract void runCommand(UserInformation info, String[] args);
}