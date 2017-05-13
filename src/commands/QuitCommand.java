package commands;

import console.Helper;
import console.User;
import model.SerializationHelper;
import model.UserSave;

/**
 * A command to handle player exiting.
 *
 * @author Zachary Chandler
 */
public class QuitCommand extends Command {

    private static final String EXIT_MESSAGE = "Thank you for experiencing Node Traversing Simulator 2017";

    @Override
    public String[] getAliases() {
        return new String[] {"QUIT", "Q"} ;
    }

    @Override
    public String getPreferredName() {
        return "quit";
    }
    
    @Override
    public void runCommand(User info, String[] args) {
        saveUser(info);
        info.println(EXIT_MESSAGE);
        
        String message = Helper.buildString(info.getUsername(), " popped out of existence.");
        User.chat.printlnToOthersInRoom(info, message);
    }
    
    /**
     * A method to save the current users information. The input and output streams need not exist for this method to 
     * save properly.
     * 
     * @param info the user to save.
     */
    public static void saveUser(User info) {
        SerializationHelper.saveUser(new UserSave(info.getCurrentRoom().getRoomID(), info.getUsername()));
    }

    @Override
    public String getShortHelpDescription() {
        return "Quits the game.";
    }
}
