package model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * A room manager that generates rooms, and can look them up after generating. A RoomManager:
 *      keeps track of the starting room.
 *      generates new rooms to use.
 *      looks up rooms from their ID.
 * 
 * @author Zachary Chandler
 */
public final class RoomManager implements Serializable {

    /** Generated SVUID */
    private static final long serialVersionUID = -8083744640096022029L;
    
    /** A map of roomID's to rooms for fast lookup. */
    private Map<Integer, RoomNode> rooms;
    
    /** The initial room in the graph, any room not connected to this one isn't part of the graph. */
    private RoomNode startingRoom;
    
    /** The next Unique ID to use. */
    private int nextRoomID;

    /**
     * Instantiates a RoomManager with a single starting room.
     */
    public RoomManager() {
        this.nextRoomID = 0;
        this.rooms = new TreeMap<>();
        this.startingRoom = newRoom();
    }
    
    /**
     * @return a new room that is tracked by this room manager
     */
    public RoomNode newRoom() {
        return newRoom("simple room", "a very bland room");
    }

    /**
     * Create a new room for the given name and description.
     * @param name the name of the new room.
     * @param description the description of the new room.
     * @return the new room.
     */
    public RoomNode newRoom(String name, String description) {
        RoomNode result = new RoomNode(nextRoomID++, name, description);
        trackRoom(result);
        return result;
    }
    
    /**
     * Gets the starting room of the room manager. The start room will never be null.
     * @return the startingRoom
     */
    public RoomNode getStartingRoom() {
        return startingRoom;
    }

    /**
     * Set the starting room of the RoomManager. If no room with the roomID is found, an IllegalArgumentException is
     * thrown.
     * 
     * @param roomID the ID of the room to set.
     */
    public void setStartingRoom(int roomID) {
        RoomNode newStartingRoom  = rooms.get(roomID);
        
        if (newStartingRoom == null) {
            throw new IllegalArgumentException("Could not find room: " + roomID);
        }
        
        this.startingRoom = newStartingRoom;
    }
    
    /**
     * Get the room for the given ID. If no room is found, a null room is returned.
     * 
     * @param roomID the ID to search for.
     * @return the RoomNode or null if it is not found.
     */
    public RoomNode getRoom(int roomID) {
        return this.rooms.get(roomID);
    }
    
    /**
     * Adds the given room to the tracked nodes.
     * 
     * @param room the room to track.
     */
    private void trackRoom(RoomNode room) {
        if (!this.rooms.containsKey(room.getRoomID())) {
            this.rooms.put(room.getRoomID(), room);            
        }
    }
}
