/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle;

import com.captainsoft.TADn.model.Position;
import com.captainsoft.spark.utils.Utils;

/**
 * 
 *
 * @author mathias fringes
 */
public final class TileUpdate {

	//
	
	private final Position position;	
	private final TileValues tileValues;
	
	//
	
	public TileUpdate(int x, int y, TileValues tileValues) {
		this(new Position(x, y), tileValues);		
	}
	
	public TileUpdate(Position pos, TileValues tileValues) {		
		this.tileValues = tileValues;
		this.position = pos;
	}

	//
	
	public Position position() {
		return position;
	}

	public TileValues values() {
		return tileValues;
	}
	
	//
	
	@Override
	public String toString() {	
		return Utils.stringer("TileUpdate", position, tileValues);
	}
	
}
