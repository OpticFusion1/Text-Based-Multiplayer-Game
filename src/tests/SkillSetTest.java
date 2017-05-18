package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Skill;
import model.SkillSet;

public class SkillSetTest {

	SkillSet skills;
	
	@Before
	public void setUp() throws Exception {
		skills = new SkillSet();
	}

	@Test
	public void testSkillSet_StartAtLevelOne() {
		for (Skill s : Skill.values()) {
			assertEquals(0, skills.getXP(s));
			assertEquals(1, skills.getLevel(s));			
		}
	}

	@Test(expected = NullPointerException.class)
	public void testAddXP_NullSkill_NullPointerException() {
		skills.addXP(null, 2);
	}

	@Test(expected = NullPointerException.class)
	public void testGetXP_NullSkill_NullPointerException() {
		skills.getXP(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetLevel_NullSkill_NullPointerException() {
		skills.getLevel(null);
	}
	
	@Test
	public void testAddXP_UnderLevel_LevelDoesNotChangeAndXPDoesNotChange() {
		for (Skill s : Skill.values()) {
			skills.addXP(s, SkillSet.getXPForLevel(2) - 1);
			assertEquals(1, skills.getLevel(s));
			assertEquals(SkillSet.getXPForLevel(2) - 1, skills.getXP(s));
		}
	}

    @Test
    public void testAddXP_OnLevel_LevelDoesChange() {
        for (Skill s : Skill.values()) {
            skills.addXP(s, SkillSet.getXPForLevel(1));
            assertEquals(1, skills.getLevel(s));
        }
    }
    
    @Test
    public void testAddXP_TwoLevelsWorth_LevelDoesChange() {
        for (Skill s : Skill.values()) {
            skills.addXP(s, SkillSet.getXPForLevel(2));
            assertEquals(2, skills.getLevel(s));
        }
    }

    @Test
    public void testAddXP_ZeroAmount_LevelAndXPDoesNotChange() {
        for (Skill s : Skill.values()) {
            skills.addXP(s, 0);
            assertEquals(0, skills.getXP(s));
            assertEquals(1, skills.getLevel(s));
        }
    }
    
    @Test
    public void testAddXP_PastMaxLevel_StaysAtMaxLevel() {
        skills.addXP(Skill.STRENGTH, SkillSet.getXPForLevel(SkillSet.MAX_LEVEL));
        skills.addXP(Skill.STRENGTH, SkillSet.getXPForLevel(SkillSet.MAX_LEVEL));
        
        assertEquals(SkillSet.MAX_LEVEL, skills.getLevel(Skill.STRENGTH));
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddXP_NegativeAmount_IllegalArgumentException() {
		skills.addXP(Skill.STRENGTH, -1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetXPForLevel_OnNegativeLevel_ArrayIndexOutOfBoundsException() {
		SkillSet.getXPForLevel(-1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetXPForLevel_OnZeroLevel_ArrayIndexOutOfBoundsException() {
		SkillSet.getXPForLevel(0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetXPForLevel_HigherThanMaxLevel_ArrayIndexOutOfBoundsException() {
		SkillSet.getXPForLevel(SkillSet.MAX_LEVEL + 1);
	}

	@Test
	public void testGetXPForLevel_OnMaxLevel_DoesNotThrowArrayIndexOutOfBoundsException() {
		SkillSet.getXPForLevel(SkillSet.MAX_LEVEL);
	}
	
	@Test
	public void testGetXPForLevel_OnMaxLevel_ReturnsValueAboveZero() {
		assertTrue(0 < SkillSet.getXPForLevel(SkillSet.MAX_LEVEL));
	}
}
