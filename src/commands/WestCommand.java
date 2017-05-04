package commands;

import console_gui.CurrentInformation;
import model.RoomNode;

public class WestCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"WEST", "W"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        RoomNode potentialRoom = info.getCurrentRoom().getWest();
        
        if (potentialRoom == null) {
            info.out.println("You can't go that way.");
        } else {
            info.setCurrentRoom(potentialRoom);
            info.out.println(info.getCurrentRoom().getDescription());
        }
    }

}
