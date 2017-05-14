package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Character;
import model.RoomNode;

public class PlayerInformationTest {

    Character playerWithNulls;
    Character playerWithBasicValues;
    
    RoomNode initialRoom;
    
    @Before
    public void setUp() throws Exception {
        initialRoom = new RoomNode(5, "name");
        
        playerWithNulls = new Character(null, null);
        playerWithBasicValues = new Character(initialRoom, "name");
    }

    @Test
    public void testPlayerInformation_Nulls() {
        assertEquals(playerWithNulls.getCurrentRoomID(), 0);
        assertEquals(playerWithNulls.getRoom(), null);
        assertEquals(playerWithNulls.getName(), null);
    }
    
    @Test
    public void testPlayerInformation_ValidPlayer() {
        assertEquals(playerWithBasicValues.getCurrentRoomID(), 5);
        assertEquals(playerWithBasicValues.getRoom(), initialRoom);
        assertEquals(playerWithBasicValues.getName(), "name");
    }

    @Test
    public void testSetUsername_ValidString() {
        playerWithNulls.setName("names");
        assertEquals(playerWithNulls.getName(), "names");
    }

    @Test
    public void testSetRoom() {
        playerWithNulls.setRoom(initialRoom);
        assertEquals(playerWithNulls.getRoom(), initialRoom);
    }

}
