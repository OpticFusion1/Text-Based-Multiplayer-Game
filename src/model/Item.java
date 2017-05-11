package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	
	/** Aliases of this item. */
	private Set<String> aliases;

	/** The string to run on use. */
    private String onUse;

    /** 
     * Construct an item with the given name.
     * @param name the name of the new item.
     */
    public Item(String name) {
        this.setName(name);
        this.description = "a very plain object";
        this.aliases = new TreeSet<>();
        this.aliases.add(name.toUpperCase());
        this.onUse = "echo [nothing special happens]";
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

    /**
     * @param aliasToAdd the 
     * @return the alias to add.
     */
    public void addAlias(String aliasToAdd) {
        String aliasInUpperCase = aliasToAdd.toUpperCase();
        
        if (!aliases.contains(aliasInUpperCase)) {
            aliases.add(aliasInUpperCase);
        }
    }

    /**
     * @param aliases the alias to remove
     */
    public void removeAlias(String aliasToRemove) {
        aliases.remove(aliasToRemove.toUpperCase());
    }
    
    /**
     * Checks if the given string matches the objects name or aliases. Ignores case differences.
     * @param nameToMatch the name to check.
     * @return if the name matched.
     */
    public boolean match(String nameToMatch) {
        return this.aliases.contains(nameToMatch.toUpperCase());
    }
    
    /**
     * @return the aliases of the item.
     */
    public List<String> getAliases() {
        return new LinkedList<String>(aliases);
    }

    /**
     * @return the onUse
     */
    public String getOnUse() {
        return onUse;
    }

    /**
     * @param onUse the onUse to set
     */
    public void setOnUse(String onUse) {
        this.onUse = onUse;
    }
}
