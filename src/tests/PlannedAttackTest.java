package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Character;
import model.PlannedAttack;

public class PlannedAttackTest {

    PlannedAttack attack;
    
    long creationTime;
    
    long delay;
    
    @Before
    public void setUp() throws Exception {
        delay = 3;
        creationTime = System.currentTimeMillis();
        attack = new PlannedAttack(delay){ @Override public void attack(Character attacker, Character... victims) { } };
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPlannedAttackLong_NegativeDelay_IllegalArgumentException() {
        new PlannedAttack(-1){ @Override public void attack(Character attacker, Character... victims) { } };
    }

    @Test
    public void testPlannedAttackLong_ZeroDelay_NoExceptions() {
        new PlannedAttack(0){ @Override public void attack(Character attacker, Character... victims) { } };
    }
    
    @Test
    public void testShouldAttackLong_OnTimeOfAttack_True() {
        assertTrue(attack.shouldAttack(delay + creationTime));
    }

    @Test
    public void testShouldAttackLong_OneMillisAfterTimeOfAttack_True() {
        assertTrue(attack.shouldAttack(delay + creationTime + 1));
    }
    
    @Test
    public void testShouldAttackLong_OneMilliBeforeTimeOfAttack_True() {
        assertFalse(attack.shouldAttack(delay + creationTime - 1));
    }

    @Test
    public void testShouldAttackLongLong_OneMilliAfterTime_True() {
        assertTrue(PlannedAttack.shouldAttack(creationTime, creationTime + 1));
    }

    @Test
    public void testShouldAttackLongLong_OneMilliBeforeTime_False() {
        assertFalse(PlannedAttack.shouldAttack(creationTime, creationTime - 1));
    }
    
    @Test
    public void testShouldAttackLongLong_AtSameTime_True() {
        assertTrue(PlannedAttack.shouldAttack(creationTime, creationTime));
    }
}
