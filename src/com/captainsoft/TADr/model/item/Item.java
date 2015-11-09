/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.item;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * An item!
 * 
 * @author mathias fringes
 */
public final class Item {
	
	// fields
		
	private int id = 0;
	private int coins = 0;
	private int suit = 0;
    private int typeId = 0;
    private int value = 0;
    private int weight = 0;
    private ItemType type;
    private String name = ""; 
    
    // constructors
    
    public Item() {
    	super();
    }
    
    // accessors	

	public int id() {
		return this.id;
	}

	public void id(int id) {
		this.id = id;
	}

	public String name() {
		return this.name;
	}

	public void name(String name) {
		this.name = name;
	}
	
	public ItemType type() {
		return type;
	}
		
	public int typeId() {
		return this.typeId;
	}

	public void typeId(int typeId) {
		this.typeId = typeId;
		this.type = ItemType.byId(typeId);
	}

	public int value() {
		return this.value;
	}

	public void value(int value) {
		this.value = value;
	}

	public int coins() {
		return this.coins;
	}

	public void coins(int coins) {
		this.coins = coins;
	}

	public int weight() {
		return this.weight;
	}

	public void weight(int weight) {
		this.weight = weight;
	}

	public int suit() {
		return this.suit;
	}

	public void suit(int suit) {
		this.suit = suit;
	}			

	// public
	
	public boolean canWear(int pmNr) {					
   	 	boolean canWear = false;    	 
        switch (suit()) {
            case 0:
                canWear = true;
                break;
            case 1:
           	    canWear = (pmNr == 1);                                    
                break;
            case 2:
                canWear = (pmNr == 2);                     
                break;
            case 3:
                canWear = (pmNr == 3);                     
                break;
            case 4:
                canWear = (pmNr == 4); 
                break;
            case 10:
                canWear = (pmNr < 4);
                break;
            case 20:
                canWear = (pmNr == 1 || pmNr == 3);                	                                      
                break;
            case 30:
                canWear = (pmNr == 2 || pmNr == 3);                     
                break;
            case 40:
                canWear = (pmNr != 2);                     
                break;
            case 50:
                canWear = (pmNr != 1);
                break;
        }
	     return canWear;	    
	}
	
	public boolean isMoney() {
		return (id >= 246 && id <= 250);
	}
	
	public boolean isWeapon() {		
		return (type == ItemType.Weapon);
	}
	
	public boolean isArmor() {
		return type.isArmor();		
	}
	
	public boolean isKey() {
		return (type == ItemType.Key);
	}
	
	public boolean isPuzzleItem() {
		return (type == ItemType.Puzzle || isKey());
	}
	
	public boolean isFightItem() {
		return type.isFightItem();		
	}
	
	public boolean isMagicScroll() {
		return (id >= 139 && id <= 150);
	}
	
	public boolean isChemicalMagic() {
		return (id >= 151 && id <= 158);
	}
		
	public boolean isBomb() {
		return (id >= 161 && id <= 166);
	}
	
	public String suitString() {		
        StringBuilder sb = new StringBuilder(6);
        for (int pmNr = 1; pmNr < 5; pmNr++) {        	
        	sb.append(canWear(pmNr) ? "!" : "X");
        }            
        return sb.toString();
	}	
	
	// overridden
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) { 
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Item item = (Item) object;
		return (id == item.id);			
	}

	@Override
	public String toString() {		
		return stringer("Item", id, name, typeId, value);
	}
	
}
