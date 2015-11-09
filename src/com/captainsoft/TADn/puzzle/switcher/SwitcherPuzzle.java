/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.switcher;

import com.captainsoft.TADn.engine.*;
import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.puzzle.*;
import com.captainsoft.TADn.puzzle.event.*;
import com.captainsoft.TADn.sound.SndFacade;

/**
 * Puzzle for a switch.
 *
 * @author mathias fringes
 */
public final class SwitcherPuzzle implements Puzzle {
	
	// fields
	
	private final GameEngine gameEngine;
	private final Position position;
	private final Switcher switcher;

	// constructors
	
	public SwitcherPuzzle(GameEngine gameEngine, Position p, PuzzleFileData data) {
		super();
		this.gameEngine = gameEngine;
		this.position = p;
		this.switcher = new Switcher(data);	
	}

	// Puzzle
	
	@Override
	public void execute() {
		SndFacade.switchSound();
		gameEngine.updateTile(new TileUpdate(position, switcher.tileValue));
		for(Integer i : switcher.events) {
			new EventPuzzle(gameEngine, position).executeEvent(i);			
		}
	}

}
