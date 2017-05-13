package commands;

import console.User;

/**
 * A command that will display when no command is found.
 * 
 * @author Zachary Chandler
 */
public class CommandNotFoundCommand extends Command {
    
    @Override
    public String[] getAliases() {
        return new String[] {"", "COMMANDNOTFOUND"} ;
    }

    @Override
    public String getPreferredName() {
        return "commandnotfound";
    }

    @Override
    public void runCommand(User info, String[] args) {
        // do we have multiple commands OR is there only one command, but it isn't the empty string
        if (args.length > 1 || (args.length == 1 && !args[0].equals(""))) {
            
            info.print("Command not found (" + args.length + "): ");
            
            for (int i = 0; i < args.length; i++) {
                info.print(args[i]);
                info.print(',');
            }
            
            info.println();    
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "displays the arguments after a malformed command.";
    }
}
