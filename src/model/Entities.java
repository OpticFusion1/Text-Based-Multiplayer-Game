package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Entities implements Serializable {
    
    private static final long serialVersionUID = 2001817413789853843L;
    
    private final List<NonPlayerCharacter> npcs;
    private final List<Player> players;

    public Entities() {
        npcs = new LinkedList<>();
        players = new LinkedList<>();
    }
    
    public List<NonPlayerCharacter> getNPCs() {
        return Collections.unmodifiableList(npcs);
    }
    
    public boolean addNPC(NonPlayerCharacter npc) {
        return npcs.add(npc);
    }
    
    public boolean removeNPC(NonPlayerCharacter npc) {
        return npcs.remove(npc);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean addPlayer(Player p) {
        return players.add(p);
    }
    
    public boolean removePlayer(Player p) {
        return players.remove(p);
    }
    
    public void purgeTrackedPlayers() {
        players.clear();
    }
}
