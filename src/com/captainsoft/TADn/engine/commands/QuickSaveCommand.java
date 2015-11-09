/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.loader.GameLoader;
import com.captainsoft.TADn.party.Game;
import com.captainsoft.spark.control.Command;

/**
 * Saves the current game.
 *
 * @author mathias fringes
 */
public final class QuickSaveCommand implements Command {

	private final GameEngine gameEngine;
	
	public QuickSaveCommand(GameEngine gameEgngine) {
		super();
		this.gameEngine = gameEgngine;
	}

	@Override
	public void execute() {
		Game game = gameEngine.game();
		if (game == null) {
			return;
		}
		if (game.isNewGame()) {					
			gameEngine.sayAnonym("quicksave.cannot.isnew");					
		} else {
			GameLoader gameLoader = TadRepo.inst().GameLoader();
			gameLoader.save(game);
			gameEngine.sayAnonym("quicksave", game.name());
		}
	}
	
}