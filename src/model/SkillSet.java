package model;

import java.util.EnumMap;

/**
 * A class to keep track of a characters skills.
 * 
 * @author Zachary Chandler
 */
public class SkillSet {

	/** The max level of a skill. */
	public static final int MAX_LEVEL = 100;
	
	/** the amount of xp required to get to level at the index of level - 1. */
	private static final int[] XP_FOR_LEVEL = new int[MAX_LEVEL]; {
		int value = 0;
		for (int i = 0; i < MAX_LEVEL; i++) {
			value += (i + 1) * 100;
			XP_FOR_LEVEL[i] = value; 
		}
	}
	
	/** The skill values in this skill set. */
	private EnumMap<Skill, SkillValue> skills;
	
	/**
	 * Create a new set of skills with level zero in all skills.
	 */
	public SkillSet() {
		this.skills = new EnumMap<Skill, SkillValue>(Skill.class);
		
		for (Skill s :Skill.values()) {
			skills.put(s, new SkillValue());
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
	 * A class to hold and manage the values in a skill.
	 * 
	 * @author Zachary Chandler
	 */
	private class SkillValue {
		private int level;
		private int xp;
		
		/**
		 * Creates a new skill with zero xp and levels.
		 */
		public SkillValue() {
			this.xp = 0;
			this.level = 0;
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
		 * @param xp the amount of xp to add.
		 * @throws IllegalArgumentException if the amount of xp is negative.
		 */
		public void addXP(int xp) {
			if (xp < 0) {
				throw new IllegalArgumentException();
			}
			
			this.xp += xp;
			
			while (xp >= SkillSet.getXPForLevel(level + 1)) {
				level++;
			}
		}
	}
}

