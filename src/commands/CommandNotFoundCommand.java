package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class CommandNotFoundCommand extends RunnableCommand {

    public static final String LOOK_UP_STRING = "";
    
    @Override
    public String[] getAliases() {
        return new String[] {LOOK_UP_STRING} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        System.out.print("Command not found (" + args.length + "): ");
        
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            System.out.print(" ");
        }
        
        System.out.println();
    }

}
