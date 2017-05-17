package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import model.Universe;

/**
 * A server thread to listen to incoming connections and start client threads.
 *
 * @author Zachary Chandler
 */
public class Server implements Runnable {

    /** The ServerSocket. */
    private ServerSocket server;
    
    /** The threads to add to. */
    private ThreadPoolExecutor threads;
    
    /** The clients of this server. */
    private Clients clients;

    /**
     * Initializes a ServerSocket for the given universe, on the given port, with a given thread pool to add to. This
     * constructor does not start the server, it only initializes all of the variables required for the server.
     * 
     * @param threads the threads the clients of the server will run on.
     * @param u the universe the clients will reside in.
     * @param port the port the server will listen on.
     * @throws IOException if an error occurs while starting the server socket.
     */
    public Server(ThreadPoolExecutor threads, Universe u, int port) throws IOException {
        this.server = new ServerSocket(port);
        this.threads = threads;
        this.clients = new Clients(u);
    }
    
    /**
     * Runs the server. Starts listening for incoming connections and starts the given clients.
     */
    @Override
    public void run() {
        System.out.printf("Opening server on port %s\n", server.getLocalPort());
        
        while (true) {
            Socket client;
            try {
                client = server.accept();
            } catch (IOException e1) {
                System.out.println("Closing port listener.");
                return;
            }
            
            try {
                if (threads.getPoolSize() < threads.getMaximumPoolSize()) {
                    threads.execute(clients.addClient(client));                    
                } else {
                    client.close();
                }
            } catch (IOException e) {
                System.out.printf("Unable to start client handler for incoming connection: %s\n", client);
            }                
        }
    }

    /**
     * Shutdown the server and all client connections.
     */
    public void close() {
        try {
            server.close();
        } catch (IOException e) {
            System.out.println("I/O Exception while closing server.");
            return;
        }
        clients.closeClients();
        
        System.out.println("Closing server.");
    }

}
