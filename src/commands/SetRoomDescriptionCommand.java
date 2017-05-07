package commands;

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
            StringBuilder description = new StringBuilder();
            
            for (int i = 1; i < args.length; i++) {
                description.append(args[i]);
                description.append(' ');
            }
            
            info.getCurrentRoom().setDescription(description.toString());
            info.out.println("Set description: " + description.toString());
        }
        
        if (error) {
            info.out.print("Usage: \"setroomdescription <description>\"\n");
        }
    }

}
