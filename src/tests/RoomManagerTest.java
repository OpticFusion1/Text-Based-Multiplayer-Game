package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.RoomManager;
import model.RoomNode;

/**
 * A class to test the room manager.
 *
 * @author Zachary Chandler
 */
public class RoomManagerTest {

    RoomManager rooms;
    
    @Before
    public void setUp() throws Exception {
        rooms = new RoomManager();
    }

    @Test
    public void testRoomManager() {
        assertNotEquals(rooms.getStartingRoom(), null);
    }

    @Test
    public void testNewRoom() {
        assertTrue(rooms.newRoom() instanceof RoomNode);
    }

    @Test
    public void testNewRoomStringString() {
        RoomNode room = rooms.newRoom("name", "description");
        
        assertEquals(room.getName(), "name");
        assertEquals(room.getDescription(), "description");
        assertEquals(rooms.getRoom(room.getRoomID()), room);
    }

    @Test
    public void testGetStartingRoom() {
        assertNotEquals(rooms.getStartingRoom(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetStartingRoom_InvalidID_IllegalArgumentException() {
        rooms.setStartingRoom(-5);
    }
    
    @Test
    public void testSetStartingRoom_ValidID_IsSet() {
        RoomNode room = rooms.newRoom("name", "description");
        
        rooms.setStartingRoom(room.getRoomID());
        assertEquals(room, rooms.getStartingRoom());
    }

    @Test
    public void testGetRoom_InvalidID_Null() {
        assertEquals(null, rooms.getRoom(2));
        assertEquals(null, rooms.getRoom(-5));
    }

    @Test
    public void testGetRoom_ValidID_CorrectRoom() {
        RoomNode room = rooms.newRoom("name", "description");

        assertEquals(room, rooms.getRoom(room.getRoomID()));
        assertEquals(rooms.getStartingRoom(), rooms.getRoom(rooms.getStartingRoom().getRoomID()));
    }

}
