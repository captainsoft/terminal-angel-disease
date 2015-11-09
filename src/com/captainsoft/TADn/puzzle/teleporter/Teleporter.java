/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.teleporter;

import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.puzzle.*;

/**
 * 
 *
 * @author mathias fringes
 */
public final class Teleporter {
	
	// fields
	
	public boolean playSound;
	public int mapNr;
	public Position position;
	public TileValues tileValue;

	// constructors
	
	public Teleporter() {
		super();
	}
	
	// public
	
	public Teleporter(PuzzleFileData pd) {
		super();
		position = new Position(pd.value(2), pd.value(3));
		playSound = (pd.value(6) == 1);
		tileValue = new TileValues(pd.value(4), 255, pd.value(5), 255);
		mapNr = pd.value(1);
	}
	
	boolean onSameMap() {
		return (mapNr == 0);
	}

}