package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Item;

public class InspectCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"INSPECT"};
    }

    @Override
    public String getPreferredName() {
        return "inspect";
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        if (args.length == 1) {
            info.out.printf("RoomID: %d\n", info.getCurrentRoom().getRoomID());
            info.out.printf("Room Name: %s\n", info.getCurrentRoom().getName());
            info.out.printf("Room Description: %s\n", info.getCurrentRoom().getDescription());
        } else if (args.length > 1) {
            String potentialName = Helper.mergeStrings(args, 1, args.length - 1);
            Item toDescribe = info.getCurrentRoom().findItem(potentialName);
            
            if (toDescribe == null) {
                info.out.printf("Could not find the item: %s\n", potentialName);
            } else {
                info.out.printf("Item Name: %s\n", toDescribe.getName());
                info.out.printf("Item Description: %s\n", toDescribe.getDescription());
                info.out.println("Aliases: ");
                
                for (String s : toDescribe.getAliases()) {
                    info.out.printf("    %s\n", s.toLowerCase());
                }
                
            }
        }
    }
    
    @Override
    public String getShortHelpDescription() {
        return "Inspects a given item, or the room if no item is given.";
    }
}
