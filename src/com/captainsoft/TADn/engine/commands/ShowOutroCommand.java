/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import com.captainsoft.TADn.cuts.CutScene;
import com.captainsoft.TADn.cuts.Outro;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Plays the original outro command ;)
 *
 * @author mathias fringes
 */
public final class ShowOutroCommand extends ShowCutSceneCommand {
	
	// fields
	
	private final GameEngine gameEngine;	

	// constructors
	
	public ShowOutroCommand(MainViewer mv, GameEngine gameEngine) {
		super(mv);		
		this.gameEngine = gameEngine;
	}

	// override
	
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