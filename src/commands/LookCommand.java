package commands;

import console_gui.CurrentInformation;
import model.RoomNode;

public class LookCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"LOOK"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.out.println(info.getCurrentRoom().getDescription());
        
        RoomNode r = info.getCurrentRoom();
        
        info.out.print("Possible Directions: ");

        if (r.getUp() != null) {
            info.out.print("up ");
        }

        if (r.getDown() != null) {
            info.out.print("down ");
        }

        if (r.getNorth() != null) {
            info.out.print("north ");
        }

        if (r.getEast() != null) {
            info.out.print("east ");
        }

        if (r.getSouth() != null) {
            info.out.print("south ");
        }
        
        if (r.getWest() != null) {
            info.out.print("west ");
        }
        
        info.out.print('\n');
    }

}
