package commands;

import console.Helper;
import console.User;

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
        info.save();
        info.println(EXIT_MESSAGE);
        
        String message = Helper.buildString(info.getUsername(), " popped out of existence");
        User.chat.printlnToOthersInRoom(info, message);
    }

    @Override
    public String getShortHelpDescription() {
        return "Quits the game.";
    }
}
