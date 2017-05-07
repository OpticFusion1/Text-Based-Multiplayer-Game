package commands;

import console_gui.UserInformation;

public abstract class RunnableCommand {
   
    /**
     * @return return a list of aliases for this Command.
     */
   public abstract String[] getAliases();
   
   /**
    * Run the command for the given arguments on the given user.
    * @param info the user that will have the command run.
    * @param args the arguments of the command.
    */
   public abstract void runCommand(UserInformation info, String[] args);
   
}