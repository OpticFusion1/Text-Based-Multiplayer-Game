package commands;

import console_gui.UserInformation;
import model.RoomNode;

public class WestCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"WEST", "W"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        RoomNode potentialRoom = info.getCurrentRoom().getWest();
        
        if (potentialRoom == null) {
            info.out.println("You can't go that way.");
        } else {
            info.setCurrentRoom(potentialRoom);
            info.out.println(info.getCurrentRoom().getName());
        }
    }

}
