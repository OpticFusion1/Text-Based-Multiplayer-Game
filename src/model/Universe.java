package model;

import java.io.Serializable;

public class Universe implements Serializable {
    private static final long serialVersionUID = 4029343319101273492L;
    
    public final RoomManager rooms;
    public final Entities entities;
    
    public Universe() {
        this.rooms = new RoomManager();
        this.entities = new Entities();
    }
}
