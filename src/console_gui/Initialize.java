package console_gui;

import model.RoomManager;
import model.RoomNode;
import model.SerializationHelper;

/**
 * Initializes a simple save file of the room manager.
 * 
 * @author Zachary Chandler
 */
public class Initialize {

    /* TODO
     * add better documentation
     * add check for connect so it doesn't overwrite a room
     * add check for dig so it doesn't overwrite a room
     * test initial use
     * add more tests?
     * 1.0!
     */
    public static void main(String[] args) {
        RoomNode startingRoom = new RoomNode(0, "The Void", "Emptiness without space.");
        RoomManager roomManager = new RoomManager(startingRoom);
        
        boolean saved = SerializationHelper.saveRoomManager(roomManager);
        
        if (saved) {
            System.out.println("Sucessfully Initialized a Save File!");
        } else {
            System.err.println("Failed to Initialize a Save File!");
        }
    }

}
