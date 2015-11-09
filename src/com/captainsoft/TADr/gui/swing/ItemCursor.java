/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.gui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Deque;
import java.util.LinkedList;

import com.captainsoft.TADr.loader.ItemRepository;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The mouse cursor, with possible item in hand.
 *
 * @author mathias fringes
 */
public final class ItemCursor {

    // fields

	private final Component compenent;
	private final Deque<Item> itemList = new LinkedList<Item>(); 
	private final ItemRepository itemRepository;
	private final Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	// constructors
	
	public ItemCursor(Component component, ItemRepository itemRepository) {
		super();
		this.compenent = component;
		this.itemRepository = itemRepository;
        item(null);
	}
		
	// public
		
	public void add(Item item) {
        Cursor cursor;
		if (item == null) {
            Surface c = itemRepository.getImage(251);
            cursor = toolkit.createCustomCursor(c.image(), new Point(1, 1), "tad");
		} else {
			itemList.addLast(item);
			//
			Surface s = new Surface(itemRepository.getCursorImage(item), true);
            s.color(new Color(177, 177, 177));
			s.line(1, 1, 4, 1);
			s.line(1, 1, 4, 4);
			s.line(1, 1, 1, 4);
			Image cursorImage = s.image();
			//
			cursor = toolkit.createCustomCursor(cursorImage, new Point(1, 1), "tad");
		}
		compenent.setCursor(cursor);
	}
		
	public void item(Item item) {
		itemList.pollLast();
		if (item == null) {
			item = itemList.pollLast();
		}
		add(item);		
	}
	
	public Item item() {
		return itemList.peekLast();
	}

	public boolean hasItem() {
		return (itemList.size() > 0);
	}
	
}
