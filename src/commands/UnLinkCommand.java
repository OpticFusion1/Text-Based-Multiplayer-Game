package commands;

import console_gui.UserInformation;
import model.Direction;
import model.RoomNode;

public class UnLinkCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"UNLINK"} ;
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
                    info.out.printf("UnLinked Pathway: %s\n", directionToUnlink.toString());
                }
            }
        }
        
        if (error) {
            info.out.print("Usage: \"unlink <DIRECTION\"\n");
        }
    }

}
