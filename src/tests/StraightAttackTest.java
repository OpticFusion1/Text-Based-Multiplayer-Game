package tests;

import static org.junit.Assert.*;
import static model.DamageType.*;
import static model.Skill.*;

import org.junit.Before;
import org.junit.Test;

import model.Character;
import model.NonPlayerCharacter;
import model.RoomManager;
import model.StraightAttack;

public class StraightAttackTest {

    public static final int DAMAGE = 30;
    public static final int COST = 40;
    
    StraightAttack magicAttack;
    StraightAttack healingAttack;
    StraightAttack noAttack;
    
    Character npc1;
    Character npc2;
    Character npc3;
    
    @Before
    public void setUp() throws Exception {
        RoomManager rooms = new RoomManager();
        magicAttack = new StraightAttack(FIRE, APTITUDE, ARCANA, DAMAGE, COST);
        healingAttack = new StraightAttack(LIGHT, FAITH, BLESSING, -DAMAGE, COST);
        noAttack = new StraightAttack(PHYSICAL, STRENGTH, STAMINA, 0, COST);
        
        npc1 = new NonPlayerCharacter(rooms.getStartingRoom(), "name1");
        npc2 = new NonPlayerCharacter(rooms.getStartingRoom(), "name2");
        npc3 = new NonPlayerCharacter(rooms.getStartingRoom(), "name3");
    }

    @Test
    public void testStraightAttack_ValidInputs_InitializedVariables() {
        assertEquals(magicAttack.getDamageType(), FIRE);
        assertEquals(magicAttack.getDamageSkill(), APTITUDE);
        assertEquals(magicAttack.getDamagePool(), ARCANA);
        assertEquals(magicAttack.getDamageCost(), COST);
        assertEquals(magicAttack.getDamageAmount(), DAMAGE);
    }

    @Test(expected = NullPointerException.class)
    public void testStraightAttack_NullDamageType_NullPointerException() {
        new StraightAttack(null, STRENGTH, STAMINA, DAMAGE, COST);
    }

    @Test(expected = NullPointerException.class)
    public void testStraightAttack_NullDamageSkill_NullPointerException() {
        new StraightAttack(PHYSICAL, null, STAMINA, DAMAGE, COST);
    }
    
    @Test(expected = NullPointerException.class)
    public void testStraightAttack_NullDamageCost_NullPointerException() {
        new StraightAttack(PHYSICAL, STRENGTH, null, DAMAGE, COST);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStraightAttack_NonConsumableCost_IllegalArgumentException() {
        new StraightAttack(PHYSICAL, STRENGTH, STRENGTH, DAMAGE, COST);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testStraightAttack_NegativeCost_NoExceptions() {
        assertNotEquals(null, new StraightAttack(PHYSICAL, STRENGTH, STAMINA, DAMAGE, -1));
    }

    @Test
    public void testStraightAttack_NegativeDamage_NoExceptions() {
        assertNotEquals(null, new StraightAttack(PHYSICAL, STRENGTH, STAMINA, -10, 3));
    }
    
    @Test(expected = NullPointerException.class)
    public void testAttack_NullCaster_NullPointerException() {
        magicAttack.attack(null, npc1, npc2, npc3);
    }
    
    @Test(expected = NullPointerException.class)
    public void testAttack_NullOther_NullPointerException() {
        magicAttack.attack(npc1, npc2, null, npc3);
    }

    @Test
    public void testAttack_HarmingAttack_HarmDoneToEachOther() {
        magicAttack.attack(npc1, npc2, npc3);

        assertTrue(npc2.getHealth() < npc2.skills.getMaxPoolFor(VITALITY));
        assertTrue(npc3.getHealth() < npc2.skills.getMaxPoolFor(VITALITY));
        
    }
    
    @Test
    public void testAttack_HealingAttack_HealingDoneToEachOther() {
        magicAttack.attack(npc1, npc2, npc3);
        healingAttack.attack(npc1, npc2, npc3);

        assertTrue(npc2.getHealth() == npc2.skills.getMaxPoolFor(VITALITY));
        assertTrue(npc3.getHealth() == npc2.skills.getMaxPoolFor(VITALITY));
    }

    @Test
    public void testAttack_NoAttack_HealthStaysTheSameForEachOther() {
        noAttack.attack(npc1, npc2, npc3);

        assertTrue(npc2.getHealth() == npc2.skills.getMaxPoolFor(VITALITY));
        assertTrue(npc3.getHealth() == npc2.skills.getMaxPoolFor(VITALITY));
    }
    

    @Test
    public void testAttack_HealingAttack_XPAdded() {
        magicAttack.attack(npc1, npc2, npc3);
        healingAttack.attack(npc1, npc2, npc3);

        assertEquals(npc1.skills.getXP(FAITH), 30 * 2);
        assertEquals(npc1.skills.getXP(BLESSING), COST);
    }
    

    @Test
    public void testAttack_HarmingAttack_XPAdded() {
        magicAttack.attack(npc1, npc2, npc3);
        assertEquals(npc1.skills.getXP(APTITUDE), 30 * 2);
        assertEquals(npc1.skills.getXP(ARCANA), COST);

        
    }

    @Test
    public void testAttack_NoAttack_XPOnlyAddedForCost() {
        noAttack.attack(npc1, npc2, npc3);
        
        assertEquals(npc1.skills.getXP(STRENGTH), 0 * 2);
        assertEquals(npc1.skills.getXP(STAMINA), COST);
    }
}
