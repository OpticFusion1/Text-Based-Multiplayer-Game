package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Direction;
import model.RoomNode;

public class LinkCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"LINK"};
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
                }
            }
        }
        
        if (error) {
            info.out.print("Usage: \"link <DIRECTION> <RoomID>\"\n");
        }
    }

}
