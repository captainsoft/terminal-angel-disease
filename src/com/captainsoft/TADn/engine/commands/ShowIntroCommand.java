/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import com.captainsoft.TADn.cuts.CutScene;
import com.captainsoft.TADn.cuts.Intro;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Plays the intro.
 *
 * @author mathias fringes
 */
public final class ShowIntroCommand extends ShowCutSceneCommand {
	
	// fields
	
	private final GameEngine gameEngine;	

	// constructors
	
	public ShowIntroCommand(MainViewer mv, GameEngine gameEngine) {
		super(mv);		
		this.gameEngine = gameEngine;
	}

	// overridden
	
	@Override
	protected CutScene createCutScene(Updater updater, UiBoxContainer backgroundBox) {
		return new Intro(updater, backgroundBox);
	}
	
	@Override
	protected void sceneIsOver() {	
		gameEngine.doStart();			
	}
	
}