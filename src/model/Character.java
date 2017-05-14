package model;

import java.io.Serializable;

/**
 * A class to contain all the information required to save the state of the user.
 * 
 * 
 * @author Zachary Chandler
 */
public class Character implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = -2130515270184748942L;
    
    private static final RoomNode waitingRoom = new RoomNode(-1, "An Empty Room", "There is nothing here.");

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
     * Cause the character to disappear.
     */
    public void disapear() {
        setRoom(waitingRoom);
    }

    /**
     * @return the currentRoomID
     */
    public int getCurrentRoomID() {
        return currentRoomID;
    }
}
