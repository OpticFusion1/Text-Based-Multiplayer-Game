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
        simpleRoom = new RoomNode(0, "The Void");
        simpleRoom.setDirection(Direction.DOWN, new RoomNode(1, "The Sky" ));
        simpleRooms = new RoomManager(simpleRoom);
    }

    @Test
    public void Constructor_MultipleRoomsBeforeCall_AllRoomsTracked() {
        assertEquals("Room manager should be able to lookup rooms that already exist!",
                 simpleRooms.getRoom(1), simpleRoom.getDirection(Direction.DOWN));
    }

    @Test
    public void trackRoom_NodeInGraph_IsTracked() {
        simpleRoom.setDirection(Direction.UP, new RoomNode(2, "More Void", "A description"));
        simpleRooms.trackRoom(simpleRoom.getDirection(Direction.UP));
        
        assertEquals("Room manager should be able to track rooms that are connected to the graph!",
                 simpleRooms.getRoom(2), simpleRoom.getDirection(Direction.UP));
    }
    

    @Test
    public void trackRoom_NodeInGraphButOverwrittingID_IsNotTracked() {
        simpleRoom.setDirection(Direction.UP, new RoomNode(1, "More Void", "A description"));
        simpleRooms.trackRoom(simpleRoom.getDirection(Direction.UP));
        
        assertEquals("Room manager should not lose track of nodes with invalid IDs!",
                 simpleRooms.getRoom(1), simpleRoom.getDirection(Direction.DOWN));
    }

    @Test
    public void addAllConnectedRooms_NewRoomsInEachDirection_AllNewRoomsAdded() {
        simpleRoom.setDirection(Direction.UP, new RoomNode(2, "More Void"));
        simpleRoom.setDirection(Direction.NORTH, new RoomNode(3, "More Void" ));
        simpleRoom.setDirection(Direction.EAST, new RoomNode(4, "More Void"));
        simpleRoom.setDirection(Direction.SOUTH, new RoomNode(5, "More Void"));
        simpleRoom.setDirection(Direction.WEST, new RoomNode(6, "More Void"));

        simpleRooms.addAllConnectedRooms();
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(2), simpleRoom.getDirection(Direction.UP));
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(3), simpleRoom.getDirection(Direction.NORTH));
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(4), simpleRoom.getDirection(Direction.EAST));
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(5), simpleRoom.getDirection(Direction.SOUTH));
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(6), simpleRoom.getDirection(Direction.WEST));
    }

}
