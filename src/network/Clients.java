package network;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import model.Universe;

/**
 * A class to keep track of clients.
 *
 * @author Zachary Chandler
 */
public class Clients {
    
    /** The currently running clients. */
    List<Client> clients;
    
    /** The universe of the clients. */
    private Universe u;
    
    
    /**
     * Create a set of clients that run on the given universe.
     * @param u the universe to run on.
     */
    public Clients(Universe u) {
        clients = new LinkedList<>();
        this.u = u;
    }
    
    /**
     * Add a new client and return a runnable for that client.
     * @param s the socket of the client.
     * @return a runnable that will run the client.
     * @throws IOException if an IOException occurs while creating the client.
     */
    public Runnable addClient(Socket s) throws IOException {
        Client c = new Client(s, u);
        clients.add(c);
        
        return ()-> {
            c.run();
            clients.remove(c);
        };
        
    }
    
    /**
     * Close all currently running clients.
     */
    public void closeClients() {
        for (Client c : clients) {
            c.close();
        }
    }
    
}
