package commands;

import console_gui.Helper;
import console_gui.UserInformation;

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
                    setRoom(info, fieldToSet, value);
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
    
    public void setRoom(UserInformation info, Field fieldToSet, String value) {
        boolean result = true;
        
        switch (fieldToSet) {
        case NAME:
            info.getCurrentRoom().setName(value);
            break;
        
        case DESCRIPTION:
            info.getCurrentRoom().setDescription(value);
            break;
            
        default:
            result = false;
        }
        
        if (result) {
            info.out.printf("Set %s of %s\n", fieldToSet, info.getCurrentRoom().getName());
        } else {
            info.out.printf("Could not set %s of %s\n", fieldToSet.toString(), info.getCurrentRoom().getName());            
        }
    }
    
    private enum Field {
        NAME, DESCRIPTION;
    }

    @Override
    public String getShortHelpDescription() {
        return "Sets a given value. Usage \"set <room> <name|description> <value>\"";
    }
}
