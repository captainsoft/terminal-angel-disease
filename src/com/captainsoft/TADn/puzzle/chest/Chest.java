/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.chest;

import com.captainsoft.TADn.party.*;
import com.captainsoft.TADn.puzzle.*;

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
	public int puzzleTileFirstValue = -1;
	public int puzzleTileThirdValue = -1;
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
	
	// public methods
	
	public Item item(int index) {
		return items[index];
	}
	
	public void item(int index, Item item) {
		items[index] = item;
	}
	
	public boolean updateTile(Tile tile) {
		if (tileValues != null) {
			return tileValues.applyTo(tile);
		} else {
			return false;
		}		
	}

}
