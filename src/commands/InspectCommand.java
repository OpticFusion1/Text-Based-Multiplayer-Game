package commands;

import console.Helper;
import console.User;
import model.Item;

/**
 * A command to get debug information on things. For instance, RoomID's, names, descriptions and use commands.
 * 
 * @author Zachary Chandler
 */
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
    public void runCommand(User info, String[] args) {
        if (args.length == 1) {
            info.printf("RoomID: %d\n", info.getCurrentRoom().getRoomID());
            info.printf("Room Name: %s\n", info.getCurrentRoom().getName());
            info.printf("Room Description: %s\n", info.getCurrentRoom().getDescription());
        } else if (args.length > 1) {
            String potentialName = Helper.mergeStrings(args, 1, args.length - 1);
            Item toDescribe = info.getCurrentRoom().findItem(potentialName);
            
            if (toDescribe == null) {
                info.printf("Could not find the item: %s\n", potentialName);
            } else {
                info.printf("Item Name: %s\n", toDescribe.getName());
                info.printf("Item Description: %s\n", toDescribe.getDescription());
                info.printf("Item OnUse: %s\n", toDescribe.getOnUse());
                info.println("Aliases: ");
                
                for (String s : toDescribe.getAliases()) {
                    info.printf("    %s\n", s.toLowerCase());
                }
                
            }
        }
    }
    
    @Override
    public String getShortHelpDescription() {
        return "Inspects a given item, or the room if no item is given.";
    }
}
