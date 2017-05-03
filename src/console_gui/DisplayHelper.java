package console_gui;

import java.util.TreeSet;

import model.RoomNode;

public class DisplayHelper {
    
    public static void printAllRoomNodes(RoomNode node) {
        printAllRoomNodes(node, new TreeSet<>());
    }
    
    private static void printAllRoomNodes(RoomNode node, TreeSet<RoomNode> seenNodes) {
        if (node != null && !seenNodes.contains(node)) {
            System.out.println(node.getRoomID() + ": " + node.getDescription());
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
