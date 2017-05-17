package console;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.RunnableCommand;
import commands.LookCommand;
import commands.QuitCommand;
import model.RoomNode;
import model.SerializationHelper;
import model.Player;

/**
 * The console deals with logging players and launching incoming commands from users.
 * 
 * @author Zachary Chandler
 */
public class Console {
    
    /** A constant for white space so we can easily check if a name contains white space. */
    private static final Pattern WHITE_SPACE = Pattern.compile("\\s");
    
    /** The maximum number of login attempts before the console gives up. */
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    
    /**
     * This program loads and runs a world for a single user.
     * @throws NoSuchElementException if the user closed the connection while running.
     */
    public static void start(User info) throws NoSuchElementException {
        
        if (!login(info)) {
            info.println("Failed to login, now exiting.");
        } else {
            play(info);
        }
        
    }
    
    /**
     * Let the user start playing. when this method exits the user is logged out of the game.
     * 
     * Precondition, login method must have returned true on this user.
     * 
     * @param info the user.
     * @throws NoSuchElementException if the user closes the connection while they are playing.
     */
    private static void play(User info) throws NoSuchElementException {
        String message = Helper.buildString(info.getUsername(), " popped into existence");
        info.chat.printlnToOthersInRoom(message);
        info.input.insertNextCommand(LookCommand.instance);
        
        NoSuchElementException ex = null;
        
        try {
            mainLoop(info);
        } catch (NoSuchElementException e) {
            // save it for now, we will need to clean up first.
            ex = e;
        }
        
        message = Helper.buildString(info.getUsername(), " popped out of existence");
        info.chat.printlnToOthersInRoom(message);
        cleanup(info);
        
        if (ex != null) {
            throw ex;
        }
    }
    
    /**
     * Cleans up the user, logs them out, saves their character, removes them from the logged in players, and removes
     * their entity from the tracked entities in their universe.
     */
    private static void cleanup(User info) {
        info.save();
        info.u.entities.removePlayer(info.getPlayer());
    }
    
    /**
     * @param info the user to log in.
     * @return if the user was logged in.
     */
    private static boolean login(User info) {
        int loginFails = 0;
        
        while (loginFails < MAX_LOGIN_ATTEMPTS && !attemptLogin(info)) {
            loginFails++;            
        }
        
        return loginFails < MAX_LOGIN_ATTEMPTS;
    }
    
    /**
     * Attempts to log the user in.
     * @param info the user we will be talking to.
     * @return if the user was able to log in.
     */
    private static boolean attemptLogin(User info) {
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
            info.u.entities.addPlayer(info.getPlayer());
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
        Player save = SerializationHelper.loadUser(username);
        save.setUser(info);
        info.setPlayer(save);
        
        RoomNode room = info.u.rooms.getRoom(save.getCurrentRoomID());
        if (room != null) {
            info.setCurrentRoom(room);
        } else {
            info.setCurrentRoom(info.u.rooms.getStartingRoom());
        }
        
        return true;
    }

    /**
     * Prompt the user if they want to create a new player, if so create a player with the given username.
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
            Player p = new Player(info, info.u.rooms.getStartingRoom(), username);
            info.setPlayer(p);
            info.save();
            result = true;
        }
        
        return result;
    }

    /**
     * Get a user name from the player, a valid user name is one that doesn't contain whitespace, and isn't
     * already logged in. If one could not be obtained on the first try, a null is returned.
     * 
     * @param info the user to talk to.
     * @return the user name of the user or null if it was invalid.
     */
    private static String getUserName(User info) {
        String name;
        
        info.print("Please Enter Your Username: ");
        
        name = info.input.nextLine();
        Matcher matches = WHITE_SPACE.matcher(name);
        
        if (matches.find()) {
            info.println("Error, user names cannot contain whitespace!");
            name = null;
        } else if (info.u.entities.isPlayerLoggedIn(name)) {
            info.println("This account is already logged in!");
            name = null;
        } 
        
        return name;
    }
    
    /**
     * Runs commands from the user until they logout.
     * 
     * @param info the user we will be using.
     */
    private static void mainLoop(User info) {
        boolean result = true;
        
        while (result) {
            RunnableCommand com = info.input.getNextCommand();
            com.run(info);
            
            result = com.getRunnable() instanceof QuitCommand;
        }
    }
    
}
