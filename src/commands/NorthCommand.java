package commands;

import console_gui.UserInformation;
import model.RoomNode;

public class NorthCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"NORTH", "N"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        RoomNode potentialRoom = info.getCurrentRoom().getNorth();
        
        if (potentialRoom == null) {
            info.out.println("You can't go that way.");
        } else {
            info.setCurrentRoom(potentialRoom);
            info.out.println(info.getCurrentRoom().getDescription());
        }
    }

}
