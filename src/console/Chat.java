package console;

/**
 * A class to handle chat and player messages.
 *
 * @author Zachary Chandler
 */
public class Chat {
    
    /** The players in the game. */
    private final PlayerManager players;

    /**
     * Instantiates a new chat for a player manager.
     * @param players the players in the game.
     */
    public Chat(PlayerManager players) {
        this.players = players;
    }
    
    /**
     * Prints a string to the room.
     * @param info the user who's room we will print to.
     * @param message the message to display to the room.
     */
    public void printlnToRoom(User info, String message) {
        for (User p : players.getPlayers(info.getCurrentRoom())) {
            p.println(message);
        }
    }
    
    /**
     * Prints a string to the room excluding the given player.
     * @param info the player in question.
     * @param message the string to display.
     */
    public void printlnToOthersInRoom(User info, String message) {
        for (User p : players.getPlayers(info.getCurrentRoom())) {
            if (p != info) {
                p.println(message);                
            }
        }
    }
    
}
