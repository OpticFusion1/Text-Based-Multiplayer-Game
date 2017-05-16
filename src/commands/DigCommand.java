package commands;

import console.Helper;
import console.User;
import model.Direction;
import model.RoomNode;

/**
 * creates a room in the direction and joins it back in the inverse direction. 
 * the room is given the room name and will have one path in it leading back to
 * the initial room.
 *
 * after creating the room, the user is moved into the room and the rooms
 * information is displayed.
 * 
 * @author Zachary Chandler
 */
public class DigCommand extends Command {

    public static final String DEFUALT_DESCRIPTION = "a very bland room";
    
    @Override
    public String[] getAliases() {
        return new String[] {"DIG"} ;
    }

    @Override
    public String getPreferredName() {
        return "dig";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        boolean error = false;
        
        if (args.length < 3) {
            info.print("Not enough arguments, ");
            error = true;
        } else if (args.length > 4) {
            info.print("Too many arguments, ");
            error = true;
        } else {
            String name = args[2];
            String description = (args.length == 4) ? args[3] : DEFUALT_DESCRIPTION;
            Direction directionToDig = Direction.translateDirection(args[1]);
                        
            if (directionToDig == null) {
                info.print("Direction not found, ");
                error = true;
            } else {
                Direction oppositeDirection = Direction.getOppositeDirection(directionToDig);
                RoomNode room = info.getCurrentRoom().getDirection(directionToDig);
                
                if (room != null) {
                    info.println("There is already a room there!");
                } else {
                    RoomNode newRoom = new RoomNode(info.u.rooms.getUniqueRoomID(), name, description);
                    String message = Helper.buildString(info.getUsername(), " creates a room to the ",
                            directionToDig.lowercaseName, ".");

                    info.chat.printlnToOthersInRoom(message);
                    
                    info.getCurrentRoom().setDirection(directionToDig, newRoom);
                    newRoom.setDirection(oppositeDirection, info.getCurrentRoom());

                    info.u.rooms.trackRoom(newRoom);
                    MoveCommand.move(info, directionToDig);
                }
            }
        }
        
        if (error) {
            info.print("see 'help dig' for more details.");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Creates a new room in the given direction.";
    }

}
