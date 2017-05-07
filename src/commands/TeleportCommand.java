package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.RoomNode;

public class TeleportCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"TELEPORT", "TEL"};
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length != 2) {
            System.out.println("Invalid number of arguments, ");
            error = true;
        } else {
            Integer choice = Helper.safeParseInt(args[1]);
            
            if (choice == null) {
                info.out.println("Invalid number format, ");
                error = true;
            } else {
                RoomNode result = info.rooms.getRoom(choice);
                
                if (result == null) {
                    info.out.println("Room not found!");
                } else {
                    info.setCurrentRoom(result);
                    MoveCommand.LOOK.run(info);
                }
            }
        }
        
        if (error) {
            info.out.println("Usage, \"teleport <room number>\"");
        }
        
    }

}
