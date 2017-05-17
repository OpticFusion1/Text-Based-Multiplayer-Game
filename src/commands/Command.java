package commands;

import java.io.File;

import console.Helper;
import console.User;

/**
 * A class to run a command. And to load and display a help file.
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
    * @param info the user that will run the command
    * @param args the arguments of the command.
    */
   public abstract void runCommand(User info, String[] args);
   
   /**
    * @return the help file for the object. 
    */
   public abstract String getShortHelpDescription();

   /**
    * A name that will be displayed and associated with the command.
    * 
    * @return preferred name for the command.
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
   public final int compareTo(Command other) {
       return this.toString().compareTo(other.toString());
   }
   
   @Override
   public final String toString() {
       return getPreferredName();
   }
}