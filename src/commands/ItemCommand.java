package commands;

import console.Helper;
import console.User;
import model.Item;

/**
 * A command to deal with multiple commands regarding items. See the help page for more details on it's use.
 * 
 * @author Zachary Chandler
 */
public class ItemCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"ITEM"};
    }

    @Override
    public String getPreferredName() {
        return "item";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        boolean error = false;
        
        if (args.length < 3) {
            info.print("Not enough arguements, ");
            error = true;
        } else {
            String name = args[2];
            Item item = info.getCurrentRoom().findItem(name);
            String value = null;
            CommandType field;
            
            try {
                field = CommandType.valueOf(args[1].toUpperCase()); 
                
                if (field.minimumArgs > args.length) {
                    info.print("Not enough arguements, ");
                    error = true;
                } else {
                    value = Helper.mergeStrings(args, field.minimumArgs - 1, args.length - 1);
                }
            } catch (IllegalArgumentException e) {
                field = null;
                error = true;
                info.printf("Option not found %s, ", args[1]);
            }
            
            if (item == null && needsItem(field)) {
                info.printf("Item %s not found, ", name);
                error = true;
            }
            
            if (!error) {
                switch (field) {
                case ALIAS:
                    alias(info, item, value);
                    break;
                case DESCRIBE:
                    describe(info, item, value);
                    break;
                case RENAME:
                    rename(info, item, value);
                    break;
                case UNALIAS:
                    unalias(info, item, value);
                    break;
                case SETUSE:
                    setuse(info, item, value);
                    break;
                case CREATE:
                    create(info, value);
                    break;
                case DESTROY:
                    destroy(info, value);
                    break;
                default:
                    throw new IllegalStateException();
                }
            }
            
        }
        
        if (error) {
            info.print("see 'help item' for more information.\n");
        }
    }

    private void setuse(User info, Item item, String value) {
        item.setOnUse(value);
        info.printf("Set %s to run '%s' on use\n", item.getName(), value);
    }

    private void destroy(User info, String value) {
        Item item = info.getCurrentRoom().findItem(value);
        
        if (item == null) {
            info.printf("Could not find %s to destroy.\n", value);            
        } else {
            String message = Helper.buildString(info.getUsername(), " destroyed ", item.getName());
            
            info.getCurrentRoom().removeItem(item);
            info.chat.printlnToRoom(message);
        }
    }

    private void create(User info, String value) {
        info.getCurrentRoom().addItem(new Item(value));
        
        String message = Helper.buildString(info.getUsername(), " created ", value);
        info.chat.printlnToRoom(message);
    }

    private void alias(User info, Item item, String value) {
        item.addAlias(value);
        info.printf("Aliased %s as %s\n", item.getName(), value);
    }
    
    private void describe(User info, Item item, String value) {
        item.setDescription(value);
        info.printf("Described %s as %s\n", item.getName(), value);
    }
    
    private void rename(User info, Item item, String value) {
        item.setName(value);
        String message = Helper.buildString(info.getUsername(), " renamed ", item.getName(), " to ", value);
        info.chat.printlnToRoom(message);
    }
    
    private void unalias(User info, Item item, String value) {
        item.removeAlias(value);
        info.printf("Unaliased %s as %s\n", item.getName(), value); 
    }

    private boolean needsItem(CommandType field) {
        return field != CommandType.CREATE && field != CommandType.DESTROY;
    }
    
    private enum CommandType {
        CREATE(3), DESTROY(3), RENAME(4), DESCRIBE(4), ALIAS(4), UNALIAS(4), SETUSE(4);
        
        public int minimumArgs;
        
        CommandType(int minimumArgs) {
            this.minimumArgs = minimumArgs;
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Create/Destroy/Rename/Describe/Alias/Unalias/SetUse for items.";
    }
}
