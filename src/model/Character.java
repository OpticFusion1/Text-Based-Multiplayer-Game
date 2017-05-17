package model;

import java.io.Serializable;

/**
 * A class to contain all the information required to save the state of the user.
 * 
 * 
 * @author Zachary Chandler
 */
public abstract class Character implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = -2130515270184748942L;

    /** The name of the character. */
    private String name;

    /** The room ID of the current room. */
    private int currentRoomID;

    /** The room of the player. */
    private transient RoomNode room;

    /** The name of this character in upper case. */
    private String upperCaseName;

    /**
     * Instantiate a Character in the given room with the given name.
     * if name is null, a NullPointerException is thrown.
     * 
     * @param saveRoom the room to place the character into.
     * @param name the name of the character.
     * @throws a NullPointerException if name is null.
     */
    public Character(RoomNode room, String name) {
        setName(name);
        setRoom(room);
    }
    
    /**
     * Instantiate a Character with the given name. The current room will be null so any calls to getRoom without 
     * setting a non-null room will result in a null.
     * 
     * If name is null, a NullPointerException is thrown.
     * 
     * @param name the name of the character.
     * @throws NullPointerException if name is null.
     */
    protected Character(String name) {
        setName(name);
    }

    /**
     * @return the name of the character
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the upper case value of this characters name.
     */
    public String getUpperCaseName() {
        return upperCaseName;
    }

    /**
     * if name is null, a NullPointerException is thrown.
     * @param name the name to set
     */
    public void setName(String name) {
        this.upperCaseName = name.toUpperCase();
        this.name = name;
    }

    /**
     * @return the current room the character is in
     */
    public RoomNode getRoom() {
        return room;
    }

    /**
     * Sets the current room of the character. If the given room is null, the current room will not change.
     * 
     * @param room the room to set
     */
    public void setRoom(RoomNode room) {
        if (room != null) {
            if (this.room != null) {
                this.room.removeCharacter(this);
            }
            
            this.room = room;
            this.currentRoomID = room.getRoomID();
            
            this.room.addCharacter(this);
        }
    }
    
    /**
     * Cause the character to be removed from the room.
     */
    public void removeFromCurrentRoom() {
        if (this.room != null) {
            this.room.removeCharacter(this);
            this.room = null;
        }
    }

    /**
     * @return the currentRoomID
     */
    public int getCurrentRoomID() {
        return currentRoomID;
    }
}
