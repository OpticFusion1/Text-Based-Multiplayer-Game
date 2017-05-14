package commands;

import console.Helper;
import console.User;

/**
 * A command to handle player exiting.
 *
 * @author Zachary Chandler
 */
public class QuitCommand extends Command {

    private static final String EXIT_MESSAGE = "goodbye";

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
        info.println(EXIT_MESSAGE);
        String message = Helper.buildString(info.getUsername(), " popped out of existence");
        info.chat.printlnToOthersInRoom(message);
        
        info.logout();
    }

    @Override
    public String getShortHelpDescription() {
        return "Quits the game.";
    }
}
