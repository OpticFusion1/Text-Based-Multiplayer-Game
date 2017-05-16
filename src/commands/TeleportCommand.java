package commands;

import console.Helper;
import console.User;
import model.RoomNode;

/**
 * A command to teleport the user to a given room.
 *
 * @author Zachary Chandler
 */
public class TeleportCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"TELEPORT", "TEL"};
    }

    @Override
    public String getPreferredName() {
        return "teleport";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        boolean error = false;
        
        if (args.length != 2) {
            info.println("Invalid number of arguments, ");
            error = true;
        } else {
            Integer choice = Helper.safeParseInt(args[1]);
            
            if (choice == null) {
                info.println("Invalid number format, ");
                error = true;
            } else {
                RoomNode result = info.u.rooms.getRoom(choice);
                
                if (result == null) {
                    info.println("Room not found!");
                } else {
                    String message = Helper.buildString(info.getUsername(), " teleported in");
                    info.chat.printlnToOthersInRoom(message);
                    
                    info.setCurrentRoom(result);

                    message = Helper.buildString(info.getUsername(), " teleported out");
                    info.chat.printlnToOthersInRoom(message);
                    
                    info.input.insertNextCommand(LookCommand.instance);
                }
            }
        }
        
        if (error) {
            info.println("see 'help teleport' for more information");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Teleports to the given room.";
    }
}
