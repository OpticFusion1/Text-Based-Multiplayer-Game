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
     * 
     * Refactoring
     * add descriptions to objects
     * 
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
