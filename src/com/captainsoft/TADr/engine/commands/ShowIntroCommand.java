/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.cuts.CutScene;
import com.captainsoft.TADr.cuts.Intro;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Plays the intro.
 *
 * @author mathias fringes
 */
public final class ShowIntroCommand extends ShowCutSceneCommand {
	
	// constructors
	
	public ShowIntroCommand(MainViewer mv) {
		super(mv);				
	}

	// overridden
	
	@Override
	protected CutScene createCutScene(Updater updater, UiBoxContainer backgroundBox) {
		return new Intro(updater, backgroundBox);
	}
	
	@Override
	protected void sceneIsOver() {	
		gameEngine.startPlaying();			
	}
	
}