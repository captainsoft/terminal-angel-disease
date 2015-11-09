/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Deque;
import java.util.LinkedList;

import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The mouse cursor, with possible item in hand.
 *
 * @author mathias fringes
 */
public final class ItemCursor {

	private final Component c;
	private final Deque<Item> itemList = new LinkedList<Item>(); 
	private final ItemRepository itemRepository;
	private final Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	private Cursor cursor = null;
	
	// constructors
	
	public ItemCursor(Component c, ItemRepository itemRepository) {
		super();
		this.c = c;
		this.itemRepository = itemRepository;
	}
		
	// public methods
		
	public void add(Item item) {
		if (item == null) {
			cursor = null;
		} else {
			itemList.addLast(item);
			//
			Surface s = new ImageSurface(itemRepository.getCursorImage(item));						
			s.color(Color.RED);
			s.line(1, 1, 4, 1);
			s.line(1, 1, 5, 5);
			s.line(1, 1, 1, 4);
			Image cursorImage = s.image();
			//
			cursor = toolkit.createCustomCursor(cursorImage, new Point(1, 1), "tad");
		}
		c.setCursor(cursor);
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
	
	public Cursor cursor() {
		return cursor;
	}

	public boolean hasItem() {
		return (itemList.size() > 0);
	}
	
}
