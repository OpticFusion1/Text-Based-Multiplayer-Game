package commands;

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
    }

    @Override
    public String getShortHelpDescription() {
        return "Quits the game.";
    }
}
