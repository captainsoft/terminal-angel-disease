/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.chest;

import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.ui.CloseWindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * Controller for a chest window.
 *
 * @author mathias fringes
 */
public final class ChestWindowController extends CloseWindowController {
	
	// private methods
	
	private final GameEngine gameEngine;
	private final Chest chest;

	// constructors
	
	public ChestWindowController(GameEngine gameEngine, Chest chest) {
		super();
		this.gameEngine = gameEngine;
		this.chest = chest;
	}	
	
    // implementation of WindowController
	
	@Override
	public UiBoxContainer createWindow(BoxCommandList mg) {
		final ChestWindow cw = new ChestWindow(chest);
		cw.pos(50, 30);
		for (int i = 0; i < 6; i++) {
			final ItemBox b = cw.itemBox[i];
			if (b != null) {
				mg.setRightCmd(b, new Command() {
					@Override
					public void execute() {
						if (gameEngine.mainViewer().hasItemInHand()) {
							return;
						}
						gameEngine.takeItem(b.item());
						b.item(null);
						gameEngine.mainViewer().updateBox(b);
						if (cw.allTaken()) {							
							gameEngine.closeWindows();
							executeCloseCommand();					
						}
					}					
				});
			}			
		}		
		return cw;
	}	

}
