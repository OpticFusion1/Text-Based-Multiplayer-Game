package model;

/**
 * An interface for a simple attack.
 * 
 * @author Zachary Chandler
 */
public interface Attack {

	/**
	 * An attack from the caster onto others.
	 */
	abstract void attack(Character caster, Character... others);
	
}
