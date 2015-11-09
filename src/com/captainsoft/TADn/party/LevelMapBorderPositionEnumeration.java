/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import static com.captainsoft.TADn.party.LevelMap.X_LEN;
import static com.captainsoft.TADn.party.LevelMap.Y_LEN;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import com.captainsoft.TADn.model.Position;

/**
 * Goes around the standard map and enumerates the border position.
 *
 * @author mathias fringes
 */
public final class LevelMapBorderPositionEnumeration implements Enumeration<Position> {

	//
	
	private boolean hasMore = true;
	private int x = 1;
	private int y = 1;	
	
	//
	
	public LevelMapBorderPositionEnumeration() {
		super();	
	}
	
	// Enumeration
	
	@Override
	public boolean hasMoreElements() {
		return hasMore;
	}

	@Override
	public Position nextElement() {
		if (!hasMoreElements()) {
			throw new NoSuchElementException("No more tiles, map is over!");	
		}
		//
		Position p = new Position(x, y);
		//
		if (y == 1 || y == X_LEN) {
			x++;
			if (x == X_LEN + 1) {
				y++;
				x = 1;
			}
		} else {
			if (x == 1) {
				x = X_LEN;
			} else {
				x = 1;
				y++;
			}
		}			
		//
		hasMore = !(x == 1 && y == Y_LEN + 1);
		//
		return p;
	}

}
