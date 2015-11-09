/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.useitem;

import com.captainsoft.TADn.puzzle.*;

/** 
 *
 * @author mathias fringes
 */
public final class UseItem {

	final boolean killItem;
	final int itemId;
	final int eventId;
	final TileValues tileValues;
	
	public UseItem(PuzzleFileData pd) {
		super();
		itemId = pd.value(1);
		killItem = (pd.value(8) == 0);
		eventId = (pd.value(5) > 0) ? pd.value(5) : 0;
		tileValues = new TileValues(pd.value(2), TileValues.NOV, pd.value(3), pd.value(4));
	}

}
