package console_gui;

import java.util.TreeSet;

import model.RoomNode;

/**
 * Some test code that could end up being useful later on.
 * 
 * @author Zachary Chandler
 */
public class DisplayHelper {
    
    public static void printAllRoomNodes(RoomNode node) {
        printAllRoomNodes(node, new TreeSet<>());
    }
    
    private static void printAllRoomNodes(RoomNode node, TreeSet<RoomNode> seenNodes) {
        if (node != null && !seenNodes.contains(node)) {
            System.out.println(node.getRoomID() + ": " + node.getName());
            seenNodes.add(node);
            
            printAllRoomNodes(node.getUp());
            printAllRoomNodes(node.getDown());
            printAllRoomNodes(node.getNorth());
            printAllRoomNodes(node.getWest());
            printAllRoomNodes(node.getEast());
            printAllRoomNodes(node.getSouth());
        }
    }

}
