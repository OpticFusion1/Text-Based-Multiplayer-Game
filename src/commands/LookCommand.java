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
        System.out.println(info.getCurrentRoom().getDescription());
        
        RoomNode r = info.getCurrentRoom();
        
        System.out.print("Possible Directions: ");

        if (r.getUp() != null) {
            System.out.print("up ");
        }

        if (r.getDown() != null) {
            System.out.print("down ");
        }

        if (r.getNorth() != null) {
            System.out.print("north ");
        }

        if (r.getEast() != null) {
            System.out.print("east ");
        }

        if (r.getSouth() != null) {
            System.out.print("south ");
        }
        
        if (r.getWest() != null) {
            System.out.print("west ");
        }
        
        System.out.print('\n');
    }

}
