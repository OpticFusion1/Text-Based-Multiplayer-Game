package console;

import java.io.PrintStream;

import model.RoomManager;
import model.RoomNode;

/**
 * A class to keep track of a users information
 * 
 * Responsibilities:
 *      keep track of the current room.
 *      keep track of a room manager
 *      
 * @author Zachary Chandler
 */
public class User implements Comparable<User> {
    
    /** All of the players in the program. */
    private static final PlayerManager MANAGER = new PlayerManager();
    
    /** The chat of the program. */
    public static final Chat chat = new Chat(MANAGER);
    
    /** The current room of the user. */
    private RoomNode currentRoom;
    
    /** The user name of the user. */
    private String username;

    /** The rooms the user will traverse. */
    public final RoomManager rooms;

    /** A PrintStream to the user. */
    private final PrintStream out;
    
    /** A way to get input from the user. */
    public final UserInputScanner input;
    
    
    /**
     * Instantiate the current information on a given graph of rooms.
     * 
     * Preconditions:
     *      rooms must not be null.
     *      
     * @param rooms the room graph we will have information on.
     * @param out an output stream to the user.
     * @param input a way to get input from the user.
     */
    public User(RoomManager rooms, PrintStream out, UserInputScanner input) {
        if (rooms == null) {
            throw new NullPointerException("Cannot use null RoomManager in CurrentInformation instantiation!");
        }
        
        this.out = out;
        this.input = input;
        this.rooms = rooms;
        currentRoom = rooms.getStartingRoom();
    }
    
    /**
     * @return the currentRoom
     */
    public RoomNode getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * @param currentRoom the currentRoom to set
     */
    public void setCurrentRoom(RoomNode currentRoom) {
        if (currentRoom != null) {
            this.currentRoom = currentRoom;
            MANAGER.setRoomOfPlayer(this, currentRoom);
        }
    }

    /**
     * @return the user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the user name to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Display a line of text to the player.
     * @param s the line of text.
     */
    public void println(String s) {
        out.println(s);
    }
    
    /**
     * Displays a printf to the player.
     * @param str1 the string format.
     * @param args the arguments in the string.
     */
    public void printf(String str1, Object... args) {
        out.printf(str1, args);
    }
    
    /**
     * Display some text to the player without adding a newline.
     * @param s the text.
     */
    public void print(String s) {
        out.print(s);
    }
    
    /**
     * Prints a char to the users console.
     * @param c
     */
    public void print(char c) {
        out.print(c);
    }

    /**
     * Prints a newline to the users console.
     */
    public void println() {
        out.println();
    }

    @Override
    public int compareTo(User o) {
        return this.getUsername().compareTo(o.getUsername());
    }
}
