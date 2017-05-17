package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import console.User;

/**
 * A room object that represents a room in a graph of interconnected nodes.
 * 
 * Keeps track of the name, description, items in the room, characters in the room, users in the room, a unique ID for
 * the room, and adjacent rooms.
 * 
 * @author Zachary Chandler
 */
public class RoomNode implements Serializable, Comparable<RoomNode> {
    
    /** Generated SVUID */
    private static final long serialVersionUID = 4749342966413694748L;

    /** Items in this RoomNode. */
    private final List<Item> items;
    
    /** Characters in this RoomNode. */
    private final List<Character> characters;
    
    /** Users in this RoomNode. No users are saved upon serialization. See writeObject() */
    private final List<User> users; 
    
    /** Adjacent room nodes. */
    private final EnumMap<Direction, RoomNode> directions;
    
    /** A unique ID associated with the room. */
    private final int roomID;
    
    /** A description of the room. */
    private String name;

    /** The description of the room. */
	private String description;

    
    /**
     * Instantiates a RoomNode with the given description. This constructor is Protected so that any RoomNode that is 
     * created must be done through a RoomManager, and so that any RoomNode will have to be tracked by the RoomManager.
     * 
     * if the description or name are null, a NullPointerException is thrown.
     * 
     * @param roomID a unique ID associated with the room.
     * @param name the name of the room.
     * @param description the description of the room.
     */
    protected RoomNode(int roomID, String name, String description) {
        if (name == null || description == null) {
            throw new NullPointerException("Cannot use null in RoomNode constructor!");
        }
        
        this.name = name;
        this.roomID = roomID;
        this.description = description;
        this.items = new LinkedList<Item>();
        this.directions = new EnumMap<>(Direction.class);
        this.characters = new LinkedList<>();
        this.users = new LinkedList<>();
    }

    /**
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the room, if name is null, a NullPointerException is thrown.
     * 
     * @param name the new name of the room.
     */
    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException("Cannot use null description in RoomNode constructor!");
        }
        
        this.name = name;
    }

    /**
     * @return an unmodifiable list of items in this room.
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Adds an item to the room. If the item is already in the list or null, no changes occur.
     * 
     * @param item the item to add to the room.
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
     * Attempts to find an item in the room. It finds any item with the same name or aliases in any case form of the
     * given itemName.
     * 
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
	 * @return the description of the room.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the given direction with with the given room.
	 * 
	 * If the theDirection is null, a NullPointerException is thrown.
	 * 
	 * @param theDirection the direction to set.
	 * @param room the room to put in the given direction.
	 * @throws NullPointerException if theDirection is null.
	 */
	public void setDirection(Direction theDirection, RoomNode room) {
	    directions.put(theDirection, room);
	}
	
	/**
	 * Get the adjacent RoomNode in the given direction. If theDirection is null, a NullPointerException is thrown.
	 * 
	 * @param theDirection the direction to get.
	 * @return the room in theDirection given.
	 * @throws NullPointerException if theDirection is null.
	 */
	public RoomNode getDirection(Direction theDirection) {
	    if (theDirection == null) {
	        throw new NullPointerException();
	    }
	    return directions.get(theDirection);
	}

    /**
     * @return an unmodifiable list of characters in the room
     */
    public final List<Character> getCharacters() {
        return Collections.unmodifiableList(characters);
    }

    /**
     * Adds the given user to the list of users in the room. If r is null, a NullPointerException is thrown.
     * @param theUser the user to add.
     * @throws NullPointerException if r is null.
     */
    private void addUser(User theUser) {
        if (theUser == null) {
            throw new NullPointerException("Null user in RoomNode.");
        }
        
        users.add(theUser);
    }
    
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Add a character to the room. If c is null or already in the room, nothing is changed.
     * 
     * @param c the character to add.
     */
    protected void addCharacter(Character c) {
        if (!characters.contains(c) && c != null) {
            characters.add(c);
            
            if (c instanceof Player) {
                addUser(((Player) c).getUser());
            }
        }
    }
    
    /**
     * Remove a character from the room.
     * @param c the character to remove.
     * @return if the character was removed.
     */
    protected boolean removeCharacter(Character c) {
        if (c instanceof Player) {
            users.remove(((Player) c).getUser());
        }
        
        return characters.remove(c);
    }
    
    /**
     * Find a character in the room based on a given name.
     * @param s the name of the character.
     * @return the character found or null if it wasn't found.
     * @throws NullPointerException if s is null.
     */
    public Character find(String s) {
        s = s.toUpperCase();
        Character result = null;
        
        for (Character c : characters) {
            if (c.getUpperCaseName().equals(s)) {
                result = c;
                break;
            }
        }
        
        return result;
    }

    @Override
    public int compareTo(RoomNode other) {
        return this.roomID - other.roomID;
    }
    
    /**
     * A utility hook to avoid saving users.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        List<User> swap = new LinkedList<>(users);
        users.clear();
        out.defaultWriteObject();
        users.addAll(swap);
    }	
}
