package console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.RunnableCommand;
import commands.MoveCommand;
import commands.QuitCommand;
import model.RoomNode;
import model.SerializationHelper;
import model.UserSave;

/**
 * The console...?
 *
 * @author Zachary Chandler
 */
public class Console {
    
    /** A constant for white space so we don't have to re-create it. */
    private static final Pattern WHITE_SPACE = Pattern.compile("\\s");
    
    /**
     * This program loads and runs a world for a single user.
     */
    public static void start(User info) {
        
        int loginFails = 0;
        while (loginFails < 3 && !login(info)) loginFails++;
        
        if (loginFails >= 3) {
            info.println("Failed to login, now exiting.");
            return;
        }

        MoveCommand.arrive(info);
        while(mainLoop(info));
    }
    
    /**
     * Attempts to log the user in.
     * @param info the user we will be talking to.
     * @return if the user was able to log in.
     */
    public static boolean login(User info) {
        boolean result;
        
        info.print("Please Enter Your Username: ");
        
        String username = info.input.nextLine();
        Matcher matches = WHITE_SPACE.matcher(username);
        
        if (matches.find()) {
            info.println("Error, user names cannot contain whitespace!");
            result = false;
        } else if (!SerializationHelper.userExists(username)) {
            info.print("Unable to find user, would you like to create a new save? (YES/NO): ");
            String ans = info.input.nextLine().toUpperCase();
            
            if (ans.equals("YES") || ans.equals("Y")) {
                info.setUsername(username);
                result = true;
            } else {
                result = false;
            }
        } else {
            UserSave save = SerializationHelper.loadUser(username);
            info.setUsername(save.username);
            
            RoomNode room = info.rooms.getRoom(save.currentRoomID);
            if (room != null) {
                info.setCurrentRoom(room);
            }
            
            result = true;
        }
        
        return result;
    }
    
    /**
     * Runs the main loop of the program, essentially running commands from the user.
     * 
     * @param info the user we will be using.
     * @return if the mainLoop should continue.
     */
    public static boolean mainLoop(User info) {
        boolean result = true;
        
        RunnableCommand com = info.input.getNextCommand();
        com.run(info);
        
        if (com.getRunnable() instanceof QuitCommand) {
            result = false;
        }
        
        return result;
    }
    
}
