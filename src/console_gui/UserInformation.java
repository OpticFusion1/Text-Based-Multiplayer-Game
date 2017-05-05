package console_gui;

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
public class UserInformation {
    
    /** The current room of the user. */
    private RoomNode currentRoom;
    
    /** The user name of the user. */
    private String username;

    /** The rooms the user will traverse. */
    public final RoomManager rooms;

    /** A PrintStream to the user. */
    public final PrintStream out;
    
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
    public UserInformation(RoomManager rooms, PrintStream out, UserInputScanner input) {
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
}
