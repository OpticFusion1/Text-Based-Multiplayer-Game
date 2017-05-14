package model;

import console.User;

public class Player extends Character {
    
    /** SVUID */
    private static final long serialVersionUID = 3350784687773159317L;
    
    transient private User user;

    public Player(User user, RoomNode room, String name) {
        super(room, name);
        this.user = user;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}
