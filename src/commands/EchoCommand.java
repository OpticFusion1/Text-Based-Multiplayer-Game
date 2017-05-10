package commands;

import console_gui.Helper;
import console_gui.UserInformation;

public class EchoCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[]{"ECHO"};
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        
        if (args.length > 1) {
            String value = Helper.mergeStrings(args, 1, args.length - 1);
            info.out.println(value);
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
