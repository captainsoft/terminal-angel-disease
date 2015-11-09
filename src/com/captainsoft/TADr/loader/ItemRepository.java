/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.loader;

import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.item.ItemInstance;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Loads item data and images.
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