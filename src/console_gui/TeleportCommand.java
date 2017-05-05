package console_gui;

import commands.RunnableCommand;
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
            String inputLine = args[1];
            
            Integer choice;
            
            try {
                choice = Integer.parseInt(inputLine);           
            } catch (NumberFormatException e) {
                info.out.println("Invalid number format, ");
                choice = null;
                error = true;
            }
            
            if (choice != null) {
                RoomNode result = info.rooms.getRoom(choice);
                
                if (result == null) {
                    info.out.println("Room not found.");
                } else {
                    info.setCurrentRoom(result);
                    info.out.println(info.getCurrentRoom().getDescription());
                }
            }
        }
        
        if (error) {
            info.out.println("Usage, \"teleport <room number>\"");
        }
        
    }

}
