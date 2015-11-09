/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.gui.boxes.common;

import java.awt.Color;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.ItemRepository;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * A box with an Item display.
 *
 * @author mathias fringes
 */
public final class ItemBox extends UiBox {
		
	// fields
	
	private final static Color emptyColor = Color.BLACK;

	public int inventoryIndex = -1;
	
	private Item item = null;
	private Surface image = null;
	private ItemRepository itemRepo = TadRepo.inst().ItemRepo();
	
	// constructors
	
	public ItemBox() {
		super();
		size(40, 40);		
	}
	
	// accessors
	
	public void item(Item item) {
		this.item = item;
		if (item != null) {			
			this.image = itemRepo.getCursorImage(item);
		}
	}
	
	public Item item() {
		return item;
	}
		
	public void disableIfEmpty() {
		if (item == null) {
			enabled(false);
		}		
	}	
	
	// overridden

	@Override
	protected void draw(Surface s) {
		super.draw(s);
		if (item == null || image == null) {			
			s.fill(emptyColor, 0, 0, width, height);
		} else {
			s.fill(emptyColor, 0, 0, width, height);
			s.blit(image, 0, 0, width, height);
		}
	}
	
}