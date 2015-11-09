/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.shop;

import com.captainsoft.TADn.*;
import com.captainsoft.TADn.party.*;
import com.captainsoft.TADn.puzzle.*;

/**
 * Domain object for a shop.
 *
 * @author mathias fringes
 */
public final class Shop {

	private Item[] items = new Item[9];
	
	public Shop() {
		super();
	}
	
	public Shop(PuzzleFileData fileData) {
		for(int i = 0; i < items.length; i++) {
			items[i] = TadRepo.inst().ItemRepo().item(fileData.value(i + 1));
		}
	}

	public Item item(int index) {
		return items[index];
	}
	
}