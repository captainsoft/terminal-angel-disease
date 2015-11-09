/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.map;

import static com.captainsoft.TADr.model.map.LevelMap.X_LEN;
import static com.captainsoft.TADr.model.map.LevelMap.Y_LEN;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import com.captainsoft.TADr.model.Position;

/**
 * Goes around the standard map and enumerates the border position.
 *
 * @author mathias fringes
 */
public final class LevelMapBorderPositionEnumeration implements Enumeration<Position> {

	// fields
	
	private boolean hasMore = true;

	private int x = 1;

	private int y = 1;	
	
	// constructors
	
	public LevelMapBorderPositionEnumeration() {
		super();	
	}
	
	// Enumeration

	public boolean hasMoreElements() {
        return hasMore;
    }

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
