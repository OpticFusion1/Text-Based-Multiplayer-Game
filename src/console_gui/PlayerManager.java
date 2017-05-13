package console_gui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.RoomNode;

public class PlayerManager {

    private TreeMap<RoomNode, List<User>> roomToPlayers;
    private TreeMap<User, RoomNode> playerToRoom;

    public PlayerManager() {
        this.roomToPlayers = new TreeMap<RoomNode, List<User>>();
        this.playerToRoom = new TreeMap<User, RoomNode>();
    }
    
    public RoomNode getRoomOfPlayer(User p) {
        return playerToRoom.get(p);
    }
    
    public void setRoomOfPlayer(User p, RoomNode room) {
        RoomNode previousRoom = playerToRoom.get(p);
        
        if (previousRoom != null) {
            roomToPlayers.get(previousRoom).remove(p);
        }
        
        List<User> rooms = roomToPlayers.get(room);
        
        if (rooms == null) {
            rooms = new LinkedList<User>();
            roomToPlayers.put(room, rooms);
        }
        
        rooms.add(p);
        playerToRoom.put(p, room);                
    }
    
    public List<User> getPlayers(RoomNode room) {
        return Collections.unmodifiableList(roomToPlayers.get(room));
    }
}
