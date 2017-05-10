package commands;

import console_gui.UserInformation;
import model.SerializationHelper;
import model.UserSave;

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
        SerializationHelper.saveUser(new UserSave(info.getCurrentRoom().getRoomID(), info.getUsername()));
        info.out.println(EXIT_MESSAGE);
    }

    @Override
    public String getShortHelpDescription() {
        return "Quits the game.";
    }
}
