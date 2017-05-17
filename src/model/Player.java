package model;

import console.User;

/**
 * A class to represent players. A player is different than a character in that it can have an associated user.
 *
 * @author Zachary Chandler
 */
public class Player extends Character {
    
    /** SVUID */
    private static final long serialVersionUID = 3350784687773159317L;
    
    /** The user of this player. */
    transient private User user;

    /**
     * Create a new player for the given user in the given room with the given name. If name is null or user is null a 
     * NullPointerException is thrown.
     * 
     * @param user the user of this player.
     * @param room the room to place the player in.
     * @param name the name of this player.
     * @throws NullPointerException if name is null.
     */
    public Player(User user, RoomNode room, String name) {
        super(name); // can't call normal constructor because it sets the room, and the room needs to know our user.
        
        setUser(user);
        setRoom(room);
    }

    /**
     * @return the user of this player.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set.
     * @throws NullPointerException if user is null.
     */
    public void setUser(User user) {
        if (user == null) {
            throw new NullPointerException("Cannot set player user to null.");
        }
        
        this.user = user;
    }
    
}
