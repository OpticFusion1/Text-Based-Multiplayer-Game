package commands;

import console_gui.UserInformation;
import model.Item;

public class CreateCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"CREATE"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 2) {
            info.out.print("Not enough arguments, ");
            
            error = true;
        } else {
            StringBuilder name = new StringBuilder();
            
            
            name.append(args[1]);
            for (int i = 2; i < args.length; i++) {
                name.append(" ");
                name.append(args[i]);
            }
            
            info.getCurrentRoom().addItem(new Item(name.toString()));
            
            info.out.print("Created: ");
            info.out.println(name.toString());
        }
        
        if (error) {
            info.out.print("Usage, \"create <name>\"");
        }
    }

}
