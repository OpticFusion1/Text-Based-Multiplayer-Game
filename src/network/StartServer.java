package network;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import model.SerializationHelper;
import model.Universe;

/**
 * A class to start the server.
 *
 * @author Zachary Chandler
 */
public class StartServer {

    /** The port for the server to listen on. */
    public static final int PORT = 2222;
    
    /**
     * The main method to start the server.
     * @param args the arguments of the program. none do anything.
     * @throws IOException if an IOException occurs while starting the server.
     */
    public static void main(String[] args) throws IOException {
        
        // Initialize data
        Universe u =  SerializationHelper.loadUniverse();
        if (u == null) {
            System.err.println("Failed to Load System!");
            return;
        } else {
            System.out.println("Loaded System.");
        }

        // Start server
        final ThreadPoolExecutor threads = (ThreadPoolExecutor) Executors.newFixedThreadPool(51);
        final Server server = new Server(threads, u, PORT);
        threads.execute(server);
        
        // Start handling administrative commands
        Scanner adminInput = new Scanner(System.in);
        if (adminInput.hasNextLine()) {
            adminInput.nextLine();            
        }
        
        // Save data
        SerializationHelper.saveUniverse(u);
        System.out.println("Saved Universe.");

        // Close connections
        server.close();
        adminInput.close();
        threads.shutdown();
    }

}
