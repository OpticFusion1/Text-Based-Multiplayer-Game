package console;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.RunnableCommand;
import commands.LookCommand;
import commands.QuitCommand;
import model.RoomNode;
import model.SerializationHelper;
import model.PlayerInformation;

/**
 * The console...?
 *
 * @author Zachary Chandler
 */
public class Console {
    
    /** A constant for white space so we don't have to re-create it. */
    private static final Pattern WHITE_SPACE = Pattern.compile("\\s");
    
    /** The players that are currently logged in. */
    private static final Set<String> loggedInPlayers = new TreeSet<String>();
    
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

        String message = Helper.buildString(info.getUsername(), " popped into existence");
        User.chat.printlnToOthersInRoom(info, message);
        info.input.insertNextCommand(LookCommand.instance);
        
        while(mainLoop(info));
        loggedInPlayers.remove(info.getUsername().toUpperCase());
    }
    
    /**
     * Attempts to log the user in.
     * @param info the user we will be talking to.
     * @return if the user was able to log in.
     */
    public static boolean login(User info) {
        boolean result;

        String username = getUserName(info);
        
        if (username == null) {
            result = false;
        } else if (!SerializationHelper.userExists(username)) {
            result = createNewPlayer(info, username);
        } else {
            result = loadUser(info, username);
        }
        
        if (result) {
            loggedInPlayers.add(username.toUpperCase());            
        }
        
        return result;
    }
    
    /**
     * Loads a user from the given username to the user.
     * @param info the user to load the player into.
     * @param username the name of the player to load.
     * @return if the user was successfully loaded.
     */
    private static boolean loadUser(User info, String username) {
        PlayerInformation save = SerializationHelper.loadUser(username);
        info.setUsername(save.getUsername());
        
        RoomNode room = info.rooms.getRoom(save.getCurrentRoomID());
        if (room != null) {
            info.setCurrentRoom(room);
        } else {
            info.setCurrentRoom(info.getCurrentRoom());
        }
        
        return true;
    }

    /**
     * Prompt the user if they want to create a new player, if so create the player. 
     * 
     * @param info the user.
     * @param username the name of the player to create.
     * @return if the user created a new player.
     */
    private static boolean createNewPlayer(User info, String username) {
        boolean result = false;
        
        info.print("Unable to find user, would you like to create a new save? (YES/NO): ");
        String ans = info.input.nextLine().toUpperCase();
        
        if (ans.equals("YES") || ans.equals("Y")) {
            PlayerInformation p = new PlayerInformation(info.rooms.getStartingRoom(), username);
            info.setPlayerInformation(p);
            info.save();
            result = true;
        }
        
        return result;
    }

    /**
     * Get a valid user name from the player, a valid user name is one that doesn't contain whitespace, and isn't
     * already logged in.
     * @param info the user to talk to.
     * @return the user name of the user or null if it was invalid.
     */
    public static String getUserName(User info) {
        String name;
        
        info.print("Please Enter Your Username: ");
        
        name = info.input.nextLine();
        Matcher matches = WHITE_SPACE.matcher(name);
        
        if (matches.find()) {
            info.println("Error, user names cannot contain whitespace!");
            name = null;
        } else if (loggedInPlayers.contains(name.toUpperCase())) {
            info.println("This account is already logged in!");
            name = null;
        } 
        
        return name;
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
