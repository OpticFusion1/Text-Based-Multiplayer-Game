package network;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import model.Universe;

public class Clients {
    List<ClientHandler> clients;
    private Universe u;
    
    
    public Clients(Universe u) {
        clients = new LinkedList<>();
        this.u = u;
    }
    
    public Runnable addClient(Socket s) throws IOException {
        ClientHandler c = new ClientHandler(s, u);
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
