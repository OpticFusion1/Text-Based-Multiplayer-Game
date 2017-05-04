package console_gui;

import java.io.PrintStream;
import java.io.Serializable;

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
public class CurrentInformation implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = -3765710897647543132L;

    /** The current room of the user. */
    private RoomNode currentRoom;
    
    /** The rooms the user will traverse. */
    public final RoomManager rooms;

    transient public PrintStream out;
    
    /**
     * Instantiate the current information on a given graph of rooms.
     * 
     * Preconditions:
     *      rooms must not be null.
     *      
     * @param rooms the room graph we will have information on.
     */
    public CurrentInformation(RoomManager rooms, PrintStream out) {
        if (rooms == null) {
            throw new NullPointerException("Cannot use null RoomManager in CurrentInformation instantiation!");
        }
        
        this.out = out;
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
}
