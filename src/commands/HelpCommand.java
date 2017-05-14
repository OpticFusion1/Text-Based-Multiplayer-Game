package commands;

import console.User;
import console.UserInputScanner;

/**
 * A command to help the user by displaying all commands and displaying the help page for a given command.
 * 
 * @author Zachary Chandler
 */
public class HelpCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"HELP", "?"};
    }

    @Override
    public String getPreferredName() {
        return "help";
    }

    @Override
    public void runCommand(User info, String[] args) {
        
        if (args.length < 2) {
            info.println("\nPossible Commands");
            for (Command c : UserInputScanner.COMMANDS) {
                info.printf("%-15s - %s\n", c.getPreferredName(), c.getShortHelpDescription());
            }            
        } else if (args.length == 2) {
            Command c = UserInputScanner.COMMAND_MAP.get(args[1].toUpperCase());
            
            if (c == null) {
                info.println("Sorry, we couldn't not find that command.");
            } else {
                info.println(c.getHelpPage());
            }
        } else {
            info.println("Usage 'help [commnad]'");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Gets help for all all available commands.";
    }
}
