package commands;

import console_gui.UserInformation;

/**
 * A command that can be run.
 * 
 * @author Zachary Chandler.
 */
public class RunnableCommand {
    /** The arguments to be run. */
    private final String[] args;
    
    /** The runnable statements associated with the command. */
    private Command com;
    
    /**
     * Create a new command for the given arguments and command.
     * 
     * @param args the arguments from the user.
     * @param com the command that will be run.
     */
    public RunnableCommand(String[] args, Command com) {
        this.args = args;
        this.com = com;
    }
    
    /**
     * Runs the command.
     * @param info the user that the command will be run on.
     */
    public void run(UserInformation info) {
        com.runCommand(info, args);
    }
    
    /**
     * @return the RunnableCommand of this Command.
     */
    public Command getRunnable() {
        return com;
    }
}
