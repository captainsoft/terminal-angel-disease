/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.event;

import com.captainsoft.TADn.*;
import com.captainsoft.TADn.engine.*;
import com.captainsoft.TADn.loader.*;
import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.puzzle.*;

/**
 * 
 *
 * @author mathias fringes
 */
public final class EventPuzzle implements Puzzle {

	// fields
	
	private final PuzzleLoader puzzleLoader;
	private final PuzzleFileData puzzleData;
	private Position position;
	private GameEngine gameEngine;
	
	// constructors
	
	public EventPuzzle(GameEngine gameEngine, Position p) {
		this(gameEngine, p, null);
	}
	
	public EventPuzzle(GameEngine gameEngine, Position p, PuzzleFileData data) {
		super();
		this.puzzleLoader = TadRepo.inst().puzzleLoader();
		this.gameEngine = gameEngine;
		this.puzzleData = data;
		this.position = p;
	}
	
	// public
	
	public void executeEvent(int eventId) {
		if (eventId != 0 && eventId != 255) {
			Event event = puzzleLoader.loadEvent(gameEngine.levelMap().nr(), eventId);
			playEvent(event);
		}
	}
	
	// private
	
	private void playEvent(Event event) {	
		Position position = (event.ownPosition == null) ? gameEngine.party().position() : event.ownPosition;		
		gameEngine.updateTile(new TileUpdate(position, event.tileValue));
		executeEvent(event.nextEventId);		
		if (event.isActive) {
			gameEngine.reexecuteTileAction();
		}
	}		

	// Puzzle

	@Override
	public void execute() {		
		for (int i = 1; i < 9; i++) {
			int eventId = puzzleData.value(i);            
			executeEvent(eventId);            
		}
		if (puzzleData.value(9) != 255) {
			gameEngine.levelMap().tile(position).set(3, puzzleData.value(9));
		}
	}

}
