package commands;

import console_gui.UserInformation;
import model.Direction;
import model.RoomNode;

public class DigCommand extends RunnableCommand {

    public static final String DEFUALT_DESCRIPTION = "a very bland room";
    
    @Override
    public String[] getAliases() {
        return new String[] {"DIG"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 3) {
            info.out.print("Not enough arguments, ");
            error = true;
        } else if (args.length > 4) {
            info.out.print("Too many arguments, ");
            error = true;
        } else {
            String name = args[2];
            String description = (args.length == 4) ? args[3] : DEFUALT_DESCRIPTION;
            
            Direction directionToDig = Direction.translateDirection(args[1]);
            Direction oppositeDirection = Direction.getOppositeDirection(directionToDig);
            
            if (directionToDig == null) {
                info.out.print("Direction not found, ");
                error = true;
            } else {
                RoomNode room = info.getCurrentRoom().getDirection(directionToDig);
                
                if (room != null) {
                    info.out.println("There is already a room there!");
                } else {
                    RoomNode newRoom = new RoomNode(info.rooms.getUniqueRoomID(), name, description);
                    
                    info.getCurrentRoom().setDirection(directionToDig, newRoom);
                    newRoom.setDirection(oppositeDirection, info.getCurrentRoom());

                    info.rooms.trackRoom(newRoom);
                    MoveCommand.move(info, directionToDig);
                }
            }
        }
        
        if (error) {
            info.out.print("Usage: \"dig <direction> <name>\"\n");
        }
    }

}
