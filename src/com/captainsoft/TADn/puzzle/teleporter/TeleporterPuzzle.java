/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.
 */
package com.captainsoft.TADn.puzzle.teleporter;

import com.captainsoft.TADn.engine.*;
import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.puzzle.*;
import com.captainsoft.TADn.sound.SndFacade;
import com.captainsoft.spark.utils.*;

/**
 * 
 * @author fringes
 */
public final class TeleporterPuzzle implements Puzzle {

	// fields
	
	private final Teleporter teleporter;
	private final GameEngine gameEngine;
	private final Position position;

	// constructors
	
	public TeleporterPuzzle(GameEngine gameEngine, Teleporter t) {
		super();
		this.teleporter = t;
		this.gameEngine = gameEngine;
		this.position = gameEngine.party().position();
	}
	
	public TeleporterPuzzle(GameEngine gameEngine, Position position, PuzzleFileData pd) {
		super();
		this.teleporter = new Teleporter(pd);
		this.gameEngine = gameEngine;
		this.position = position;
	}

	// Puzzle
	
	@Override
	public void execute() {
		Log.trace("executing teleporter puzzle");
		
		if (teleporter.playSound) {		
			SndFacade.teleprismaSound();
		}
				
		if (teleporter.onSameMap()) {
			gameEngine.updateTile(new TileUpdate(position, teleporter.tileValue));			
		}
		gameEngine.teleportParty(teleporter.mapNr, teleporter.position);
	}

}
