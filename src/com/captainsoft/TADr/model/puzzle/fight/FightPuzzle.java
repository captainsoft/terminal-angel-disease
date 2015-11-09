/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.fight;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.fight.Fight;
import com.captainsoft.TADr.model.fight.FightWindowController;
import com.captainsoft.TADr.model.puzzle.Puzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.TileUpdate;
import com.captainsoft.TADr.model.puzzle.TileValues;
import com.captainsoft.TADr.model.puzzle.event.EventPuzzle;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.utils.Log;

/**
 * The fight puzzle (if there is a triggered, special fight).
 *
 * @author mathias fringes
 */
public final class FightPuzzle implements Puzzle {

	// fields
	
	private final GameEngine gameEngine;
	private final Fight fight;
	private final PuzzleFileData data;
	private final Position position;
	
	// constructors
	
	public FightPuzzle(GameEngine gameEngine, Position p, PuzzleFileData data) {
		super();
		this.gameEngine = gameEngine;		
		this.fight = new Fight(gameEngine.party(), p, 0, data.value(2), data.value(1), data.value(3));
		this.data = data;
		this.position = p;
	}	
	
	// Puzzle

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

			public void execute() {
								
				for (int i = 0; i < 3; i++) {
					int ed = data.value(7+i); 
					if (ed > 0) {
						Log.info("executing fight event " + ed);
						new EventPuzzle(position).executeEvent(ed);						
					}
				}
						
				// update this position
				TileValues tv = new TileValues();
				tv.set(1, data.value(4));
				tv.set(3, data.value(5));
				if (tv.active()) {
					gameEngine.updateTile(new TileUpdate(position, tv));
					gameEngine.puzzleEngine().executePassivePuzzle(position);
				}							
			}			
		};
		
		fw.beginFight();	
	}

}