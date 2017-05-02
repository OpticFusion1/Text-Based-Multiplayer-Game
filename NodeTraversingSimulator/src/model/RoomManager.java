package model;

import java.io.Serializable;

/**
 * A room manager that holds a graph of nodes.
 * 
 * Responsibilities:
 *      keep track of the starting room.
 * 
 * 
 * @author Zachary Chandler
 */
public class RoomManager implements Serializable {

    /** Generated SVUID */
    private static final long serialVersionUID = -8083744640096022029L;
    
    private RoomNode startingRoom;
    
    /**
     * Instantiates a RoomManager with the given starting room.
     * 
     * Preconditions:
     *      startingRoom may not be null.
     * 
     * @param startingRoom
     */
    public RoomManager(RoomNode startingRoom) {
        if (startingRoom == null) {
            throw new NullPointerException("Cannot have a null startingRoom in a RoomManager.");
        }
        
        this.startingRoom = startingRoom;
    }
    
    /**
     * Postconditions:
     *      startingRoom will never be null.
     * 
     * @return the startingRoom
     */
    public RoomNode getStartingRoom() {
        return startingRoom;
    }

    /**
     * Preconditions:
     *      startingRoom may not be null.
     *      
     * @param startingRoom the startingRoom to set
     */
    public void setStartingRoom(RoomNode startingRoom) {
        if (startingRoom == null) {
            throw new NullPointerException("Cannot have a null startingRoom in a RoomManager.");
        }
        
        this.startingRoom = startingRoom;
    }
}
