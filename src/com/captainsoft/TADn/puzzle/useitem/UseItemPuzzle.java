/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.useitem;

import com.captainsoft.TADn.engine.*;
import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.party.*;
import com.captainsoft.TADn.puzzle.*;
import com.captainsoft.TADn.puzzle.event.*;
import com.captainsoft.TADn.sound.SndFacade;
import com.captainsoft.spark.utils.*;

/**
 * 
 * @author mathias fringes
 */
public final class UseItemPuzzle implements Puzzle {
		
	// fields
	
	private final GameEngine gameEngine;
	private final Item item;
	private final Position position;
	private final UseItem useItem;
	
	// constructors
	
	public UseItemPuzzle(GameEngine gameEngine, Position p, Item item, PuzzleFileData pd) {
		super();
		this.gameEngine = gameEngine;
		this.position = p;
		if (item != null) {
			this.item = item;
		} else {
			this.item = gameEngine.itemInHand();
		}		
		this.useItem = new UseItem(pd);
	}
	
	// Puzzle

	@Override
	public void execute() {		
		Log.trace("Executing item puzzle");
		if (item == null) {
			Log.trace("Abort executing item puzzle, cause item is null.");
			return;
		}
		if (useItem.itemId != item.id()) {		
			gameEngine.say("item.use.wrong");
			return;
		}
		if (item.type() == ItemType.Key) {
			SndFacade.keySound();			
		}
		gameEngine.updateTile(new TileUpdate(position, useItem.tileValues));		
		if (useItem.eventId > 0) {
			new EventPuzzle(gameEngine, position).executeEvent(useItem.eventId);
		}
		if (useItem.killItem) {			
			gameEngine.deleteItem(item);			
		}
	}

}
