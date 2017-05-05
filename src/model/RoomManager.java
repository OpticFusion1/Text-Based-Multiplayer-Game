package model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A room manager that holds a graph of nodes.
 * 
 * Responsibilities:
 *      keep track of the starting room.
 *      generate unique room IDs.
 *      look up rooms from their ID
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
     * Instantiates a RoomManager with the given starting room. And adds all connected rooms to be tracked.
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
        this.rooms = new TreeMap<>();
        
        this.addAllConnectedRooms();
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
    
    /**
     * Get the room for this ID. If this room hasn't been added, it will not be found.
     * @param roomID the ID to search for.
     * @return the RoomNode or null if it is not found.
     */
    public RoomNode getRoom(int roomID) {
        return this.rooms.get(roomID);
    }
    
    /**
     * Adds the given room to the tracked nodes. Undefined behavior for nodes not in the current graph. If a node is
     * already being tracked with that ID then subsequent call to getRoom will return the original room.
     * 
     * @param room the room to track.
     */
    public void trackRoom(RoomNode room) {
        if (!this.rooms.containsKey(room.getRoomID())) {
            this.rooms.put(room.getRoomID(), room);            
        }
    }
    
    /**
     * Searches and adds all rooms connected to the starting node that are not being tracked.
     */
    public void addAllConnectedRooms() {
        addAllConnectedRooms(new TreeSet<RoomNode>(), this.startingRoom);
    }
    
    /**
     * Removes all known rooms that are disconnected from the starting room.
     */
    public void purgeDisconnectedRooms() {
        this.rooms = new TreeMap<>();
        addAllConnectedRooms();
    }
    
    /**
     * Adds all of the connected rooms to be tracked.
     * @param seenRooms the rooms already seen and added.
     * @param room the current room.
     */
    private void addAllConnectedRooms(Set<RoomNode> seenRooms, RoomNode room) {
        if (room != null && !seenRooms.contains(room)) {
            seenRooms.add(room);
            this.trackRoom(room);
            
            addAllConnectedRooms(seenRooms, room.getUp());
            addAllConnectedRooms(seenRooms, room.getDown());
            addAllConnectedRooms(seenRooms, room.getNorth());
            addAllConnectedRooms(seenRooms, room.getWest());
            addAllConnectedRooms(seenRooms, room.getEast());
            addAllConnectedRooms(seenRooms, room.getSouth());
        }
    }
}
