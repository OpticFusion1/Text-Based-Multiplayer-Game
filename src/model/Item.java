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

    /** The description of the item. */
	private String description;

    /** 
     * Construct an item with the given name.
     * @param name the name of the new item.
     */
    public Item(String name) {
        this.setName(name);
        this.description = "a very plain object";
    }
    
    /** 
     * Construct an item with the given name.
     * @param name the name of the new item.
     * @param description the description of the new item.
     */
    public Item(String name, String description) {
        this.setName(name);
        this.description = description;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    
}
