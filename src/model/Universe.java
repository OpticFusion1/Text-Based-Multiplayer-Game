package model;

import java.io.Serializable;
import java.util.Timer;

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
    
    /** The timer to do attacks. */
    private transient Timer attackTimer;
    
    /**
     * Creates a new universe.
     */
    public Universe() {
        this.rooms = new RoomManager();
        this.entities = new Entities();
    }
    
    /**
     * Start the attack scheduler.
     */
    public void startAttackScheduler() {
        if (attackTimer != null) {
            throw new IllegalStateException();
        }
        
        attackTimer = new Timer();
        attackTimer.schedule(new AttackScheduler(entities), 0, 100);
    }
}
