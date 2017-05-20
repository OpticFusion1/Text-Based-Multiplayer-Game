package tests;

import static model.Skill.ARCANA;
import static model.Skill.STRENGTH;
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
	
    @Test
    public void testConsume_ZeroArcana_NoChanges() {
        int amount = skills.getPool(ARCANA);

        assertTrue(skills.consume(ARCANA, 0));
        assertEquals(amount, skills.getPool(ARCANA));
    }

    @Test
    public void testConsume_OneArcana_OneLessArcana() {
        int amount = skills.getPool(ARCANA);

        assertTrue(skills.consume(ARCANA, 1));
        assertEquals(amount - 1, skills.getPool(ARCANA));
    }

    @Test
    public void testConsume_AllArcana_ArcanaAtZero() {
        int amount = skills.getPool(ARCANA);

        assertTrue(skills.consume(ARCANA, amount));
        assertEquals(0, skills.getPool(ARCANA));
    }
    
    @Test
    public void testConsume_AllArcanaPlusOne_ArcanaNotConsumed() {
        int amount = skills.getPool(ARCANA);

        assertFalse(skills.consume(ARCANA, amount + 1));
        assertEquals(amount, skills.getPool(ARCANA));
    }

    @Test
    public void testConsume_TwiceAvailableArcana_ArcanaNotConsumed() {
        int amount = skills.getPool(ARCANA);

        assertFalse(skills.consume(ARCANA, amount + amount));
        assertEquals(amount, skills.getPool(ARCANA));
    }

    @Test(expected = NullPointerException.class)
    public void testConsume_NullType_NullPointerException() {
        skills.consume(null, 0);
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetPool_NullType_NullPointerException() {
        skills.getPool(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConsume_NonConsumable_IllegalArgumentException() {
        skills.consume(STRENGTH, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConsume_NegativeAmount_IllegalArgumentException() {
        skills.consume(ARCANA, -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSkill_NonConsumable_IllegalArgumentException() {
        skills.getPool(STRENGTH);
    }
    
    @Test
    public void testRefill_ZeroArcana_NoChanges() {
        int amount = skills.getPool(ARCANA);

        skills.refill(ARCANA, 0);
        assertEquals(amount, skills.getPool(ARCANA));
    }

    @Test
    public void testRefill_OneArcana_OneLessArcana() {
        assertTrue(skills.consume(ARCANA, skills.getPool(ARCANA)));
        skills.refill(ARCANA, 1);
        assertEquals(1, skills.getPool(ARCANA));
    }

    @Test
    public void testRefill_AllArcana_ArcanaAtMax() {
        int amount = skills.getPool(ARCANA);
        
        assertTrue(skills.consume(ARCANA, amount));
        assertEquals(0, skills.getPool(ARCANA));
        
        skills.refill(ARCANA, amount);
        assertEquals(amount, skills.getPool(ARCANA));
    }

    @Test
    public void testRefill_AllArcanaPlusOne_ArcanaAtMax() {
        int amount = skills.getPool(ARCANA);
        
        assertTrue(skills.consume(ARCANA, amount));
        assertEquals(0, skills.getPool(ARCANA));
        
        skills.refill(ARCANA, amount + 1);
        assertEquals(amount, skills.getPool(ARCANA));
    }

    @Test
    public void testRefill_TwiceMaxArcana_ArcanaAtMax() {
        int amount = skills.getPool(ARCANA);
        
        assertTrue(skills.consume(ARCANA, amount));
        assertEquals(0, skills.getPool(ARCANA));
        
        skills.refill(ARCANA, amount + amount);
        assertEquals(amount, skills.getPool(ARCANA));
    }

    @Test(expected = NullPointerException.class)
    public void testRefill_NullType_NullPointerException() {
        skills.refill(null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRefill_NonConsumable_IllegalArgumentException() {
        skills.refill(STRENGTH, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRefill_NegativeAmount_IllegalArgumentException() {
        skills.refill(ARCANA, -1);
    }
    
    @Test
    public void testAddXP_PastNewLevel_PoolIsRefilledToMax() {
        int amount = skills.getPool(ARCANA);
        assertTrue(skills.consume(ARCANA, amount));
        assertEquals(0, skills.getPool(ARCANA));
        
        
        skills.addXP(ARCANA, SkillSet.getXPForLevel(2));
        assertEquals(200, skills.getPool(ARCANA));
    }
    
    @Test
    public void testAddXP_WithinLevel_PoolIsNotRefilled() {
        int amount = skills.getPool(ARCANA);
        assertTrue(skills.consume(ARCANA, amount / 2));
        assertEquals(amount / 2, skills.getPool(ARCANA));
        
        skills.addXP(ARCANA, amount / 2);
        assertEquals(50, skills.getPool(ARCANA));
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetMaxPoolFor_NullSkill_NullPointerException() {
        skills.getMaxPoolFor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMaxPoolFor_NonConsumableSkill_IllegalArgumentException() {
        skills.getMaxPoolFor(STRENGTH);
    }

    @Test
    public void testGetMaxPoolFor_ConsumableSkillLevelOne_CorrectValueReturned() {
        assertEquals(100, skills.getMaxPoolFor(Skill.STAMINA));
    }
}
