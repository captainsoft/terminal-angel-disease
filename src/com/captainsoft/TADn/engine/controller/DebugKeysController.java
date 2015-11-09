/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.fight.Fight;
import com.captainsoft.TADn.fight.FightWindowController;

/**
 * Controller for handling debug keys.
 *
 * @author mathias fringes
 */
public final class DebugKeysController {
	
	// fields
	
	private final GameEngine gameEngine;
	
	// constructors
	
	public DebugKeysController(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;	
	}
	
	// public
	
	public void keyPress(int keyCode) {
		if (!Debugger.inst.on) {
			return;
		}
		//
		switch (keyCode) {		
			case 121: // F10				
				gameEngine.sayAsIs("Reloading tiles");
				int mapNr = gameEngine.party().mapNumber();
				gameEngine.party().mapNumber(-1);
				gameEngine.teleportParty(mapNr, gameEngine.party().position());
			break;
			case 122: // F11				
				Debugger.inst.noMonsters = !Debugger.inst.noMonsters;
				gameEngine.sayAsIs("No Monsters is now: " + Debugger.inst.noMonsters);
				break;
			case 123:	// F12				
				gameEngine.sayAsIs("Starting dummy fight");
				startDummyFight();				
				break;	
		}
	}
	
	// private

	private void startDummyFight() {
		List<Integer> ml = Arrays.asList(2, 2, 2, 2, 5);
		Collections.shuffle(ml);
		
		FightWindowController fw = new FightWindowController(
				gameEngine, 
				new Fight(gameEngine.party(), 
						ml.get(0),
						ml.get(1),
						ml.get(2),
						ml.get(3),
						ml.get(4)					
					));
		gameEngine.showWindow(fw);
		try {
			fw.beginFight();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}	

}
