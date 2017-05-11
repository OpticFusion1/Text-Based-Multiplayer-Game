package commands;

import console_gui.UserInformation;
import console_gui.UserInputScanner;

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
    public void runCommand(UserInformation info, String[] args) {
        
        if (args.length < 2) {
            info.out.println("\nPossible Commands");
            for (Command c : UserInputScanner.COMMANDS) {
                info.out.printf("%-15s - %s\n", c.getPreferredName(), c.getShortHelpDescription());
            }            
        } else if (args.length == 2) {
            Command c = UserInputScanner.COMMAND_MAP.get(args[1].toUpperCase());
            
            if (c == null) {
                info.out.println("Sorry, we couldn't not find that command.");
            } else {
                info.out.println(c.getHelpPage());
            }
        } else {
            info.out.println("Usage 'help [commnad]'");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Gets help for all all available commands.";
    }
}
