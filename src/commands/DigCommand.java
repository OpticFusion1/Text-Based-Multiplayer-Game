package commands;

import console_gui.UserInformation;
import model.Direction;
import model.RoomNode;

public class DigCommand extends RunnableCommand {

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
        } else {
            StringBuilder name = new StringBuilder();
            
            for (int i = 2; i < args.length; i++) {
                name.append(args[i]);
                name.append(' ');
            }
            
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
                    RoomNode newRoom = new RoomNode(info.rooms.getUniqueRoomID(), name.toString());
                    
                    info.getCurrentRoom().setDirection(directionToDig, newRoom);
                    newRoom.setDirection(oppositeDirection, info.getCurrentRoom());

                    info.rooms.trackRoom(newRoom);
                    info.out.println("Created Room!");
                }
            }
        }
        
        if (error) {
            info.out.print("Usage: \"dig <direction> <name>\"\n");
        }
    }

}
