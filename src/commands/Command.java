package commands;

import console_gui.CurrentInformation;
import console_gui.RunnableCommand;

public class Command {
    private final String[] args;
    private RunnableCommand com;
    
    public Command(String[] args, RunnableCommand com) {
        this.args = args;
        this.com = com;
    }
    
    public void run(CurrentInformation info) {
        com.runCommand(info, args);
    }
    
    public RunnableCommand getRunnable() {
        return com;
    }
}
