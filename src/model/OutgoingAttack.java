package model;

/**
 * A class to help manage attack delays. Specifically an OutgoingAttack can tell you whether or not it should be
 * executed given the current time.
 *
 * @author Zachary Chandler
 */
public class OutgoingAttack {
    
    /** The time when the attack should occur. */
    private long timeToAttack;

    /** The attack to perform. */
    private Attack attack;
    
    /** The caster of the attack. */
    private Character caster;
    
    /** The ones being attacked. */
    private Character[] others;
    
    
    /**
     * Create a new outgoing attack for the given delay in milliseconds. If delay is negative an 
     * IllegalArgumentException is thrown.
     * 
     * @param delay the number of milliseconds to wait to attack.
     * @param attack the attack to execute.
     * @param caster the one performing the attack.
     * @param others the ones affected by the attack.
     * @throws NullPointerException if attack, caster, or any others in other is null.
     * @throws IllegalArgumentException if delay is negative.
     */
    public OutgoingAttack(long delay, Attack attack, Character caster, Character... others) {
        
        if (attack == null || caster == null) {
            throw new NullPointerException();
        }
        
        for (Character other : others) {
            if (other == null) {
                throw new NullPointerException();
            }
        }
        
        if (delay < 0) {
            throw new IllegalArgumentException();
        }
        
        this.timeToAttack = System.currentTimeMillis() + delay;
        this.attack = attack;
        this.caster = caster;
        this.others = others;
    }
    
    /**
     * Check if this attack should occur before or at the otherTime.
     * 
     * @param otherTime time in milliseconds.
     * @return if this attack should occur before the otherTime.
     */
    public boolean shouldAttack(long otherTime) {
        return shouldAttack(this.timeToAttack, otherTime);
    }
    
    /**
     * Perform the attack.
     */
    public void attack() {
        attack.attack(caster, others);
    }
    
    /**
     * An attack shouldAttack when t1 is at or after t2. t1 and t2 are time in milliseconds.
     * 
     * @param t1 the current planned time to attack.
     * @param t2 the other time to compare to.
     * @return if a planned attack should occur with the given times.
     */
    public static boolean shouldAttack(long t1, long t2) {
        return t1 <= t2;
    }
    
}
