package commands;

import console.User;

/**
 * A command that will display when no command is found. In the form of:
 *      Command not found (...)
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
            
            info.print("Command not found (");
            info.print(args[0]);
            info.print(")\n"); 
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "displays the arguments after a malformed command.";
    }
}
