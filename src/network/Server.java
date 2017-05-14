package network;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import model.RoomManager;
import model.SerializationHelper;

public class Server {

    public static final int PORT = 2222;
    
    
    
    public static void main(String[] args) throws IOException {
        
        // Initialize data
        RoomManager rm =  SerializationHelper.loadRoomManager();
        if (rm == null) {
            System.err.println("Failed to Load System!");
            return;
        } else {
            rm.addAllConnectedRooms();
            System.out.println("Loaded System.");
        }

        // Start server
        final ThreadPoolExecutor threads = (ThreadPoolExecutor) Executors.newFixedThreadPool(51);
        final ServerHandler server = new ServerHandler(threads, rm, PORT);
        threads.execute(server);
        
        // Start handling administrative commands
        Scanner adminInput = new Scanner(System.in);
        if (adminInput.hasNextLine()) {
            adminInput.nextLine();            
        }
        
        // Save data
        SerializationHelper.saveRoomManager(rm);
        System.out.println("Saved System.");

        // Close connections
        server.close();
        ClientHandler.close();
        adminInput.close();
        threads.shutdown();
    }

}
