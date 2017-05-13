package commands;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import console_gui.Helper;
import console_gui.User;
import console_gui.UserInputScanner;
import model.Item;

/**
 * A command to run the use string of an item as this players input string. Multiple recursions of the use command
 * through this are limited to MAX_RECURSIONs.
 *
 * @author Zachary Chandler
 */
public class UseCommand extends Command {

    /** The max number of recursions we will allow the user to go through. */
    public static final int MAX_RECURSIONS = 20;
    
    /** Another different argument we can use as a flag to tell us when we are in recursion. */
    private static final String FAKE_USE = "FAKE_USE";
    
    /** The number of times a particular user has gone through recursion. */
    private static final Map<String, Integer> recursionCounters = new TreeMap<>();
    
    @Override
    public String[] getAliases() {
        return new String[] {"USE"};
    }

    @Override
    public void runCommand(User info, String[] args) {
        boolean error = false;
        
        Integer recursions = recursionCounters.get(info.getUsername());
        recursions = recursions == null ? 0 : recursions;
        
        
        if (args[0] == FAKE_USE) {
            recursionCounters.put(info.getUsername(), recursions + 1);
        } else {
            recursionCounters.put(info.getUsername(), 0);
        }
        
        if (recursions > MAX_RECURSIONS ) {
            info.print("Max use command recursions hit, ");
            error = true;
        } else if (args.length < 2) {
            info.print("Not enough arguments, ");
            error = true;
        } else {
            String itemName = Helper.mergeStrings(args, 1, args.length - 1);
            
            Item i = info.getCurrentRoom().findItem(itemName);
            
            if (i == null) {
                info.print("Item not found, ");
                error = true;
            } else {
                List<RunnableCommand> toAdd = UserInputScanner.getCommands(i.getOnUse());
                
                while (!toAdd.isEmpty()) {
                    RunnableCommand c = toAdd.remove(toAdd.size() - 1);
                    
                    if (c.getRunnable() instanceof UseCommand) {
                        String[] newArgs = c.getArgs();
                        newArgs[0] = FAKE_USE;
                        c = new RunnableCommand(newArgs, this);
                    }
                    
                    info.input.insertNextCommand(c);
                }
            }
        }
        
        if (error) {
            info.print(" see 'help use' for more details.\n");
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Use a given item.";
    }

    @Override
    public String getPreferredName() {
        return "use";
    }
}

