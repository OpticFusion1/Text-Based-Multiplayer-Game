package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Item;

public class SetCommand extends RunnableCommand {

    
    @Override
    public String[] getAliases() {
        return new String[] {"SET"};
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
                    Item i = info.getCurrentRoom().findItem(args[1]);
                    
                    if (i == null) {
                        info.out.printf("Could not find item %s, ", args[1]);
                        error = true;
                    } else {
                        setItem(info, i, fieldToSet, value);
                    }
                }
            }
        }
        
        if (error) {
            info.out.print("Usage \"set <room|item-name> <name|description> <value>\"\n");
        }
    }
    
    private void setItem(UserInformation info, Item item, Field fieldToSet, String value) {
        boolean result = true;
        
        switch (fieldToSet) {
        case NAME:
            item.setName(value);
            break;
        
        case DESCRIPTION:
            item.setDescription(value);
            break;
            
        default:
            result = false;
        }
        
        if (result) {
            info.out.printf("Set %s of %s\n", fieldToSet.toString(), item.getName());
        } else {
            info.out.printf("Could not set %s of %s\n", fieldToSet.toString(), item.getName());
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

}


