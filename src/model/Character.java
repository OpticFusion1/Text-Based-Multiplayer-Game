package model;

import java.io.Serializable;

/**
 * A class to contain all the information required to save the state of the user.
 * 
 * @author Zachary Chandler
 */
public abstract class Character implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = -2130515270184748942L;

    /** The name of the character. */
    private String name;

    /** The room ID of the current room. */
    private int currentRoomID;

    /** The room of the player. */
    private transient RoomNode room;

    /** The name of this character in upper case. */
    private String upperCaseName;
    
    /** The skills of the player. */
    public final SkillSet skills;
    
    /** The current outgoing attack. */
    private OutgoingAttack currentAttack;

    /**
     * Instantiate a Character in the given room with the given name. if name is null, a NullPointerException is thrown.
     * If room is null, this user will not be in any room. 
     * 
     * @param saveRoom the room to place the character into.
     * @param name the name of the character.
     * @throws a NullPointerException if name is null.
     */
    public Character(RoomNode room, String name) {
        this(name);
        setRoom(room);
    }
    
    /**
     * Instantiate a Character with the given name. The current room will be null so any calls to getRoom without 
     * setting a non-null room will result in a null.
     * 
     * If name is null, a NullPointerException is thrown.
     * 
     * @param name the name of the character.
     * @throws NullPointerException if name is null.
     */
    protected Character(String name) {
        setName(name);
        
        this.skills = new SkillSet();
    }

    /**
     * @return the name of the character
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the upper case value of this characters name.
     */
    public String getUpperCaseName() {
        return upperCaseName;
    }

    /**
     * if name is null, a NullPointerException is thrown.
     * @param name the name to set
     */
    public void setName(String name) {
        this.upperCaseName = name.toUpperCase();
        this.name = name;
    }

    /**
     * @return the current room the character is in
     */
    public RoomNode getRoom() {
        return room;
    }

    /**
     * Sets the current room of the character. If the given room is null, the current room will not change.
     * 
     * @param room the room to set
     */
    public void setRoom(RoomNode room) {
        if (room != null) {
            if (this.room != null) {
                this.room.removeCharacter(this);
            }
            
            this.room = room;
            this.currentRoomID = room.getRoomID();
            
            this.room.addCharacter(this);
        }
    }
    
    /**
     * Cause the character to be removed from the room.
     */
    public void removeFromCurrentRoom() {
        if (this.room != null) {
            this.room.removeCharacter(this);
            this.room = null;
        }
    }

    /**
     * @return the currentRoomID
     */
    public int getCurrentRoomID() {
        return currentRoomID;
    }

    /**
     * @return the health of the player.
     */
	public int getHealth() {
		return skills.getPool(Skill.VITALITY);
	}

	/**
	 * Harm the character from the given attacker. If type or attacker is null,
	 * a NullPointerException is thrown.
	 * 
	 * The amount of actual damage inflicted may be lower than the given amount.
	 * It is possible that the character had a lower amount of health than the
	 * given amount.
	 * 
	 * @param type the type of damage afflicting this player.
	 * @param attacker the character harming this character.
	 * @param amount the amount of damage being inflicted.
	 * @return the amount of actual damage inflicted.
	 * @throws NullPointerException if attacker or type is null.
	 * @throws IllegalArgumentException if amount is negative.
	 */
	public int harm(DamageType type, Character attacker, int amount) {
	    if (type == null || attacker == null) {
	        throw new NullPointerException();
	    }
	    
	    if (amount < 0) {
	        throw new IllegalArgumentException();
	    }
	    
	    int result;
	    
	    if (amount >= getHealth()) {
	        result = getHealth();
	        skills.consume(Skill.VITALITY, getHealth());
	    } else {
	        result = amount;
	        skills.consume(Skill.VITALITY, amount);
	    }
	    
		return result;
	}

    /**
     * Heal the character from the given caster. If type or attacker is null,
     * a NullPointerException is thrown.
     * 
     * The amount of actual damage healed may be lower than the given amount.
     * It is possible that the character had a lower amount of health until max than the
     * given amount.
     * 
     * @param healingType the type of healing affecting this player.
     * @param caster the character harming this character.
     * @param healAmount the amount of damage being healed.
     * @return the amount of actual damage inflicted.
     * @throws NullPointerException if caster or healingType is null.
     * @throws IllegalArgumentException if amount is negative.
     */
    public int heal(DamageType healingType, Character caster, int healAmount) {
        if (healingType == null || caster == null) {
            throw new NullPointerException();
        }
        
        if (healAmount < 0) {
            throw new IllegalArgumentException();
        }
        
        int initialHealth = skills.getPool(Skill.VITALITY);
        skills.refill(Skill.VITALITY, healAmount);
        return skills.getPool(Skill.VITALITY) - initialHealth;
    }

    /**
     * @param otherTime the current time.
     * @return if this character should attack.
     */
    public boolean shouldAttack(long otherTime) {
        return currentAttack == null ? false : currentAttack.shouldAttack(otherTime);
    }

    /**
     * Perform the current attack. And removes it as the current attack.
     * @throws NullPointerException if there is no attack to perform.
     */
    public void consumeOutgoingAttack() {
        currentAttack.attack();
        currentAttack = null;
    }

    /**
     * if newAttack is null, the character will not have a current attack.
     * @param newAttack the attack to set
     */
    public void setCurrentAttack(OutgoingAttack newAttack) {
        this.currentAttack = newAttack;
    }
}
