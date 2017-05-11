package commands;

import console_gui.UserInformation;
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
    public void runCommand(UserInformation info, String[] args) {
        saveUser(info);
        info.out.println(EXIT_MESSAGE);
    }
    
    /**
     * A method to save the current users information. The input and output streams need not exist for this method to 
     * save properly.
     * 
     * @param info the user to save.
     */
    public static void saveUser(UserInformation info) {
        SerializationHelper.saveUser(new UserSave(info.getCurrentRoom().getRoomID(), info.getUsername()));
    }

    @Override
    public String getShortHelpDescription() {
        return "Quits the game.";
    }
}
