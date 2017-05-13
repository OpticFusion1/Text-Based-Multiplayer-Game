package network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;

import commands.QuitCommand;
import console.Console;
import console.User;
import console.UserInputScanner;
import model.RoomManager;

public class ClientHandler implements Runnable {

    private User info;
    
    private Socket soc;

    public ClientHandler(Socket user, RoomManager rm) throws IOException  {
        this.soc = user;
        
        UserInputScanner input = new UserInputScanner(user.getInputStream());
        PrintStream out = new PrintStream(user.getOutputStream(), true);
        
        this.info = new User(rm, out, input);
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
            QuitCommand.saveUser(info);
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
    
    private String getConnectorName() {
        String name = info.getUsername();
        return name == null ? soc.toString() : name;
    }

}
