package console_gui;

import commands.Command;
import commands.CommandScanner;
import commands.QuitCommand;
import model.SerializationHelper;

public class Console {
    
    public static void main(String[] args) {
        CurrentInformation info = (CurrentInformation) SerializationHelper.loadObject(
                                                            SerializationHelper.QUICK_SAVE_LOCATION.toString());
        CommandScanner input = new CommandScanner(System.in, System.out);
        
        if (info == null) {
            System.out.println("Failed to load Save File!");
            return;
        }
        
        System.out.println(info.getCurrentRoom().getDescription());
        
        
        Command com = input.getNextCommand();
        
        while (!(com.getRunnable() instanceof QuitCommand)) {
            if (com != null) {
                com.run(info);
            }
            
            com = input.getNextCommand();
        }

        System.out.println("Thank you for experiencing Node Traversing Simulator 2017");
        
        SerializationHelper.saveObject(info, SerializationHelper.QUICK_SAVE_LOCATION.toString());
        
        System.out.println("Sucessfully Saved, now exiting...");
    }
}
