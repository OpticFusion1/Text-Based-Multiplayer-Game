package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
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
    private final List<Item> items;
    
    /** Adjacent room nodes. */
    private final EnumMap<Direction, RoomNode> directions;
    
    /** A unique ID associated with the room. */
    private final int roomID;
    
    /** A description of the room. */
    private String name;

    /** The description of the room. */
	private String description; 
    
    /**
     * Instantiates a RoomNode with the given description.
     * 
     * Preconditions:
     *      description may not be null.
     *      name may not be null
     * 
     * @param roomID a unique ID associated with the room.
     * @param name the name of the room.
     * @param description the description of the room.
     */
    public RoomNode(int roomID, String name, String description) {
        if (name == null || description == null) {
            throw new NullPointerException("Cannot use null in RoomNode constructor!");
        }
        
        this.name = name;
        this.roomID = roomID;
        this.description = description;
        this.items = new LinkedList<Item>();
        this.directions = new EnumMap<>(Direction.class);
    }
    
    /**
     * 
     * Instantiates a RoomNode with the given description.
     * 
     * Preconditions:
     *      name may not be null
     * 
     * @param name the name of the room.
     * @param description the description of the room.
     */
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

    @Override
    public int compareTo(RoomNode other) {
        return this.roomID - other.roomID;
    }

    /**
     * @return the items in this room.
     */
    public List<Item> getItems() {
        return new ArrayList<Item>(items);
    }

    /**
     * If the item is already in the list or null, no changes occur.
     * 
     * @param item the items to add to the room.
     */
    public void addItem(Item item) {
        if (!items.contains(item) && item != null) {
            this.items.add(item);            
        }
    }

    /**
     * @param item the items to remove.
     * @return if the item was removed.
     */
    public boolean removeItem(Item item) {
        return this.items.remove(item);
    }
    
    /**
     * Ignores case differences.
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
	    if (theDirection != null) {
	        directions.put(theDirection, room);
	    }
	}
	
	/**
	 * Get the RoomNode in the given direction. Throws NullPointerException if theDirection is null.
	 * 
	 * @param theDirection the direction to get.
	 * @return the room in theDirection given.
	 */
	public RoomNode getDirection(Direction theDirection) {
	    return directions.get(theDirection);
	}
	
}
