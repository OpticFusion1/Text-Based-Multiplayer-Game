package commands;

import console_gui.UserInformation;
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
            info.out.print("see 'help dig' for more details.");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Creates a new room in the given direction and connects it back\n"
                + "                  to the current room.";
    }

}
