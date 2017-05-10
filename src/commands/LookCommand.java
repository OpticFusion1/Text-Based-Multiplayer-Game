package commands;

import java.util.List;

import console_gui.UserInformation;
import model.Direction;
import model.Item;
import model.RoomNode;

public class LookCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"LOOK", "L"} ;
    }

    @Override
    public String getPreferredName() {
        return "look";
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

        for (Direction d : Direction.values()) {
            if (r.getDirection(d) != null) {
                info.out.printf("%s ", d.toString());
            }
        }
        
        info.out.print('\n');
    }

    @Override
    public String getShortHelpDescription() {
        return "Gives a description of the surroundings.";
    }
}
