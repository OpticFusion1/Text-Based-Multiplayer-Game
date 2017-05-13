package commands;

import console.Helper;
import console.User;
import model.Direction;
import model.RoomNode;

/**
 * A command to separate a room from the other room, bi-directionally if applicable.
 * 
 * separates the current room from the room in the given direction such that a
 * move in the current room in the given direction will not succeed and a move
 * in the other room in the opposite direction will not lead to the current
 * room.
 * 
 * after this command is called the path in the direction is unlinked and if 
 * the other room had an opposite path to this room, it will also be unlinked.
 *
 * @author Zachary Chandler
 */
public class SeparateCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"SEPARATE"} ;
    }

    @Override
    public String getPreferredName() {
        return "separate";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        boolean error = false;
        
        if (args.length != 2) {
            info.print("Invalid number of arguments, ");
            
            error = true;
        } else {
            Direction directionToWallOff = Direction.translateDirection(args[1]);
            Direction oppositeDirection = Direction.getOppositeDirection(directionToWallOff);
            
            if (directionToWallOff == null) {
                info.print("Direction not found, ");
                error = true;
            } else {
                RoomNode room = info.getCurrentRoom().getDirection(directionToWallOff);
                
                if (room == null) {
                    info.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setDirection(directionToWallOff, null);
                    
                    if (info.getCurrentRoom() == room.getDirection(oppositeDirection)) {
                        room.setDirection(oppositeDirection, null);                        
                    }
                    
                    String message = Helper.buildString(info.getUsername(), " separated ",
                            info.getCurrentRoom().getName(), "from", room.getName());

                    User.chat.printlnToRoom(info, message);
                }
            }
            
        }
        
        if (error) {
            info.print("see help seperate for more details.\n");
        }
        
    }

    @Override
    public String getShortHelpDescription() {
        return "Seperates two rooms, bi-directionally if possible.";
    }
}
