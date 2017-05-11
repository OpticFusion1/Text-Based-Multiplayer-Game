package commands;

import java.io.File;

import console_gui.Helper;
import console_gui.UserInformation;

/**
 * A class to be able to run a command. Keep track of the help file
 * 
 * Each inheriting class needs to be interchangeable with other instances of itself. Such that a call to any method is
 * equivalent between all instances of any inheriting class.
 * 
 * @author Zachary Chandler
 */
public abstract class Command implements Comparable<Command>{
   
    /** The help for the command. */
    private String helpPage;
    
    /**
     * Initialize the command. Automatically loads the help file for this command.
     */
    public Command() {
        File helpPage = new File("data/help/" + this.getClass().getSimpleName() + ".help");
        
        if (helpPage.exists() && !helpPage.isDirectory()) {
            this.helpPage = Helper.readFileAsString(helpPage);            
        } else {
            System.out.printf("Help page not found: %s\n", helpPage.toString());
            this.helpPage = "This command doesn't have a help page yet!";
        }
    }
  
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
   
   /**
    * Get the help page on this command. Located at data/help/thisClass.help;
    * @return the help page of this command.
    */
   public String getHelpPage() {
       return this.helpPage;
   }
   
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