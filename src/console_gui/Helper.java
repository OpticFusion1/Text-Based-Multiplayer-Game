package console_gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

import model.RoomNode;

/**
 * A helper class for some more obscure methods that can be re-used.
 * 
 * @author Zachary Chandler
 */
public class Helper {
    
    /**
     * Prints all rooms that are connected to node.
     * 
     * @param node the starting room.
     */
    public static void printAllConnectedRoomNodes(RoomNode node) {
        printAllConnectedRoomNodes(node, new TreeSet<>());
    }
    
    /**
     * The recursive method for printing all rooms.
     * @param node the current node.
     * @param seenNodes the nodes we have already seen.
     */
    private static void printAllConnectedRoomNodes(RoomNode node, TreeSet<RoomNode> seenNodes) {
        if (node != null && !seenNodes.contains(node)) {
            System.out.println(node.getRoomID() + ": " + node.getName());
            seenNodes.add(node);
            
            printAllConnectedRoomNodes(node.getUp(), seenNodes);
            printAllConnectedRoomNodes(node.getDown(), seenNodes);
            printAllConnectedRoomNodes(node.getNorth(), seenNodes);
            printAllConnectedRoomNodes(node.getWest(), seenNodes);
            printAllConnectedRoomNodes(node.getEast(), seenNodes);
            printAllConnectedRoomNodes(node.getSouth(), seenNodes);
        }
    }
    
    /**
     * Parses an Integer without throwing exceptions. If one couldn't be parsed, a null is returned.
     * 
     * @param stringInt the string to attempt to parse.
     * @return the integer representation of the string, or null if there isn't one.
     */
    public static Integer safeParseInt(String stringInt) {
        Integer choice;
        
        try {
            choice = Integer.parseInt(stringInt);
        } catch (NumberFormatException e) {
            choice = null;
        }
        
        return choice;
    }
    
    /**
     * Merges an array of strings into a single string for the given starting and ending values in the array. The result
     * will be all of the strings from strings[start] through strings[end] inclusively.
     * 
     * @param strings the array of strings to merge.
     * @param start the first element in the array to merge.
     * @param end the final element in the array to merge.
     * @return the string of the given elements merged togeather.
     */
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

    
    /**
     * Reads a file as a string and returns it. theFile needs to exist, not a directory and have read access.
     * @param theFile the file to read from.
     * @return the contents of the file in a string.
     */
    public static String readFileAsString(File theFile) {
        StringBuilder result = new StringBuilder();
        Scanner input = null;
        
        try {
            input = new Scanner(theFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: attempted to read from invalid file.");
            return "INVALID FILE LOADED";
        }
        
        
        while (input.hasNextLine()) {
            result.append('\n');
            result.append(input.nextLine());
        }
        
        input.close();
        return result.toString();
    }
    
    public static String buildString(String... args) {
        StringBuilder result = new StringBuilder();
        
        for (String s : args) {
            result.append(s);
        }
        
        return result.toString();
    }
}
