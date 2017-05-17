package network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;

import console.Console;
import console.User;
import console.UserInputScanner;
import model.Universe;

/**
 * The client thread that will run for a given user.
 *
 * @author Zachary Chandler
 */
public class Client implements Runnable {

    /** The user to run for. */
    private User info;
    
    /** The actual socket of the user. */
    private Socket soc;

    /**
     * Start a user with the given socket on the given universe.
     * @param user the socket of the connection.
     * @param u the Universe.
     * @throws IOException if an IO exception occurs.
     */
    public Client(Socket user, Universe u) throws IOException  {
        this.soc = user;
        
        UserInputScanner input = new UserInputScanner(user.getInputStream());
        PrintStream out = new PrintStream(user.getOutputStream(), true);
        
        this.info = new User(u, out, input);
    }
    
    @Override
    public void run() {
        boolean closedGracefully;
        System.out.printf("Connected: %s\n", getConnectorName());
        
        try {
            Console.start(info);
            closedGracefully = true;
        } catch (NoSuchElementException e) {
            System.out.printf("%s has closed the connection\n", getConnectorName());
            closedGracefully = false;
        }

        if (closedGracefully) {
            System.out.printf("%s has logged out\n", getConnectorName());            
        }
        
        try { 
            soc.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * @return the name of the connection or the IP if the user hasn't logged in yet.
     */
    private String getConnectorName() {
        String name = info.getUsername();
        return name == null ? soc.toString() : name;
    }
    
    /**
     * Close the current client.
     */
    public void close() {
        try {
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
