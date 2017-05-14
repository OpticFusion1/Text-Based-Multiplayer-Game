package model;

import java.io.Serializable;

/**
 * A class to contain all the information required to save the state of the user.
 * 
 * 
 * @author Zachary Chandler
 */
public class PlayerInformation implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = -2130515270184748942L;

    /** The user name of the user. */
    private String username;

    /** The room the user will load back into. */
    private int currentRoomID;

    /** The room of the player. */
    private transient RoomNode room;

    /**
     * Instantiate a UserSave with the given information.
     * 
     * @param saveRoom the room the user will be saved in.
     * @param username the user name of the user.
     */
    public PlayerInformation(RoomNode room, String username) {
        setUsername(username);
        setRoom(room);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
            this.room = room;
            this.currentRoomID = room.getRoomID();
        }
    }

    /**
     * @return the currentRoomID
     */
    public int getCurrentRoomID() {
        return currentRoomID;
    }

    /**
     * @param currentRoomID the currentRoomID to set
     */
    public void setCurrentRoomID(int currentRoomID) {
        this.currentRoomID = currentRoomID;
    }
}
