package console_gui;

import java.util.TreeSet;

import model.RoomNode;

/**
 * Some test code that could end up being useful later on.
 * 
 * @author Zachary Chandler
 */
public class Helper {
    
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
    
    public static Integer safeParseInt(String stringInt) {
        Integer choice;
        
        try {
            choice = Integer.parseInt(stringInt);
        } catch (NumberFormatException e) {
            choice = null;
        }
        
        return choice;
    }
    
    public static String mergeStrings(String[] strings, int start, int end) {
        if (end >= strings.length || start < 0 || start >= strings.length) {
            throw new IllegalArgumentException();
        }
        
        StringBuilder name = new StringBuilder();
        
        if (strings.length > 0) {
            name.append(strings[start]);
            for (int i = start + 1; i <= end; i++) {
                name.append(" ");
                name.append(strings[i]);
            }            
        }
        
        return name.toString();
    }

}
