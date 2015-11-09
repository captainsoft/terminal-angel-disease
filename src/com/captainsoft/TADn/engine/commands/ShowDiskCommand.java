/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.utils.Log;

/**
 * Shows the disk menu.
 *
 * @author mathias fringes
 */
public final class ShowDiskCommand implements Command {
	
	private final GameEngine gameEngine;

	public ShowDiskCommand(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
	}

	@Override
	public void execute() {
		if (!gameEngine.mainViewer().windowShown() && (gameEngine.itemInHand() == null)) {
			Log.info("Show disk menu");
			MenuController mc = new MenuController(gameEngine);
			mc.show();
		}
	}
	
}