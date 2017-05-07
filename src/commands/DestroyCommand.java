package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Item;

public class DestroyCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"DESTROY"};
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 2) {
            info.out.print("Not enough arguments, ");
            error = true;
        } else {
            String potentialName = Helper.mergeStrings(args, 1, args.length - 1);
            
            Item toDestroy = info.getCurrentRoom().findItem(potentialName);
            
            if (toDestroy == null) {
                info.out.println("Could not find item: ");
            } else {
                info.getCurrentRoom().removeItem(toDestroy);
                info.out.print("Destroyed: ");                
            }
            
            info.out.println(potentialName.toString());
        }
        
        if (error) {
            info.out.print("Usage, \"destroy <name>\"");
        }
    }

}
