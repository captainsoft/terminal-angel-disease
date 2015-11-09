/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.teleporter;

import com.captainsoft.TADr.model.*;
import com.captainsoft.TADr.model.puzzle.*;

/**
 * Target data for a Teleporter, that will move the party to a new position and/or map!
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
	
	public Teleporter(PuzzleFileData pd) {
		super();
		position = new Position(pd.value(2), pd.value(3));
		playSound = (pd.value(6) == 1);
		tileValue = new TileValues(pd.value(4), 255, pd.value(5), 255);
		mapNr = pd.value(1);
	}
	
	// package
	
	boolean onSameMap() {
		return (mapNr == 0);
	}

}