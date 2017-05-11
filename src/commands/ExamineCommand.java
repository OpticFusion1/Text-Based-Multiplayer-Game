package commands;

import console_gui.Helper;
import console_gui.UserInformation;
import model.Item;

/**
 * A command to examine an object. List various information for an object such as name and description.
 * 
 * @author Zachary Chandler
 */
public class ExamineCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"EXA", "EXAMINE"};
    }

    @Override
    public String getPreferredName() {
        return "examine";
    }
    
    @Override
    public void runCommand(UserInformation info, String[] args) {
        if (args.length == 1) {
            info.out.println("Usage \"examine <item-name>\"");
        } else if (args.length > 1) {
            String potentialName = Helper.mergeStrings(args, 1, args.length - 1);
            Item toDescribe = info.getCurrentRoom().findItem(potentialName);
            
            if (toDescribe == null) {
                info.out.printf("Could not find the item: %s\n", potentialName);
            } else {
                info.out.printf("%s\n     %s\n", toDescribe.getName(), toDescribe.getDescription());                
            }
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Examines a given item.";
    }
}
