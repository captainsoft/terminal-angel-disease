/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.map;

import com.captainsoft.TADr.model.Position;

/**
 * A view for a map.
 *
 * @author mathias fringes
 */
public final class MapTileView {

	// fields
				
	public final int xspan;

	public final int yspan;
	
	public Position topLeft;
	
	// constructors
	
	public MapTileView(int xspan, int yspan) {
		super();				
		this.xspan = xspan;
		this.yspan = yspan;
	}
	
	// public	

	public boolean isVisible(Position p) {
		int x1 = topLeft.x;	
		int x2 = topLeft.x + (xspan - 1);
		int y1 = topLeft.y;	
		int y2 = topLeft.y + (yspan - 1);
		return p.inside(x1, y1, x2, y2);
	}
	
	public boolean onBorders(Position p, int xlen, int ylen) {		
		Position right = btmRight();
		boolean atx = (p.x >= topLeft.x && p.x <= topLeft.x + (xlen - 1)) || (p.x <= right.x && p.x >= right.x - (xlen - 1));		
		boolean aty = (p.y >= topLeft.y && p.y <= topLeft.y + (ylen - 1)) || (p.y <= right.y && p.y >= right.y - (ylen - 1));				
		return (atx || aty);			
	}

	public Position center() {
		return topLeft.add(new Position(xspan / 2, yspan / 2));
	}
	
	public void center(Position position) {
		this.topLeft = position.subst(new Position(xspan / 2, yspan / 2));	
	}
	
	/**
	 * Gets the bottom right position.
	 * 
	 * @return
	 */
	public Position btmRight() {
		return topLeft.add(xspan - 1, yspan - 1);
	}
	
}