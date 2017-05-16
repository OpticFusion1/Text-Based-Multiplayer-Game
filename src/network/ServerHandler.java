package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import model.Universe;

public class ServerHandler implements Runnable {

    private ServerSocket server;
    private ThreadPoolExecutor threads;
    //private RoomManager rooms;
    private Clients clients;

    public ServerHandler(ThreadPoolExecutor threads, Universe u, int port) throws IOException {
        this.server = new ServerSocket(port);
        this.threads = threads;
        //this.rooms = rooms;
        this.clients = new Clients(u);
    }
    
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
     * Shutdown the server and all connections.
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
