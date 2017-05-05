package model;

import java.io.Serializable;

/**
 * A class to contain all the information required to save the state of the user.
 * 
 * 
 * @author Zachary Chandler
 */
public class UserSave implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = -2130515270184748942L;

    /** The user name of the user. */
    public final String username;

    /** The room the user will load back into. */
    public final int currentRoomID;
    
    /**
     * Instantiate a UserSave with the given information.
     * 
     * @param saveRoom the room the user will be saved in.
     * @param username the user name of the user.
     */
    public UserSave(int saveRoom, String username) {
        this.username = username;
        this.currentRoomID = saveRoom;
    }
}
