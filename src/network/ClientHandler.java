package network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;

import commands.QuitCommand;
import console_gui.Console;
import console_gui.UserInformation;
import console_gui.UserInputScanner;
import model.RoomManager;

public class ClientHandler implements Runnable {

    private UserInformation info;
    
    private Socket soc;

    public ClientHandler(Socket user, RoomManager rm) throws IOException  {
        this.soc = user;
        
        UserInputScanner input = new UserInputScanner(user.getInputStream());
        PrintStream out = new PrintStream(user.getOutputStream(), true);
        
        this.info = new UserInformation(rm, out, input);
    }
    
    @Override
    public void run() {
        boolean closedGracefully;
        System.out.printf("Connected: %s\n", getConnectionName());
        
        try {
            Console.start(info);
            closedGracefully = true;
        } catch (NoSuchElementException e) {
            System.out.printf("%s has closed the connection\n", getConnectionName());
            QuitCommand.saveUser(info);
            closedGracefully = false;
        }

        if (closedGracefully) {
            System.out.printf("%s has logged out\n", getConnectionName());            
        }
        
        try { 
            soc.close();
        } catch (IOException e) {
            e.getMessage();
        }            
        
    }
    
    private String getConnectionName() {
        String name = info.getUsername();
        return name == null ? soc.toString() : name;
    }

}
