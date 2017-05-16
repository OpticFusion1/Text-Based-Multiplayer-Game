package network;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import model.RoomManager;

public class Clients {
    List<ClientHandler> clients;
    private RoomManager rooms;
    
    
    public Clients(RoomManager rooms) {
        clients = new LinkedList<>();
        this.rooms = rooms;
    }
    
    public Runnable addClient(Socket s) throws IOException {
        ClientHandler c = new ClientHandler(s, rooms);
        clients.add(c);
        
        return ()-> {
            c.run();
            clients.remove(c);
        };
        
    }
    
    public void closeClients() {
        for (ClientHandler c : clients) {
            c.close();
        }
    }
    
}
