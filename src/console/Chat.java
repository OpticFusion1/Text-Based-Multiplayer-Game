package console;

/**
 * A class to handle chat and player messages.
 *
 * @author Zachary Chandler
 */
public class Chat {

    private User info;

    /**
     * Instantiates a new chat for a player manager.
     * @param info the current user.
     */
    public Chat(User info) {
        this.info = info;
    }
    
    /**
     * Prints a string to the room.
     * @param message the message to display to the room.
     */
    public void printlnToRoom(String message) {
        for (User p : info.getPlayersInRoom()) {
            p.println(message);
        }
    }
    
    /**
     * Prints a string to the room excluding the given player.
     * @param message the string to display.
     */
    public void printlnToOthersInRoom(String message) {
        for (User p : info.getPlayersInRoom()) {
            if (p != info) {
                p.println(message);
            }
        }
    }
    
}
