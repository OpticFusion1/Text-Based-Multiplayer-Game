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
            CommandType field = getField(args[1].toUpperCase());
            
            if (field == null) {
                error = true;
                info.printf("Option not found %s, ", args[1]);
            } else if (field.minimumArgs > args.length) {
                info.print("Not enough arguements, ");
                error = true;
            } else {
                value = Helper.mergeStrings(args, field.minimumArgs - 1, args.length - 1);         
            }
            
            if (item == null && needsItem(field)) {
                info.printf("Item %s not found, ", name);
                error = true;
            }
            
            if (!error) {
                run(info, item, value, field);
            }
            
        }
        
        if (error) {
            info.print("see 'help item' for more information.\n");
        }
    }

    /**
     * @param info the user that is running the command.
     * @param item an item that the command will need.
     * @param value a value that the command will need.
     * @param field the command we will run.
     */
    private void run(User info, Item item, String value, CommandType field) {
        switch (field) {
        case ALIAS:     alias(info, item, value);       break;
        case DESCRIBE:  describe(info, item, value);    break;
        case RENAME:    rename(info, item, value);      break;
        case UNALIAS:   unalias(info, item, value);     break;
        case SETUSE:    setuse(info, item, value);      break;
        case CREATE:    create(info, value);            break;
        case DESTROY:   destroy(info, item);           break;
        
        default: throw new IllegalStateException();
        }
    }

    /**
     * @param fieldValue the value of the field we will edit.
     * @return the command type that will edit the field.
     */
    private CommandType getField(String fieldValue) {
        CommandType field;
        try {
            field = CommandType.valueOf(fieldValue); 
        } catch (IllegalArgumentException e) {
            field = null;
        }
        return field;
    }

    /**
     * Set the use field of the item.
     * @param info the user running the command.
     * @param item the item that we will set the user field of.
     * @param value the value of the use to set.
     */
    private void setuse(User info, Item item, String value) {
        item.setOnUse(value);
        info.printf("Set %s to run '%s' on use\n", item.getName(), value);
    }

    /**
     * Destroy an item.
     * @param info the user destroying the item.
     * @param item the name of the item to destroy.
     */
    private void destroy(User info, Item item) {
        info.getCurrentRoom().removeItem(item);
        
        String message = Helper.buildString(info.getUsername(), " destroyed ", item.getName());
        info.chat.printlnToRoom(message);
    }
    
    /**
     * Create an item.
     * @param info the user creating the item.
     * @param value the name of the item to be created.
     */
    private void create(User info, String value) {
        info.getCurrentRoom().addItem(new Item(value));
        
        String message = Helper.buildString(info.getUsername(), " created ", value);
        info.chat.printlnToRoom(message);
    }

    /** 
     * Alias the item with the given value.
     * @param info the user aliasing the item.
     * @param item the item to be aliased.
     * @param value the name of the alias.
     */
    private void alias(User info, Item item, String value) {
        item.addAlias(value);
        info.printf("Aliased %s as %s\n", item.getName(), value);
    }
    
    /**
     * Set the description of the item.
     * @param info the user describing the item.
     * @param item the item to be described.
     * @param value the description to set.
     */
    private void describe(User info, Item item, String value) {
        item.setDescription(value);
        info.printf("Described %s as %s\n", item.getName(), value);
    }
    
    /**
     * Set the name of the item.
     * @param info the user setting the name of the item.
     * @param item the item being renamed.
     * @param value the new name of the item.
     */
    private void rename(User info, Item item, String value) {
        item.setName(value);
        String message = Helper.buildString(info.getUsername(), " renamed ", item.getName(), " to ", value);
        info.chat.printlnToRoom(message);
    }
    
    /**
     * Remove an alias for an item.
     * @param info the user taking off the alias.
     * @param item the item being unaliased.
     * @param value the alias to remove.
     */
    private void unalias(User info, Item item, String value) {
        item.removeAlias(value);
        info.printf("Unaliased %s as %s\n", item.getName(), value); 
    }

    /**
     * Check if the given command type needs an item in the arguments.
     * @param field the command type to check.
     * @return if it requires an actual item.
     */
    private boolean needsItem(CommandType field) {
        return field != CommandType.CREATE;
    }
    
    /**
     * An enum of the sub commands this command supports.
     *
     * @author Zachary Chandler
     */
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
