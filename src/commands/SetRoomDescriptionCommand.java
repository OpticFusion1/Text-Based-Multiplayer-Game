package commands;

import console_gui.Helper;
import console_gui.UserInformation;

public class SetRoomDescriptionCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"SETROOMDESCRIPTION"};
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 1) {
            info.out.print("Not enough arguments, ");
            error = true;
        } else {
            String description = Helper.mergeStrings(args, 1, args.length);
            info.getCurrentRoom().setDescription(description.toString());
            info.out.println("Set description: " + description.toString());
        }
        
        if (error) {
            info.out.print("Usage: \"setroomdescription <description>\"\n");
        }
    }

}
