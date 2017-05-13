package console;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.RoomNode;

/**
 * Manages the link between players and their rooms.
 *
 * @author Zachary Chandler
 */
public class PlayerManager {

    /** players that are in a room */
    private TreeMap<RoomNode, List<User>> roomToPlayers;
    
    /** room of a player */
    private TreeMap<User, RoomNode> playerToRoom;

    /** unmodifiable map of players in a room */
    private TreeMap<RoomNode, List<User>> unmodifiableRoomToPlayers;
    
    /** Creates a new player manager without any players in it. */
    public PlayerManager() {
        this.roomToPlayers = new TreeMap<RoomNode, List<User>>();
        this.unmodifiableRoomToPlayers = new TreeMap<RoomNode, List<User>>();
        this.playerToRoom = new TreeMap<User, RoomNode>();
    }
    
    /**
     * Gets the room of a player.
     * @param p the player
     * @return the room of the player.
     */
    public RoomNode getRoomOfPlayer(User p) {
        return playerToRoom.get(p);
    }
    
    /**
     * Sets the room of a player.
     * @param p the player.
     * @param room the room.
     */
    public void setRoomOfPlayer(User p, RoomNode room) {
        RoomNode previousRoom = playerToRoom.get(p);
        
        if (previousRoom != null) {
            roomToPlayers.get(previousRoom).remove(p);
        }
        
        List<User> rooms = roomToPlayers.get(room);
        
        if (rooms == null) {
            rooms = new LinkedList<User>();
            roomToPlayers.put(room, rooms);
            unmodifiableRoomToPlayers.put(room, Collections.unmodifiableList(rooms));
        }
        
        rooms.add(p);
        playerToRoom.put(p, room);                
    }
    
    /**
     * Gets an unmodifiable list of players in a given room.
     * @param room the room to look through.
     * @return the players in the room.
     */
    public List<User> getPlayers(RoomNode room) {
        return unmodifiableRoomToPlayers.get(room);
    }
}
