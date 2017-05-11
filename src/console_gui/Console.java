package console_gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.RunnableCommand;
import commands.MoveCommand;
import commands.QuitCommand;
import model.RoomManager;
import model.RoomNode;
import model.SerializationHelper;
import model.UserSave;

/**
 * The console program to run this program.
 *
 * @author Zachary Chandler
 */
public class Console {
    
    /** A constant for white space so we don't have to re-create it. */
    private static final Pattern WHITE_SPACE = Pattern.compile("\\s");
    
    /**
     * This program loads and runs a world for a single user.
     */
    public static void main(String[] args) {
        RoomManager rm =  SerializationHelper.loadRoomManager();
        
        if (rm == null) {
            System.err.println("Failed to Load System!");
            return;
        }
        
        rm.addAllConnectedRooms();
        
        
        UserInputScanner input = new UserInputScanner(System.in);
        UserInformation info = new UserInformation(rm, System.out, input);
        
        int fails = 0;
        
        while (!login(info) && fails < 3) fails++;
        
        if (fails >= 3) {
            info.out.println("Failed to login, now exiting.");
            return;
        }

        MoveCommand.arrive(info);
        
        while(mainLoop(info));
        
        SerializationHelper.saveRoomManager(rm);
    }
    
    /**
     * Attempts to log the user in.
     * @param info the user we will be talking to.
     * @return if the user was able to log in.
     */
    public static boolean login(UserInformation info) {
        boolean result;
        
        info.out.print("Please Enter Your Username: ");
        
        String username = info.input.nextLine();
        Matcher matches = WHITE_SPACE.matcher(username);
        
        if (matches.find()) {
            info.out.println("Error, user names cannot contain whitespace!");
            result = false;
        } else if (!SerializationHelper.userExists(username)) {
            info.out.print("Unable to find user, would you like to create a new save? (YES/NO): ");
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
    public static boolean mainLoop(UserInformation info) {
        boolean result = true;
        
        if (!info.input.hasQueuedCommand()) {
            info.out.print('>');
        }
        
        RunnableCommand com = info.input.getNextCommand();
        com.run(info);
        
        if (com.getRunnable() instanceof QuitCommand) {
            result = false;
        }
        
        return result;
    }
    
}
