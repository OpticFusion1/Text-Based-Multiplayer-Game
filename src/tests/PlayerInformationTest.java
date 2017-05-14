package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.PlayerInformation;
import model.RoomNode;

public class PlayerInformationTest {

    PlayerInformation playerWithNulls;
    PlayerInformation playerWithBasicValues;
    
    RoomNode initialRoom;
    
    @Before
    public void setUp() throws Exception {
        initialRoom = new RoomNode(5, "name");
        
        playerWithNulls = new PlayerInformation(null, null);
        playerWithBasicValues = new PlayerInformation(initialRoom, "name");
    }

    @Test
    public void testPlayerInformation_Nulls() {
        assertEquals(playerWithNulls.getCurrentRoomID(), 0);
        assertEquals(playerWithNulls.getRoom(), null);
        assertEquals(playerWithNulls.getUsername(), null);
    }
    
    @Test
    public void testPlayerInformation_ValidPlayer() {
        assertEquals(playerWithBasicValues.getCurrentRoomID(), 5);
        assertEquals(playerWithBasicValues.getRoom(), initialRoom);
        assertEquals(playerWithBasicValues.getUsername(), "name");
    }

    @Test
    public void testSetUsername_ValidString() {
        playerWithNulls.setUsername("names");
        assertEquals(playerWithNulls.getUsername(), "names");
    }

    @Test
    public void testSetRoom() {
        playerWithNulls.setRoom(initialRoom);
        assertEquals(playerWithNulls.getRoom(), initialRoom);
    }

}
