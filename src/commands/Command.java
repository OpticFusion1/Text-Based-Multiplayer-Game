package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import console_gui.UserInformation;

/**
 * A class to run a given command. As a rule each inheriting class needs to be interchangeable with other instances of 
 * itself. Such that a call to any methods is equivalent between all instances of any inheriting class.
 *  
 * @author Zachary Chandler
 */
public abstract class Command implements Comparable<Command>{
   
    private String helpPage;
    
    public Command() {
        File helpPage = new File("data/help/" + this.getClass().getSimpleName() + ".help");
        
        if (helpPage.exists() && !helpPage.isDirectory()) {
            this.helpPage = loadHelpPage(helpPage);            
        } else {
            System.out.printf("Help page not found: %s\n", helpPage.toString());
            this.helpPage = "This command doesn't have a help page yet!";
        }
    }
    
    
    private final String loadHelpPage(File f) {
        StringBuilder result = new StringBuilder();
        Scanner input = null;
        
        try {
            input = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println("Please only use valid files.");
            e.printStackTrace();
            System.exit(1);
        }
        
        
        while (input.hasNextLine()) {
            result.append('\n');
            result.append(input.nextLine());
        }
        
        input.close();
        return result.toString();
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