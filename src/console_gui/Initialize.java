package console_gui;

import model.RoomManager;
import model.RoomNode;
import model.SerializationHelper;

public class Initialize {

    public static void main(String[] args) {
        
        RoomNode startingRoom = new RoomNode(0, "The Void: emptiness without space.");
        RoomManager roomManager = new RoomManager(startingRoom);
        CurrentInformation info = new CurrentInformation(roomManager, System.out);
        
        boolean saved = SerializationHelper.saveObject(info, SerializationHelper.QUICK_SAVE_LOCATION.toString());
        
        if (saved) {            
            System.out.println("Sucessfully Initialized a Save File!");
        }
    }

}
