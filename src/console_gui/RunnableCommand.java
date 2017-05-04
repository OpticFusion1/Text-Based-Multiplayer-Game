package console_gui;

public abstract class RunnableCommand {
    
   public abstract String[] getAliases();
   public abstract void runCommand(CurrentInformation info, String[] args);
}