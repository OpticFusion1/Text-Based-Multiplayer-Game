package model;

/**
 * An enum to describe the different directions each RoomNode can hold.
 * @author Zachary Chandler
 */
public enum Direction {
    UP, DOWN, NORTH, EAST, SOUTH, WEST;
    
    public final String lowercaseName = this.toString().toLowerCase();
    
    
    
    /**
     * Translate the given string into a direction.
     * @param directionAsString.
     * @return the direction the string describes, or null if it isn't a direction.
     */
    public static Direction translateDirection(String directionAsString) {
        Direction result;
        
        switch (directionAsString.toUpperCase()) {
        case "D":
        case "DOWN":
            result = Direction.DOWN;
            break;

        case "U":
        case "UP":
            result = Direction.UP;
            break;

        case "N":
        case "NORTH":
            result = Direction.NORTH;
            break;

        case "E":
        case "EAST":
            result = Direction.EAST;
            break;

        case "S":
        case "SOUTH":
            result = Direction.SOUTH;
            break;

        case "W":
        case "WEST":
            result = Direction.WEST;
            break;
            
        default:
            result = null;
        }
        
        return result;
    }
    
    /**
     * Get the opposite direction of theDirection
     * @param theDirection
     * @return the opposite direction or null if theDirection is null
     */
    public static Direction getOppositeDirection(Direction theDirection) {
        switch (theDirection) {
        case UP:    return DOWN;
        case DOWN:  return UP;
        case NORTH: return SOUTH;
        case SOUTH: return NORTH;
        case EAST:  return WEST;
        case WEST:  return EAST;
        default:    return null;
        }
    }
}
