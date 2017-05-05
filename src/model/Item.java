package model;

import java.io.Serializable;

/**
 * An item.
 * 
 * @author Zachary Chandler
 */
public class Item implements Serializable {
    
    /** Generated SVUID. */
    private static final long serialVersionUID = 3789601981159608040L;
    
    /** The name of the item. */
    private String name;

    /** 
     * Construct an item with the given name.
     * @param name the name of the new item.
     */
    public Item(String name) {
        this.setName(name);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
