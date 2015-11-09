/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.model;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * A rectangle with an enumeration of all included position objects!
 *
 * @author mathias fringes
 */
public class PositionFrame implements Enumeration<Position> {
		
	// fields
	
	private Position left;
	private int width;
	private int height;
	private int x = 1;
	private int y = 1; 

	// constructors
	
	public PositionFrame(Position left, int width, int height) {
		super();
		this.left = left;
		this.width = width;
		this.height = height;
		this.x = left.x;
		this.y = left.y;
	}
	
	// Enumeration

	@Override
	public final boolean hasMoreElements() {
		return ((x < (left.x + width)) && (y < (left.y + height)));
	}
	
	@Override
	public final Position nextElement() {		
		if (!hasMoreElements()) {
			throw new NoSuchElementException("No more position in frame!");
		}
		Position p = new Position(x, y);
		x++;
		if (x == (left.x + width)) {
			y++;
			x = left.x;
		}		
		return p;			
	}

}