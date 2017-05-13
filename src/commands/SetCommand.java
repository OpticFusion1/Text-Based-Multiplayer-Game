package commands;

import console.Helper;
import console.User;
import model.RoomNode;

/**
 * A command to set values for the current room. Such as the name and description.
 *
 * @author Zachary Chandler
 */
public class SetCommand extends Command {
    
    @Override
    public String[] getAliases() {
        return new String[] {"SET"};
    }

    @Override
    public String getPreferredName() {
        return "set";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        boolean error = false;
        
        if (args.length < 4) {
            info.print("Not enough arguements, ");
            error = true;
        } else {
            String value = Helper.mergeStrings(args, 3, args.length - 1);
            Field fieldToSet;
            
            try {
                fieldToSet = Field.valueOf(args[2].toUpperCase());                
            } catch (IllegalArgumentException e) {
                fieldToSet = null;
                info.print("Invalid argument, ");
                error = true;
            }
            
            if (!error) {                
                switch (args[1].toUpperCase()) {
                case "ROOM":
                    boolean result = setRoom(info.getCurrentRoom(), fieldToSet, value);

                    if (result) {
                        String message = Helper.buildString(info.getUsername(), " set ", fieldToSet.toString(),
                                " of the room to ", value);

                        User.chat.printlnToRoom(info, message);
                    } else {
                        info.printf("Could not set %s of %s\n", fieldToSet.toString(), info.getCurrentRoom().getName());            
                    }
                    break;
                    
                default:
                    info.printf("Cannot set %s\n", args[1]);
                }
            }
        }
        
        if (error) {
            info.print("Usage \"set <room> <name|description> <value>\"\n");
        }
    }
    
    /**
     * A method to set the given field of the given room for the given value.
     * 
     * @param room the room to set values of.
     * @param fieldToSet the field to set.
     * @param value the value to set the field.
     * @return if the field was set.
     */
    public boolean setRoom(RoomNode room, Field fieldToSet, String value) {
        boolean result = true;
        
        switch (fieldToSet) {
        case NAME:
            room.setName(value);
            break;
        
        case DESCRIPTION:
            room.setDescription(value);
            break;
            
        default:
            result = false;
        }
        
        
        return result;
    }
    
    /**
     * An enumerator for fields we could set.
     * @author Zachary Chandler
     */
    private enum Field {
        NAME, DESCRIPTION;
    }

    @Override
    public String getShortHelpDescription() {
        return "Sets a given value in the room.";
    }
}
