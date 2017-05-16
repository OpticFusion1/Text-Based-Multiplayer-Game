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

    /** The user name of the user. */
    private String name;

    /** The room the user will load back into. */
    private int currentRoomID;

    /** The room of the player. */
    private transient RoomNode room;

    /**
     * Instantiate a UserSave with the given information.
     * 
     * @param saveRoom the room the user will be saved in.
     * @param name the user name of the user.
     */
    public Character(RoomNode room, String name) {
        setName(name);
        setRoom(room);
    }
    
    /**
     * Instantiate a Character with the given name. The current room will be null so any calls to getRoom without 
     * setting a non-null room will result in a null.
     * 
     * @param name
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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the room
     */
    public RoomNode getRoom() {
        return room;
    }

    /**
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
    public void removeFromRoom() {
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
