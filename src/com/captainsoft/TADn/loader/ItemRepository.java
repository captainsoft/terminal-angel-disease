/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader;

import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemInstance;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * 
 *
 * @author mathias fringes
 */
public interface ItemRepository {
	
	public Item item(int number);
	
	public Item item(ItemInstance itemInstance);
	
	public Surface getImage(int number);
	
	public Surface getImage(Item item);
		
	public Surface getCursorImage(Item item);

}