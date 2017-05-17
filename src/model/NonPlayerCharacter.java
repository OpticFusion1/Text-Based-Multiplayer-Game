package model;

/**
 * A class to represent npcs. This needed to on a different branch from players to that a player could never be an 
 * npc and vis versa.
 *
 * @author Zachary Chandler
 */
public class NonPlayerCharacter extends Character {

    /** Generated SVUID. */
    private static final long serialVersionUID = 2853191703651175761L;

    /**
     * Create a new npc in the given room for the given name. If name is null, a NullPointerException is thrown.
     * @param room the room to place the new npc in.
     * @param name the name of the npc.
     * @throws NullPointerException if name is null.
     */
    public NonPlayerCharacter(RoomNode room, String name) {
        super(room, name);
    }

}
