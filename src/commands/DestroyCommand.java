package commands;

import java.util.List;

import console_gui.UserInformation;
import model.Item;

public class DestroyCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"DESTROY"};
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 2) {
            info.out.print("Not enough arguments, ");
            error = true;
        } else {
            StringBuilder potentialName = new StringBuilder();
            
            potentialName.append(args[1]);
            for (int i = 2; i < args.length; i++) {
                potentialName.append(" ");
                potentialName.append(args[i]);
            }
            
            //info.getCurrentRoom().addItem(new Item(potentialName.toString()));
            
            List<Item> items = info.getCurrentRoom().getItems();
            String toDestroy = potentialName.toString();
            boolean result = false;
            for (Item i : items) {
                if (i.getName().equals(toDestroy)) {
                    result = info.getCurrentRoom().removeItem(i);
                    break;
                }
            }
            
            if (result) {
                info.out.print("Destroyed: ");                
            } else {
                info.out.println("Could not find item: ");
            }
            
            info.out.println(potentialName.toString());
        }
        
        if (error) {
            info.out.print("Usage, \"destroy <name>\"");
        }
    }

}
