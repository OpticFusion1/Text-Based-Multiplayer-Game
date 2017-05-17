package model;

import java.io.Serializable;

/**
 * A universe class to track the room graph and entities in the universe.
 *
 * @author Zachary Chandler
 */
public class Universe implements Serializable {
    /** Generated SVUID. */
    private static final long serialVersionUID = 4029343319101273492L;
    
    /** The rooms in the universe. */
    public final RoomManager rooms;
    
    /** The entities in the universe. */
    public final Entities entities;
    
    /**
     * Creates a new universe.
     */
    public Universe() {
        this.rooms = new RoomManager();
        this.entities = new Entities();
    }
}
