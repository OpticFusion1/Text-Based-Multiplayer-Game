package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Item;

public class ItemTest {

    Item itemWithOnlyName;
    Item itemWithOnlyNameDescription;
    
    @Before
    public void setUp() throws Exception {
        itemWithOnlyName = new Item("name");
        itemWithOnlyNameDescription = new Item("name", "description");
    }

    @Test
    public void itemName() {
        assertEquals(itemWithOnlyName.getName(), "name");
        
        List<String> aliases = itemWithOnlyName.getAliases();
        assertEquals(aliases.size(), 1);
        assertEquals(aliases.get(0), "NAME");
    }

    @Test
    public void itemNameDescription() {
        assertEquals(itemWithOnlyNameDescription.getName(), "name");
        assertEquals(itemWithOnlyNameDescription.getDescription(), "description");
    }

    @Test
    public void addAlias() {
        itemWithOnlyName.addAlias("whatsha");
        assertEquals(itemWithOnlyName.getAliases().get(1), "WHATSHA");
    }
    
    @Test
    public void addAlias_AliasAlreadyExist_IsNotAdded() {
        itemWithOnlyName.addAlias("name");
        assertEquals(itemWithOnlyName.getAliases().size(), 1);
    }
}
