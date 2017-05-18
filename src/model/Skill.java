package model;

/**
 * An enum to represent the different skills in the game.
 * 
 * @author Zachary Chandler
 */
public enum Skill {
	STRENGTH(false), STAMINA(true), APTITUDE(false), ARCANA(true), FAITH(false), BLESSING(true), VITALITY(true);
	
	public final boolean consumable;
	
	Skill(boolean consumable) {
	    this.consumable = consumable;
	}
}
