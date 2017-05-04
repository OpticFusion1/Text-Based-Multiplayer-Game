package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;
import model.RoomNode;

public class SouthCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"SOUTH", "S"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        RoomNode potentialRoom = info.getCurrentRoom().getSouth();
        
        if (potentialRoom == null) {
            System.out.println("You can't go that way.");
        } else {
            info.setCurrentRoom(potentialRoom);
            System.out.println(info.getCurrentRoom().getDescription());
        }
    }

}
