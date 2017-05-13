package commands;

import console_gui.Helper;
import console_gui.User;

public class SayCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"SAY"};
    }

    @Override
    public void runCommand(User info, String[] args) {
        
        if (args.length > 1) {
            StringBuilder message = new StringBuilder();
            message.append(info.getUsername());
            message.append(": ");
            message.append(Helper.mergeStrings(args, 1, args.length - 1));
            
            info.printlnToRoom(message.toString());
        } else {
            info.println(getShortHelpDescription());
        }
        
    }

    @Override
    public String getShortHelpDescription() {
        return "Displays a message to other users.";
    }

    @Override
    public String getPreferredName() {
        return "say";
    }

}
