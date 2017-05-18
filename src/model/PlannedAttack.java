package model;

/**
 * A class to help delay attacks. Specifically a PlannedAttack can tell you whether or not it should be executed for
 * the given current time.
 *
 * @author Zachary Chandler
 */
public abstract class PlannedAttack implements Attack {
    
    /** The time when the attack should occur. */
    private long timeToAttack;

    /**
     * Create a new planned attack for the given delay in milliseconds. If delay is negative an IllegalArgumentException
     * is thrown.
     * 
     * @param delay the number of milliseconds to wait to attack.
     * @throws IllegalArgumentException if delay is negative.
     */
    public PlannedAttack(long delay) {
        if (delay < 0) {
            throw new IllegalArgumentException();
        }
        
        timeToAttack = System.currentTimeMillis() + delay;
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
     * A planned attack shouldAttack when t1 is at or after t2. t1 and t2 are time in milliseconds.
     * 
     * @param t1 the current planned time to attack.
     * @param t2 the other time to compare to.
     * @return if a planned attack should occur with the given times.
     */
    public static boolean shouldAttack(long t1, long t2) {
        return t1 <= t2;
    }
    
}
