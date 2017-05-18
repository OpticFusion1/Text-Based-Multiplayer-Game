package model;

import java.util.EnumMap;

/**
 * A class to keep track of a characters skills.
 * 
 * @author Zachary Chandler
 */
public class SkillSet {

	/** The amount of xp required to get to level at the index of level - 1. */
	private static final int[] XP_FOR_LEVEL = new int[] {
	        0, // XP for level 1 must be 0, or else skills won't start at level 1. 
	        100, 500, 1300, 2500
	};

    /** The max level of a skill. */
    public static final int MAX_LEVEL = XP_FOR_LEVEL.length;
    
	/** The skill values in this skill set. */
	private EnumMap<Skill, SkillValue> skills;
	
	/**
	 * Create a new set of skills with level one in all skills and zero xp in all skills.
	 */
	public SkillSet() {
		this.skills = new EnumMap<Skill, SkillValue>(Skill.class);
		
		for (Skill s :Skill.values()) {
			skills.put(s, new SkillValue(1));
		}
		
	}

	/**
	 * @param skill the skill to check.
	 * @return the amount of xp in that skill.
	 * @throws NullPointerException of skill is null.
	 */
	public int getXP(Skill skill) {
		return skills.get(skill).getXP();
	}
	
	/**
	 * If the given xp exceeds the amount required for the MAX_LEVEL, the level stays at MAX_LEVEL. If the xp exceeds
	 * the current level, the skill levels up. If a level up occurs and the skill is consumable, the pool for that skill
	 * is refilled to the new max.
	 * 
	 * @param skill the skill to add xp to.
	 * @param amount the amount of xp to add.
	 * @throws NullPointerException of skill is null.
	 * @throws IllegalArgumentException if amount is negative.
	 */
	public void addXP(Skill skill, int amount) {
		skills.get(skill).addXP(amount);
	}

	/**
	 * @param skill the skill to get the level of.
	 * @return the current level of that skill.
	 * @throws NullPointerException of skill is null.
	 */
	public int getLevel(Skill skill) {
		return skills.get(skill).getLevel();
	}

	/**
	 * Get the required xp for a skill to be at a given level i.
	 * @param i the level to check.
	 * @return the amount of xp to get there.
	 * @throws ArrayIndexOutOfBoundsException if i is zero, negative or greater than MAX_LEVEL
	 */
	public static int getXPForLevel(int i) {
		return XP_FOR_LEVEL[i - 1];
	}
	
	/**
	 * @param i the level
	 * @return the maximum pool size for that level.
	 */
	public static int getMaxPoolSizeForLevel(int i) {
	    return i * 100;
	}
	
	/**
	 * Consume part of the pool of a consumable skill. If the amount is greater than the available pool, the method
	 * returns false and the pool does not change. Otherwise the amount given is taken from the pool and the method
	 * returns true. If amount is negative or type is not consumable an IllegalArgumentException is thrown. If type
	 * is null, a NullPointerException is thrown.
	 *  
	 * @param type the type of skill to consume.
	 * @param amount the amount to consume.
	 * @return if any of the pool was consumed.
	 * @throws NullPointerException if type is null.
	 * @throws IllegalArgumentException if type is not consumable.
	 */
    public boolean consume(Skill type, int amount) {
        if (type == null) {
            throw new NullPointerException();
        } else if (!type.consumable || amount < 0) {
            throw new IllegalArgumentException();
        }
        
        return skills.get(type).consume(amount);
    }

    /**
     * @param type the pool to get.
     * @return the amount left in the pool.
     * @throws NullPointerException if type is null.
     * @throws IllegalArgumentException if type is not consumable.
     */
    public int getPool(Skill type) {
        if (type == null) {
            throw new NullPointerException();
        } else if (!type.consumable) {
            throw new IllegalArgumentException();
        }
        
        return skills.get(type).getPool();
    }

    /**
     * Refill part of the pool of a consumable skill. If the amount is greater than the available pool, the the pool
     * is set to the max. Otherwise the amount given is added to the pool.  If amount is negative or type is not
     * consumable an IllegalArgumentException is thrown. If type is null, a NullPointerException is thrown.
     * 
     * @param type the skill to refill the pool of.
     * @param amount the amount of the pool to refill.
     * @throws NullPointerException if type is null.
     * @throws IllegalArgumentException is type is not consumable or amount is negative. 
     */
    public void refill(Skill type, int amount) {
        if (type == null) {
            throw new NullPointerException();
        } else if (!type.consumable || amount < 0) {
            throw new IllegalArgumentException();
        }
        
        skills.get(type).refill(amount);
    }
    
	/**
	 * A class to hold and manage the values in a skill.
	 * 
	 * @author Zachary Chandler
	 */
	private class SkillValue {
		private int level;
		private int xp;
		private int pool;
		
		/**
		 * Creates a new skill with zero xp and levels.
		 * @throws ArrayIndexOutOfBoundsException if level is out of accessable levels.
		 */
		public SkillValue(int level) {
			addXP(getXPForLevel(level));
			pool = getMaxPoolSizeForLevel(level);
		}
		
		/**
		 * @return the level of the skill.
		 */
		public int getLevel() {
			return level;
		}
		
		/**
		 * @return the experience points of the skill.
		 */
		public int getXP() {
			return xp;
		}
		
		/**
		 * If the given xp exceeds the amount required for the MAX_LEVEL, the level stays at MAX_LEVEL. If the xp
		 * exceeds the current level, the skill levels up. If a level up occurs and the skill is consumable, the pool
		 * for that skill is refilled to the new max.
		 * 
		 * @param xp the amount of xp to add.
		 * @throws IllegalArgumentException if the amount of xp is negative.
		 */
		public void addXP(int xp) {
			if (xp < 0) {
				throw new IllegalArgumentException();
			}
			
			this.xp += xp;
			
			while (level < MAX_LEVEL && xp >= SkillSet.getXPForLevel(level + 1)) {
				level++;
				pool = getMaxPoolSizeForLevel(level);
			}
		}

        /**
         * @return the pool
         */
        public int getPool() {
            return pool;
        }

        /**
         * Attempts to consume a part of the pool. If amount is greater than the available pool, the pool doesn't change
         * and the method returns false. Otherwise the pool shrinks by the amount and returns true. If amount is 
         * negative, an IllegalArgumentException is thrown.
         * 
         * @param amount the amount of the pool to take.
         * @return if any of the pool was taken.
         * @throws IllegalArgumentException if amount is negative.
         */
        public boolean consume(int amount) {
            if (amount < 0) {
                throw new IllegalArgumentException();
            }

            if (amount > pool) {
                return false;
            } else {
                pool -= amount;
                return true;
            }
        }
        
        /**
         * Refills the pool by the given amount up to the max pool size for the current level.
         * @param amount of the pool to refill.
         * @throws IllegalArgumentException if amount is negative.
         */
        public void refill(int amount) {
            if (amount < 0) {
                throw new IllegalArgumentException();
            }
            
            pool += amount;
            
            if (pool > getMaxPoolSizeForLevel(level)) {
                pool = getMaxPoolSizeForLevel(level);
            }
        }
	}
}

