package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Direction;

public class DirectionTest {

    @Test
    public void oppositeDirection() {
        assertEquals(Direction.DOWN, Direction.getOppositeDirection(Direction.UP));
        assertEquals(Direction.UP, Direction.getOppositeDirection(Direction.DOWN));
        assertEquals(Direction.EAST, Direction.getOppositeDirection(Direction.WEST));
        assertEquals(Direction.WEST, Direction.getOppositeDirection(Direction.EAST));
        assertEquals(Direction.NORTH, Direction.getOppositeDirection(Direction.SOUTH));
        assertEquals(Direction.SOUTH, Direction.getOppositeDirection(Direction.NORTH));
    }
    
    @Test
    public void lowerCaseName() {
        assertEquals(Direction.DOWN.lowercaseName, Direction.DOWN.toString().toLowerCase());
        assertEquals(Direction.UP.lowercaseName, Direction.UP.toString().toLowerCase());
        assertEquals(Direction.NORTH.lowercaseName, Direction.NORTH.toString().toLowerCase());
        assertEquals(Direction.SOUTH.lowercaseName, Direction.SOUTH.toString().toLowerCase());
        assertEquals(Direction.EAST.lowercaseName, Direction.EAST.toString().toLowerCase());
        assertEquals(Direction.WEST.lowercaseName, Direction.WEST.toString().toLowerCase());
    }

    @Test
    public void translateDirection_ValidStringLowercase_CorrectDirection() {
        assertEquals(Direction.translateDirection("down"), Direction.DOWN);
        assertEquals(Direction.translateDirection("d"), Direction.DOWN);
        assertEquals(Direction.translateDirection("up"), Direction.UP);
        assertEquals(Direction.translateDirection("u"), Direction.UP);
        assertEquals(Direction.translateDirection("north"), Direction.NORTH);
        assertEquals(Direction.translateDirection("n"), Direction.NORTH);
        assertEquals(Direction.translateDirection("south"), Direction.SOUTH);
        assertEquals(Direction.translateDirection("s"), Direction.SOUTH);
        assertEquals(Direction.translateDirection("east"), Direction.EAST);
        assertEquals(Direction.translateDirection("e"), Direction.EAST);
        assertEquals(Direction.translateDirection("west"), Direction.WEST);
        assertEquals(Direction.translateDirection("w"), Direction.WEST);
    }

    @Test
    public void translateDirection_InvalidString_Null() {
        assertEquals(Direction.translateDirection("updown"), null);
    }
    
    @Test(expected = NullPointerException.class)
    public void translateDirection_NullParam_NullExceptionThrown() {
        Direction.translateDirection(null);
        assertFalse(true);
    }
    
    @Test
    public void oppositeDirection_NullParam_NullResult() {
        assertEquals(Direction.getOppositeDirection(null), null);
    }
}
