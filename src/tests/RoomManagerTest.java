package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.RoomManager;
import model.RoomNode;

public class RoomManagerTest {

    private RoomManager simpleRooms;
    private RoomNode simpleRoom;
    
    @Before
    public void setup() {
        simpleRoom = new RoomNode(0, "The Void");
        simpleRoom.setDown(new RoomNode(1, "The Sky" ));
        simpleRooms = new RoomManager(simpleRoom);
    }

    @Test
    public void Constructor_MultipleRoomsBeforeCall_AllRoomsTracked() {
        assertEquals("Room manager should be able to lookup rooms that already exist!",
                 simpleRooms.getRoom(1), simpleRoom.getDown());
    }

    @Test
    public void trackRoom_NodeInGraph_IsTracked() {
        simpleRoom.setUp(new RoomNode(2, "More Void", null));
        simpleRooms.trackRoom(simpleRoom.getUp());
        
        assertEquals("Room manager should be able to track rooms that are connected to the graph!",
                 simpleRooms.getRoom(2), simpleRoom.getUp());
    }
    

    @Test
    public void trackRoom_NodeInGraphButOverwrittingID_IsNotTracked() {
        simpleRoom.setUp(new RoomNode(1, "More Void", null));
        simpleRooms.trackRoom(simpleRoom.getUp());
        
        assertEquals("Room manager should not lose track of nodes with invalid IDs!",
                 simpleRooms.getRoom(1), simpleRoom.getDown());
    }

    @Test
    public void addAllConnectedRooms_NewRoomsInEachDirection_AllNewRoomsAdded() {
        simpleRoom.setUp(new RoomNode(2, "More Void"));
        simpleRoom.setNorth(new RoomNode(3, "More Void" ));
        simpleRoom.setEast(new RoomNode(4, "More Void"));
        simpleRoom.setSouth(new RoomNode(5, "More Void"));
        simpleRoom.setWest(new RoomNode(6, "More Void"));

        simpleRooms.addAllConnectedRooms();
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(2), simpleRoom.getUp());
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(3), simpleRoom.getNorth());
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(4), simpleRoom.getEast());
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(5), simpleRoom.getSouth());
        assertEquals("Room manager didn't add all connected nodes!", simpleRooms.getRoom(6), simpleRoom.getWest());
    }

}
