/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import static com.captainsoft.TADn.party.LevelMap.X_LEN;
import static com.captainsoft.TADn.party.LevelMap.Y_LEN;

import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.model.PositionFrame;

/**
 * 
 *
 * @author mathias fringes
 */
public final class LevelMapPositionEnumeration extends PositionFrame {
		
	public LevelMapPositionEnumeration() {
		super(new Position(1, 1), X_LEN, Y_LEN);
	}

}