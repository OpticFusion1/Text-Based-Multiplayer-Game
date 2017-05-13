package commands;

import console_gui.Helper;
import console_gui.User;

/**
 * A command to echo a line of text back to the user. Useful for item use.
 * 
 * @author Zachary Chandler
 */
public class EchoCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[]{"ECHO"};
    }

    @Override
    public void runCommand(User info, String[] args) {
        
        if (args.length > 1) {
            String value = Helper.mergeStrings(args, 1, args.length - 1);
            info.println(value);
        }
        
    }

    @Override
    public String getShortHelpDescription() {
        return "Echo a string back at yourself.";
    }

    @Override
    public String getPreferredName() {
        return "echo";
    }

}
