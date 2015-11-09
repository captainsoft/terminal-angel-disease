/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.cuts.CutScene;
import com.captainsoft.TADr.cuts.Outro;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Plays the original outro command ;)
 *
 * @author mathias fringes
 */
public final class ShowOutroCommand extends ShowCutSceneCommand {
		
	// constructors
	
	public ShowOutroCommand(MainViewer mv) {
		super(mv);
	}

	// overridden
	
	@Override
	protected void sceneIsOver() {
		gameEngine.reset();
		MenuController mc = new MenuController(gameEngine);
		mc.show();	
	}

	@Override
	protected CutScene createCutScene(Updater updater, UiBoxContainer backgroundBox) {
		return new Outro(updater, backgroundBox, 
				gameEngine.game().player(), 
				gameEngine.party().name());
	}
	
}