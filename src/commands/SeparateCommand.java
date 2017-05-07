package commands;

import console_gui.UserInformation;
import model.Direction;
import model.RoomNode;

public class SeparateCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"SEPARATE"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length != 2) {
            info.out.print("Invalid number of arguments, ");
            
            error = true;
        } else {
            Direction directionToWallOff = Direction.translateDirection(args[1]);
            Direction oppositeDirection = Direction.getOppositeDirection(directionToWallOff);
            
            if (directionToWallOff == null) {
                info.out.print("Direction not found, ");
                error = true;
            } else {
                RoomNode room = info.getCurrentRoom().getDirection(directionToWallOff);
                
                if (room == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setDirection(directionToWallOff, null);
                    
                    if (info.getCurrentRoom() == room.getDirection(oppositeDirection)) {
                        room.setDirection(oppositeDirection, null);                        
                    }
                    
                    info.out.printf("Sperated %s from %s\n", info.getCurrentRoom().getName(), room.getName());
                }
            }
            
        }
        
        if (error) {
            info.out.print("Usage: \"separate <DIRECTION>\"\n");
        }
        
    }

}
