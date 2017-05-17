package console;

import java.io.PrintStream;
import java.util.List;

import model.Player;
import model.RoomNode;
import model.SerializationHelper;
import model.Universe;

/**
 * A class to keep track of a users information
 * 
 * Responsibilities:
 *      keep track of the current room.
 *      keep track of a room manager.
 *      keep track of a player manager.
 *      
 * @author Zachary Chandler
 */
public class User implements Comparable<User> {
    
    /** The chat of the program. */
    public final Chat chat = new Chat(this);

    /** The rooms the user will traverse. */
    public final Universe u;

    /** A PrintStream to the user. */
    private final PrintStream out;
    
    /** A way to get input from the user. */
    public final UserInputScanner input;

    /** The information of the player. */
    private Player ply;
    
    /**
     * Instantiate a user with the given I/O streams and the given universe.
     * 
     * Preconditions:
     *      universe must not be null.
     *      
     * @param u the universe this user resides in.
     * @param out an output stream to the user.
     * @param input an input stream from the user.
     */
    public User(Universe u, PrintStream out, UserInputScanner input) {
        if (u == null) {
            throw new NullPointerException("Cannot use null Universe in User instantiation!");
        }
        
        this.out = out;
        this.input = input;
        this.u = u;
        this.ply = new Player(this, null, null);
    }

    /**
     * Save the users information. Including player details.
     */
    public void save() {
        SerializationHelper.saveUser(ply);
    }

    /**
     * @param p the player to set for this user.
     */
    public void setPlayer(Player p) {
        this.ply = p;
        p.setUser(this);
    }
    
    /**
     * @return the player of this user.
     */
    public Player getPlayer() {
        return ply;
    }
    
    /**
     * @return the currentRoom
     */
    public RoomNode getCurrentRoom() {
        return ply.getRoom();
    }
    
    /**
     * Sets the current room of the user. If currentRoom is null, the user doesn't change rooms.
     * 
     * @param currentRoom the current room to set.
     */
    public void setCurrentRoom(RoomNode currentRoom) {
        if (currentRoom != null) {
            // characters handle changing rooms, so just call that method.
            ply.setRoom(currentRoom);
        }
    }
    
    /**
     * @return all of the players in this users room.
     */
    public List<User> getPlayersInRoom() {
        return ply.getRoom().getUsers();
    }

    /**
     * @return the user name of the user or null, if no name is set.
     */
    public String getUsername() {
        return ply.getName();
    }

    /**
     * @param username the user name to set
     */
    public void setUsername(String username) {
        this.ply.setName(username);
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
