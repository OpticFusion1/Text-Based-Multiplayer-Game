package commands;

import console.Helper;
import console.User;
import model.Direction;
import model.RoomNode;

/**
 * Unlinks the current room from another in the given direction. Applies only to the current room.
 *
 * @author Zachary Chandler
 */
public class UnLinkCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"UNLINK"} ;
    }

    @Override
    public String getPreferredName() {
        return "unlink";
    }
    
    @Override
    public void runCommand(User info, String[] args) {

        boolean error = false;
        
        if (args.length != 2) {
            info.print("Invalid number of arguments, ");
            
            error = true;
        } else {
            Direction directionToUnlink = Direction.translateDirection(args[1]);
            
            if (directionToUnlink == null) {
                info.print("Direction not found, ");
                error = true;
            } else {
                RoomNode room = info.getCurrentRoom().getDirection(directionToUnlink);
                
                if (room == null) {
                    info.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setDirection(directionToUnlink, null);
                    
                    String message = Helper.buildString(info.getUsername(), " removed pathway ",
                            directionToUnlink.lowercaseName, " to ", room.getName());
                    
                    info.chat.printlnToRoom(message);
                }
            }
        }
        
        if (error) {
            info.print("see 'help unlink' for more information\n");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Closes a path in this room only.";
    }
}
