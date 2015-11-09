/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.switcher;

import java.util.*;

import com.captainsoft.TADn.puzzle.*;

/**
 * Describes a switch.
 *
 * @author mathias fringes
 */
public final class Switcher {
	
	// fields
	
	public final TileValues tileValue;
	public final List<Integer> events = new ArrayList<Integer>(3);
	
	// constructors
	
	public Switcher(PuzzleFileData data) {
		super();				
		tileValue = new TileValues();
		tileValue.set(1, data.value(1));
		tileValue.set(3, data.value(2));
		tileValue.set(4, data.value(3));
		//
		// events
		for (int i = 0; i < 3; i++) {
			int v = data.value(4 + i);
			if (v > 0) {
				events.add(v);
			}
		}	
	}
	
}
