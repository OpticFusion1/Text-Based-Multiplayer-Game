package commands;

import console_gui.UserInformation;
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
    public void runCommand(UserInformation info, String[] args) {

        boolean error = false;
        
        if (args.length != 2) {
            info.out.print("Invalid number of arguments, ");
            
            error = true;
        } else {
            Direction directionToUnlink = Direction.translateDirection(args[1]);
            
            if (directionToUnlink == null) {
                info.out.print("Direction not found, ");
                error = true;
            } else {
                RoomNode room = info.getCurrentRoom().getDirection(directionToUnlink);
                
                if (room == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setDirection(directionToUnlink, null);
                    info.out.printf("Removed pathway %s to %s\n", directionToUnlink.toString(), room.getName());
                }
            }
        }
        
        if (error) {
            info.out.print("see 'help unlink' for more information\n");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Closes a path in this room only.";
    }
}
