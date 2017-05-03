package model;

import java.io.Serializable;

/**
 * A room object that represents a room in a graph of interconnected nodes.
 * 
 * Responsibilities:
 *      keep track of the description of the room.
 *      keep track of adjacent rooms.
 *      keep track of roomID.
 * 
 * @author Zachary Chandler
 */
public class RoomNode implements Serializable, Comparable<RoomNode> {
    
    /** Generated SVUID */
    private static final long serialVersionUID = 4749342966413694748L;
    
    /** Adjacent RoomNodes. */
    private RoomNode up, down, north, south, east, west;
    
    /** A description of the room. */
    private String description;
    
    /** A unique ID associated with the room. */
    private int roomID; 
    
    /**
     * Instantiates a RoomNode with the given description.
     * 
     * Preconditions:
     *      description may not be null.
     * 
     * @param description.
     * @param roomID a unique ID associated with the room.
     */
    public RoomNode(int roomID, String description) {
        if (description == null) {
            throw new NullPointerException("Cannot use null description in RoomNode constructor!");
        }
        
        this.description = description;
        this.roomID = roomID;
    }
    
    /**
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Postconditions:
     *      the description will never be null.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Preconditions:
     *      description may not be null.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        if (description == null) {
            throw new NullPointerException("Cannot use null description in RoomNode constructor!");
        }
        this.description = description;
    }

    /**
     * @return the up or null if their isn't one.
     */
    public RoomNode getUp() {
        return up;
    }

    /**
     * Sets an adjacent RoomNode
     * 
     * @param up the up to set
     */
    public void setUp(RoomNode up) {
        this.up = up;
    }

    /**
     * @return the down or null if their isn't one.
     */
    public RoomNode getDown() {
        return down;
    }

    /**
     * Sets an adjacent RoomNode
     * 
     * @param down the down to set
     */
    public void setDown(RoomNode down) {
        this.down = down;
    }

    /**
     * @return the north or null if their isn't one.
     */
    public RoomNode getNorth() {
        return north;
    }

    /**
     * Sets an adjacent RoomNode
     * 
     * @param north the north to set
     */
    public void setNorth(RoomNode north) {
        this.north = north;
    }

    /**
     * @return the south or null if their isn't one.
     */
    public RoomNode getSouth() {
        return south;
    }

    /**
     * Sets an adjacent RoomNode
     * 
     * @param south the south to set
     */
    public void setSouth(RoomNode south) {
        this.south = south;
    }

    /**
     * @return the east or null if their isn't one.
     */
    public RoomNode getEast() {
        return east;
    }

    /**
     * Sets an adjacent RoomNode
     * 
     * @param east the east to set
     */
    public void setEast(RoomNode east) {
        this.east = east;
    }

    /**
     * @return the west or null if their isn't one.
     */
    public RoomNode getWest() {
        return west;
    }

    /**
     * Sets an adjacent RoomNode
     * 
     * @param west the west to set
     */
    public void setWest(RoomNode west) {
        this.west = west;
    }

    @Override
    public int compareTo(RoomNode other) {
        return this.roomID - other.roomID;
    }
}
