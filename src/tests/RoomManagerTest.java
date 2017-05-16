package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.RoomManager;
import model.RoomNode;

public class RoomManagerTest {

    private RoomManager simpleRooms;
    private RoomNode simpleRoom;
    
    @Before
    public void setup() {
        simpleRooms = new RoomManager();
        simpleRoom = simpleRooms.newRoom();
        simpleRoom.setDirection(Direction.DOWN, simpleRooms.newRoom());
    }

    @Test
    public void test() {
        fail();
    }
    
    /*@Test
    public void Constructor_MultipleRoomsBeforeCall_AllRoomsTracked() {
        assertEquals("Room manager should be able to lookup rooms that already exist!",
                 simpleRooms.getRoom(1), simpleRoom.getDirection(Direction.DOWN));
    }

    @Test
    public void trackRoom_NodeInGraph_IsTracked() {
        simpleRoom.setDirection(Direction.UP, simpleRooms.newRoom());
        assertEquals("Room manager should be able to track rooms that are connected to the graph!",
                 simpleRooms.getRoom(2), simpleRoom.getDirection(Direction.UP));
    }
    

    @Test
    public void trackRoom_NodeInGraphButOverwrittingID_IsNotTracked() {
        simpleRoom.setDirection(Direction.UP, simpleRooms.newRoom());
        assertEquals("Room manager should not lose track of nodes with invalid IDs!",
                 simpleRooms.getRoom(1), simpleRoom.getDirection(Direction.DOWN));
    }

    @Test
    public void addAllConnectedRooms_NewRoomsInEachDirection_AllNewRoomsAdded() {
        for (Direction d : Direction.values()) {
            simpleRoom.setDirection(d, simpleRooms.newRoom());
        }
        
        for (Direction d : Direction.values()) {
            assertEquals("Room manager didn't add all connected nodes!", 
                    simpleRooms.getRoom(simpleRoom.getDirection(d).getRoomID()),
                    simpleRoom.getDirection(d));
        }
    }*/

}
