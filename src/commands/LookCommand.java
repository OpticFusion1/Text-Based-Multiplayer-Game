package commands;

import java.util.List;

import console.User;
import model.Direction;
import model.Item;
import model.RoomNode;

/**
 * A command to gather basic information about the users surroundings. Including, room name, room description, objects,
 * and possible paths.
 * 
 * @author Zachary Chandler
 */
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
    public void runCommand(User info, String[] args) {
    	RoomNode r = info.getCurrentRoom();

    	info.println(r.getName());
        info.println(r.getDescription());
        
        
        List<Item> itemsInTheRoom = r.getItems();
        
        if (!itemsInTheRoom.isEmpty()) {
            info.println("You can see:");
            
            
            
            for (Item i : itemsInTheRoom) {
                info.print('\t');
                info.print(i.getName());
                info.print('\n');
            }            
        }
        
        info.print("Possible Directions: ");

        for (Direction d : Direction.values()) {
            if (r.getDirection(d) != null) {
                info.printf("%s ", d.lowercaseName);
            }
        }
        
        info.print('\n');
    }

    @Override
    public String getShortHelpDescription() {
        return "Gives a description of the surroundings.";
    }
}
