package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Item;

public class CreateCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"CREATE"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 2) {
            info.out.print("Not enough arguments, ");
            
            error = true;
        } else {
            String name = Helper.mergeStrings(args, 1, args.length - 1);
            
            info.getCurrentRoom().addItem(new Item(name.toString()));
            info.out.printf("Created: %s\n", name);
        }
        
        if (error) {
            info.out.print("Usage, \"create <name>\"");
        }
    }

}
