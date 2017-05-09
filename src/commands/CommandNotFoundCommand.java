package commands;

import console_gui.UserInformation;

public class CommandNotFoundCommand extends Command {

    public static final String LOOK_UP_STRING = "";
    
    @Override
    public String[] getAliases() {
        return new String[] {LOOK_UP_STRING, "COMMANDNOTFOUND"} ;
    }

    @Override
    public String getPreferredName() {
        return "commandnotfound";
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        // do we have multiple commands OR is there only one command, but it isn't the empty string
        if (args.length > 1 || (args.length == 1 && !args[0].equals(""))) {
            
            info.out.print("Command not found (" + args.length + "): ");
            
            for (int i = 0; i < args.length; i++) {
                info.out.print(args[i]);
                info.out.print(',');
            }
            
            info.out.println();    
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "displays the given command that wasn't found.";
    }
}
