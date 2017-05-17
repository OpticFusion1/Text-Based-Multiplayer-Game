package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.Item;
import model.NonPlayerCharacter;
import model.RoomManager;
import model.RoomNode;

public class RoomNodeTest {

    RoomManager rooms;
    
    RoomNode withNameDescription;
    RoomNode withItems;
    RoomNode withCharacters;
    
    List<Item> items;
    
    @Before
    public void setUp() throws Exception {
        rooms = new RoomManager();
        withNameDescription = rooms.getStartingRoom();
        withNameDescription.setName("name");
        withNameDescription.setDescription("description");
        withItems = rooms.newRoom();
        
        items = new LinkedList<>();
        items.add(new Item("item 1"));
        items.add(new Item("item 2"));
        items.add(new Item("item 3"));
        items.add(new Item("item 4"));
        items.add(new Item("item 5"));
        
        withCharacters = rooms.newRoom();
        new NonPlayerCharacter(withCharacters, "aname");
        new NonPlayerCharacter(withCharacters, "rei");
        new NonPlayerCharacter(withCharacters, "wa");
        
        for (Item i : items) {
            withItems.addItem(i);
        }
        
    }

    @Test
    public void testRoomNodeIntStringString() {
        assertEquals(withNameDescription.getName(), "name");
        assertEquals(withNameDescription.getDescription(), "description");
        assertEquals(withNameDescription.getRoomID(), 0);
        assertEquals(withNameDescription.getItems().size(), 0);
        

        assertEquals(withNameDescription.getDirection(Direction.NORTH), null);
        assertEquals(withNameDescription.getDirection(Direction.EAST), null);
        assertEquals(withNameDescription.getDirection(Direction.SOUTH), null);
        assertEquals(withNameDescription.getDirection(Direction.WEST), null);
        assertEquals(withNameDescription.getDirection(Direction.UP), null);
        assertEquals(withNameDescription.getDirection(Direction.DOWN), null);
    }

    @Test
    public void testSetName() {
        withNameDescription.setName("a different name");
        assertEquals(withNameDescription.getName(), "a different name");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddItem_WithoutMethodCall_ExceptionThrown() {
        List<Item> items = withNameDescription.getItems();
        items.add(new Item("name"));        
    }

    @Test
    public void testAddItem_WithMethodCall_ItemIsAdded() {
        List<Item> items = withNameDescription.getItems();
        
        assertEquals(items.size(), 0);
        
        withNameDescription.addItem(new Item("name"));
        items = withNameDescription.getItems();
        assertEquals(items.size(), 1);
        assertEquals(items.get(0).getName(), "name");
    }

    @Test
    public void testAddItem_SameItemAgain_ItemIsNotAdded() {
        Item i = new Item("name");

        withNameDescription.addItem(i);
        withNameDescription.addItem(i);
        List<Item> items = withNameDescription.getItems();
        
        assertEquals(items.size(), 1);
        assertEquals(items.get(0).getName(), "name");
    }
    
    @Test
    public void testAddItem_Null_ItemIsNotAdded() {
        withNameDescription.addItem(null);
        assertEquals(withNameDescription.getItems().size(), 0);
    }

    @Test
    public void testRemoveItem_ItemNotInRoom_NoChanges() {
        withItems.removeItem(new Item("sslfksfls"));
        List<Item> roomItems = withItems.getItems();
        assertEquals(roomItems.size(), items.size());
    }

    @Test
    public void testRemoveItem_ItemInRoom_ItemRemoved() {
        List<Item> roomItems = withItems.getItems();
        assertEquals(roomItems.size(), items.size());
        withItems.removeItem(items.get(2));

        roomItems = withItems.getItems();
        assertEquals(roomItems.size(), items.size() - 1);
        assertFalse(roomItems.contains(items.get(2)));
    }
    
    @Test
    public void testRemoveItem_Null_NoChanges() {
        withItems.removeItem(null);
        List<Item> roomItems = withItems.getItems();
        assertEquals(roomItems.size(), items.size());
    }
    
    @Test
    public void testSetDirection_ValidDirection_IsSet() {
        withNameDescription.setDirection(Direction.NORTH, rooms.newRoom());
        assertTrue(withNameDescription.getDirection(Direction.NORTH) != null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetDirection_NullDirection_Exception() {
        withNameDescription.setDirection(null, rooms.newRoom());
    }

    @Test(expected = NullPointerException.class)
    public void testFindCharacter_NullName_NullPointerException() {
        withCharacters.find(null);
    }

    @Test
    public void testFindCharacter_CharacterNotInThere_NullReturned() {
        assertEquals(null, withCharacters.find("notaname"));
    }
    
    @Test
    public void testFindCharacter_CharacterInThere_CharacterReturned() {
        assertEquals("aname", withCharacters.find("aname").getName());
    }
}
