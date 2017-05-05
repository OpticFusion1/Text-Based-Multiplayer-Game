package commands;

import java.util.List;

import console_gui.UserInformation;
import model.Item;
import model.RoomNode;

public class LookCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"LOOK"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
    	RoomNode r = info.getCurrentRoom();

    	info.out.println(r.getName());
        info.out.println(r.getDescription());
        
        
        List<Item> itemsInTheRoom = r.getItems();
        
        if (!itemsInTheRoom.isEmpty()) {
            info.out.println("You can see:");
            
            for (Item i : itemsInTheRoom) {
                info.out.print('\t');
                info.out.print(i.getName());
                info.out.print('\n');
            }            
        }
        
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
