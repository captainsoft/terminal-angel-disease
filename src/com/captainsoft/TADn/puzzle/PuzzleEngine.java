/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.loader.PuzzleLoader;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.puzzle.chest.ChestPuzzle;
import com.captainsoft.TADn.puzzle.event.EventPuzzle;
import com.captainsoft.TADn.puzzle.fight.FightPuzzle;
import com.captainsoft.TADn.puzzle.shop.ShopPuzzle;
import com.captainsoft.TADn.puzzle.switcher.SwitcherPuzzle;
import com.captainsoft.TADn.puzzle.talk.TalkPuzzle;
import com.captainsoft.TADn.puzzle.teleporter.TeleporterPuzzle;
import com.captainsoft.TADn.puzzle.useitem.UseItemPuzzle;
import com.captainsoft.spark.utils.Log;

/**
 * The main puzzle engine.
 *
 * @author mathias fringes
 */
public final class PuzzleEngine {
	
	// fields
		
	private final GameEngine gameEngine;
	private final PuzzleLoader puzzleLoader;
	
	// constructors
	
	public PuzzleEngine(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
		this.puzzleLoader = TadRepo.inst().puzzleLoader();
	}

	// public methods
	
	public boolean hasActivePuzzle(Position p) {
		int pid = gameEngine.levelMap().tile(p).activePuzzle();
		return (pid > 0);			
	}
	
	public boolean executeActivePuzzle(Position p) {
		int pid = gameEngine.levelMap().tile(p).activePuzzle();
		if (pid == 0) {
			return false;
		}
		Log.info("Executing active puzzle value: " + pid);
		executePuzzle(p, pid);	
		return true;
	}
	
	public boolean executePassivePuzzle(final Position p) {
		final int pid = gameEngine.levelMap().tile(p).passivePuzzle(); 
		if (pid == 0) {
			return false;			
		}	
		Log.info("Executing passive puzzle value: " + pid + " @ " + p);
		executePuzzle(p, pid);		
		return true;
	}
	
	public void doItemPuzzle(Item item, Position pos) {			
		int pid = gameEngine.levelMap().tile(pos).passivePuzzle(); 
		if (pid == 0) {
			return;
		}
		Log.info("Executing item puzzle value: " + pid);
		PuzzleFileData data = puzzleLoader.loadPuzzle(gameEngine.levelMap().nr(), pid);
		if (data.id() == 3) {
			new UseItemPuzzle(gameEngine, pos, item, data).execute();
		}		
	}	
		
	// private methods		
	
	private void executePuzzle(Position position, int pid) {
		PuzzleFileData data = puzzleLoader.loadPuzzle(gameEngine.levelMap().nr(), pid);		
		Puzzle pz = createPuzzle(position, data);
		if (pz != null) {
			Log.debug("Created puzzle class: " + pz.getClass().getName());
			pz.execute();
		} else {
			Log.warn("Nothing found for position: "  + position + " - PID: " + pid);			
		}
	}
	
	private Puzzle createPuzzle(Position p, PuzzleFileData data) {
		switch (data.id()) {
			case 1:
				return new ShopPuzzle(gameEngine, data);
			case 2:
				return new SwitcherPuzzle(gameEngine, p, data);		
			case 3:				
				return new UseItemPuzzle(gameEngine, p, null, data);
			case 4:
				return new FightPuzzle(gameEngine, p, data);
			case 6:
				return new ChestPuzzle(gameEngine, p, data);
			case 8:
				return new EventPuzzle(gameEngine, p, data);
			case 7:
				return new TeleporterPuzzle(gameEngine, p, data);
			case 9:
				return new TalkPuzzle(gameEngine, p, data);
			default:
				return null;				
		}					
    }	

}