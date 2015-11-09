/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.fight;

import com.captainsoft.TADn.*;
import com.captainsoft.TADn.engine.*;
import com.captainsoft.TADn.fight.*;
import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.puzzle.*;
import com.captainsoft.TADn.puzzle.event.*;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.utils.*;

/**
 * The fight puzzle type.
 *
 * @author mathias fringes
 */
public final class FightPuzzle implements Puzzle {

	// fields
	
	private final GameEngine gameEngine;
	private final Fight fight;
	private final PuzzleFileData data;
	private final Position p;
	
	// constructors
	
	public FightPuzzle(GameEngine gameEngine, Position p, PuzzleFileData data) {
		super();
		this.gameEngine = gameEngine;		
		this.fight = new Fight(gameEngine.party(), 0, data.value(2), data.value(1), data.value(3));
		this.data = data;
		this.p = p;
	}	
	
	// implementation of Puzzle
	
	@Override
	public void execute() {		
		if (data.value(6) > 0) {
			fight.rewardItem = TadRepo.inst().ItemRepo().item(data.value(6));
		} else {
			fight.rewardItem = null;
		}
		
		//
		FightWindowController fw = new FightWindowController(gameEngine, fight);
		gameEngine.showWindow(fw);		
			
		// 
		// after fight		
		fight.afterFightCommand = new Command() {
			@Override
			public void execute() {
								
				for (int i = 0; i < 3; i++) {
					int ed = data.value(7+i); 
					if (ed > 0) {
						Log.info("executing fight event " + ed);
						new EventPuzzle(gameEngine, p).executeEvent(ed);						
					}
				}
			
				// update this position
				TileValues tv = new TileValues();
				tv.set(1, data.value(4));
				tv.set(3, data.value(5));
				if (tv.active()) {
					gameEngine.updateTile(new TileUpdate(p, tv));
					gameEngine.puzzleEngine().executePassivePuzzle(p);
				}							
			}			
		};
		
		fw.beginFight();	
	}

}