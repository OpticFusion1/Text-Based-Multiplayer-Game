package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    
    /** Items in this RoomNode. */
    private List<Item> items;
    
    /** Adjacent RoomNodes. */
    private RoomNode up, down, north, south, east, west;
    
    /** A description of the room. */
    private String name;
    
    /** A unique ID associated with the room. */
    private int roomID;

    /** The description of the room. */
	private String description; 
    
    /**
     * Instantiates a RoomNode with the given description.
     * 
     * Preconditions:
     *      description may not be null.
     * 
     * @param description.
     * @param roomID a unique ID associated with the room.
     */
    public RoomNode(int roomID, String name, String description) {
        if (name == null) {
            throw new NullPointerException("Cannot use null description in RoomNode constructor!");
        }
        
        this.name = name;
        this.roomID = roomID;
        this.description = description;
        this.items = new LinkedList<Item>();
    }
    
    public RoomNode(int uniqueRoomID, String name) {
        this(uniqueRoomID, name, "A very bland room.");
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
    public String getName() {
        return name;
    }

    /**
     * Preconditions:
     *      description may not be null.
     * 
     * @param description the description to set
     */
    public void setName(String description) {
        if (description == null) {
            throw new NullPointerException("Cannot use null description in RoomNode constructor!");
        }
        this.name = description;
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

    /**
     * @return the items in this room.
     */
    public List<Item> getItems() {
        if (items == null) {
            items = new LinkedList<Item>();
        }
        
        return new ArrayList<Item>(items);
    }

    /**
     * @param item the items to add to the room.
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * @param item the items to remove.
     * @return if the item was removed.
     */
    public boolean removeItem(Item item) {
        return this.items.remove(item);
    }
    
    /**
     * @param itemName the name of the item to look for.
     * @return the item or null, if it wasn't found.
     */
    public Item findItem(String itemName) {
        Item result = null;
        
        for (Item i : items) {
            if (i.match(itemName)) {
                result = i;
                break;
            }
        }
        
        return result;
    }

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the given direction with with the given room.
	 * 
	 * If the theDirection is null, no direction is set.
	 * 
	 * @param theDirection the direction to set.
	 * @param room the room to put in the given direction.
	 */
	public void setDirection(Direction theDirection, RoomNode room) {
	    switch (theDirection) {
        case DOWN:  this.setDown(room);     break;
        case UP:    this.setUp(room);       break;
        case NORTH: this.setNorth(room);    break;
        case SOUTH: this.setSouth(room);    break;
        case EAST:  this.setEast(room);     break;
        case WEST:  this.setWest(room);     break;
	    }
	}
	
	/**
	 * Get the RoomNode in the given direction.
	 * @param theDirection the direction to get.
	 * @return the room in theDirection given.
	 */
	public RoomNode getDirection(Direction theDirection) {
	    RoomNode result = null;
	    
	    switch (theDirection) {
        case DOWN:  result = this.getDown();    break;
        case UP:    result = this.getUp();      break;
        case NORTH: result = this.getNorth();   break;
        case SOUTH: result = this.getSouth();   break;
        case EAST:  result = this.getEast();    break;
        case WEST:  result = this.getWest();    break;
        }
	    
	    return result;
	}
	
}
