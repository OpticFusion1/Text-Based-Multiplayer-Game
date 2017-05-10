package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Item;

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
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 3) {
            info.out.print("Not enough arguements, ");
            error = true;
        } else {
            String name = args[2];
            Item item = info.getCurrentRoom().findItem(name);
            String value = null;
            CommandType field;
            
            try {
                field = CommandType.valueOf(args[1].toUpperCase()); 
                
                if (field.minimumArgs > args.length) {
                    info.out.print("Not enough arguements, ");
                    error = true;
                } else {
                    value = Helper.mergeStrings(args, field.minimumArgs - 1, args.length - 1);
                }
            } catch (IllegalArgumentException e) {
                field = null;
                error = true;
                info.out.printf("Option not found %s, ", args[1]);
            }
            
            if (item == null && needsItem(field)) {
                info.out.printf("Item %s not found, ", name);
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
            info.out.print("see 'help item' for more information.\n");
        }
    }

    private void destroy(UserInformation info, String value) {
        Item item = info.getCurrentRoom().findItem(value);
        
        if (item == null) {
            info.out.printf("Could not find %s to destroy.\n", value);            
        } else {
            info.getCurrentRoom().removeItem(item);
            info.out.printf("Destroyed: %s\n", value);
        }
    }

    private void create(UserInformation info, String value) {
        info.getCurrentRoom().addItem(new Item(value));
        info.out.printf("Created: %s\n", value);
    }

    private void alias(UserInformation info, Item item, String value) {
        item.addAlias(value);
        info.out.printf("Aliased %s as %s\n", item.getName(), value);
    }
    
    private void describe(UserInformation info, Item item, String value) {
        item.setDescription(value);
        info.out.printf("Described %s as %s\n", item.getName(), value);
    }
    
    private void rename(UserInformation info, Item item, String value) {
        item.setName(value);
        info.out.printf("Renamed %s to %s\n", item.getName(), value);
    }
    
    private void unalias(UserInformation info, Item item, String value) {
        item.removeAlias(value);
        info.out.printf("Unaliased %s as %s\n", item.getName(), value); 
    }

    private boolean needsItem(CommandType field) {
        return field != CommandType.CREATE && field != CommandType.DESTROY;
    }

    private enum CommandType {
        CREATE(3), DESTROY(3), RENAME(4), DESCRIBE(4), ALIAS(4), UNALIAS(4);
        
        public int minimumArgs;
        
        CommandType(int minimumArgs) {
            this.minimumArgs = minimumArgs;
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Create/Destroy/Rename/Describe/Alias/Unalias items.";
    }
}
