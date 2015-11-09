/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.chest;

import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.TADr.model.puzzle.TileValues;

/**
 * A chest. 
 *
 * @author mathias fringes
 */
public final class Chest {
	
	// fields
	
	private Item[] items;	
	
	public boolean needsKey = false;

	public int thiefLevel = 0;

	public TileValues tileValues;
	
	// constructors
	
	public Chest() {
		super();
		items = new Item[6];
	}
	
	public Chest(Item firstItem) {
		this();
		item(0, firstItem);
	}
	
	// public
	
	public Item item(int index) {
		return items[index];
	}
	
	public void item(int index, Item item) {
		items[index] = item;
	}
	
	public boolean updateTile(Tile tile) {
        return ((tileValues != null) && (tileValues.applyTo(tile)));
	}

}
