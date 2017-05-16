package model;

import java.io.Serializable;

public class Universe implements Serializable {
    private static final long serialVersionUID = 4029343319101273492L;
    
    public final RoomManager rooms;
    public final Entities entities;
    
    public Universe(RoomManager rooms) {
        this.rooms = rooms;
        this.entities = new Entities();
    }
}
