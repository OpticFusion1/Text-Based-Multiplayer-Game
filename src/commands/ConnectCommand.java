package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Direction;
import model.RoomNode;

public class ConnectCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"CONNECT"};
    }

    @Override
    public String getPreferredName() {
        return "connect";
    }
    
    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 3) {
            info.out.print("Not enough arguments, ");
            error = true;
        } else if (args.length > 3) {
            info.out.println("Too many arguments, ");
            error = true;
        } else {
            Direction direction = Direction.translateDirection(args[1]);
            Direction oppositeDirection = Direction.getOppositeDirection(direction);
            
            Integer choice = Helper.safeParseInt(args[2]);
            
            if (direction == null) {
                info.out.print("Direction not found, ");
                error = true;
            }
            
            if (choice == null) {
                info.out.print("Invalid room number format, ");
                error = true;
            } 

            if (!error) {
                RoomNode room = info.rooms.getRoom(choice);
                
                if (room == null) {
                    info.out.print("Room not found, ");
                    error = true;
                } else {
                    info.getCurrentRoom().setDirection(direction, room);
                    
                    if (room.getDirection(oppositeDirection) == null) {
                        room.setDirection(oppositeDirection, info.getCurrentRoom());
                    }
                    info.out.printf("Connected %s to %s\n", info.getCurrentRoom().getName(), room.getName());
                }
            }
        }
        
        if (error) {
            info.out.print("see 'help connect' for more details.");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Connects to rooms togeather, bi-directionally if possible.";
    }

}
