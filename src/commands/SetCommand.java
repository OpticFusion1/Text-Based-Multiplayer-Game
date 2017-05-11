package commands;

import console_gui.Helper;
import console_gui.UserInformation;
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
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 4) {
            info.out.print("Not enough arguements, ");
            error = true;
        } else {
            String value = Helper.mergeStrings(args, 3, args.length - 1);
            Field fieldToSet;
            
            try {
                fieldToSet = Field.valueOf(args[2].toUpperCase());                
            } catch (IllegalArgumentException e) {
                fieldToSet = null;
                info.out.print("Invalid argument, ");
                error = true;
            }
            
            if (!error) {                
                switch (args[1].toUpperCase()) {
                case "ROOM":
                    boolean result = setRoom(info.getCurrentRoom(), fieldToSet, value);

                    if (result) {
                        info.out.printf("Set %s of %s\n", fieldToSet, info.getCurrentRoom().getName());
                    } else {
                        info.out.printf("Could not set %s of %s\n", fieldToSet.toString(), info.getCurrentRoom().getName());            
                    }
                    break;
                    
                default:
                    info.out.printf("Cannot set %s\n", args[1]);
                }
            }
        }
        
        if (error) {
            info.out.print("Usage \"set <room> <name|description> <value>\"\n");
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
