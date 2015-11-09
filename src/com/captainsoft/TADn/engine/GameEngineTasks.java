/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.painting.SimpleMapDrawer;
import com.captainsoft.spark.control.Command;

/**
 * 
 *
 * @author mathias fringes
 */
public final class GameEngineTasks implements Command {

	// fields
		
	private final SimpleMapDrawer drawer;
	
	// constructors
	
	public GameEngineTasks(SimpleMapDrawer drawer) {
		super();		
		this.drawer = drawer;
	}

	// Command 
	
	@Override
	public void execute() {		
		
		GameEngine gameEngine = TadRepo.inst().GameEngine();
		if (gameEngine == null) {
			return;
		}
		
		if (gameEngine.getState() != GameState.PLAYING) {
			return;
		}
		if (gameEngine.mainViewer().windowShown()) {
			// TODO synchronize??
			return;
		}
		
		drawer.play();			
	}
		
	
}