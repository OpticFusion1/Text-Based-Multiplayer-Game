package model;

import java.io.Serializable;

/**
 * A room manager that holds a graph of nodes.
 * 
 * Responsibilities:
 *      keep track of the starting room.
 *      generate unique room IDs.
 * 
 * @author Zachary Chandler
 */
public class RoomManager implements Serializable {

    /** Generated SVUID */
    private static final long serialVersionUID = -8083744640096022029L;
    
    /** The initial room in the graph, any room not connected to this one isn't part of the graph. */
    private RoomNode startingRoom;
    
    /** The next Unique ID to use. */
    private int nextRoomID;
    
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
        this.nextRoomID = startingRoom.getRoomID() + 1;
    }
    
    /**
     * @return a unique room ID.
     */
    public int getUniqueRoomID() {
        return nextRoomID++;
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
