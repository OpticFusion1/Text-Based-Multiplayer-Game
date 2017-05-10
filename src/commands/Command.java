package commands;

import console_gui.UserInformation;

/**
 * A class to run a given command. As a rule each inheriting class needs to be interchangeable with other instances of 
 * itself. Such that a call to any methods is equivalent between all instances of any inheriting class.
 *  
 * @author Zachary Chandler
 */
public abstract class Command implements Comparable<Command>{
   
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
   
   /**
    * @return the help file for the object. 
    */
   public abstract String getShortHelpDescription();

   /**
    * @return preferred command name
    */
   public abstract String getPreferredName();
   
   @Override
   public final int hashCode() {
       return this.getClass().hashCode();
   }
   
   @Override
   public final boolean equals(Object other) {
       return this.getClass().equals(other.getClass());
   }
   
   @Override
   public final int compareTo(Command other) {
       return this.toString().compareTo(other.toString());
   }
   
   @Override
   public final String toString() {
       return getPreferredName();
   }
}