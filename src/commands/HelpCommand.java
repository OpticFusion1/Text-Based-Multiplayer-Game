package commands;

import console_gui.UserInformation;
import console_gui.UserInputScanner;

public class HelpCommand extends Command {

    @Override
    public String[] getAliases() {
        return new String[] {"HELP", "?"};
    }

    @Override
    public String getPreferredName() {
        return "help";
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        info.out.println("\nPossible Commands");
        for (Command c : UserInputScanner.COMMANDS) {
            info.out.printf("%-15s - %s\n", c.getPreferredName(), c.getShortHelpDescription());
        }
    }

    @Override
    public String getShortHelpDescription() {
        return "Lists all help descriptions for all available commands.";
    }

}
