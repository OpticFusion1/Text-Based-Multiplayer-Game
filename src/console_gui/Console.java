package console_gui;

import java.util.Scanner;

import model.RoomNode;
import model.SerializationHelper;

public class Console {
    private static CurrentInformation info;
    private static Scanner input;
    
    public static void main(String[] args) {
        info = (CurrentInformation) SerializationHelper.loadObject(SerializationHelper.QUICK_SAVE_LOCATION.toString());
        input = new Scanner(System.in);
        
        if (info == null) {
            System.out.println("Failed to load Save File!");
            return;
        }
        
        System.out.println(info.getCurrentRoom().getDescription());
        
        while(processInput());

        System.out.println("Thank you for experiencing Node Traversing Simulator 2017");
        
        SerializationHelper.saveObject(info, SerializationHelper.QUICK_SAVE_LOCATION.toString());
        
        System.out.println("Sucessfully Saved, now exiting...");
    }
    
    
    public static boolean processInput() {
        boolean result = true;
        
        System.out.print(">");
        
        String command = input.nextLine().toUpperCase();
        
        switch (command) {
        case "Q":
        case "QUIT":
            result = false;
            break;

        case "U":
        case "UP":
            moveTo(info.getCurrentRoom().getUp());
            break;

        case "D":
        case "DOWN":
            moveTo(info.getCurrentRoom().getDown());
            break;
            
        case "N":
        case "NORTH":
            moveTo(info.getCurrentRoom().getNorth());
            break;

        case "S":
        case "SOUTH":
            moveTo(info.getCurrentRoom().getSouth());
            break;

        case "E":
        case "EAST":
            moveTo(info.getCurrentRoom().getEast());
            break;

        case "W":
        case "WEST":
            moveTo(info.getCurrentRoom().getWest());
            break;
            
        case "L":
        case "LOOK":
            System.out.println(info.getCurrentRoom().getDescription());
            break;
            
        default:
            System.out.println("Command not found!");
        }
        
        return result;
    }
    
    public static void moveTo(RoomNode node) {
        if (node == null) {
            System.out.println("You cannot go that direction.");
        } else {
            info.setCurrentRoom(node);
            System.out.println(info.getCurrentRoom().getDescription());
        }
    }

}
