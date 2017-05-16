package model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

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
     * @param startingRoom
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

    public RoomNode newRoom(String name, String description) {
        RoomNode result = new RoomNode(nextRoomID++, name, description);
        trackRoom(result);
        return result;
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
    public void setStartingRoom(int room) {
        RoomNode newStartingRoom  = rooms.get(room);
        
        if (newStartingRoom == null) {
            throw new IllegalArgumentException("Could not find room: " + room);
        }
        
        this.startingRoom = newStartingRoom;
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
    private void trackRoom(RoomNode room) {
        if (!this.rooms.containsKey(room.getRoomID())) {
            this.rooms.put(room.getRoomID(), room);            
        }
    }
    
    /*
    /**
     * Searches and adds all rooms connected to the starting node that are not being tracked.
     *
    public void addAllConnectedRooms() {
        addAllConnectedRooms(new TreeSet<RoomNode>(), this.startingRoom);
    }
    
    /**
     * Adds all of the connected rooms to be tracked.
     * @param seenRooms the rooms already seen and added.
     * @param room the current room.
     *
    private void addAllConnectedRooms(Set<RoomNode> seenRooms, RoomNode room) {
        if (room != null && !seenRooms.contains(room)) {
            seenRooms.add(room);
            this.trackRoom(room);
            
            addAllConnectedRooms(seenRooms, room.getDirection(Direction.UP));
            addAllConnectedRooms(seenRooms, room.getDirection(Direction.DOWN));
            addAllConnectedRooms(seenRooms, room.getDirection(Direction.NORTH));
            addAllConnectedRooms(seenRooms, room.getDirection(Direction.EAST));
            addAllConnectedRooms(seenRooms, room.getDirection(Direction.SOUTH));
            addAllConnectedRooms(seenRooms, room.getDirection(Direction.WEST));
        }
    }
    */
}
