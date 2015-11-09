/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.chest;

import com.captainsoft.TADn.*;
import com.captainsoft.TADn.engine.*;
import com.captainsoft.TADn.loader.*;
import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.party.*;
import com.captainsoft.TADn.puzzle.*;
import com.captainsoft.TADn.sound.SndFacade;
import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.utils.Log;

/**
 * Puzzle for a chest.
 *
 * @author mathias fringes
 */
public final class ChestPuzzle implements Puzzle {
	
	// fields
	
	private final Chest chest;	
	private final ItemRepository itemRepo = TadRepo.inst().ItemRepo();
	private final GameEngine gameEngine;
	private final Position position;
		
	// constructors 
	
	public ChestPuzzle(GameEngine gameEngine, Position position, PuzzleFileData data) {
		super();
		this.chest = createFromPuzzleFileData(data);		
		this.gameEngine = gameEngine;	
		this.position = position;		
	}
	
	// private methods
	
	private Chest createFromPuzzleFileData(PuzzleFileData data) {
		Chest chest = new Chest();
		for(int i = 0; i < 6; i++) {
			chest.item(i, itemRepo.item(data.value(i + 4)));			
		}
		chest.needsKey = (data.value(1) >= 99); 
		chest.thiefLevel = data.value(1);
		chest.tileValues = new TileValues();
		Log.log(data);
		chest.tileValues.set(1, data.value(2));
		chest.tileValues.set(3, data.value(3));
		// debug thing cause is forgot sometimes, causes you an open a chest eternally...!
		chest.tileValues.set(3, 0);
		Log.log(chest.tileValues);
		return chest;
	}	
	
	// implementation of Puzzle

	@Override
	public void execute() {
		Party party = gameEngine.party();
		boolean usesLockPick = ItemInstance.Lockpick.match(gameEngine.mainViewer().itemInHand()); 
		if (chest.needsKey) {
			String key = usesLockPick ? "chest.needsKeyNoDietrich" : "chest.needsKey";
			gameEngine.say(party.detective(), key);			
			return;
		}
		
		if (party.detective().special4.value() >= chest.thiefLevel || usesLockPick) {		

			if(usesLockPick) {
				gameEngine.takeItem(null);
			}

			boolean update = chest.updateTile(gameEngine.levelMap().tile(position));
			if (update) {
				gameEngine.mainViewer().updatePosition(position);
			}
			SndFacade.chestSound();
			WindowController wc = new ChestWindowController(gameEngine, chest);
			gameEngine.showWindow(wc);						
		} else {
			if (!usesLockPick) {
				gameEngine.say(party.detective(), "chest.cannotOpen");
			}
		}
	}
	
}
