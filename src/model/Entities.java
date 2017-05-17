package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Entities keeps track NonPlayerCharacters and Players in the universe. A serialized Entities will never have 
 * have any players upon de-serialization. 
 *
 * @author Zachary Chandler
 */
public class Entities implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = 2001817413789853843L;
    
    /** The npcs in the universe. */
    private final List<NonPlayerCharacter> npcs;
    
    /** The players in the universe. */
    private final List<Player> players;

    /**
     * Creates a new Entities without any tracked npcs or players.
     */
    public Entities() {
        npcs = new LinkedList<>();
        players = new LinkedList<>();
    }
    
    /**
     * Get the npcs in the universe in an unmodifiable list.
     * @return the npcs in the universe.
     */
    public List<NonPlayerCharacter> getNPCs() {
        return Collections.unmodifiableList(npcs);
    }
    
    /**
     * Add an npc to be tracked. If npc is null, a NullPointerException will be thrown.
     * @param npc the npc to track.
     * @return if the npc was added.
     */
    public boolean addNPC(NonPlayerCharacter npc) {
        if (npc == null) {
            throw new NullPointerException("Cannot add null npc");
        }
        
        return npcs.add(npc);
    }
    
    /**
     * Remove a npc from the universe. If npc is null, a NullPointerException is thrown.
     *      the npc will be removed from their current room if they are in one.
     *      
     * @param npc the character to remove.
     * @return if the npc was removed.
     */
    public boolean removeNPC(NonPlayerCharacter npc) {
        cleanupCharacter(npc);
        return npcs.remove(npc);
    }

    /**
     * Gets an unmodifiable list of players in the universe right now.
     * @return the players in the universe.
     */
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Adds a player p to the universe. If p is null, a NullPointerException is thrown.
     * @param p the player to add.
     * @return if the player was added.
     */
    public boolean addPlayer(Player p) {
        if (p == null) {
            throw new NullPointerException("Cannot add null players!");
        }
        
        return players.add(p);
    }
    
    /**
     * Remove a player from the universe. If p is null, a NullPointerException is thrown.
     *      the player will be removed from their current room if they are in one.
     *      
     * @param p the player to remove.
     * @return if the player was removed.
     */
    public boolean removePlayer(Player p) {
        cleanupCharacter(p);
        return players.remove(p);
    }
    
    /**
     * Check if their is a player with the given username currently logged in.
     * @param name the name to look for.
     * @return if their is a player with the given username.
     */
    public boolean isPlayerLoggedIn(String name) {
        name = name.toUpperCase();
        
        for (Player p : players) {
            if (p.getUpperCaseName().equals(name)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Cleanup a character so we can remove them from the tracked entities. After this method ends the character will
     * be fully removed from the model data such that they are not in any room.
     * 
     * @param c the character to cleanup.
     */
    private void cleanupCharacter(Character c) {
        c.removeFromCurrentRoom();
    }

    /**
     * A simple work around to avoid saving players while maintaining a final list.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
      List<Player> swap = new LinkedList<>(players);
      players.clear();
      out.defaultWriteObject();
      players.addAll(swap);
    }
}
