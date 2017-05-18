package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Character;
import model.DamageType;
import model.NonPlayerCharacter;
import model.RoomManager;
import model.RoomNode;

/**
 * A class to test the character class.
 *
 * @author Zachary Chandler
 */
public class CharacterTest {
    RoomManager rooms;
    Character normalCharacter;

    @Before
    public void setUp() throws Exception {
        rooms = new RoomManager();
        normalCharacter = new NonPlayerCharacter(rooms.getStartingRoom(), "Aname");
    }

    @Test
    public void testCharacterRoomNodeString_NormalCharacter_ValidValues() {
        assertEquals(normalCharacter.getCurrentRoomID(), 0);
        assertEquals(normalCharacter.getRoom(), rooms.getStartingRoom());
        assertEquals(normalCharacter.getName(), "Aname");
        assertEquals(normalCharacter.getUpperCaseName(), "ANAME");
        assertEquals(rooms.getStartingRoom().getCharacters().size(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void testCharacterRoomNodeString_NullName_NullPointerException() {
        new NonPlayerCharacter(rooms.getStartingRoom(), null);
    }
    
    @Test
    public void testCharacterRoomNodeString_NullRoom_ValidButNullRoom() {
        Character c = new NonPlayerCharacter(null, "Aname");
        
        assertEquals(c.getCurrentRoomID(), 0);
        assertEquals(c.getRoom(), null);
        assertEquals(c.getName(), "Aname");
        assertEquals(c.getUpperCaseName(), "ANAME");
        assertEquals(rooms.getStartingRoom().getCharacters().size(), 1);
    }

    @Test
    public void testSetName_ValidName_NameChanged() {
        normalCharacter.setName("ADifferentName");
        assertEquals(normalCharacter.getName(), "ADifferentName");
        assertEquals(normalCharacter.getUpperCaseName(), "ADifferentName".toUpperCase());
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetName_NullName_NullPointerException() {
        normalCharacter.setName(null);
    }

    @Test
    public void testSetRoom() {
        RoomNode newRoom = rooms.newRoom();
        
        normalCharacter.setRoom(newRoom);

        assertEquals(normalCharacter.getCurrentRoomID(), 1);
        assertEquals(normalCharacter.getRoom(), newRoom);
        assertEquals(0, rooms.getStartingRoom().getCharacters().size());
        assertEquals(1, newRoom.getCharacters().size());
    }

    @Test
    public void testRemoveFromCurrentRoom() {
        normalCharacter.removeFromCurrentRoom();
        
        assertEquals(normalCharacter.getCurrentRoomID(), 0);
        assertEquals(normalCharacter.getRoom(), null);
    }

    @Test
    public void testGetHealth_InitialHealth_NonZero() {
    	assertNotEquals(0, normalCharacter.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testHarm_NullDamageType_NullPointerException() {
    	normalCharacter.harm(null, normalCharacter, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testHarm_NullHarmer_NullPointerException() {
    	normalCharacter.harm(DamageType.PHYSICAL, null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHarm_NegativeDamage_IllegalArgumentException() {
    	normalCharacter.harm(DamageType.PHYSICAL, normalCharacter, -1);
    }
    
    @Test
    public void testHarm_ZeroDamage_NoDamageTaken() {
    	assertEquals(0, normalCharacter.harm(DamageType.PHYSICAL,
    						normalCharacter, 0));
    }
    
    @Test
    public void testHarm_FiveDamageOnNakedCharacter_DamageTakenInResult() {
    	assertEquals(5, normalCharacter.harm(DamageType.PHYSICAL,
    						normalCharacter, 5));
    }

    @Test
    public void testHarm_FiveDamageOnNakedCharacter_HealthIsExactlyFiveLower() {
        int health = normalCharacter.getHealth();
        
        normalCharacter.harm(DamageType.PHYSICAL, normalCharacter, 5);
        
        assertEquals(health - 5, normalCharacter.getHealth());
    }

    @Test
    public void testHarm_OverCurrentHealth_HealthStopsAtZero() {
        int health = normalCharacter.getHealth();
        
        normalCharacter.harm(DamageType.PHYSICAL, normalCharacter, health + health);
        
        assertEquals(0, normalCharacter.getHealth());
    }

    @Test
    public void testHarm_OnCurrentHealth_HealthStopsAtZero() {
        int health = normalCharacter.getHealth();
        
        normalCharacter.harm(DamageType.PHYSICAL, normalCharacter, health);
        
        assertEquals(0, normalCharacter.getHealth());
    }
}
