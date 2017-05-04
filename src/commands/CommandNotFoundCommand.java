package commands;

import console_gui.CurrentInformation;

public class CommandNotFoundCommand extends RunnableCommand {

    public static final String LOOK_UP_STRING = "";
    
    @Override
    public String[] getAliases() {
        return new String[] {LOOK_UP_STRING} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.out.print("Command not found (" + args.length + "): ");
        
        for (int i = 0; i < args.length; i++) {
            info.out.print(args[i]);
            info.out.print(" ");
        }
        
        info.out.println();
    }

}
