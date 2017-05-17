package model;

/**
 * An interface for a simple attack.
 * 
 * @author Zachary Chandler
 */
public interface Attack {

	/**
	 * An attack from the attacker onto the victim.
	 */
	abstract void attack(Character attacker, Character victim);
	
}
