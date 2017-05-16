package network;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import model.SerializationHelper;
import model.Universe;

public class Server {

    public static final int PORT = 2222;
    
    public static void main(String[] args) throws IOException {
        
        // Initialize data
        Universe u =  SerializationHelper.loadUniverse();
        if (u == null) {
            System.err.println("Failed to Load System!");
            return;
        } else {
            u.rooms.addAllConnectedRooms();
            System.out.println("Loaded System.");
        }

        // Start server
        final ThreadPoolExecutor threads = (ThreadPoolExecutor) Executors.newFixedThreadPool(51);
        final ServerHandler server = new ServerHandler(threads, u, PORT);
        threads.execute(server);
        
        // Start handling administrative commands
        Scanner adminInput = new Scanner(System.in);
        if (adminInput.hasNextLine()) {
            adminInput.nextLine();            
        }
        
        // Save data
        u.entities.purgeTrackedPlayers();
        SerializationHelper.saveUniverse(u);
        System.out.println("Saved Universe.");

        // Close connections
        server.close();
        adminInput.close();
        threads.shutdown();
    }

}
