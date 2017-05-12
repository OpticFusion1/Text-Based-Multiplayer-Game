package model;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class PlayersManager {

    private TreeMap<RoomNode, List<Player>> roomToPlayers;
    private TreeMap<Player, RoomNode> playerToRoom;

    public PlayersManager() {
        this.roomToPlayers = new TreeMap<RoomNode, List<Player>>();
        this.playerToRoom = new TreeMap<Player, RoomNode>();
    }
    
    public RoomNode getRoomOfPlayer(Player p) {
        return playerToRoom.get(p);
    }
    
    public void setRoomOfPlayer(Player p, RoomNode room) {
        RoomNode previousRoom = playerToRoom.get(p);
        
        if (previousRoom != null) {
            roomToPlayers.get(previousRoom).remove(p);
        }
        
        List<Player> rooms = roomToPlayers.get(room);
        
        if (rooms == null) {
            rooms = new LinkedList<Player>();
            roomToPlayers.put(room, rooms);
        }
        
        rooms.add(p);
    }
    
    public List<Player> getPlayers(RoomNode room) {
        List<Player> rooms = roomToPlayers.get(room);
        if (rooms == null) {
            rooms = new LinkedList<Player>();
        } else {
            rooms = new LinkedList<Player>(rooms);
        }
        
        return rooms;
    }
}
