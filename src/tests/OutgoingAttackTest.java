package tests;

import static model.DamageType.FIRE;
import static model.DamageType.LIGHT;
import static model.DamageType.PHYSICAL;
import static model.Skill.APTITUDE;
import static model.Skill.ARCANA;
import static model.Skill.BLESSING;
import static model.Skill.FAITH;
import static model.Skill.STAMINA;
import static model.Skill.STRENGTH;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Character;
import model.NonPlayerCharacter;
import model.OutgoingAttack;
import model.RoomManager;
import model.StraightAttack;

public class OutgoingAttackTest {
    public static final int DAMAGE = 30;
    public static final int COST = 40;
    
    long creationTime;
    long delay;
    
    OutgoingAttack attack;
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

        delay = 3;
        creationTime = System.currentTimeMillis();
        attack = new OutgoingAttack(delay, magicAttack, npc1, npc2, npc3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlannedAttackLong_NegativeDelay_IllegalArgumentException() {
        new OutgoingAttack(-1, magicAttack, npc1, npc2, npc3);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPlannedAttackLong_NullAttack_NullPointerException() {
        new OutgoingAttack(-1, null, npc1, npc2, npc3);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPlannedAttackLong_NullCaster_NullPointerException() {
        new OutgoingAttack(-1, magicAttack, null, npc2, npc3);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPlannedAttackLong_NullOther_NullPointerException() {
        new OutgoingAttack(-1, magicAttack, npc1, null, npc3);
    }

    @Test
    public void testPlannedAttackLong_ZeroDelay_NoExceptions() {
        new OutgoingAttack(0, magicAttack, npc1, npc2,  npc3);
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
        assertTrue(OutgoingAttack.shouldAttack(creationTime, creationTime + 1));
    }

    @Test
    public void testShouldAttackLongLong_OneMilliBeforeTime_False() {
        assertFalse(OutgoingAttack.shouldAttack(creationTime, creationTime - 1));
    }
    
    @Test
    public void testShouldAttackLongLong_AtSameTime_True() {
        assertTrue(OutgoingAttack.shouldAttack(creationTime, creationTime));
    }
}
