/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.teleporter;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.puzzle.AbstractPuzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.TileUpdate;
import com.captainsoft.TADr.sound.SndFacade;
import com.captainsoft.spark.utils.Log;

/**
 * Teleporter puzzle.
 *
 * @author fringes
 */
public final class TeleporterPuzzle extends AbstractPuzzle {

	// fields
	
	private final Teleporter teleporter;
	private final Position position;

	// constructors
	
	public TeleporterPuzzle(Teleporter t) {
		super();
		this.teleporter = t;
		this.position = gameEngine.party().position();
	}
	
	public TeleporterPuzzle(Position position, PuzzleFileData pd) {
		super();
		this.teleporter = new Teleporter(pd);
		this.position = position;
	}

	// Puzzle
	
	@Override
	public void execute() {
		Log.info("executing teleporter puzzle");
		
		if (teleporter.playSound) {		
			SndFacade.teleprismaSound();
		}
				
		if (teleporter.onSameMap()) {
			gameEngine.updateTile(new TileUpdate(position, teleporter.tileValue));			
		}
		gameEngine.teleportParty(teleporter.mapNr, teleporter.position);
	}

}
